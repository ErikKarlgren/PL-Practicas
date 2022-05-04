package tiny1.procesamientos;

import java.io.PrintStream;

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

public class Impresion implements Procesador {
	private final PrintStream out;

	public Impresion(PrintStream out) {
		this.out = out;
	}

	@Override
	public void procesa(ProgramaConDecs programa) {
		programa.declaraciones().procesa(this);
		out.println();
		out.println("&&");
		programa.instrucciones().procesa(this);
	}

	@Override
	public void procesa(DecsUna declaraciones) {
		declaraciones.declaracion().procesa(this);
	}

	@Override
	public void procesa(DecsMuchas declaraciones) {
		declaraciones.declaraciones().procesa(this);
		out.println(";");
		declaraciones.declaracion().procesa(this);
	}

	@Override
	public void procesa(DecType decType) {
		out.print("type ");
		decType.tipo().procesa(this);
		out.print(" " + decType.id());
	}

	@Override
	public void procesa(DecVar decVar) {
		out.print("var ");
		decVar.tipo().procesa(this);
		out.print(" " + decVar.id());
	}

	@Override
	public void procesa(DecProc decProc) {
		out.print("proc " + decProc.id());
		decProc.listaParams().procesa(this);
		decProc.bloque().procesa(this);
	}

	@Override
	public void procesa(InstrUna instrucciones) {
		instrucciones.instruccion().procesa(this);
	}

	@Override
	public void procesa(InstrMuchas instrucciones) {
		instrucciones.instrucciones().procesa(this);
		out.println(";");
		instrucciones.instruccion().procesa(this);
	}

	private void imprimeArgumento(Expresion arg, int prioridad) {
		if (arg.prioridad() < prioridad) {
			out.print("(");
			arg.procesa(this);
			out.print(")");
		} else {
			arg.procesa(this);
		}
	}

	@Override
	public void procesa(Suma suma) {
		imprimeArgumento(suma.arg0(), 1);
		out.print(" + ");
		imprimeArgumento(suma.arg1(), 0);
	}

	@Override
	public void procesa(Resta resta) {
		imprimeArgumento(resta.arg0(), 1);
		out.print(" - ");
		imprimeArgumento(resta.arg1(), 1);
	}

	@Override
	public void procesa(Multiplicacion multiplicacion) {
		imprimeArgumento(multiplicacion.arg0(), 4);
		out.print(" * ");
		imprimeArgumento(multiplicacion.arg1(), 4);
	}

	@Override
	public void procesa(Division division) {
		imprimeArgumento(division.arg0(), 4);
		out.print(" / ");
		imprimeArgumento(division.arg1(), 4);
	}

	@Override
	public void procesa(Menos menos) {
		out.print(" - ");
		imprimeArgumento(menos.arg(), 5);
	}

	@Override
	public void procesa(NumeroEntero numero) {
		out.print(numero.num());
	}

	@Override
	public void procesa(NumeroReal numero) {
		out.print(numero.num());
	}

	@Override
	public void procesa(Identificador identificador) {
		out.print(identificador.id());
	}

	@Override
	public void procesa(True booleanoTrue) {
		out.print("true");
	}

	@Override
	public void procesa(False booleanoFalse) {
		out.print("false");
	}

	@Override
	public void procesa(And and) {
		imprimeArgumento(and.arg0(), 1);
		out.print(" and ");
		imprimeArgumento(and.arg1(), 2);
	}

	@Override
	public void procesa(Or or) {
		imprimeArgumento(or.arg0(), 1);
		out.print(" or ");
		imprimeArgumento(or.arg1(), 2);
	}

	@Override
	public void procesa(Not not) {
		out.print("not ");
		imprimeArgumento(not.arg(), 4);
	}

	@Override
	public void procesa(Menor menor) {
		imprimeArgumento(menor.arg0(), 2);
		out.print(" < ");
		imprimeArgumento(menor.arg1(), 3);
	}

	@Override
	public void procesa(MenorIgual menorIgual) {
		imprimeArgumento(menorIgual.arg0(), 2);
		out.print(" <= ");
		imprimeArgumento(menorIgual.arg1(), 3);
	}

	@Override
	public void procesa(Mayor mayor) {
		imprimeArgumento(mayor.arg0(), 2);
		out.print(" > ");
		imprimeArgumento(mayor.arg1(), 3);
	}

	@Override
	public void procesa(MayorIgual mayorIgual) {
		imprimeArgumento(mayorIgual.arg0(), 2);
		out.print(" >= ");
		imprimeArgumento(mayorIgual.arg1(), 3);
	}

	@Override
	public void procesa(Igual igual) {
		imprimeArgumento(igual.arg0(), 2);
		out.print(" == ");
		imprimeArgumento(igual.arg1(), 3);
	}

	@Override
	public void procesa(Distinto distinto) {
		imprimeArgumento(distinto.arg0(), 2);
		out.print(" != ");
		imprimeArgumento(distinto.arg1(), 3);
	}

	@Override
	public void procesa(ProgramaSinDecs programa) {
		programa.instrucciones().procesa(this);
	}

	@Override
	public void procesa(ParamsSin parametros) {
		out.print("()");
	}

	@Override
	public void procesa(ParamsCon parametros) {
		out.print("(");
		parametros.listaParams().procesa(this);
		out.print(")");
	}

	@Override
	public void procesa(ParamValor parametro) {
		parametro.tipo().procesa(this);
		out.print(parametro.nombre());
	}

	@Override
	public void procesa(ParamRef parametro) {
		parametro.tipo().procesa(this);
		out.print(" & " + parametro.nombre());
	}

	@Override
	public void procesa(ListaParamsUno listaParametros) {
		listaParametros.parametro().procesa(this);
	}

	@Override
	public void procesa(ListaParamsMuchos listaParametros) {
		listaParametros.listaParametros().procesa(this);
		out.print(", ");
		listaParametros.parametro().procesa(this);
	}

	@Override
	public void procesa(Int tipo) {
		out.print("int");
	}

	@Override
	public void procesa(Real tipo) {
		out.print("real");
	}

	@Override
	public void procesa(TString tipo) {
		out.print("string");
	}

	@Override
	public void procesa(Bool tipo) {
		out.print("bool");
	}

	@Override
	public void procesa(TipoArray tipo) {
		out.print("array [" + tipo.longitud() + "] of ");
		tipo.tipoBase().procesa(this);
	}

	@Override
	public void procesa(TipoPointer tipo) {
		out.print("pointer ");
		tipo.tipoBase().procesa(this);
	}

	@Override
	public void procesa(TipoRecord tipo) {
		out.println("record {");
		tipo.campos().procesa(this);
		out.println();
		out.print("}");
	}

	@Override
	public void procesa(Campo campo) {
		campo.tipo().procesa(this);
		out.print(" " + campo.nombre());
	}

	@Override
	public void procesa(CamposUno campos) {
		campos.campo().procesa(this);
	}

	@Override
	public void procesa(CamposMuchos campos) {
		campos.campos().procesa(this);
		out.println(";");
		campos.campo().procesa(this);
	}

	@Override
	public void procesa(InstrAsignacion instruccion) {
		instruccion.expresionIzquierda().procesa(this);
		out.print(" = ");
		instruccion.expresionDerecha().procesa(this);
	}

	@Override
	public void procesa(InstrOptNinguna instruccionOpt) {
	}

	@Override
	public void procesa(InstrOptMuchas instruccionesOpt) {
		instruccionesOpt.instrucciones().procesa(this);
	}

	@Override
	public void procesa(InstruccionIf instruccion) {
		out.print("if ");
		instruccion.expresion().procesa(this);
		out.print(" then ");
		instruccion.instruccionesOpt().procesa(this);
		out.print(" endif");
	}

	@Override
	public void procesa(InstruccionIfElse instruccion) {
		out.print("if ");
		instruccion.expresion().procesa(this);
		out.print(" then ");
		instruccion.instruccionesOptIf().procesa(this);
		out.print(" else ");
		instruccion.instruccionesOptElse().procesa(this);
		out.print(" endif");
	}

	@Override
	public void procesa(InstruccionWhile instruccion) {
		out.print("while ");
		instruccion.expresion().procesa(this);
		out.print(" do ");
		instruccion.instruccionesOpt().procesa(this);
		out.print(" endwhile ");
	}

	@Override
	public void procesa(InstruccionRead instruccion) {
		out.print("read ");
		instruccion.expresion().procesa(this);
	}

	@Override
	public void procesa(InstruccionWrite instruccion) {
		out.print("write ");
		instruccion.expresion().procesa(this);
	}

	@Override
	public void procesa(InstruccionNL instruccion) {
		out.print("nl");
	}

	@Override
	public void procesa(InstruccionNew instruccion) {
		out.print("new ");
		instruccion.expresion().procesa(this);
	}

	@Override
	public void procesa(InstruccionDelete instruccion) {
		out.print("delete ");
		instruccion.expresion().procesa(this);
	}

	@Override
	public void procesa(InstruccionCall instruccion) {
		out.print("call " + instruccion.funcion());
		instruccion.parametros().procesa(this);
	}

	@Override
	public void procesa(InstruccionBloque instrucciones) {
		instrucciones.bloque().procesa(this);
	}

	@Override
	public void procesa(ParamsRealNinguna parametrosReal) {
		out.print("()");
	}

	@Override
	public void procesa(ParamsRealMuchas parametrosReal) {
		out.print("( ");
		parametrosReal.expresiones().procesa(this);
		out.print(")");
	}

	@Override
	public void procesa(BloqueVacio bloque) {
		out.print("{}");
	}

	@Override
	public void procesa(BloqueLleno bloques) {
		out.print("{");
		bloques.programa().procesa(this);
		out.print("}");
	}

	@Override
	public void procesa(ExpresionesUna expresiones) {
		expresiones.expresion().procesa(this);
	}

	@Override
	public void procesa(ExpresionesMuchas expresiones) {
		expresiones.expresiones().procesa(this);
		out.print(" , ");
		expresiones.expresion().procesa(this);
	}

	@Override
	public void procesa(PorCiento porCiento) {
		imprimeArgumento(porCiento.arg0(), 4);
		out.print("%");
		imprimeArgumento(porCiento.arg1(), 4);
	}

	@Override
	public void procesa(Cadena cadena) {
		out.print("\"" + cadena.cadena() + "\"");
	}

	@Override
	public void procesa(AccesoArray accesoArray) {
		imprimeArgumento(accesoArray.arg0(), 5);
		out.print("[");
		accesoArray.arg1().procesa(this);
		out.print("]");
	}

	@Override
	public void procesa(Punto punto) {
		imprimeArgumento(punto.arg0(), 5);
		out.print(".");
		out.print(punto.arg1());
	}

	@Override
	public void procesa(Flecha flecha) {
		imprimeArgumento(flecha.arg0(), 5);
		out.print("->");
		out.print(flecha.arg1());
	}

	@Override
	public void procesa(Null nulo) {
		out.print("null");
	}

	@Override
	public void procesa(ValorPuntero valorPuntero) {
		out.print("*");
		valorPuntero.arg().procesa(this);
	}

}
