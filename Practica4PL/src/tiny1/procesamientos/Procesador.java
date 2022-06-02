package tiny1.procesamientos;

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

public interface Procesador {
    static final RuntimeException NO_IMPLEMENTADA = new UnsupportedOperationException("Procesamiento no implementado");

    default void procesa(ProgramaConDecs programa) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(ProgramaSinDecs programa) {
        throw NO_IMPLEMENTADA;
    }

    // Declaraciones

    default void procesa(DecsUna declaraciones) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(DecsMuchas declaraciones) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(DecType decType) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(DecVar decVar) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(DecProc decProc) {
        throw NO_IMPLEMENTADA;
    }

    // Parametros

    default void procesa(ParamsSin parametros) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(ParamValor parametro) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(ParamRef parametro) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(ListaParamsUno listaParametros) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(ListaParamsMuchos listaParametros) {
        throw NO_IMPLEMENTADA;
    }

    // Tipos

    default void procesa(TInt tipo) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(TReal tipo) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(TString tipo) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(TBool tipo) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(TipoArray tipo) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(TipoPointer tipo) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(TipoRecord tipo) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(TipoNuevo tipoNuevo) {
        throw NO_IMPLEMENTADA;
    }

    // Campos

    default void procesa(Campo campo) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(CamposUno campos) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(CamposMuchos campos) {
        throw NO_IMPLEMENTADA;
    }

    // instrucciones

    default void procesa(InstrUna instrucciones) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstrMuchas instrucciones) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstrAsignacion instruccion) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstrOptNinguna instruccionOpt) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstrOptMuchas instruccionesOpt) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstruccionIf instruccion) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstruccionIfElse instruccion) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstruccionWhile instruccion) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstruccionRead instruccion) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstruccionWrite instruccion) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstruccionNL instruccion) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstruccionNew instruccion) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstruccionDelete instruccion) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstruccionCall instruccion) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(InstruccionBloque instrucciones) {
        throw NO_IMPLEMENTADA;
    }

    // Bloques

    default void procesa(BloqueVacio bloque) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(BloqueLleno bloques) {
        throw NO_IMPLEMENTADA;
    }

    // Expresiones

    default void procesa(ExpresionesNinguna expresiones) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(ExpresionesUna expresiones) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(ExpresionesMuchas expresiones) {
        throw NO_IMPLEMENTADA;
    }

    // Expresiones aritméticas

    default void procesa(Suma suma) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Resta resta) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Multiplicacion multiplicacion) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Division division) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(PorCiento porCiento) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Menos menos) {
        throw NO_IMPLEMENTADA;
    }

    // Expresiones básicas

    default void procesa(NumeroEntero numero) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(NumeroReal numero) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Identificador identificador) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(True booleanoTrue) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(False booleanoFalse) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Null nulo) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Cadena cadena) {
        throw NO_IMPLEMENTADA;
    }

    // Expresiones lógicas

    default void procesa(And and) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Or or) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Not not) {
        throw NO_IMPLEMENTADA;
    }

    // Expresiones comparativas

    default void procesa(Menor menor) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(MenorIgual menorIgual) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Mayor mayor) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(MayorIgual mayorIgual) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Igual igual) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Distinto distinto) {
        throw NO_IMPLEMENTADA;
    }

    // Expresiones de acceso a campos

    default void procesa(AccesoArray accesoArray) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Punto punto) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(Flecha flecha) {
        throw NO_IMPLEMENTADA;
    }

    default void procesa(ValorPuntero valorPuntero) {
        throw NO_IMPLEMENTADA;
    }
}
