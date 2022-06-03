package tiny1.procesamientos;

import java.io.PrintStream;
import java.util.Objects;

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

public class Impresion extends Procesador {
	private static final int INDENT_SIZE = 4;

	private final PrintStream out;
	private int indentLevel;

	public Impresion(PrintStream out) {
		this.out = Objects.requireNonNull(out);
		this.indentLevel = 0;
	}

	private void imprimeArgumento(Expresion arg, int prioridad) {
		if (arg.prioridad() < prioridad) {
			print("(");
			arg.procesa(this);
			print(")");
		} else {
			arg.procesa(this);
		}
	}

	private String indentacion() {
		return " ".repeat(INDENT_SIZE * indentLevel);
	}

	private void indentar() {
		indentLevel++;
	}

	private void desindentar() {
		indentLevel--;
		if (indentLevel < 0) {
			indentLevel = 0;
		}
	}

	private void print(Object obj) {
		out.print(obj);
	}

	private void println(Object obj) {
		out.println(obj);
		out.print(indentacion());
	}

	private void println() {
		out.println();
		out.print(indentacion());
	}

	@Override
	public void procesa(ProgramaConDecs programa) {
		programa.declaraciones().procesa(this);
		println();
		println("&&");
		programa.instrucciones().procesa(this);
		if (indentLevel == 0)
			println();
	}

	@Override
	public void procesa(DecsUna declaraciones) {
		declaraciones.declaracion().procesa(this);
	}

	@Override
	public void procesa(DecsMuchas declaraciones) {
		declaraciones.declaraciones().procesa(this);
		println(";");
		declaraciones.declaracion().procesa(this);
	}

	@Override
	public void procesa(DecType decType) {
		print("type ");
		decType.tipo().procesa(this);
		print(" " + decType.id());
	}

	@Override
	public void procesa(DecVar decVar) {
		print("var ");
		decVar.tipo().procesa(this);
		print(" " + decVar.id());
	}

	@Override
	public void procesa(DecProc decProc) {
		println();
		print("proc " + decProc.id() + " (");
		decProc.listaParams().procesa(this);
		print(") ");
		indentar();
		decProc.bloque().procesa(this);
		desindentar();
	}

	@Override
	public void procesa(InstrUna instrucciones) {
		instrucciones.instruccion().procesa(this);
	}

	@Override
	public void procesa(InstrMuchas instrucciones) {
		instrucciones.instrucciones().procesa(this);
		println(";");
		instrucciones.instruccion().procesa(this);
	}

	@Override
	public void procesa(Suma suma) {
		imprimeArgumento(suma.arg0(), 1);
		print(" + ");
		imprimeArgumento(suma.arg1(), 0);
	}

	@Override
	public void procesa(Resta resta) {
		imprimeArgumento(resta.arg0(), 1);
		print(" - ");
		imprimeArgumento(resta.arg1(), 1);
	}

	@Override
	public void procesa(Multiplicacion multiplicacion) {
		imprimeArgumento(multiplicacion.arg0(), 4);
		print(" * ");
		imprimeArgumento(multiplicacion.arg1(), 4);
	}

	@Override
	public void procesa(Division division) {
		imprimeArgumento(division.arg0(), 4);
		print(" / ");
		imprimeArgumento(division.arg1(), 4);
	}

	@Override
	public void procesa(Menos menos) {
		print(" - ");
		imprimeArgumento(menos.arg(), 5);
	}

	@Override
	public void procesa(NumeroEntero numero) {
		print(numero.num());
	}

	@Override
	public void procesa(NumeroReal numero) {
		print(numero.num());
	}

	@Override
	public void procesa(Identificador identificador) {
		print(identificador.id());
	}

	@Override
	public void procesa(True booleanoTrue) {
		print("true");
	}

	@Override
	public void procesa(False booleanoFalse) {
		print("false");
	}

	@Override
	public void procesa(And and) {
		imprimeArgumento(and.arg0(), 1);
		print(" and ");
		imprimeArgumento(and.arg1(), 2);
	}

	@Override
	public void procesa(Or or) {
		imprimeArgumento(or.arg0(), 1);
		print(" or ");
		imprimeArgumento(or.arg1(), 2);
	}

	@Override
	public void procesa(Not not) {
		print("not ");
		imprimeArgumento(not.arg(), 4);
	}

	@Override
	public void procesa(Menor menor) {
		imprimeArgumento(menor.arg0(), 2);
		print(" < ");
		imprimeArgumento(menor.arg1(), 3);
	}

	@Override
	public void procesa(MenorIgual menorIgual) {
		imprimeArgumento(menorIgual.arg0(), 2);
		print(" <= ");
		imprimeArgumento(menorIgual.arg1(), 3);
	}

	@Override
	public void procesa(Mayor mayor) {
		imprimeArgumento(mayor.arg0(), 2);
		print(" > ");
		imprimeArgumento(mayor.arg1(), 3);
	}

	@Override
	public void procesa(MayorIgual mayorIgual) {
		imprimeArgumento(mayorIgual.arg0(), 2);
		print(" >= ");
		imprimeArgumento(mayorIgual.arg1(), 3);
	}

	@Override
	public void procesa(Igual igual) {
		imprimeArgumento(igual.arg0(), 2);
		print(" == ");
		imprimeArgumento(igual.arg1(), 3);
	}

	@Override
	public void procesa(Distinto distinto) {
		imprimeArgumento(distinto.arg0(), 2);
		print(" != ");
		imprimeArgumento(distinto.arg1(), 3);
	}

	@Override
	public void procesa(ProgramaSinDecs programa) {
		programa.instrucciones().procesa(this);
		if (indentLevel == 0)
			println();
	}

	@Override
	public void procesa(ParamsSin parametros) {
		// no hacer nada
	}

	@Override
	public void procesa(ParamValor parametro) {
		parametro.tipo().procesa(this);
		print(" ");
		print(parametro.nombre());
	}

	@Override
	public void procesa(ParamRef parametro) {
		parametro.tipo().procesa(this);
		print(" & " + parametro.nombre());
	}

	@Override
	public void procesa(ListaParamsUno listaParametros) {
		listaParametros.parametro().procesa(this);
	}

	@Override
	public void procesa(ListaParamsMuchos listaParametros) {
		listaParametros.listaParametros().procesa(this);
		print(", ");
		listaParametros.parametro().procesa(this);
	}

	@Override
	public void procesa(TInt tipo) {
		print("int");
	}

	@Override
	public void procesa(TReal tipo) {
		print("real");
	}

	@Override
	public void procesa(TString tipo) {
		print("string");
	}

	@Override
	public void procesa(TBool tipo) {
		print("bool");
	}

	@Override
	public void procesa(TipoArray tipo) {
		print("array [" + tipo.longitud() + "] of ");
		tipo.tipoBase().procesa(this);
	}

	@Override
	public void procesa(TipoPointer tipo) {
		print("pointer ");
		tipo.tipoBase().procesa(this);
	}

	@Override
	public void procesa(TipoRecord tipo) {
		indentar();
		println("record {");
		tipo.campos().procesa(this);
		desindentar();
		println();
		print("}");
	}

	@Override
	public void procesa(TipoNuevo tipoNuevo) {
		print(tipoNuevo.nombre());
	}

	@Override
	public void procesa(Campo campo) {
		campo.tipo().procesa(this);
		print(" " + campo.nombre());
	}

	@Override
	public void procesa(CamposUno campos) {
		campos.campo().procesa(this);
	}

	@Override
	public void procesa(CamposMuchos campos) {
		campos.campos().procesa(this);
		println(";");
		campos.campo().procesa(this);
	}

	@Override
	public void procesa(InstrAsignacion instruccion) {
		instruccion.expresionIzquierda().procesa(this);
		print(" = ");
		instruccion.expresionDerecha().procesa(this);
	}

	@Override
	public void procesa(InstrOptNinguna instruccionOpt) {
		// no hacer nada
	}

	@Override
	public void procesa(InstrOptMuchas instruccionesOpt) {
		instruccionesOpt.instrucciones().procesa(this);
	}

	@Override
	public void procesa(InstruccionIf instruccion) {
		print("if ");
		instruccion.expresion().procesa(this);
		indentar();
		println(" then");
		instruccion.instruccionesOpt().procesa(this);
		desindentar();
		println();
		print("endif");
	}

	@Override
	public void procesa(InstruccionIfElse instruccion) {
		print("if ");
		instruccion.expresion().procesa(this);
		indentar();
		println(" then");
		instruccion.instruccionesOptIf().procesa(this);
		desindentar();
		println();
		print("else ");
		indentar();
		instruccion.instruccionesOptElse().procesa(this);
		desindentar();
		println();
		print("endif");
	}

	@Override
	public void procesa(InstruccionWhile instruccion) {
		println();
		print("while ");
		instruccion.expresion().procesa(this);
		indentar();
		println(" do");
		instruccion.instruccionesOpt().procesa(this);
		desindentar();
		println();
		print("endwhile");
	}

	@Override
	public void procesa(InstruccionRead instruccion) {
		print("read ");
		instruccion.expresion().procesa(this);
	}

	@Override
	public void procesa(InstruccionWrite instruccion) {
		print("write ");
		instruccion.expresion().procesa(this);
	}

	@Override
	public void procesa(InstruccionNL instruccion) {
		print("nl");
	}

	@Override
	public void procesa(InstruccionNew instruccion) {
		print("new ");
		instruccion.expresion().procesa(this);
	}

	@Override
	public void procesa(InstruccionDelete instruccion) {
		print("delete ");
		instruccion.expresion().procesa(this);
	}

	@Override
	public void procesa(InstruccionCall instruccion) {
		print("call " + instruccion.funcion() + "(");
		instruccion.parametros().procesa(this);
		print(")");
	}

	@Override
	public void procesa(InstruccionBloque instrucciones) {
		instrucciones.bloque().procesa(this);
	}

	@Override
	public void procesa(BloqueVacio bloque) {
		print("{}");
	}

	@Override
	public void procesa(BloqueLleno bloques) {
		println("{");
		bloques.programa().procesa(this);
		desindentar();
		println();
		print("}");
		indentar();
	}

	@Override
	public void procesa(ExpresionesNinguna expresiones) {
		// no hace nada
	}

	@Override
	public void procesa(ExpresionesUna expresiones) {
		expresiones.expresion().procesa(this);
	}

	@Override
	public void procesa(ExpresionesMuchas expresiones) {
		expresiones.expresiones().procesa(this);
		print(" , ");
		expresiones.expresion().procesa(this);
	}

	@Override
	public void procesa(PorCiento porCiento) {
		imprimeArgumento(porCiento.arg0(), 4);
		print("%");
		imprimeArgumento(porCiento.arg1(), 4);
	}

	@Override
	public void procesa(Cadena cadena) {
		print(cadena.cadena());
	}

	@Override
	public void procesa(AccesoArray accesoArray) {
		imprimeArgumento(accesoArray.arg0(), 5);
		print("[");
		accesoArray.arg1().procesa(this);
		print("]");
	}

	@Override
	public void procesa(Punto punto) {
		imprimeArgumento(punto.arg0(), 5);
		print(".");
		print(punto.arg1());
	}

	@Override
	public void procesa(Flecha flecha) {
		imprimeArgumento(flecha.arg0(), 5);
		print("->");
		print(flecha.arg1());
	}

	@Override
	public void procesa(Null nulo) {
		print("null");
	}

	@Override
	public void procesa(ValorPuntero valorPuntero) {
		print("*");
		valorPuntero.arg().procesa(this);
	}
}
