/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiny0.ast;

import tiny0.alex.UnidadLexica;
import tiny0.asint.StringLocalizado;
import tiny0.asint.nodos.Programa;
import tiny0.asint.nodos.declaraciones.Declaracion;
import tiny0.asint.nodos.declaraciones.Declaraciones;
import tiny0.asint.nodos.expresiones.Expresion;
import tiny0.asint.nodos.instrucciones.Instruccion;
import tiny0.asint.nodos.instrucciones.Instrucciones;
import tiny0.asint.nodos.tipos.Tipo;
import tiny0.errors.GestionErroresTiny;
import tiny0.semops.SemOps;
import tiny0.alex.AnalizadorLexicoTiny;
import tiny0.alex.ClaseLexica;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConstructorAST {
	private UnidadLexica anticipo;
	private AnalizadorLexicoTiny alex;
	private GestionErroresTiny errores;
	private SemOps sem;

	public ConstructorAST(Reader input) {
		errores = new GestionErroresTiny();
		try {
			alex = new AnalizadorLexicoTiny(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sigToken();
		sem = new SemOps();
	}

	public Programa programaP() {
		Programa prog = programa();
		empareja(ClaseLexica.EOF);
		return prog;
	}

	private Programa programa() {
		switch (anticipo.clase()) {
		case BOOL:
		case INT:
		case REAL:
			Declaraciones decs = declaraciones();
			empareja(ClaseLexica.SEP_SEC); // DAmpersand
			Instrucciones instrs = instrucciones();
			return sem.prog(decs, instrs);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.INT, ClaseLexica.REAL);
			return null;
		}
	}

	private Declaraciones declaraciones() {
		switch (anticipo.clase()) {
		case BOOL:
		case INT:
		case REAL:
			Declaracion dec = declaracion();
			return REDeclaraciones(sem.decs_una(dec));
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.INT, ClaseLexica.REAL, ClaseLexica.SEP_SEC);
			return null;
		}
	}

	private Declaraciones REDeclaraciones(Declaraciones decsh) {
		switch (anticipo.clase()) {
		case PCOMA:
			empareja(ClaseLexica.PCOMA);
			Declaracion dec = declaracion();
			return REDeclaraciones(sem.decs_muchas(decsh, dec));
		case SEP_SEC: // DAmpersand
			return decsh;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PCOMA,
					ClaseLexica.SEP_SEC);
			return null;
		}
	}

	private Declaracion declaracion() {
		switch (anticipo.clase()) {
		case BOOL:
		case INT:
		case REAL:
			Tipo t = tipo();
			UnidadLexica id = anticipo;
			empareja(ClaseLexica.VARIABLE);
			return sem.dec(t, sem.str(id.lexema(), id.fila(), id.columna()));
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.INT, ClaseLexica.REAL);
			return null;
		}
	}

	private Tipo tipo() {
		switch (anticipo.clase()) {
		case BOOL:
			empareja(ClaseLexica.BOOL);
			return sem.bool_();
		case INT:
			empareja(ClaseLexica.INT);
			return sem.int_();
		case REAL:
			empareja(ClaseLexica.REAL);
			return sem.real_();
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.INT, ClaseLexica.REAL);
			return null;
		}
		
	}

	private Instrucciones instrucciones() {
		switch (anticipo.clase()) {
		case VARIABLE:
			Instruccion instr = instruccion();
			return REInstrucciones(sem.instr_una(instr));
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.VARIABLE);
			return null;
		}
	}

	private Instrucciones REInstrucciones(Instrucciones instrsh) {
		switch (anticipo.clase()) {
		case PCOMA:
			empareja(ClaseLexica.PCOMA);
			Instruccion instr = instruccion();
			return REInstrucciones(sem.instr_muchas(instrsh, instr));
		case EOF:
			return instrsh;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PCOMA);
			return null;
		}
	}

	private Instruccion instruccion() {
		switch (anticipo.clase()) {
		case VARIABLE:
			UnidadLexica id = anticipo;
			empareja(ClaseLexica.VARIABLE);
			empareja(ClaseLexica.IGUAL_ASIG);
			Expresion exp = expresion();
			return sem.instr(sem.str(id.lexema(), id.fila(), id.columna()), exp);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.VARIABLE);
			return null;
		}
	}

	private Expresion expresion() {
		switch (anticipo.clase()) {
		case PAPERTURA:
		case MENOS:
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
		case NOT:
			return E0();
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE,
					ClaseLexica.VARIABLE, ClaseLexica.FALSE, ClaseLexica.MENOS);
			return null;
		}
	}

	private Expresion expresionBasica() {
		UnidadLexica exp = anticipo;
		StringLocalizado str = sem.str(exp.lexema(), exp.fila(), exp.columna());
		switch (anticipo.clase()) {
		case FALSE:
			empareja(ClaseLexica.FALSE);
			return sem.false_();
		case VARIABLE:
			empareja(ClaseLexica.VARIABLE);
			return sem.id(str);
		case NUM_ENTERO:
			empareja(ClaseLexica.NUM_ENTERO);
			return sem.num_entero(str);
		case NUM_REAL:
			empareja(ClaseLexica.NUM_REAL);
			return sem.num_real(str);
		case TRUE:
			empareja(ClaseLexica.TRUE);
			return sem.true_();
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NUM_REAL, ClaseLexica.TRUE, ClaseLexica.VARIABLE, ClaseLexica.FALSE);
			return null;
		}
	}

	private Expresion E0() {
		switch (anticipo.clase()) {
		case PAPERTURA:
		case MENOS:
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
		case NOT:
			Expresion exp = E1();
			return RE0(exp);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE,
					ClaseLexica.VARIABLE, ClaseLexica.FALSE, ClaseLexica.MENOS);
			return null;
		}
	}

	private Expresion RE0(Expresion exph) {
		switch (anticipo.clase()) {
		case MAS:
			empareja(ClaseLexica.MAS);
			Expresion exp0 = E0();
			return sem.suma(exph, exp0);
		case MENOS:
			empareja(ClaseLexica.MENOS);
			Expresion exp1 = E1();
			return sem.resta(exph, exp1);
		case PCIERRE:
		case PCOMA:
		case EOF:
			return exph;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.MAS,
					ClaseLexica.MENOS);
			return null;
		}
	}

	private Expresion E1() {
		switch (anticipo.clase()) {
		case PAPERTURA:
		case MENOS:
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
		case NOT:
			Expresion exp = E2();
			return RE1(exp);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE,
					ClaseLexica.VARIABLE, ClaseLexica.FALSE, ClaseLexica.MENOS);
			return null;
		}
	}

	private Expresion RE1(Expresion exph) {
		switch (anticipo.clase()) {
		case AND:
		case OR:
			String op = OP1();
			Expresion exp1 = E2();
			return RE1(sem.exp(op, exph, exp1));
		case PCIERRE:
		case EOF:
		case MAS:
		case MENOS:
		case PCOMA:
			return exph;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.OR,
					ClaseLexica.AND);
			return null;
		}
	}

	private Expresion E2() {
		switch (anticipo.clase()) {
		case PAPERTURA:
		case MENOS:
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
		case NOT:
			Expresion exp = E3();
			return RE2(exp);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE,
					ClaseLexica.VARIABLE, ClaseLexica.FALSE, ClaseLexica.MENOS);
			return null;
		}
	}

	private Expresion RE2(Expresion exph) {
		switch (anticipo.clase()) {
		case DISTINTO:
		case IGUAL_COMP:
		case MAYOR:
		case MAYOR_IG:
		case MENOR:
		case MENOR_IG:
			String op = OP2();
			Expresion exp = E3();
			return RE2(sem.exp(op, exph, exp));
		case PCIERRE:
		case MAS:
		case MENOS:
		case PCOMA:
		case AND:
		case OR:
		case EOF:
			return exph;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.DISTINTO,
					ClaseLexica.IGUAL_COMP, ClaseLexica.MENOR, ClaseLexica.MENOR_IG, ClaseLexica.MAYOR,
					ClaseLexica.MAYOR_IG);
			return null;
		}
	}

	private Expresion E3() {
		switch (anticipo.clase()) {
		case PAPERTURA:
		case MENOS:
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
		case NOT:
			Expresion exp = E4();
			return RE3(exp);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE,
					ClaseLexica.VARIABLE, ClaseLexica.FALSE, ClaseLexica.MENOS);
			return null;
		}
	}

	private Expresion RE3(Expresion exph) {
		switch (anticipo.clase()) {
		case DIV:
		case POR:
			String op = OP3();
			Expresion exp = E4();
			return sem.exp(op, exph, exp);
		case PCIERRE:
		case MAS:
		case MENOS:
		case PCOMA:
		case AND:
		case DISTINTO:
		case IGUAL_COMP:
		case MAYOR:
		case MAYOR_IG:
		case MENOR:
		case MENOR_IG:
		case OR:
		case EOF:
			return exph;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.DIV,
					ClaseLexica.POR);
			return null;
		}
	}

	private Expresion E4() {
		switch (anticipo.clase()) {
		case PAPERTURA:
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
			return E5();
		case MENOS:
			empareja(ClaseLexica.MENOS);
			return sem.menos(E5());
		case NOT:
			empareja(ClaseLexica.NOT);
			return sem.not(E4());
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE,
					ClaseLexica.VARIABLE, ClaseLexica.FALSE, ClaseLexica.MENOS);
			return null;
		}
	}

	private Expresion E5() {
		switch (anticipo.clase()) {
		case PAPERTURA:
			empareja(ClaseLexica.PAPERTURA);
			Expresion exp = expresion();
			empareja(ClaseLexica.PCIERRE);
			return exp;
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
			return expresionBasica();
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE, ClaseLexica.VARIABLE,
					ClaseLexica.FALSE);
			return null;
		}
	}

	private String OP1() {
		switch (anticipo.clase()) {
		case AND:
			empareja(ClaseLexica.AND);
			return "and";
		case OR:
			empareja(ClaseLexica.OR);
			return "or";
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.AND,
					ClaseLexica.OR);
			return null;
		}
	}

	private String OP2() {
		switch (anticipo.clase()) {
		case DISTINTO:
			empareja(ClaseLexica.DISTINTO);
			return "!=";
		case IGUAL_COMP:
			empareja(ClaseLexica.IGUAL_COMP);
			return "==";
		case MAYOR:
			empareja(ClaseLexica.MAYOR);
			return ">";
		case MAYOR_IG:
			empareja(ClaseLexica.MAYOR_IG);
			return ">=";
		case MENOR:
			empareja(ClaseLexica.MENOR);
			return "<";
		case MENOR_IG:
			empareja(ClaseLexica.MENOR_IG);
			return "<=";
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.DISTINTO,
					ClaseLexica.IGUAL_COMP, ClaseLexica.MENOR, ClaseLexica.MENOR_IG, ClaseLexica.MAYOR,
					ClaseLexica.MAYOR_IG);
			return null;
		}
	}

	private String OP3() {
		switch (anticipo.clase()) {
		case DIV:
			empareja(ClaseLexica.DIV);
			return "/";
		case POR:
			empareja(ClaseLexica.POR);
			return "*";
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.DIV,
					ClaseLexica.POR);
			return null;
		}
	}

	private void empareja(ClaseLexica claseEsperada) {
		if (anticipo.clase() == claseEsperada)
			sigToken();
		else
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), claseEsperada);
	}

	private void sigToken() {
		try {
			anticipo = alex.sigToken();
		} catch (IOException e) {
			errores.errorFatal(e);
		}
	}

}
