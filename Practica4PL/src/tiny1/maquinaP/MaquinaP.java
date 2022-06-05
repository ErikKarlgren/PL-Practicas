package tiny1.maquinaP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import javax.management.RuntimeErrorException;

public class MaquinaP {
    public static class EAccesoIlegitimo extends RuntimeException {
    }

    public static class EAccesoAMemoriaNoInicializada extends RuntimeException {
        public EAccesoAMemoriaNoInicializada(int pc, int dir) {
            super("pinst:" + pc + " dir:" + dir);
        }
    }

    public static class EAccesoFueraDeRango extends RuntimeException {
    }

    public static class ETipoDeEntradaInesperado extends RuntimeException {
        public ETipoDeEntradaInesperado(String tipo) {
            super("Tipo esperado: " + tipo);
        }
    }

    private GestorMemoriaDinamica gestorMemoriaDinamica;
    private GestorPilaActivaciones gestorPilaActivaciones;

    private class Valor {
        public int valorInt() {
            throw new EAccesoIlegitimo();
        }

        public boolean valorBool() {
            throw new EAccesoIlegitimo();
        }

        public double valorReal() {
            throw new EAccesoIlegitimo();
        }

        public String valorString() {
            throw new EAccesoIlegitimo();
        }
    }

    private class ValorNull extends Valor {
        public String toString() {
            return "null";
        }
    }

    private class ValorInt extends Valor {
        private int valor;

        public ValorInt(int valor) {
            this.valor = valor;
        }

        public int valorInt() {
            return valor;
        }

        public double valorReal() {
            return valor;
        }

        public String toString() {
            return String.valueOf(valor);
        }
    }

    private class ValorBool extends Valor {
        private boolean valor;

        public ValorBool(boolean valor) {
            this.valor = valor;
        }

        public boolean valorBool() {
            return valor;
        }

        public String toString() {
            return String.valueOf(valor);
        }
    }

    private class ValorReal extends Valor {
        private double valor;

        public ValorReal(double valor) {
            this.valor = valor;
        }

        public double valorReal() {
            return valor;
        }

        public String toString() {
            return String.valueOf(valor);
        }
    }

    private class ValorString extends Valor {
        private String valor;

        public ValorString(String valor) {
            this.valor = valor;
        }

        public String valorString() {
            return valor;
        }

        public String toString() {
            return valor;
        }
    }

    private List<Instruccion> codigoP;
    private Stack<Valor> pilaEvaluacion;
    private Valor[] datos;
    private int pc;

    public interface Instruccion {
        void ejecuta();
    }

    private final Instruccion I_SUMA;

    private class ISuma implements Instruccion {
        public void ejecuta() {
            Valor op2 = pilaEvaluacion.pop();
            Valor op1 = pilaEvaluacion.pop();
            Valor res;

            if (op1 instanceof ValorInt && op2 instanceof ValorInt) {
                res = new ValorInt(op1.valorInt() + op2.valorInt());
            } else {
                res = new ValorReal(op1.valorReal() + op2.valorReal());
            }
            pilaEvaluacion.push(res);
            pc++;
        }

        public String toString() {
            return "suma";
        };
    }

    private final Instruccion I_RESTA;

    private class IResta implements Instruccion {
        public void ejecuta() {
            Valor op2 = pilaEvaluacion.pop();
            Valor op1 = pilaEvaluacion.pop();
            Valor res;

            if (op1 instanceof ValorInt && op2 instanceof ValorInt) {
                res = new ValorInt(op1.valorInt() - op2.valorInt());
            } else {
                res = new ValorReal(op1.valorReal() - op2.valorReal());
            }
            pilaEvaluacion.push(res);
            pc++;
        }

        public String toString() {
            return "resta";
        };
    }

    private final Instruccion I_MUL;

    private class IMul implements Instruccion {
        public void ejecuta() {
            Valor op2 = pilaEvaluacion.pop();
            Valor op1 = pilaEvaluacion.pop();
            Valor res;

            if (op1 instanceof ValorInt && op2 instanceof ValorInt) {
                res = new ValorInt(op1.valorInt() * op2.valorInt());
            } else {
                res = new ValorReal(op1.valorReal() * op2.valorReal());
            }
            pilaEvaluacion.push(res);
            pc++;
        }

        public String toString() {
            return "mul";
        };
    }

    private final Instruccion I_DIV;

    private class IDiv implements Instruccion {
        public void ejecuta() {
            Valor op2 = pilaEvaluacion.pop();
            Valor op1 = pilaEvaluacion.pop();
            Valor res;

            if (op1 instanceof ValorInt && op2 instanceof ValorInt) {
                res = new ValorInt(op1.valorInt() / op2.valorInt());
            } else {
                res = new ValorReal(op1.valorReal() / op2.valorReal());
            }
            pilaEvaluacion.push(res);
            pc++;
        }

        public String toString() {
            return "div";
        };
    }

    private final Instruccion I_MOD;

    private class IMod implements Instruccion {
        public void ejecuta() {
            Valor op2 = pilaEvaluacion.pop();
            Valor op1 = pilaEvaluacion.pop();
            pilaEvaluacion.push(new ValorInt(op1.valorInt() % op2.valorInt()));
            pc++;
        }

        public String toString() {
            return "mod";
        };
    }

    private final Instruccion I_NEG;

    private class INeg implements Instruccion {
        public void ejecuta() {
            Valor op = pilaEvaluacion.pop();
            pilaEvaluacion.push(new ValorInt(-op.valorInt()));
            pc++;
        }

        public String toString() {
            return "neg";
        };
    }

    private final Instruccion I_AND;

    private class IAnd implements Instruccion {
        public void ejecuta() {
            Valor opnd2 = pilaEvaluacion.pop();
            Valor opnd1 = pilaEvaluacion.pop();
            pilaEvaluacion.push(new ValorBool(opnd1.valorBool() && opnd2.valorBool()));
            pc++;
        }

        public String toString() {
            return "and";
        };
    }

    private final Instruccion I_OR;

    private class IOr implements Instruccion {
        public void ejecuta() {
            Valor opnd2 = pilaEvaluacion.pop();
            Valor opnd1 = pilaEvaluacion.pop();
            pilaEvaluacion.push(new ValorBool(opnd1.valorBool() || opnd2.valorBool()));
            pc++;
        }

        public String toString() {
            return "or";
        };
    }

    private final Instruccion I_NOT;

    private class INot implements Instruccion {
        public void ejecuta() {
            Valor op = pilaEvaluacion.pop();
            pilaEvaluacion.push(new ValorBool(!op.valorBool()));
            pc++;
        }

        public String toString() {
            return "not";
        };
    }

    private class IApilaInt implements Instruccion {
        private int valor;

        public IApilaInt(int valor) {
            this.valor = valor;
        }

        public void ejecuta() {
            pilaEvaluacion.push(new ValorInt(valor));
            pc++;
        }

        public String toString() {
            return "apilaInt(" + valor + ")";
        };
    }

    private class IApilaBool implements Instruccion {
        private boolean valor;

        public IApilaBool(boolean valor) {
            this.valor = valor;
        }

        public void ejecuta() {
            pilaEvaluacion.push(new ValorBool(valor));
            pc++;
        }

        public String toString() {
            return "apilaBool(" + valor + ")";
        };
    }

    private class IApilaReal implements Instruccion {
        private double valor;

        public IApilaReal(double valor) {
            this.valor = valor;
        }

        public void ejecuta() {
            pilaEvaluacion.push(new ValorReal(valor));
            pc++;
        }

        public String toString() {
            return "apilaReal(" + valor + ")";
        };
    }

    private class IApilaString implements Instruccion {
        private String valor;

        public IApilaString(String valor) {
            this.valor = valor;
        }

        public void ejecuta() {
            pilaEvaluacion.push(new ValorString(valor));
            pc++;
        }

        public String toString() {
            return "apilaString(" + valor + ")";
        };
    }

    private final Instruccion I_APILA_NULL;

    private class IApilaNull implements Instruccion {
        public void ejecuta() {
            pilaEvaluacion.push(new ValorNull());
            pc++;
        }

        public String toString() {
            return "apilaNull";
        };
    }

    private class IIrA implements Instruccion {
        private int dir;

        public IIrA(int dir) {
            this.dir = dir;
        }

        public void ejecuta() {
            pc = dir;
        }

        public String toString() {
            return "ira(" + dir + ")";
        };
    }

    private class IIrF implements Instruccion {
        private int dir;

        public IIrF(int dir) {
            this.dir = dir;
        }

        public void ejecuta() {
            if (!pilaEvaluacion.pop().valorBool()) {
                pc = dir;
            } else {
                pc++;
            }
        }

        public String toString() {
            return "irf(" + dir + ")";
        };
    }

    private class IMueve implements Instruccion {
        private int tam;

        public IMueve(int tam) {
            this.tam = tam;
        }

        public void ejecuta() {
            int dirOrigen = pilaEvaluacion.pop().valorInt();
            int dirDestino = pilaEvaluacion.pop().valorInt();
            if ((dirOrigen + (tam - 1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            if ((dirDestino + (tam - 1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            for (int i = 0; i < tam; i++)
                datos[dirDestino + i] = datos[dirOrigen + i];
            pc++;
        }

        public String toString() {
            return "mueve(" + tam + ")";
        };
    }

    private IApilaind I_APILAIND;

    private class IApilaind implements Instruccion {
        public void ejecuta() {
            int dir = pilaEvaluacion.pop().valorInt();
            if (dir >= datos.length)
                throw new EAccesoFueraDeRango();
            if (datos[dir] == null)
                throw new EAccesoAMemoriaNoInicializada(pc, dir);
            pilaEvaluacion.push(datos[dir]);
            pc++;
        }

        public String toString() {
            return "apilaind";
        };
    }

    private IDesapilaind I_DESAPILAIND;

    private class IDesapilaind implements Instruccion {
        public void ejecuta() {
            Valor valor = pilaEvaluacion.pop();
            int dir = pilaEvaluacion.pop().valorInt();
            if (dir >= datos.length)
                throw new EAccesoFueraDeRango();
            datos[dir] = valor;
            pc++;
        }

        public String toString() {
            return "desapilaind";
        };
    }

    private class IAlloc implements Instruccion {
        private int tam;

        public IAlloc(int tam) {
            this.tam = tam;
        }

        public void ejecuta() {
            int inicio = gestorMemoriaDinamica.alloc(tam);
            pilaEvaluacion.push(new ValorInt(inicio));
            pc++;
        }

        public String toString() {
            return "alloc(" + tam + ")";
        };
    }

    private class IDealloc implements Instruccion {
        private int tam;

        public IDealloc(int tam) {
            this.tam = tam;
        }

        public void ejecuta() {
            int inicio = pilaEvaluacion.pop().valorInt();
            gestorMemoriaDinamica.free(inicio, tam);
            pc++;
        }

        public String toString() {
            return "dealloc(" + tam + ")";
        };
    }

    private class IActiva implements Instruccion {
        private int nivel;
        private int tamdatos;
        private int dirretorno;

        public IActiva(int nivel, int tamdatos, int dirretorno) {
            this.nivel = nivel;
            this.tamdatos = tamdatos;
            this.dirretorno = dirretorno;
        }

        public void ejecuta() {
            int base = gestorPilaActivaciones.creaRegistroActivacion(tamdatos);
            datos[base] = new ValorInt(dirretorno);
            datos[base + 1] = new ValorInt(gestorPilaActivaciones.display(nivel));
            pilaEvaluacion.push(new ValorInt(base + 2));
            pc++;
        }

        public String toString() {
            return "activa(" + nivel + "," + tamdatos + "," + dirretorno + ")";
        }
    }

    private class IDesactiva implements Instruccion {
        private int nivel;
        private int tamdatos;

        public IDesactiva(int nivel, int tamdatos) {
            this.nivel = nivel;
            this.tamdatos = tamdatos;
        }

        public void ejecuta() {
            int base = gestorPilaActivaciones.liberaRegistroActivacion(tamdatos);
            gestorPilaActivaciones.fijaDisplay(nivel, datos[base + 1].valorInt());
            pilaEvaluacion.push(datos[base]);
            pc++;
        }

        public String toString() {
            return "desactiva(" + nivel + "," + tamdatos + ")";
        }

    }

    private class IDesapilad implements Instruccion {
        private int nivel;

        public IDesapilad(int nivel) {
            this.nivel = nivel;
        }

        public void ejecuta() {
            gestorPilaActivaciones.fijaDisplay(nivel, pilaEvaluacion.pop().valorInt());
            pc++;
        }

        public String toString() {
            return "setd(" + nivel + ")";
        }
    }

    private IDup I_DUP;

    private class IDup implements Instruccion {
        public void ejecuta() {
            pilaEvaluacion.push(pilaEvaluacion.peek());
            pc++;
        }

        public String toString() {
            return "dup";
        }
    }

    private Instruccion I_STOP;

    private class IStop implements Instruccion {
        public void ejecuta() {
            pc = codigoP.size();
        }

        public String toString() {
            return "stop";
        }
    }

    private class IApilad implements Instruccion {
        private int nivel;

        public IApilad(int nivel) {
            this.nivel = nivel;
        }

        public void ejecuta() {
            pilaEvaluacion.push(new ValorInt(gestorPilaActivaciones.display(nivel)));
            pc++;
        }

        public String toString() {
            return "apilad(" + nivel + ")";
        }

    }

    private Instruccion I_IRIND;

    private class IIrind implements Instruccion {
        public void ejecuta() {
            pc = pilaEvaluacion.pop().valorInt();
        }

        public String toString() {
            return "irind";
        }
    }

    private final Scanner sc = new Scanner(System.in);
    private final Instruccion I_LEER_INT;

    private class ILeerInt implements Instruccion {
        public void ejecuta() {
            if (sc.hasNextInt()) {
                pilaEvaluacion.push(new ValorInt(sc.nextInt()));
            } else {
                throw new ETipoDeEntradaInesperado("número entero");
            }
            pc++;
        }

        public String toString() {
            return "leerInt";
        }
    }

    private final Instruccion I_LEER_REAL;

    private class ILeerReal implements Instruccion {
        public void ejecuta() {
            if (sc.hasNextDouble()) {
                pilaEvaluacion.push(new ValorReal(sc.nextDouble()));
            } else {
                throw new ETipoDeEntradaInesperado("número real");
            }
            pc++;
        }

        public String toString() {
            return "leerReal";
        }
    }

    private final Instruccion I_LEER_STRING;

    private class ILeerString implements Instruccion {
        public void ejecuta() {
            pilaEvaluacion.push(new ValorString(sc.nextLine()));
            pc++;
        }

        public String toString() {
            return "leerString";
        }
    }

    private final Instruccion I_WRITE;

    private class IWrite implements Instruccion {

        public void ejecuta() {
            Valor v = pilaEvaluacion.pop();
            System.out.print(v.toString());
            pc++;
        }

        public String toString() {
            return "write";
        }
    }

    private final Instruccion I_NEW_LINE;

    private class INewLine implements Instruccion {

        public void ejecuta() {
            System.out.println();
            pc++;
        }

        public String toString() {
            return "newLine";
        }
    }

    private class ILanzarF implements Instruccion {

        private final String err;

        public ILanzarF(String err) {
            this.err = err;
        }

        public void ejecuta() {
            throw new RuntimeErrorException(null, err);
        }

        public String toString() {
            return "lanzarF(" + err + ")";
        }
    }

    private int cmp(Valor v1, Valor v2) {
        if (v1 instanceof ValorInt && v2 instanceof ValorInt) {
            return Integer.compare(v1.valorInt(), v2.valorInt());
        } else if ((v1 instanceof ValorReal || v1 instanceof ValorInt)
                && (v2 instanceof ValorReal || v1 instanceof ValorInt)) {
            return Double.compare(v1.valorReal(), v2.valorReal());
        } else if (v1 instanceof ValorString && v2 instanceof ValorString) {
            return v1.valorString().compareTo(v2.valorString());
        } else if (v1 instanceof ValorBool && v2 instanceof ValorBool) {
            return Boolean.compare(v1.valorBool(), v2.valorBool());
        } else {
            throw new RuntimeErrorException(null, "Tipos de datos no compatibles");
        }
    }

    private final Instruccion I_MENOR;

    private class IMenor implements Instruccion {

        public void ejecuta() {
            Valor v2 = pilaEvaluacion.pop();
            Valor v1 = pilaEvaluacion.pop();
            boolean menor = cmp(v1, v2) < 0;
            pilaEvaluacion.push(new ValorBool(menor));
            pc++;
        }

        public String toString() {
            return "menor";
        }
    }

    private final Instruccion I_MAYOR;

    private class IMayor implements Instruccion {

        public void ejecuta() {
            Valor v2 = pilaEvaluacion.pop();
            Valor v1 = pilaEvaluacion.pop();
            boolean mayor = cmp(v1, v2) > 0;
            pilaEvaluacion.push(new ValorBool(mayor));
            pc++;
        }

        public String toString() {
            return "mayor";
        }
    }

    private final Instruccion I_MENORIGUAL;

    private class IMenorIgual implements Instruccion {

        public void ejecuta() {
            Valor v2 = pilaEvaluacion.pop();
            Valor v1 = pilaEvaluacion.pop();
            boolean menorIgual = cmp(v1, v2) <= 0;
            pilaEvaluacion.push(new ValorBool(menorIgual));
            pc++;
        }

        public String toString() {
            return "menorIgual";
        }
    }

    private final Instruccion I_MAYORIGUAL;

    private class IMayorIgual implements Instruccion {

        public void ejecuta() {
            Valor v2 = pilaEvaluacion.pop();
            Valor v1 = pilaEvaluacion.pop();
            boolean mayorIgual = cmp(v1, v2) >= 0;
            pilaEvaluacion.push(new ValorBool(mayorIgual));
            pc++;
        }

        public String toString() {
            return "mayorIgual";
        }
    }

    private final Instruccion I_IGUAL;

    private class IIgual implements Instruccion {

        public void ejecuta() {
            Valor v2 = pilaEvaluacion.pop();
            Valor v1 = pilaEvaluacion.pop();
            boolean igual = cmp(v1, v2) == 0;
            pilaEvaluacion.push(new ValorBool(igual));
            pc++;
        }

        public String toString() {
            return "igual";
        }
    }

    private final Instruccion I_DISTINTO;

    private class IDistinto implements Instruccion {

        public void ejecuta() {
            Valor v2 = pilaEvaluacion.pop();
            Valor v1 = pilaEvaluacion.pop();
            boolean distinto = cmp(v1, v2) != 0;
            pilaEvaluacion.push(new ValorBool(distinto));
            pc++;
        }

        public String toString() {
            return "distinto";
        }
    }

    public Instruccion suma() {
        return I_SUMA;
    }

    public Instruccion resta() {
        return I_RESTA;
    }

    public Instruccion mul() {
        return I_MUL;
    }

    public Instruccion div() {
        return I_DIV;
    }

    public Instruccion mod() {
        return I_MOD;
    }

    public Instruccion neg() {
        return I_NEG;
    }

    public Instruccion and() {
        return I_AND;
    }

    public Instruccion or() {
        return I_OR;
    }

    public Instruccion not() {
        return I_NOT;
    }

    public Instruccion apilaInt(int val) {
        return new IApilaInt(val);
    }

    public Instruccion apilaBool(boolean val) {
        return new IApilaBool(val);
    }

    public Instruccion apilaReal(double val) {
        return new IApilaReal(val);
    }

    public Instruccion apilaString(String val) {
        return new IApilaString(val);
    }

    public Instruccion apilaNull() {
        return I_APILA_NULL;
    }

    public Instruccion apilad(int nivel) {
        return new IApilad(nivel);
    }

    public Instruccion apilaInd() {
        return I_APILAIND;
    }

    public Instruccion desapilaInd() {
        return I_DESAPILAIND;
    }

    public Instruccion mueve(int tam) {
        return new IMueve(tam);
    }

    public Instruccion irA(int dir) {
        return new IIrA(dir);
    }

    public Instruccion irF(int dir) {
        return new IIrF(dir);
    }

    public Instruccion irInd() {
        return I_IRIND;
    }

    public Instruccion alloc(int tam) {
        return new IAlloc(tam);
    }

    public Instruccion dealloc(int tam) {
        return new IDealloc(tam);
    }

    public Instruccion activa(int nivel, int tam, int dirretorno) {
        return new IActiva(nivel, tam, dirretorno);
    }

    public Instruccion desactiva(int nivel, int tam) {
        return new IDesactiva(nivel, tam);
    }

    public Instruccion desapilad(int nivel) {
        return new IDesapilad(nivel);
    }

    public Instruccion dup() {
        return I_DUP;
    }

    public Instruccion stop() {
        return I_STOP;
    }

    public Instruccion leerInt() {
        return I_LEER_INT;
    }

    public Instruccion leerReal() {
        return I_LEER_REAL;
    }

    public Instruccion leerString() {
        return I_LEER_STRING;
    }

    public Instruccion write() {
        return I_WRITE;
    }

    public Instruccion newLine() {
        return I_NEW_LINE;
    }

    public Instruccion lanzarF(String err) {
        return new ILanzarF(err);
    }

    public Instruccion menor() {
        return I_MENOR;
    }

    public Instruccion mayor() {
        return I_MAYOR;
    }

    public Instruccion menorIgual() {
        return I_MENORIGUAL;
    }

    public Instruccion mayorIgual() {
        return I_MAYORIGUAL;
    }

    public Instruccion igual() {
        return I_IGUAL;
    }

    public Instruccion distinto() {
        return I_DISTINTO;
    }

    public void ponInstruccion(Instruccion i) {
        codigoP.add(i);
    }

    private int tamdatos;
    private int tamheap;
    private int ndisplays;

    public MaquinaP(int tamdatos, int tampila, int tamheap, int ndisplays) {
        this.tamdatos = tamdatos;
        this.tamheap = tamheap;
        this.ndisplays = ndisplays;
        this.codigoP = new ArrayList<>();
        pilaEvaluacion = new Stack<>();
        datos = new Valor[tamdatos + tampila + tamheap];
        this.pc = 0;
        I_SUMA = new ISuma();
        I_RESTA = new IResta();
        I_MUL = new IMul();
        I_DIV = new IDiv();
        I_MOD = new IMod();
        I_NEG = new INeg();
        I_AND = new IAnd();
        I_OR = new IOr();
        I_NOT = new INot();
        I_APILA_NULL = new IApilaNull();
        I_APILAIND = new IApilaind();
        I_DESAPILAIND = new IDesapilaind();
        I_IRIND = new IIrind();
        I_DUP = new IDup();
        I_STOP = new IStop();
        I_LEER_INT = new ILeerInt();
        I_LEER_REAL = new ILeerReal();
        I_LEER_STRING = new ILeerString();
        I_WRITE = new IWrite();
        I_NEW_LINE = new INewLine();
        I_MENOR = new IMenor();
        I_MAYOR = new IMayor();
        I_MENORIGUAL = new IMenorIgual();
        I_MAYORIGUAL = new IMayorIgual();
        I_IGUAL = new IIgual();
        I_DISTINTO = new IDistinto();
        gestorPilaActivaciones = new GestorPilaActivaciones(tamdatos, (tamdatos + tampila) - 1, ndisplays);
        gestorMemoriaDinamica = new GestorMemoriaDinamica(tamdatos + tampila, (tamdatos + tampila + tamheap) - 1);
    }

    public void ejecuta() {
        while (pc != codigoP.size()) {
            codigoP.get(pc).ejecuta();
        }
    }

    public void muestraCodigo() {
        System.out.println("CodigoP");
        for (int i = 0; i < codigoP.size(); i++) {
            System.out.println(" " + i + ":" + codigoP.get(i));
        }
    }

    public void muestraEstado() {
        System.out.println("Tam datos:" + tamdatos);
        System.out.println("Tam heap:" + tamheap);
        System.out.println("PP:" + gestorPilaActivaciones.pp());
        System.out.print("Displays:");
        for (int i = 1; i <= ndisplays; i++)
            System.out.print(i + ":" + gestorPilaActivaciones.display(i) + " ");
        System.out.println();
        System.out.println("Pila de evaluacion");
        for (int i = 0; i < pilaEvaluacion.size(); i++) {
            System.out.println(" " + i + ":" + pilaEvaluacion.get(i));
        }
        System.out.println("Datos");
        for (int i = 0; i < datos.length; i++) {
            System.out.println(" " + i + ":" + datos[i]);
        }
        System.out.println("PC:" + pc);
    }

    public static void main(String[] args) {
        MaquinaP m = new MaquinaP(5, 10, 10, 2);

        /*
         * int x;
         * proc store(int v) {
         * x = v
         * }
         * &&
         * call store(5)
         */

        m.ponInstruccion(m.activa(1, 1, 8));
        m.ponInstruccion(m.dup());
        m.ponInstruccion(m.apilaInt(0));
        m.ponInstruccion(m.suma());
        m.ponInstruccion(m.apilaInt(5));
        m.ponInstruccion(m.desapilaInd());
        m.ponInstruccion(m.desapilad(1));
        m.ponInstruccion(m.irA(9));
        m.ponInstruccion(m.stop());
        m.ponInstruccion(m.apilaInt(0));
        m.ponInstruccion(m.apilad(1));
        m.ponInstruccion(m.apilaInt(0));
        m.ponInstruccion(m.suma());
        m.ponInstruccion(m.mueve(1));
        m.ponInstruccion(m.desactiva(1, 1));
        m.ponInstruccion(m.irInd());
        m.ejecuta();
        m.muestraEstado();
    }
}
