package tiny0.procesamientos;

import java.util.HashMap;

import tiny0.asint.nodos.Declaracion;
import tiny0.asint.nodos.DecsMuchas;
import tiny0.asint.nodos.DecsUna;
import tiny0.asint.nodos.InstrMuchas;
import tiny0.asint.nodos.InstrUna;
import tiny0.asint.nodos.Instruccion;
import tiny0.asint.nodos.Programa;
import tiny0.asint.nodos.StringLocalizado;
import tiny0.asint.nodos.expresiones.Expresion;
import tiny0.asint.nodos.expresiones.aritmeticas.Division;
import tiny0.asint.nodos.expresiones.aritmeticas.Menos;
import tiny0.asint.nodos.expresiones.aritmeticas.Multiplicacion;
import tiny0.asint.nodos.expresiones.aritmeticas.Resta;
import tiny0.asint.nodos.expresiones.aritmeticas.Suma;
import tiny0.asint.nodos.expresiones.basicas.False;
import tiny0.asint.nodos.expresiones.basicas.Identificador;
import tiny0.asint.nodos.expresiones.basicas.NumeroEntero;
import tiny0.asint.nodos.expresiones.basicas.NumeroReal;
import tiny0.asint.nodos.expresiones.basicas.True;
import tiny0.asint.nodos.expresiones.booleanas.comparacion.Distinto;
import tiny0.asint.nodos.expresiones.booleanas.comparacion.Igual;
import tiny0.asint.nodos.expresiones.booleanas.comparacion.Mayor;
import tiny0.asint.nodos.expresiones.booleanas.comparacion.MayorIgual;
import tiny0.asint.nodos.expresiones.booleanas.comparacion.Menor;
import tiny0.asint.nodos.expresiones.booleanas.comparacion.MenorIgual;
import tiny0.asint.nodos.expresiones.booleanas.logicas.And;
import tiny0.asint.nodos.expresiones.booleanas.logicas.Not;
import tiny0.asint.nodos.expresiones.booleanas.logicas.Or;

class Valores<T> extends HashMap<StringLocalizado, T> {}

public class Evaluacion implements Procesador {

    private Valores<Integer> valoresEnteros = new Valores<>();
    private Valores<Double> valoresReales = new Valores<>();
    private Valores<Boolean> valoresBooleanos = new Valores<>();

    private Expresion resultado;

    private void declararVariable(Declaracion declaracion) {
        StringLocalizado id = declaracion.id();
        String tipo = declaracion.tipo().toString();
        if (tipo.equals("entero")) {
            valoresEnteros.put(id, null);
        } else if (tipo.equals("real")) {
            valoresReales.put(id, null);
        } else if (tipo.equals("booleano")) {
            valoresBooleanos.put(id, null);
        } else {
            throw new IllegalArgumentException("Tipo de variable no soportado: " + tipo);
        }
    }

    private void insertarVariable(StringLocalizado nombre, String valor) {
        if (valor.equals("true")) {
            valoresBooleanos.put(nombre, true);
        } else if (valor.equals("false")) {
            valoresBooleanos.put(nombre, false);
        } else if (isInteger(valor)) {
            valoresEnteros.put(nombre, Integer.parseInt(valor));
        } else if (isReal(valor)) {
            valoresReales.put(nombre, Double.parseDouble(valor));
        } else {
            throw new RuntimeException("Valor no reconocido: " + valor);
        }
    }

    private boolean isInteger(String valor) {
        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isReal(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void procesa(Programa programa) {
        programa.declaraciones().procesa(this);
        programa.instrucciones().procesa(this);
    }

    @Override
    public void procesa(Declaracion declaracion) {
        StringLocalizado id = declaracion.id();
        if (valoresEnteros.containsKey(id) || valoresReales.containsKey(id) || valoresBooleanos.containsKey(id)) {
            throw new IllegalStateException("Variable ya declarada: " + id);
        }
        declararVariable(declaracion);
    }

    @Override
    public void procesa(DecsUna declaraciones) {
        declaraciones.declaracion().procesa(this);
    }

    @Override
    public void procesa(DecsMuchas declaraciones) {
        declaraciones.declaraciones().procesa(this);
        declaraciones.declaracion().procesa(this);
    }

    @Override
    public void procesa(Instruccion instruccion) {
        instruccion.expresion().procesa(this);

    }

    @Override
    public void procesa(InstrUna instrucciones) {
    }

    @Override
    public void procesa(InstrMuchas instrucciones) {
    }

    @Override
    public void procesa(Suma suma) {
    }

    @Override
    public void procesa(Resta resta) {
    }

    @Override
    public void procesa(Multiplicacion multiplicacion) {
    }

    @Override
    public void procesa(Division division) {
    }

    @Override
    public void procesa(Menos menos) {
    }

    @Override
    public void procesa(NumeroEntero numero) {
        
    }

    @Override
    public void procesa(NumeroReal numero) {
    }

    @Override
    public void procesa(Identificador identificador) {
    }

    @Override
    public void procesa(True booleanoTrue) {
    }

    @Override
    public void procesa(False booleanoFalse) {
    }

    @Override
    public void procesa(And and) {
    }

    @Override
    public void procesa(Or or) {
    }

    @Override
    public void procesa(Not not) {
    }

    @Override
    public void procesa(Menor menor) {
    }

    @Override
    public void procesa(MenorIgual menorIgual) {
    }

    @Override
    public void procesa(Mayor mayor) {
    }

    @Override
    public void procesa(MayorIgual mayorIgual) {
    }

    @Override
    public void procesa(Igual igual) {
    }

    @Override
    public void procesa(Distinto distinto) {
    }

}
