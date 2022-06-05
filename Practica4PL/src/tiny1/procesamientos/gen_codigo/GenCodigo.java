package tiny1.procesamientos.gen_codigo;

import java.util.Objects;
import java.util.Stack;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.bloques.*;
import tiny1.asint.nodos.campos.*;
import tiny1.asint.nodos.declaraciones.*;
import tiny1.asint.nodos.expresiones.basicas.*;
import tiny1.asint.nodos.expresiones.*;
import tiny1.asint.nodos.expresiones.acceso_campo.*;
import tiny1.asint.nodos.expresiones.aritmeticas.*;
import tiny1.asint.nodos.expresiones.booleanas.logicas.*;
import tiny1.asint.nodos.expresiones.booleanas.comparacion.*;
import tiny1.asint.nodos.instrucciones.*;
import tiny1.asint.nodos.parametros.*;
import tiny1.asint.nodos.programa.*;
import tiny1.asint.nodos.tipos.*;
import tiny1.errors.GestionErroresTiny;
import tiny1.maquinaP.MaquinaP;
import tiny1.procesamientos.Procesador;
import tiny1.procesamientos.RefExc;

public class GenCodigo extends Procesador {

    private final MaquinaP maq;
    private final Stack<DecProc> procs;
    private final GestionErroresTiny err;

    public GenCodigo(MaquinaP maquinaP) {
        maq = Objects.requireNonNull(maquinaP);
        procs = new Stack<>();
        err = new GestionErroresTiny();
    }

    private void genIns(MaquinaP.Instruccion instruccion) {
        maq.ponInstruccion(instruccion);
    }

    private void recolectaProcs(Declaraciones declaraciones) {
        procs.addAll(new RecolectaProcs().procesar(declaraciones));
    }

    private void genCodigoComprobarNull() {
        genIns(maq.dup());
        genIns(maq.apilaInt(-1));
        genIns(maq.distinto());
        genIns(maq.lanzarF("Error: no se puede eliminar una variable no inicializada"));
    }

    private Tipo refExc(Tipo tipo){
        return new RefExc().procesar(tipo);
    }

    @Override
    public void procesa(ProgramaConDecs programa) {
        programa.instrs().procesa(this);
        recolectaProcs(programa.decs());
        while (!procs.isEmpty()) {
            DecProc proc = procs.pop();
            proc.procesa(this);
        }
    }

    @Override
    public void procesa(ProgramaSinDecs programa) {
        programa.instrs().procesa(this);
    }

    @Override
    public void procesa(DecProc decProc) {
        decProc.bloque().procesa(this);
        genIns(maq.desactiva(decProc.nivel().get(), decProc.tam().get()));
        genIns(maq.irInd());
        // TODO: hace falta recolectaProcs? o se llama al procesar el bloque?
    }

    @Override
    public void procesa(ParamsSin parametros) {
        // No hacer nada
    }

    @Override
    public void procesa(ParamValor parametro) {
        // No hacer nada
    }

    @Override
    public void procesa(ParamRef parametro) {
        // No hacer nada
    }

    @Override
    public void procesa(ListaParamsUno listaParametros) {
        // No hacer nada
    }

    @Override
    public void procesa(ListaParamsMuchos listaParametros) {
        // No hacer nada
    }

    @Override
    public void procesa(TInt tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(TReal tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(TString tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(TBool tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(TipoArray tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(TipoPointer tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(TipoRecord tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(TipoNuevo tipoNuevo) {
        // No hacer nada
    }

    @Override
    public void procesa(TNull tNull) {
        // No hacer nada
    }

    @Override
    public void procesa(Campo campo) {
        // No hacer nada
    }

    @Override
    public void procesa(CamposUno campos) {
        // No hacer nada
    }

    @Override
    public void procesa(CamposMuchos campos) {
        // No hacer nada
    }

    @Override
    public void procesa(InstrUna instrucciones) {
        instrucciones.instr().procesa(this);
    }

    @Override
    public void procesa(InstrMuchas instrucciones) {
        instrucciones.instrs().procesa(this);
        instrucciones.instr().procesa(this);
    }

    @Override
    public void procesa(InstrAsignacion instruccion) {
        instruccion.expIzq().procesa(this);
        instruccion.expDer().procesa(this);

        // TODO: revisar si esto es correcto
        if (instruccion.expDer().esDesignador()) {
            int tam = instruccion.expDer().tipoNodo().tam().get();
            genIns(maq.mueve(tam));
        } else {
            genIns(maq.desapilaInd());
        }
    }

    @Override
    public void procesa(InstrOptNinguna instruccionOpt) {
        // No hacer nada
    }

    @Override
    public void procesa(InstrOptMuchas instruccionesOpt) {
        instruccionesOpt.instrs().procesa(this);
    }

    @Override
    public void procesa(InstruccionIf instruccion) {
        instruccion.exp().procesa(this);
        genIns(maq.irF(instruccion.instrsOpt().dirSiguiente().get()));
        instruccion.instrsOpt().procesa(this);
    }

    @Override
    public void procesa(InstruccionIfElse instruccion) {
        instruccion.exp().procesa(this);
        genIns(maq.irF(instruccion.instrsOptElse().dirInicio().get()));
        instruccion.instrsOptIf().procesa(this);
        genIns(maq.irA(instruccion.instrsOptElse().dirSiguiente().get()));
        instruccion.instrsOptElse().procesa(this);
    }

    @Override
    public void procesa(InstruccionWhile instruccion) {
        instruccion.exp().procesa(this);
        genIns(maq.irF(instruccion.instrsOpt().dirSiguiente().get()));
        instruccion.instrsOpt().procesa(this);
        genIns(maq.irA(instruccion.exp().dirInicio().get()));
    }

    @Override
    public void procesa(InstruccionRead instruccion) {
        instruccion.exp().procesa(this);
        Tipo tipoRefExc = refExc(instruccion.exp().tipoNodo());
        genIns(new GenInsRead(maq).procesar(tipoRefExc));
        genIns(maq.desapilaInd());
    }

    @Override
    public void procesa(InstruccionWrite instruccion) {
        instruccion.exp().procesa(this);
        genIns(maq.write());
    }

    @Override
    public void procesa(InstruccionNL instruccion) {
        genIns(maq.newLine());
    }

    @Override
    public void procesa(InstruccionNew instruccion) {
        instruccion.exp().procesa(this);
        genIns(maq.alloc(instruccion.exp().tipoNodo().tam().get()));
        genIns(maq.desapilaInd());
    }

    @Override
    public void procesa(InstruccionDelete instruccion) {
        instruccion.exp().procesa(this);
        genIns(maq.apilaInd());
        genCodigoComprobarNull();
        genIns(maq.dealloc(instruccion.exp().tipoNodo().tam().get()));
    }

    @Override
    public void procesa(InstruccionCall instruccion) {
        DecProc vinculo = (DecProc) instruccion.vinculo();
        genIns(maq.activa(
                vinculo.nivel().get(),
                vinculo.tam().get(),
                instruccion.dirSiguiente().get()));
        genCodigoParametros(instruccion.params(), vinculo.params());
        genIns(maq.desapilad(vinculo.nivel().get()));
        genIns(maq.irA(vinculo.dirInicio().get()));
    }

    private void genCodigoParametros(Expresiones exps, ListaParams params) {
        if (exps instanceof ExpresionesMuchas && params instanceof ListaParamsMuchos)
            genCodigoParametros((ExpresionesMuchas) exps, (ListaParamsMuchos) params);
        else if (exps instanceof ExpresionesUna && params instanceof ListaParamsUno)
            genCodigoParametros((ExpresionesUna) exps, (ListaParamsUno) params);
        else if (exps instanceof ExpresionesNinguna && params instanceof ParamsSin)
            genCodigoParametros((ExpresionesNinguna) exps, (ParamsSin) params);
        else {
            err.errorProcesamiento("El número de parámetros de la llamada no es correcto", exps, params);
        }
    }

    private void genCodigoParametros(ExpresionesNinguna exps, ParamsSin params) {
        // No hacer nada
    }

    private void genCodigoParametros(ExpresionesUna exp, ListaParamsUno param) {
        genCodigoPaso(exp.exp(), param.param());
    }

    private void genCodigoParametros(ExpresionesMuchas exps, ListaParamsMuchos params) {
        Expresiones exps1 = exps.exps();
        ListaParams params1 = params.params();

        if (exps1 instanceof ExpresionesMuchas && params1 instanceof ListaParamsMuchos)
            genCodigoParametros((ExpresionesMuchas) exps, (ListaParamsMuchos) params1);
        else if (exps1 instanceof ExpresionesUna && params1 instanceof ListaParamsUno)
            genCodigoParametros((ExpresionesUna) exps1, (ListaParamsUno) params1);
        else if (exps1 instanceof ExpresionesNinguna && params1 instanceof ParamsSin)
            genCodigoParametros((ExpresionesNinguna) exps1, (ParamsSin) params1);
        else {
            err.errorProcesamiento("El número de parámetros de la llamada no es correcto", exps, params);
        }
    }

    private void genCodigoPaso(Expresion exp, Parametro param) {
        genIns(maq.dup());
        genIns(maq.apilaInt(param.direccion().get()));
        genIns(maq.suma());
        exp.procesa(this);

        if (!exp.esDesignador() && param instanceof ParamValor) {
            genIns(maq.mueve(param.tam().get()));
        } else {
            genIns(maq.desapilaInd());
        }
    }

    @Override
    public void procesa(InstruccionBloque instrucciones) {
        instrucciones.bloque().procesa(this);
    }

    @Override
    public void procesa(BloqueVacio bloque) {
        // No hacer nada
    }

    @Override
    public void procesa(BloqueLleno bloque) {
        bloque.programa().procesa(this);
    }

    @Override
    public void procesa(ExpresionesNinguna expresiones) {
        // No hacer nada
    }

    @Override
    public void procesa(ExpresionesUna expresiones) {
        expresiones.exp().procesa(this);
    }

    @Override
    public void procesa(ExpresionesMuchas expresiones) {
        expresiones.exps().procesa(this);
        expresiones.exp().procesa(this);
    }

    @Override
    public void procesa(Suma suma) {
        genCodigoExpBin(suma);
        genIns(maq.suma());
    }

    @Override
    public void procesa(Resta resta) {
        genCodigoExpBin(resta);
        genIns(maq.resta());
    }

    @Override
    public void procesa(Multiplicacion multiplicacion) {
        genCodigoExpBin(multiplicacion);
        genIns(maq.mul());
    }

    @Override
    public void procesa(Division division) {
        genCodigoExpBin(division);
        genIns(maq.div());
    }

    @Override
    public void procesa(PorCiento porCiento) {
        genCodigoExpBin(porCiento);
        genIns(maq.mod());
    }

    public void genCodigoExpBin(ExpresionBinaria exp) {
        exp.arg0().procesa(this);
        if (exp.arg0().esDesignador()) {
            genIns(maq.apilaInd());
        }
        exp.arg1().procesa(this);
        if (exp.arg1().esDesignador()) {
            genIns(maq.apilaInd());
        }
    }

    @Override
    public void procesa(Menos menos) {
        menos.arg().procesa(this);
        if (menos.arg().esDesignador()) {
            genIns(maq.apilaInd());
        }
        genIns(maq.neg());
    }

    @Override
    public void procesa(NumeroEntero numero) {
        genIns(maq.apilaInt(numero.numAsInt()));
    }

    @Override
    public void procesa(NumeroReal numero) {
        genIns(maq.apilaReal(numero.numAsDouble()));
    }

    @Override
    public void procesa(Identificador identificador) {
        Nodo vinculo = identificador.vinculo();
        if (vinculo.nivel().get() == 0) {
            genIns(maq.apilaInt(vinculo.direccion().get()));
        } else {
            genIns(maq.apilad(vinculo.nivel().get()));
            genIns(maq.apilaInt(vinculo.direccion().get()));
            genIns(maq.suma());
            if (vinculo instanceof ParamRef) {
                genIns(maq.apilaInd());
            }
        }
    }

    @Override
    public void procesa(True booleanoTrue) {
        genIns(maq.apilaBool(true));
    }

    @Override
    public void procesa(False booleanoFalse) {
        genIns(maq.apilaBool(false));
    }

    @Override
    public void procesa(Null nulo) {
        genIns(maq.apilaNull());
    }

    @Override
    public void procesa(Cadena cadena) {
        genIns(maq.apilaString(cadena.cadena().toString()));
    }

    @Override
    public void procesa(And and) {
        genCodigoExpBin(and);
        genIns(maq.and());
    }

    @Override
    public void procesa(Or or) {
        genCodigoExpBin(or);
        genIns(maq.or());
    }

    @Override
    public void procesa(Not not) {
        not.arg().procesa(this);
        if (not.arg().esDesignador()) {
            genIns(maq.apilaInd());
        }
    }

    @Override
    public void procesa(Menor menor) {
        genCodigoExpBin(menor);
        genIns(maq.menor());
    }

    @Override
    public void procesa(MenorIgual menorIgual) {
        genCodigoExpBin(menorIgual);
        genIns(maq.menorIgual());
    }

    @Override
    public void procesa(Mayor mayor) {
        genCodigoExpBin(mayor);
        genIns(maq.mayor());
    }

    @Override
    public void procesa(MayorIgual mayorIgual) {
        genCodigoExpBin(mayorIgual);
        genIns(maq.mayorIgual());
    }

    @Override
    public void procesa(Igual igual) {
        genCodigoExpBin(igual);
        genIns(maq.igual());
    }

    @Override
    public void procesa(Distinto distinto) {
        genCodigoExpBin(distinto);
        genIns(maq.distinto());
    }

    @Override
    public void procesa(AccesoArray accesoArray) {
        accesoArray.arg0().procesa(this);
        accesoArray.arg1().procesa(this);
        if (accesoArray.arg1().esDesignador()) {
            genIns(maq.apilaInd());
        }
        genIns(maq.apilaInt(accesoArray.arg0().tipoNodo().tam().get()));
        genIns(maq.mul());
        genIns(maq.suma());
    }

    @Override
    public void procesa(Punto punto) {
        punto.exp().procesa(this);
        genIns(maq.apilaInt(punto.desplazamiento().get()));
        genIns(maq.suma());
    }

    @Override
    public void procesa(Flecha flecha) {
        flecha.exp().procesa(this);
        genCodigoComprobarNull();
        genIns(maq.apilaInd());
        genIns(maq.apilaInt(flecha.desplazamiento().get()));
        genIns(maq.suma());
    }

    @Override
    public void procesa(ValorPuntero valorPuntero) {
        valorPuntero.arg().procesa(this);
        genCodigoComprobarNull();
        genIns(maq.apilaInd());
    }
}
