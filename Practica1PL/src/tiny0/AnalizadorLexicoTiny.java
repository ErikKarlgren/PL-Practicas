package tiny0;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AnalizadorLexicoTiny {

	private Reader input;
	private StringBuffer lex;
	private int sigCar;
	private int filaInicio;
	private int columnaInicio;
	private int filaActual;
	private int columnaActual;
	private static String NL = System.getProperty("line.separator");

	private static enum Estado {
		INICIO, REC_APERSAND, REC_SEP_SEC, REC_PCOMA, REC_VARIABLE, REC_IGUAL_ASIG, REC_NUM_ENTERO_1, REC_NUM_ENTERO_2,
		REC_NUM_REAL_1, REC_NUM_REAL_2, REC_NUM_REAL_3, REC_MAS, REC_MENOS, REC_POR, REC_DIV, REC_MENOR, REC_MAYOR,
		REC_MENOR_IG, REC_MAYOR_IG, REC_IGUAL_COMP, REC_DISTINTO, REC_PAPERTURA, REC_PCIERRE, REC_EXCLAMACION,
		REC_PUNTO, REC_EXPON, REC_SIGNO, REC_CERO_DECIMAL, REC_EOF
	}

	private Estado estado;

	public AnalizadorLexicoTiny(Reader input) throws IOException {
		this.input = input;
		lex = new StringBuffer();
		sigCar = input.read();
		filaActual = 1;
		columnaActual = 1;
	}

	public UnidadLexica sigToken() throws IOException {
		estado = Estado.INICIO;
		filaInicio = filaActual;
		columnaInicio = columnaActual;
		lex.delete(0, lex.length());
		while (true) {
			switch (estado) {
			case INICIO:
				if (hayLetra())
					transita(Estado.REC_VARIABLE);
				else if (hayDigitoPos())
					transita(Estado.REC_NUM_ENTERO_2);
				else if (hayCero())
					transita(Estado.REC_NUM_ENTERO_1);
				else if (haySuma())
					transita(Estado.REC_MAS);
				else if (hayResta())
					transita(Estado.REC_MENOS);
				else if (hayPor())
					transita(Estado.REC_POR);
				else if (hayDiv())
					transita(Estado.REC_DIV);
				else if (hayPApertura())
					transita(Estado.REC_PAPERTURA);
				else if (hayPCierre())
					transita(Estado.REC_PCIERRE);
				else if (hayIgual())
					transita(Estado.REC_IGUAL_ASIG);
				else if (hayApersand())
					transita(Estado.REC_APERSAND);
				else if (hayMayor())
					transita(Estado.REC_MAYOR);
				else if (hayMenor())
					transita(Estado.REC_MENOR);
				else if (hayExclamacion())
					transita(Estado.REC_EXCLAMACION);
				else if (hayPComa())
					transita(Estado.REC_PCOMA);
				else if (hayEOF())
					transita(Estado.REC_EOF);
				else if (haySep())
					transitaIgnorando(Estado.INICIO);
				else
					error();
				break;
			case REC_VARIABLE:
				if (hayLetra() || hayDigito() || hayBarraBaja())
					transita(Estado.REC_VARIABLE);
				else
					return unidadVariable();
				break;
			case REC_NUM_ENTERO_2:
				if (hayE())
					transita(Estado.REC_EXPON);
				else if (hayPunto())
					transita(Estado.REC_PUNTO);
				else if(hayDigito())
					transita(Estado.REC_NUM_ENTERO_2);
				else
					return unidadNumEntero();
				break;
			case REC_NUM_ENTERO_1:
				if (hayPunto())
					transita(Estado.REC_PUNTO);
				else if (hayE())
					transita(Estado.REC_EXPON);
				else
					return unidadNumEntero();
				break;
			case REC_PUNTO:
				if (hayDigito())
					transita(Estado.REC_NUM_REAL_1);
				else
					error();
				break;
			case REC_NUM_REAL_1:
				if (hayCero())
					transita(Estado.REC_CERO_DECIMAL);
				else if (hayE())
					transita(Estado.REC_EXPON);
				else if(hayDigitoPos())
					transita(Estado.REC_NUM_REAL_1);
				else
					return unidadNumReal();
				break;
			case REC_NUM_REAL_2:
				if (hayDigito())
					transita(Estado.REC_NUM_REAL_2);
				else
					return unidadNumReal();
				break;
			case REC_NUM_REAL_3:
				return unidadNumReal();
			case REC_CERO_DECIMAL:
				if (hayDigitoPos())
					transita(Estado.REC_NUM_REAL_1);
				else if (hayCero())
					transita(Estado.REC_CERO_DECIMAL);
				else
					error();
				break;
			case REC_EXPON:
				if (hayCero())
					transita(Estado.REC_NUM_REAL_3);
				else if (haySuma() || hayResta())
					transita(Estado.REC_SIGNO);
				else if (hayDigitoPos())
					transita(Estado.REC_NUM_REAL_2);
				else
					error();
				break;
			case REC_SIGNO:
				if (hayCero())
					transita(Estado.REC_NUM_REAL_3);
				else if (hayDigitoPos())
					transita(Estado.REC_NUM_REAL_2);
				else
					error();
				break;
			case REC_MAS:
				if (hayDigitoPos())
					transita(Estado.REC_NUM_ENTERO_2);
				else if (hayCero())
					transita(Estado.REC_NUM_ENTERO_1);
				else
					return unidadMas();
				break;
			case REC_MENOS:
				if (hayDigitoPos())
					transita(Estado.REC_NUM_ENTERO_2);
				else if (hayCero())
					transita(Estado.REC_NUM_ENTERO_1);
				else
					return unidadMenos();
				break;
			case REC_POR:
				return unidadPor();
			case REC_DIV:
				return unidadDiv();
			case REC_PAPERTURA:
				return unidadPApertura();
			case REC_PCIERRE:
				return unidadPCierre();
			case REC_PCOMA:
				return unidadPComa();
			case REC_IGUAL_ASIG:
				if (hayIgual())
					transita(Estado.REC_IGUAL_COMP);
				else
					return unidadIgualAsig();
				break;
			case REC_IGUAL_COMP:
				return unidadIgualComp();
			case REC_MAYOR:
				if (hayIgual())
					transita(Estado.REC_MAYOR_IG);
				else
					return unidadMayor();
				break;
			case REC_MAYOR_IG:
				return unidadMayorIg();
			case REC_MENOR:
				if (hayIgual())
					transita(Estado.REC_MENOR_IG);
				else
					return unidadMenor();
				break;
			case REC_MENOR_IG:
				return unidadMenorIg();
			case REC_APERSAND:
				if (hayApersand())
					transita(Estado.REC_SEP_SEC);
				else
					error();
				break;
			case REC_SEP_SEC:
				return unidadSepSec();
			case REC_EXCLAMACION:
				if(hayIgual())
					transita(Estado.REC_DISTINTO);
				else 
					error();
				break;
			case REC_DISTINTO:
				return unidadDistinto();
			case REC_EOF:
				return unidadEof();
			}
		}
	}

	private void transita(Estado sig) throws IOException {
		lex.append((char) sigCar);
		sigCar();
		estado = sig;
	}

	private void transitaIgnorando(Estado sig) throws IOException {
		sigCar();
		filaInicio = filaActual;
		columnaInicio = columnaActual;
		estado = sig;
	}

	private void sigCar() throws IOException {
		sigCar = input.read();
		if (sigCar == NL.charAt(0))
			saltaFinDeLinea();
		if (sigCar == '\n') {
			filaActual++;
			columnaActual = 0;
		} else {
			columnaActual++;
		}
	}

	private void saltaFinDeLinea() throws IOException {
		for (int i = 1; i < NL.length(); i++) {
			sigCar = input.read();
			if (sigCar != NL.charAt(i))
				error();
		}
		sigCar = '\n';
	}

	private boolean hayLetra() {
		return sigCar >= 'a' && sigCar <= 'z' || sigCar >= 'A' && sigCar <= 'Z';
	}

	private boolean hayBarraBaja() {
		return sigCar == '_';
	}

	private boolean hayE() {
		return sigCar == 'e' || sigCar == 'E';
	}

	private boolean hayDigitoPos() {
		return sigCar >= '1' && sigCar <= '9';
	}

	private boolean hayCero() {
		return sigCar == '0';
	}

	private boolean hayDigito() {
		return hayDigitoPos() || hayCero();
	}

	private boolean haySuma() {
		return sigCar == '+';
	}

	private boolean hayResta() {
		return sigCar == '-';
	}

	private boolean hayPor() {
		return sigCar == '*';
	}

	private boolean hayDiv() {
		return sigCar == '/';
	}

	private boolean hayPApertura() {
		return sigCar == '(';
	}

	private boolean hayPCierre() {
		return sigCar == ')';
	}

	private boolean hayIgual() {
		return sigCar == '=';
	}

	private boolean hayPunto() {
		return sigCar == '.';
	}

	private boolean hayApersand() {
		return sigCar == '&';
	}

	private boolean hayMayor() {
		return sigCar == '>';
	}

	private boolean hayMenor() {
		return sigCar == '<';
	}

	private boolean hayExclamacion() {
		return sigCar == '!';
	}

	private boolean hayPComa() {
		return sigCar == ';';
	}
	
	private boolean haySep() { return sigCar == ' ' || sigCar == '\t' || sigCar == '\n'; }
	
	private boolean hayNL() { return sigCar == '\r' || sigCar == '\b' || sigCar == '\n'; }
	
	private boolean hayEOF() {
		return sigCar == -1;
	}

	private UnidadLexica unidadVariable() {
		switch (lex.toString()) {
		case "int":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.INT);
		case "real":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.REAL);
		case "bool":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.BOOL);
		case "true":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.TRUE);
		case "false":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.FALSE);
		case "and":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.AND);
		case "or":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OR);
		case "not":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.NOT);
		default:
			return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.VARIABLE, lex.toString());
		}
	}

	private UnidadLexica unidadNumEntero() {
		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.NUM_ENTERO, lex.toString());
	}

	private UnidadLexica unidadNumReal() {
		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.NUM_REAL, lex.toString());
	}

	private UnidadLexica unidadMas() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAS);
	}

	private UnidadLexica unidadMenos() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOS);
	}

	private UnidadLexica unidadPor() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.POR);
	}

	private UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DIV);
	}

	private UnidadLexica unidadPApertura() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PAPERTURA);
	}

	private UnidadLexica unidadPCierre() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PCIERRE);
	}

	private UnidadLexica unidadIgualAsig() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.IGUAL_ASIG);
	}

	private UnidadLexica unidadIgualComp() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.IGUAL_COMP);
	}

	private UnidadLexica unidadMayor() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYOR);
	}

	private UnidadLexica unidadMayorIg() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYOR_IG);
	}

	private UnidadLexica unidadMenor() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOR);
	}

	private UnidadLexica unidadMenorIg() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOR_IG);
	}

	private UnidadLexica unidadDistinto() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DISTINTO);
	}

	private UnidadLexica unidadPComa() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PCOMA);
	}

	private UnidadLexica unidadSepSec() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.SEP_SEC);
	}

	private UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.EOF);
	}

	private void error() {
		System.err.println("(" + filaActual + ',' + columnaActual + "): Caracter ineXperado");
		System.exit(1);
	}

	public static void main(String arg[]) throws IOException {
		Reader input = new InputStreamReader(new FileInputStream("input.txt"));
		AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
		UnidadLexica unidad;
		do {
			unidad = al.sigToken();
			System.out.println(unidad);
		} while (unidad.clase() != ClaseLexica.EOF);
	}
}