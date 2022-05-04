package tiny1.procesamientos;

import tiny1.asint.nodos.bloques.*;
import tiny1.asint.nodos.campos.*;
import tiny1.asint.nodos.declaraciones.*;
import tiny1.asint.nodos.expresiones.basicas.*;
import tiny1.asint.nodos.expresiones.*;
import tiny1.asint.nodos.expresiones.aritmeticas.*;
import tiny1.asint.nodos.expresiones.booleanas.logicas.*;
import tiny1.asint.nodos.expresiones.booleanas.comparacion.*;
import tiny1.asint.nodos.instrucciones.*;
import tiny1.asint.nodos.parametros.*;
import tiny1.asint.nodos.programa.*;
import tiny1.asint.nodos.tipos.*;

public interface Procesador {

    void procesa(ProgramaConDecs programa);

    void procesa(ProgramaSinDecs programa);

    // Declaraciones

    void procesa(DecsUna declaraciones);

    void procesa(DecsMuchas declaraciones);

    void procesa(DecType decType);

    void procesa(DecVar decVar);

    void procesa(DecProc decProc);

    // Parametros

    void procesa(ParamsSin parametros);

    void procesa(ParamsCon parametros);

    void procesa(ParamValor parametro);

    void procesa(ParamRef parametro);

    void procesa(ListaParamsUno listaParametros);

    void procesa(ListaParamsMuchos listaParametros);

    // Tipos

    void procesa(Int tipo);

    void procesa(Real tipo);

    void procesa(TString tipo);

    void procesa(Bool tipo);

    void procesa(TipoArray tipo);

    void procesa(TipoPointer tipo);

    void procesa(TipoRecord tipo);

    void procesa(TipoNuevo tipoNuevo);

    // Campos

    void procesa(Campo campo);

    void procesa(CamposUno campos);

    void procesa(CamposMuchos campos);

    // instrucciones

    void procesa(InstrUna instrucciones);

    void procesa(InstrMuchas instrucciones);

    void procesa(InstrAsignacion instruccion);

    void procesa(InstrOptNinguna instruccionOpt);

    void procesa(InstrOptMuchas instruccionesOpt);

    void procesa(InstruccionIf instruccion);

    void procesa(InstruccionIfElse instruccion);

    void procesa(InstruccionWhile instruccion);

    void procesa(InstruccionRead instruccion);

    void procesa(InstruccionWrite instruccion);

    void procesa(InstruccionNL instruccion);

    void procesa(InstruccionNew instruccion);

    void procesa(InstruccionDelete instruccion);

    void procesa(InstruccionCall instruccion);

    void procesa(InstruccionBloque instrucciones);

    // Params Real

    void procesa(ParamsRealNinguna parametrosReal);

    void procesa(ParamsRealMuchas parametrosReal);

    // Bloques

    void procesa(BloqueVacio bloque);

    void procesa(BloqueLleno bloques);

    // Expresiones

    void procesa(ExpresionesUna expresiones);

    void procesa(ExpresionesMuchas expresiones);

    // Expresiones aritméticas

    void procesa(Suma suma);

    void procesa(Resta resta);

    void procesa(Multiplicacion multiplicacion);

    void procesa(Division division);

    void procesa(PorCiento porCiento);

    void procesa(Menos menos);

    // Expresiones básicas

    void procesa(NumeroEntero numero);

    void procesa(NumeroReal numero);

    void procesa(Identificador identificador);

    void procesa(True booleanoTrue);

    void procesa(False booleanoFalse);

    void procesa(Null nulo);

    void procesa(Cadena cadena);

    // Expresiones lógicas

    void procesa(And and);

    void procesa(Or or);

    void procesa(Not not);

    // Expresiones comparativas

    void procesa(Menor menor);

    void procesa(MenorIgual menorIgual);

    void procesa(Mayor mayor);

    void procesa(MayorIgual mayorIgual);

    void procesa(Igual igual);

    void procesa(Distinto distinto);

    // Expresiones de acceso a campos

    void procesa(AccesoArray accesoArray);

    void procesa(Punto punto);

    void procesa(Flecha flecha);

    void procesa(ValorPuntero valorPuntero);

}
