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

public abstract class Procesador {

    public boolean foundErrors() {
        return false;
    }

    public void procesa(ProgramaConDecs programa) {
        throw new UnsupportedOperationException("Procesamiento no implementado para ProgramaConDecs");
    }

    public void procesa(ProgramaSinDecs programa) {
        throw new UnsupportedOperationException("Procesamiento no implementado para ProgramaSinDecs");
    }

    // Declaraciones

    public void procesa(DecsUna declaraciones) {
        throw new UnsupportedOperationException("Procesamiento no implementado para DecsUna");
    }

    public void procesa(DecsMuchas declaraciones) {
        throw new UnsupportedOperationException("Procesamiento no implementado para DecsMuchas");
    }

    public void procesa(DecType decType) {
        throw new UnsupportedOperationException("Procesamiento no implementado para DecType");
    }

    public void procesa(DecVar decVar) {
        throw new UnsupportedOperationException("Procesamiento no implementado para DecVar");
    }

    public void procesa(DecProc decProc) {
        throw new UnsupportedOperationException("Procesamiento no implementado para DecProc");
    }

    // Parametros

    public void procesa(ParamsSin parametros) {
        throw new UnsupportedOperationException("Procesamiento no implementado para ParamsSin");
    }

    public void procesa(ParamValor parametro) {
        throw new UnsupportedOperationException("Procesamiento no implementado para ParamValor");
    }

    public void procesa(ParamRef parametro) {
        throw new UnsupportedOperationException("Procesamiento no implementado para ParamRef");
    }

    public void procesa(ListaParamsUno listaParametros) {
        throw new UnsupportedOperationException("Procesamiento no implementado para ListaParamsUno");
    }

    public void procesa(ListaParamsMuchos listaParametros) {
        throw new UnsupportedOperationException("Procesamiento no implementado para ListaParamsMuchos");
    }

    // Tipos

    public void procesa(TInt tipo) {
        throw new UnsupportedOperationException("Procesamiento no implementado para TInt");
    }

    public void procesa(TReal tipo) {
        throw new UnsupportedOperationException("Procesamiento no implementado para TReal");
    }

    public void procesa(TString tipo) {
        throw new UnsupportedOperationException("Procesamiento no implementado para TString");
    }

    public void procesa(TBool tipo) {
        throw new UnsupportedOperationException("Procesamiento no implementado para TBool");
    }

    public void procesa(TipoArray tipo) {
        throw new UnsupportedOperationException("Procesamiento no implementado para TipoArray");
    }

    public void procesa(TipoPointer tipo) {
        throw new UnsupportedOperationException("Procesamiento no implementado para TipoPointer");
    }

    public void procesa(TipoRecord tipo) {
        throw new UnsupportedOperationException("Procesamiento no implementado para TipoRecord");
    }

    public void procesa(TipoNuevo tipoNuevo) {
        throw new UnsupportedOperationException("Procesamiento no implementado para TipoNuevo");
    }

    public void procesa(TNull tNull) {
        throw new UnsupportedOperationException("Procesamiento no implementado para TNull");
    }

    // Campos

    public void procesa(Campo campo) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Campo");
    }

    public void procesa(CamposUno campos) {
        throw new UnsupportedOperationException("Procesamiento no implementado para CamposUno");
    }

    public void procesa(CamposMuchos campos) {
        throw new UnsupportedOperationException("Procesamiento no implementado para CamposMuchos");
    }

    // instrucciones

    public void procesa(InstrUna instrucciones) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstrUna");
    }

    public void procesa(InstrMuchas instrucciones) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstrMuchas");
    }

    public void procesa(InstrAsignacion instruccion) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstrAsignacion");
    }

    public void procesa(InstrOptNinguna instruccionOpt) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstrOptNinguna");
    }

    public void procesa(InstrOptMuchas instruccionesOpt) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstrOptMuchas");
    }

    public void procesa(InstruccionIf instruccion) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstruccionIf");
    }

    public void procesa(InstruccionIfElse instruccion) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstruccionIfElse");
    }

    public void procesa(InstruccionWhile instruccion) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstruccionWhile");
    }

    public void procesa(InstruccionRead instruccion) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstruccionRead");
    }

    public void procesa(InstruccionWrite instruccion) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstruccionWrite");
    }

    public void procesa(InstruccionNL instruccion) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstruccionNL");
    }

    public void procesa(InstruccionNew instruccion) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstruccionNew");
    }

    public void procesa(InstruccionDelete instruccion) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstruccionDelete");
    }

    public void procesa(InstruccionCall instruccion) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstruccionCall");
    }

    public void procesa(InstruccionBloque instrucciones) {
        throw new UnsupportedOperationException("Procesamiento no implementado para InstruccionBloque");
    }

    // Bloques

    public void procesa(BloqueVacio bloque) {
        throw new UnsupportedOperationException("Procesamiento no implementado para BloqueVacio");
    }

    public void procesa(BloqueLleno bloque) {
        throw new UnsupportedOperationException("Procesamiento no implementado para BloqueLleno");
    }

    // Expresiones

    public void procesa(ExpresionesNinguna expresiones) {
        throw new UnsupportedOperationException("Procesamiento no implementado para ExpresionesNinguna");
    }

    public void procesa(ExpresionesUna expresiones) {
        throw new UnsupportedOperationException("Procesamiento no implementado para ExpresionesUna");
    }

    public void procesa(ExpresionesMuchas expresiones) {
        throw new UnsupportedOperationException("Procesamiento no implementado para ExpresionesMuchas");
    }

    // Expresiones aritméticas

    public void procesa(Suma suma) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Suma");
    }

    public void procesa(Resta resta) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Resta");
    }

    public void procesa(Multiplicacion multiplicacion) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Multiplicacion");
    }

    public void procesa(Division division) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Division");
    }

    public void procesa(PorCiento porCiento) {
        throw new UnsupportedOperationException("Procesamiento no implementado para PorCiento");
    }

    public void procesa(Menos menos) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Menos");
    }

    // Expresiones básicas

    public void procesa(NumeroEntero numero) {
        throw new UnsupportedOperationException("Procesamiento no implementado para NumeroEntero");
    }

    public void procesa(NumeroReal numero) {
        throw new UnsupportedOperationException("Procesamiento no implementado para NumeroReal");
    }

    public void procesa(Identificador identificador) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Identificador");
    }

    public void procesa(True booleanoTrue) {
        throw new UnsupportedOperationException("Procesamiento no implementado para True");
    }

    public void procesa(False booleanoFalse) {
        throw new UnsupportedOperationException("Procesamiento no implementado para False");
    }

    public void procesa(Null nulo) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Null");
    }

    public void procesa(Cadena cadena) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Cadena");
    }

    // Expresiones lógicas

    public void procesa(And and) {
        throw new UnsupportedOperationException("Procesamiento no implementado para And");
    }

    public void procesa(Or or) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Or");
    }

    public void procesa(Not not) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Not");
    }

    // Expresiones comparativas

    public void procesa(Menor menor) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Menor");
    }

    public void procesa(MenorIgual menorIgual) {
        throw new UnsupportedOperationException("Procesamiento no implementado para MenorIgual");
    }

    public void procesa(Mayor mayor) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Mayor");
    }

    public void procesa(MayorIgual mayorIgual) {
        throw new UnsupportedOperationException("Procesamiento no implementado para MayorIgual");
    }

    public void procesa(Igual igual) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Igual");
    }

    public void procesa(Distinto distinto) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Distinto");
    }

    // Expresiones de acceso a campos

    public void procesa(AccesoArray accesoArray) {
        throw new UnsupportedOperationException("Procesamiento no implementado para AccesoArray");
    }

    public void procesa(Punto punto) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Punto");
    }

    public void procesa(Flecha flecha) {
        throw new UnsupportedOperationException("Procesamiento no implementado para Flecha");
    }

    public void procesa(ValorPuntero valorPuntero) {
        throw new UnsupportedOperationException("Procesamiento no implementado para ValorPuntero");
    }
}
