package tiny1.procesamientos;

import tiny1.asint.nodos.*;
import tiny1.asint.nodos.bloques.BloqueLleno;
import tiny1.asint.nodos.bloques.BloqueVacio;
import tiny1.asint.nodos.campos.Campo;
import tiny1.asint.nodos.campos.CamposMuchos;
import tiny1.asint.nodos.campos.CamposUno;
import tiny1.asint.nodos.declaraciones.DecProc;
import tiny1.asint.nodos.declaraciones.DecType;
import tiny1.asint.nodos.declaraciones.DecVar;
import tiny1.asint.nodos.declaraciones.Declaracion;
import tiny1.asint.nodos.declaraciones.DecsMuchas;
import tiny1.asint.nodos.declaraciones.DecsUna;
import tiny1.asint.nodos.expresiones.basicas.*;
import tiny1.asint.nodos.expresiones.AccesoArray;
import tiny1.asint.nodos.expresiones.ExpresionesMuchas;
import tiny1.asint.nodos.expresiones.ExpresionesUna;
import tiny1.asint.nodos.expresiones.Flecha;
import tiny1.asint.nodos.expresiones.Punto;
import tiny1.asint.nodos.expresiones.aritmeticas.*;
import tiny1.asint.nodos.expresiones.booleanas.logicas.*;
import tiny1.asint.nodos.expresiones.tipos.TipoArray;
import tiny1.asint.nodos.expresiones.tipos.TipoBasico;
import tiny1.asint.nodos.expresiones.tipos.TipoPointer;
import tiny1.asint.nodos.expresiones.tipos.TipoRecord;
import tiny1.asint.nodos.instrucciones.InstrAsignacion;
import tiny1.asint.nodos.instrucciones.InstrMuchas;
import tiny1.asint.nodos.instrucciones.InstrOptMuchas;
import tiny1.asint.nodos.instrucciones.InstrOptNinguna;
import tiny1.asint.nodos.instrucciones.InstrUna;
import tiny1.asint.nodos.instrucciones.Instruccion;
import tiny1.asint.nodos.instrucciones.InstruccionBloque;
import tiny1.asint.nodos.instrucciones.InstruccionCall;
import tiny1.asint.nodos.instrucciones.InstruccionDelete;
import tiny1.asint.nodos.instrucciones.InstruccionIf;
import tiny1.asint.nodos.instrucciones.InstruccionIfElse;
import tiny1.asint.nodos.instrucciones.InstruccionNL;
import tiny1.asint.nodos.instrucciones.InstruccionNew;
import tiny1.asint.nodos.instrucciones.InstruccionRead;
import tiny1.asint.nodos.instrucciones.InstruccionWrite;
import tiny1.asint.nodos.instrucciones.InstruccionWhile;
import tiny1.asint.nodos.parametros.ListaParamsMuchos;
import tiny1.asint.nodos.parametros.ListaParamsUno;
import tiny1.asint.nodos.parametros.ParamRef;
import tiny1.asint.nodos.parametros.ParamValor;
import tiny1.asint.nodos.parametros.ParamsCon;
import tiny1.asint.nodos.parametros.ParamsRealMuchas;
import tiny1.asint.nodos.parametros.ParamsRealNinguna;
import tiny1.asint.nodos.parametros.ParamsSin;
import tiny1.asint.nodos.programa.ProgramaConDecs;
import tiny1.asint.nodos.programa.ProgramaSinDecs;
import tiny1.asint.nodos.expresiones.booleanas.comparacion.*;

public interface Procesador {

    void procesa(ProgramaConDecs programa);

    void procesa(ProgramaSinDecs programa);

    // Declaraciones
    
   // void procesa(Declaracion declaracion);

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

    void procesa(TipoBasico tipo);

    void procesa(TipoArray tipo);

    void procesa(TipoPointer tipo);

    void procesa(TipoRecord tipo);

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

    // Otras expresiones

    void procesa(AccesoArray accesoArray);

    void procesa(Punto punto);

    void procesa(Flecha flecha);




}
