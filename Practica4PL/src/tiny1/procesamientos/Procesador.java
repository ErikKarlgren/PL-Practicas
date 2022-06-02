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

    default void procesa(ProgramaConDecs programa) {
        throw new UnsupportedOperationException();
    }

    default void procesa(ProgramaSinDecs programa) {
        throw new UnsupportedOperationException();
    }

    // Declaraciones

    default void procesa(DecsUna declaraciones) {
        throw new UnsupportedOperationException();
    }

    default void procesa(DecsMuchas declaraciones) {
        throw new UnsupportedOperationException();
    }

    default void procesa(DecType decType) {
        throw new UnsupportedOperationException();
    }

    default void procesa(DecVar decVar) {
        throw new UnsupportedOperationException();
    }

    default void procesa(DecProc decProc) {
        throw new UnsupportedOperationException();
    }

    // Parametros

    default void procesa(ParamsSin parametros) {
        throw new UnsupportedOperationException();
    }

    default void procesa(ParamValor parametro) {
        throw new UnsupportedOperationException();
    }

    default void procesa(ParamRef parametro) {
        throw new UnsupportedOperationException();
    }

    default void procesa(ListaParamsUno listaParametros) {
        throw new UnsupportedOperationException();
    }

    default void procesa(ListaParamsMuchos listaParametros) {
        throw new UnsupportedOperationException();
    }

    // Tipos

    default void procesa(TInt tipo) {
        throw new UnsupportedOperationException();
    }

    default void procesa(TReal tipo) {
        throw new UnsupportedOperationException();
    }

    default void procesa(TString tipo) {
        throw new UnsupportedOperationException();
    }

    default void procesa(TBool tipo) {
        throw new UnsupportedOperationException();
    }

    default void procesa(TipoArray tipo) {
        throw new UnsupportedOperationException();
    }

    default void procesa(TipoPointer tipo) {
        throw new UnsupportedOperationException();
    }

    default void procesa(TipoRecord tipo) {
        throw new UnsupportedOperationException();
    }

    default void procesa(TipoNuevo tipoNuevo) {
        throw new UnsupportedOperationException();
    }

    // Campos

    default void procesa(Campo campo) {
        throw new UnsupportedOperationException();
    }

    default void procesa(CamposUno campos) {
        throw new UnsupportedOperationException();
    }

    default void procesa(CamposMuchos campos) {
        throw new UnsupportedOperationException();
    }

    // instrucciones

    default void procesa(InstrUna instrucciones) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstrMuchas instrucciones) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstrAsignacion instruccion) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstrOptNinguna instruccionOpt) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstrOptMuchas instruccionesOpt) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstruccionIf instruccion) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstruccionIfElse instruccion) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstruccionWhile instruccion) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstruccionRead instruccion) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstruccionWrite instruccion) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstruccionNL instruccion) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstruccionNew instruccion) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstruccionDelete instruccion) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstruccionCall instruccion) {
        throw new UnsupportedOperationException();
    }

    default void procesa(InstruccionBloque instrucciones) {
        throw new UnsupportedOperationException();
    }

    // Bloques

    default void procesa(BloqueVacio bloque) {
        throw new UnsupportedOperationException();
    }

    default void procesa(BloqueLleno bloques) {
        throw new UnsupportedOperationException();
    }

    // Expresiones

    default void procesa(ExpresionesNinguna expresiones) {
        throw new UnsupportedOperationException();
    }

    default void procesa(ExpresionesUna expresiones) {
        throw new UnsupportedOperationException();
    }

    default void procesa(ExpresionesMuchas expresiones) {
        throw new UnsupportedOperationException();
    }

    // Expresiones aritméticas

    default void procesa(Suma suma) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Resta resta) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Multiplicacion multiplicacion) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Division division) {
        throw new UnsupportedOperationException();
    }

    default void procesa(PorCiento porCiento) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Menos menos) {
        throw new UnsupportedOperationException();
    }

    // Expresiones básicas

    default void procesa(NumeroEntero numero) {
        throw new UnsupportedOperationException();
    }

    default void procesa(NumeroReal numero) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Identificador identificador) {
        throw new UnsupportedOperationException();
    }

    default void procesa(True booleanoTrue) {
        throw new UnsupportedOperationException();
    }

    default void procesa(False booleanoFalse) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Null nulo) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Cadena cadena) {
        throw new UnsupportedOperationException();
    }

    // Expresiones lógicas

    default void procesa(And and) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Or or) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Not not) {
        throw new UnsupportedOperationException();
    }

    // Expresiones comparativas

    default void procesa(Menor menor) {
        throw new UnsupportedOperationException();
    }

    default void procesa(MenorIgual menorIgual) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Mayor mayor) {
        throw new UnsupportedOperationException();
    }

    default void procesa(MayorIgual mayorIgual) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Igual igual) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Distinto distinto) {
        throw new UnsupportedOperationException();
    }

    // Expresiones de acceso a campos

    default void procesa(AccesoArray accesoArray) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Punto punto) {
        throw new UnsupportedOperationException();
    }

    default void procesa(Flecha flecha) {
        throw new UnsupportedOperationException();
    }

    default void procesa(ValorPuntero valorPuntero) {
        throw new UnsupportedOperationException();
    }
}
