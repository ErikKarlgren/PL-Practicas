package tiny1.procesamientos.gen_codigo;

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
import tiny1.procesamientos.Procesador;

public class Etiquetado extends Procesador {

    private int etiqueta;
    private final Stack<DecProc> procs;
    private final GestionErroresTiny err;

    public Etiquetado() {
        etiqueta = 0;
        procs = new Stack<>();
        err = new GestionErroresTiny();
    }

    private void recolectaProcs(Declaraciones declaraciones) {
        procs.addAll(new RecolectaProcs().procesar(declaraciones));
    }

    private void etiquetaComprobarNull() {
        etiqueta += 4;
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
        decProc.dirInicio().set(etiqueta);
        decProc.bloque().procesa(this);
        etiqueta += 2;
        decProc.dirSiguiente().set(etiqueta);
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
        instruccion.dirInicio().set(etiqueta);
        instruccion.expIzq().procesa(this);
        instruccion.expDer().procesa(this);
        etiqueta++;
        instruccion.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(InstrOptNinguna instruccionOpt) {
        instruccionOpt.dirInicio().set(etiqueta);
        instruccionOpt.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(InstrOptMuchas instruccionesOpt) {
        instruccionesOpt.dirInicio().set(etiqueta);
        instruccionesOpt.instrs().procesa(this);
        instruccionesOpt.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(InstruccionIf instruccion) {
        instruccion.dirInicio().set(etiqueta);
        instruccion.exp().procesa(this);
        etiqueta++;
        instruccion.instrsOpt().procesa(this);
        instruccion.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(InstruccionIfElse instruccion) {
        instruccion.dirInicio().set(etiqueta);
        instruccion.exp().procesa(this);
        etiqueta++;
        instruccion.instrsOptIf().procesa(this);
        etiqueta++;
        instruccion.instrsOptElse().procesa(this);
        instruccion.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(InstruccionWhile instruccion) {
        instruccion.dirInicio().set(etiqueta);
        instruccion.exp().procesa(this);
        etiqueta++;
        instruccion.instrsOpt().procesa(this);
        etiqueta++;
        instruccion.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(InstruccionRead instruccion) {
        instruccion.dirInicio().set(etiqueta);
        instruccion.exp().procesa(this);
        etiqueta += 2;
        instruccion.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(InstruccionWrite instruccion) {
        instruccion.dirInicio().set(etiqueta);
        instruccion.exp().procesa(this);
        etiqueta++;
        instruccion.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(InstruccionNL instruccion) {
        instruccion.dirInicio().set(etiqueta);
        etiqueta++;
        instruccion.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(InstruccionNew instruccion) {
        instruccion.dirInicio().set(etiqueta);
        instruccion.exp().procesa(this);
        etiqueta += 2;
        instruccion.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(InstruccionDelete instruccion) {
        instruccion.dirInicio().set(etiqueta);
        instruccion.exp().procesa(this);
        etiqueta++;
        etiquetaComprobarNull();
        etiqueta++;
        instruccion.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(InstruccionCall instruccion) {
        instruccion.dirInicio().set(etiqueta);
        DecProc vinculo = (DecProc) instruccion.vinculo();
        etiqueta++;
        etiquetaParams(instruccion.params(), vinculo.params());
        etiqueta += 2;
        instruccion.dirSiguiente().set(etiqueta);
    }

    private void etiquetaParams(Expresiones exps, ListaParams params) {
        if (exps instanceof ExpresionesMuchas && params instanceof ListaParamsMuchos)
            etiquetaParams((ExpresionesMuchas) exps, (ListaParamsMuchos) params);
        else if (exps instanceof ExpresionesUna && params instanceof ListaParamsUno)
            etiquetaParams((ExpresionesUna) exps, (ListaParamsUno) params);
        else if (exps instanceof ExpresionesNinguna && params instanceof ParamsSin)
            etiquetaParams((ExpresionesNinguna) exps, (ParamsSin) params);
        else {
            err.errorProcesamiento("El número de parámetros de la llamada no es correcto", exps, params);
        }
    }

    private void etiquetaParams(ExpresionesNinguna exps, ParamsSin params) {
        // No hacer nada
    }

    private void etiquetaParams(ExpresionesUna exp, ListaParamsUno param) {
        etiquetaPaso(exp.exp(), param.param());
    }

    private void etiquetaParams(ExpresionesMuchas exps, ListaParamsMuchos params) {
        Expresiones exps1 = exps.exps();
        ListaParams params1 = params.params();

        if (exps1 instanceof ExpresionesMuchas && params1 instanceof ListaParamsMuchos)
            etiquetaParams((ExpresionesMuchas) exps, (ListaParamsMuchos) params1);
        else if (exps1 instanceof ExpresionesUna && params1 instanceof ListaParamsUno)
            etiquetaParams((ExpresionesUna) exps1, (ListaParamsUno) params1);
        else if (exps1 instanceof ExpresionesNinguna && params1 instanceof ParamsSin)
            etiquetaParams((ExpresionesNinguna) exps1, (ParamsSin) params1);
        else {
            err.errorProcesamiento("El número de parámetros de la llamada no es correcto", exps, params);
        }
    }

    private void etiquetaPaso(Expresion exp, Parametro param) {
        etiqueta += 3;
        exp.procesa(this);
        etiqueta++;
    }

    @Override
    public void procesa(InstruccionBloque instrucciones) {
        instrucciones.dirInicio().set(etiqueta);
        instrucciones.bloque().procesa(this);
        instrucciones.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(BloqueVacio bloque) {
        bloque.dirInicio().set(etiqueta);
        bloque.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(BloqueLleno bloque) {
        bloque.dirInicio().set(etiqueta);
        bloque.programa().procesa(this);
        bloque.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(ExpresionesNinguna expresiones) {
        expresiones.dirInicio().set(etiqueta);
        expresiones.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(ExpresionesUna expresiones) {
        expresiones.dirInicio().set(etiqueta);
        expresiones.exp().procesa(this);
        expresiones.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(ExpresionesMuchas expresiones) {
        expresiones.dirInicio().set(etiqueta);
        expresiones.exps().procesa(this);
        expresiones.exp().procesa(this);
        expresiones.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Suma suma) {
        suma.dirInicio().set(etiqueta);
        etiquetaExpBin(suma);
        etiqueta++;
        suma.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Resta resta) {
        resta.dirInicio().set(etiqueta);
        etiquetaExpBin(resta);
        etiqueta++;
        resta.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Multiplicacion multiplicacion) {
        multiplicacion.dirInicio().set(etiqueta);
        etiquetaExpBin(multiplicacion);
        etiqueta++;
        multiplicacion.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Division division) {
        division.dirInicio().set(etiqueta);
        etiquetaExpBin(division);
        etiqueta++;
        division.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(PorCiento porCiento) {
        porCiento.dirInicio().set(etiqueta);
        etiquetaExpBin(porCiento);
        etiqueta++;
        porCiento.dirSiguiente().set(etiqueta);
    }

    public void etiquetaExpBin(ExpresionBinaria exp) {
        exp.arg0().procesa(this);
        if (exp.arg0().esDesignador()) {
            etiqueta++;
        }
        exp.arg1().procesa(this);
        if (exp.arg1().esDesignador()) {
            etiqueta++;
        }
    }

    @Override
    public void procesa(Menos menos) {
        menos.dirInicio().set(etiqueta);
        menos.arg().procesa(this);
        if (menos.arg().esDesignador()) {
            etiqueta++;
        }
        etiqueta++;
        menos.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(NumeroEntero numero) {
        numero.dirInicio().set(etiqueta);
        etiqueta++;
        numero.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(NumeroReal numero) {
        numero.dirInicio().set(etiqueta);
        etiqueta++;
        numero.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Identificador identificador) {
        identificador.dirInicio().set(etiqueta);
        Nodo vinculo = identificador.vinculo();
        if (vinculo.nivel().get() == 0) {
            etiqueta++;
        } else {
            etiqueta += 3;
            if (vinculo instanceof ParamRef) {
                etiqueta++;
            }
        }
        identificador.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(True booleanoTrue) {
        booleanoTrue.dirInicio().set(etiqueta);
        etiqueta++;
        booleanoTrue.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(False booleanoFalse) {
        booleanoFalse.dirInicio().set(etiqueta);
        etiqueta++;
        booleanoFalse.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Null nulo) {
        nulo.dirInicio().set(etiqueta);
        etiqueta++;
        nulo.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Cadena cadena) {
        cadena.dirInicio().set(etiqueta);
        etiqueta++;
        cadena.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(And and) {
        and.dirInicio().set(etiqueta);
        etiquetaExpBin(and);
        etiqueta++;
        and.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Or or) {
        or.dirInicio().set(etiqueta);
        etiquetaExpBin(or);
        etiqueta++;
        or.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Not not) {
        not.dirInicio().set(etiqueta);
        not.arg().procesa(this);
        if (not.arg().esDesignador()) {
            etiqueta++;
        }
        not.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Menor menor) {
        menor.dirInicio().set(etiqueta);
        etiquetaExpBin(menor);
        etiqueta++;
        menor.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(MenorIgual menorIgual) {
        menorIgual.dirInicio().set(etiqueta);
        etiquetaExpBin(menorIgual);
        etiqueta++;
        menorIgual.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Mayor mayor) {
        mayor.dirInicio().set(etiqueta);
        etiquetaExpBin(mayor);
        etiqueta++;
        mayor.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(MayorIgual mayorIgual) {
        mayorIgual.dirInicio().set(etiqueta);
        etiquetaExpBin(mayorIgual);
        etiqueta++;
        mayorIgual.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Igual igual) {
        igual.dirInicio().set(etiqueta);
        etiquetaExpBin(igual);
        etiqueta++;
        igual.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Distinto distinto) {
        distinto.dirInicio().set(etiqueta);
        etiquetaExpBin(distinto);
        etiqueta++;
        distinto.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(AccesoArray accesoArray) {
        accesoArray.dirInicio().set(etiqueta);
        accesoArray.arg0().procesa(this);
        accesoArray.arg1().procesa(this);
        if (accesoArray.arg1().esDesignador()) {
            etiqueta++;
        }
        etiqueta += 3;
        accesoArray.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Punto punto) {
        punto.dirInicio().set(etiqueta);
        punto.exp().procesa(this);
        etiqueta += 2;
        punto.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(Flecha flecha) {
        flecha.dirInicio().set(etiqueta);
        flecha.exp().procesa(this);
        etiquetaComprobarNull();
        etiqueta += 3;
        flecha.dirSiguiente().set(etiqueta);
    }

    @Override
    public void procesa(ValorPuntero valorPuntero) {
        valorPuntero.dirInicio().set(etiqueta);
        valorPuntero.arg().procesa(this);
        etiquetaComprobarNull();
        etiqueta++;
        valorPuntero.dirSiguiente().set(etiqueta);
    }
}
