package tiny1.alex;

import tiny1.asint.ClaseLexica;

public class ALexOperations {
	private AnalizadorLexicoTiny alex;

	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;
	}

	// Para palabras reservadas

	public UnidadLexica unidadInt() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INT, "int");
	}

	public UnidadLexica unidadBool() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BOOL, "bool");
	}

	public UnidadLexica unidadReal() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REAL, "real");
	}

	public UnidadLexica unidadTrue() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRUE, "true");
	}

	public UnidadLexica unidadFalse() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSE, "false");
	}

	public UnidadLexica unidadOr() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OR, "or");
	}

	public UnidadLexica unidadAnd() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AND, "and");
	}

	public UnidadLexica unidadNot() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NOT, "not");
	}

	public UnidadLexica unidadString() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRING, "string");
	}

	public UnidadLexica unidadNull() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NULL, "null");
	}

	public UnidadLexica unidadProc() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PROC, "proc");
	}

	public UnidadLexica unidadIf() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IF, "if");
	}

	public UnidadLexica unidadThen() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.THEN, "then");
	}

	public UnidadLexica unidadElse() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ELSE, "else");
	}

	public UnidadLexica unidadEndif() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENDIF, "endif");
	}

	public UnidadLexica unidadWhile() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WHILE, "while");
	}

	public UnidadLexica unidadDo() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DO, "do");
	}

	public UnidadLexica unidadEndwhile() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENDWHILE, "endwhile");
	}

	public UnidadLexica unidadCall() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CALL, "call");
	}

	public UnidadLexica unidadRecord() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RECORD, "record");
	}

	public UnidadLexica unidadArray() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ARRAY, "array");
	}

	public UnidadLexica unidadOf() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OF, "of");
	}

	public UnidadLexica unidadPointer() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POINTER, "pointer");
	}

	public UnidadLexica unidadNew() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NEW, "new");
	}

	public UnidadLexica unidadDelete() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DELETE, "delete");
	}

	public UnidadLexica unidadRead() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.READ, "read");
	}

	public UnidadLexica unidadWrite() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WRITE, "write");
	}

	public UnidadLexica unidadNL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NL, "nl");
	}

	public UnidadLexica unidadVar() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.VAR, "var");
	}

	public UnidadLexica unidadType() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TYPE, "type");
	}

	// Otros

	public UnidadLexica unidadNumeroEntero() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NUM_ENTERO, alex.lexema());
	}

	public UnidadLexica unidadNumeroReal() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NUM_REAL, alex.lexema());
	}

	public UnidadLexica unidadVariable() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.VARIABLE, alex.lexema());
	}

	public UnidadLexica unidadSuma() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAS, "+");
	}

	public UnidadLexica unidadResta() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOS, "-");
	}

	public UnidadLexica unidadMul() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POR, "*");
	}

	public UnidadLexica unidadDiv() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIV, "/");
	}

	public UnidadLexica unidadPApertura() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAPERTURA, "(");
	}

	public UnidadLexica unidadPCierre() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PCIERRE, ")");
	}

	public UnidadLexica unidadIgualAsig() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL_ASIG, "=");
	}

	public UnidadLexica unidadIgualComp() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL_COMP, "==");
	}

	public UnidadLexica unidadComa() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA, ",");
	}

	public UnidadLexica unidadPComa() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PCOMA, ";");
	}

	public UnidadLexica unidadSepSec() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SEP_SEC, "&&");
	}

	public UnidadLexica unidadMayor() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR, ">");
	}

	public UnidadLexica unidadMenor() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR, "<");
	}

	public UnidadLexica unidadMayorIg() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR_IG, ">=");
	}

	public UnidadLexica unidadMenorIg() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR_IG, "<=");
	}

	public UnidadLexica unidadDistinto() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DISTINTO, "!=");
	}

	public UnidadLexica unidadCadena() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CADENA, alex.lexema());
	}

	public UnidadLexica unidadPorCiento() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POR_CIENTO, "%");
	}

	public UnidadLexica unidadCorApertura() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CORAPERTURA, "[");
	}

	public UnidadLexica unidadCorCierre() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CORCIERRE, "]");
	}

	public UnidadLexica unidadLlaveApertura() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVE_APERTURA, "{");
	}

	public UnidadLexica unidadLlaveCierre() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVE_CIERRE, "}");
	}

	public UnidadLexica unidadPunto() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO, ".");
	}

	public UnidadLexica unidadFlecha() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FLECHA, "->");
	}

	public UnidadLexica unidadAmpersand() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AMPERSAND, "&");
	}

	public UnidadLexica unidadEof() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF, "<EOF>");
	}

	public void error() {
		System.err.println("***" + alex.fila() + " Caracter inexperado: " + alex.lexema());
	}
}
