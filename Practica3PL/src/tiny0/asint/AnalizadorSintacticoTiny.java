/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiny0.asint;

import tiny0.alex.UnidadLexica;
import tiny0.errors.GestionErroresTiny;
import tiny0.alex.AnalizadorLexicoTiny;
import tiny0.alex.ClaseLexica;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalizadorSintacticoTiny {
	private UnidadLexica anticipo;
	private AnalizadorLexicoTiny alex;
	private GestionErroresTiny errores;

	public AnalizadorSintacticoTiny(Reader input) {
		errores = new GestionErroresTiny();
		try {
			alex = new AnalizadorLexicoTiny(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sigToken();
	}

	public void ProgramaP() {
		Programa();
		empareja(ClaseLexica.EOF);
	}

	private void Programa() {
		switch (anticipo.clase()) {
		case BOOL:
		case INT:
		case REAL:
			Declaraciones();
			empareja(ClaseLexica.SEP_SEC); // DAmpersand
			Instrucciones();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.INT, ClaseLexica.REAL);
		}
	}

	private void Declaraciones() {
		switch (anticipo.clase()) {
		case BOOL:
		case INT:
		case REAL:
			Declaracion();
			REDeclaraciones();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.INT, ClaseLexica.REAL, ClaseLexica.SEP_SEC);
		}
	}

	private void Declaracion() {
		switch (anticipo.clase()) {
		case BOOL:
		case INT:
		case REAL:
			Tipo();
			empareja(ClaseLexica.VARIABLE);// Id();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.INT, ClaseLexica.REAL);
		}
	}

	private void REDeclaraciones() {
		switch (anticipo.clase()) {
		case PCOMA:
			empareja(ClaseLexica.PCOMA);
			Declaracion();
			REDeclaraciones();
			break;
		case SEP_SEC: // DAmpersand
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PCOMA,
					ClaseLexica.SEP_SEC);
		}
	}

	private void Tipo() {
		switch (anticipo.clase()) {
		case BOOL:
			empareja(ClaseLexica.BOOL);
			break;
		case INT:
			empareja(ClaseLexica.INT);
			break;
		case REAL:
			empareja(ClaseLexica.REAL);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.BOOL,
					ClaseLexica.INT, ClaseLexica.REAL);
		}
	}

	private void Instrucciones() {
		switch (anticipo.clase()) {
		case VARIABLE:
			Instruccion();
			REInstrucciones();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.VARIABLE);
		}
	}

	private void REInstrucciones() {
		switch (anticipo.clase()) {
		case PCOMA:
			empareja(ClaseLexica.PCOMA);
			Instruccion();
			REInstrucciones();
			break;
		case EOF:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PCOMA);
		}
	}

	private void Instruccion() {
		switch (anticipo.clase()) {
		case VARIABLE:
			empareja(ClaseLexica.VARIABLE);// Id();
			empareja(ClaseLexica.IGUAL_ASIG); // =
			Expresion();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.VARIABLE);
		}
	}

	private void Expresion() {
		switch (anticipo.clase()) {
		case PAPERTURA:
		case MENOS:
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
		case NOT:
			E0();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE,
					ClaseLexica.VARIABLE, ClaseLexica.FALSE, ClaseLexica.MENOS);
		}
	}

	private void ExpresionBasica() {
		switch (anticipo.clase()) {
		case FALSE:
			empareja(ClaseLexica.FALSE);
			break;
		case VARIABLE:
			empareja(ClaseLexica.VARIABLE);
			break;
		case NUM_ENTERO:
			empareja(ClaseLexica.NUM_ENTERO);
			break;
		case NUM_REAL:
			empareja(ClaseLexica.NUM_REAL);
			break;
		case TRUE:
			empareja(ClaseLexica.TRUE);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NUM_REAL, ClaseLexica.TRUE, ClaseLexica.VARIABLE, ClaseLexica.FALSE);
		}
	}

	private void E0() {
		switch (anticipo.clase()) {
		case PAPERTURA:
		case MENOS:
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
		case NOT:
			E1();
			RE0();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE,
					ClaseLexica.VARIABLE, ClaseLexica.FALSE, ClaseLexica.MENOS);
		}
	}

	private void RE0() {
		switch (anticipo.clase()) {
		case MAS:
			empareja(ClaseLexica.MAS);
			E0();
			break;
		case MENOS:
			empareja(ClaseLexica.MENOS);
			E1();
			break;
		case PCIERRE:
		case PCOMA:
		case EOF:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.MAS,
					ClaseLexica.MENOS);
		}
	}

	private void E1() {
		switch (anticipo.clase()) {
		case PAPERTURA:
		case MENOS:
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
		case NOT:
			E2();
			RE1();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE,
					ClaseLexica.VARIABLE, ClaseLexica.FALSE, ClaseLexica.MENOS);
		}
	}

	private void RE1() {
		switch (anticipo.clase()) {
		case AND:
		case OR:
			OP1();
			E2();
			RE1();
			break;
		case PCIERRE:
		case EOF:
		case MAS:
		case MENOS:
		case PCOMA:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.OR,
					ClaseLexica.AND);
		}
	}

	private void E2() {
		switch (anticipo.clase()) {
		case PAPERTURA:
		case MENOS:
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
		case NOT:
			E3();
			RE2();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE,
					ClaseLexica.VARIABLE, ClaseLexica.FALSE, ClaseLexica.MENOS);
		}
	}

	private void RE2() {
		switch (anticipo.clase()) {
		case DISTINTO:
		case IGUAL_COMP:
		case MAYOR:
		case MAYOR_IG:
		case MENOR:
		case MENOR_IG:
			OP2();
			E3();
			RE2();
			break;
		case PCIERRE:
		case MAS:
		case MENOS:
		case PCOMA:
		case AND:
		case OR:
		case EOF:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.DISTINTO,
					ClaseLexica.IGUAL_COMP, ClaseLexica.MENOR, ClaseLexica.MENOR_IG, ClaseLexica.MAYOR,
					ClaseLexica.MAYOR_IG);
		}
	}

	private void E3() {
		switch (anticipo.clase()) {
		case PAPERTURA:
		case MENOS:
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
		case NOT:
			E4();
			RE3();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE,
					ClaseLexica.VARIABLE, ClaseLexica.FALSE, ClaseLexica.MENOS);
		}
	}

	private void RE3() {
		switch (anticipo.clase()) {
		case DIV:
		case POR:
			OP3();
			E4();
			break;
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
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.DIV,
					ClaseLexica.POR);
		}
	}

	private void E4() {
		switch (anticipo.clase()) {
		case PAPERTURA:
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
			E5();
			break;
		case MENOS:
			empareja(ClaseLexica.MENOS);
			E5();
			break;
		case NOT:
			empareja(ClaseLexica.NOT);
			E4();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NOT, ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE,
					ClaseLexica.VARIABLE, ClaseLexica.FALSE, ClaseLexica.MENOS);
		}
	}

	private void E5() {
		switch (anticipo.clase()) {
		case PAPERTURA:
			empareja(ClaseLexica.PAPERTURA);
			Expresion();
			empareja(ClaseLexica.PCIERRE);
			break;
		case FALSE:
		case VARIABLE:
		case NUM_ENTERO:
		case NUM_REAL:
		case TRUE:
			ExpresionBasica();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.NUM_ENTERO,
					ClaseLexica.NUM_REAL, ClaseLexica.PAPERTURA, ClaseLexica.TRUE, ClaseLexica.VARIABLE,
					ClaseLexica.FALSE);
		}
	}

	private void OP1() {
		switch (anticipo.clase()) {
		case AND:
			empareja(ClaseLexica.AND);
			break;
		case OR:
			empareja(ClaseLexica.OR);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.AND,
					ClaseLexica.OR);
		}
	}

	private void OP2() {
		switch (anticipo.clase()) {
		case DISTINTO:
			empareja(ClaseLexica.DISTINTO);
			break;
		case IGUAL_COMP:
			empareja(ClaseLexica.IGUAL_COMP);
			break;
		case MAYOR:
			empareja(ClaseLexica.MAYOR);
			break;
		case MAYOR_IG:
			empareja(ClaseLexica.MAYOR_IG);
			break;
		case MENOR:
			empareja(ClaseLexica.MENOR);
			break;
		case MENOR_IG:
			empareja(ClaseLexica.MENOR_IG);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.DISTINTO,
					ClaseLexica.IGUAL_COMP, ClaseLexica.MENOR, ClaseLexica.MENOR_IG, ClaseLexica.MAYOR,
					ClaseLexica.MAYOR_IG);
		}
	}
	private void OP3() {
		switch (anticipo.clase()) {
		case DIV:
			empareja(ClaseLexica.DIV);
			break;
		case POR:
			empareja(ClaseLexica.POR);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.DIV,
					ClaseLexica.POR);
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
			//anticipo = alex.yylex();
			anticipo = alex.sigToken();
		} catch (IOException e) {
			errores.errorFatal(e);
		}
	}

}
