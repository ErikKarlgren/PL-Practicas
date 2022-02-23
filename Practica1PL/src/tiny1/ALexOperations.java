package tiny1;

public class ALexOperations {
	private AnalizadorLexicoTiny alex;

	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;
	}

	// Para palabras reservadas

	public UnidadLexica unidadInt() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INT);
	}

	public UnidadLexica unidadBool() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.BOOL);
	}

	public UnidadLexica unidadReal() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.REAL);
	}

	public UnidadLexica unidadTrue() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TRUE);
	}

	public UnidadLexica unidadFalse() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FALSE);
	}

	public UnidadLexica unidadOr() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OR);
	}

	public UnidadLexica unidadAnd() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.AND);
	}

	public UnidadLexica unidadNot() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NOT);
	}

	public UnidadLexica unidadString() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.STRING);
	}

	public UnidadLexica unidadNull() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NULL);
	}

	public UnidadLexica unidadProc() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PROC);
	}

	public UnidadLexica unidadIf() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IF);
	}

	public UnidadLexica unidadThen() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.THEN);
	}

	public UnidadLexica unidadElse() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ELSE);
	}

	public UnidadLexica unidadEndif() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ENDIF);
	}

	public UnidadLexica unidadWhile() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.WHILE);
	}

	public UnidadLexica unidadDo() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DO);
	}

	public UnidadLexica unidadEndwhile() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ENDWHILE);
	}

	public UnidadLexica unidadCall() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CALL);
	}

	public UnidadLexica unidadRecord() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.RECORD);
	}

	public UnidadLexica unidadArray() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ARRAY);
	}

	public UnidadLexica unidadOf() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OF);
	}

	public UnidadLexica unidadPointer() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.POINTER);
	}

	public UnidadLexica unidadNew() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NEW);
	}

	public UnidadLexica unidadDelete() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DELETE);
	}

	public UnidadLexica unidadRead() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.READ);
	}

	public UnidadLexica unidadWrite() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.WRITE);
	}

	public UnidadLexica unidadNL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NL);
	}

	public UnidadLexica unidadVar() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.VAR);
	}

	public UnidadLexica unidadType() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TYPE);
	}

	// Otros

	public UnidadLexica unidadNumeroEntero() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.NUM_ENTERO, alex.lexema());
	}

	public UnidadLexica unidadNumeroReal() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.NUM_REAL, alex.lexema());
	}

	public UnidadLexica unidadVariable() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.VARIABLE, alex.lexema());
	}

	public UnidadLexica unidadSuma() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAS);
	}

	public UnidadLexica unidadResta() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOS);
	}

	public UnidadLexica unidadMul() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.POR);
	}

	public UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DIV);
	}

	public UnidadLexica unidadPApertura() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PAPERTURA);
	}

	public UnidadLexica unidadPCierre() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PCIERRE);
	}

	public UnidadLexica unidadIgualAsig() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IGUAL_ASIG);
	}

	public UnidadLexica unidadIgualComp() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IGUAL_COMP);
	}

	public UnidadLexica unidadComa() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.COMA);
	}

	public UnidadLexica unidadPComa() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PCOMA);
	}

	public UnidadLexica unidadSepSec() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.SEP_SEC);
	}

	public UnidadLexica unidadMayor() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYOR);
	}

	public UnidadLexica unidadMenor() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOR);
	}

	public UnidadLexica unidadMayorIg() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYOR_IG);
	}

	public UnidadLexica unidadMenorIg() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOR_IG);
	}

	public UnidadLexica unidadDistinto() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DISTINTO);
	}

	public UnidadLexica unidadCadena() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.CADENA, alex.lexema());
	}

	public UnidadLexica unidadPorCiento() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.POR_CIENTO);
	}

	public UnidadLexica unidadCorApertura() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORAPERTURA);
	}

	public UnidadLexica unidadCorCierre() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCIERRE);
	}

	public UnidadLexica unidadLlaveApertura() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_APERTURA);
	}

	public UnidadLexica unidadLlaveCierre() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_CIERRE);
	}

	public UnidadLexica unidadPunto() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO);
	}

	public UnidadLexica unidadFlecha() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FLECHA);
	}

	public UnidadLexica unidadAmpersand() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.AMPERSAND);
	}

	public UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.EOF);
	}

	public void error() {
		System.err.println("***" + alex.fila() + " Caracter inexperado: " + alex.lexema());
	}
}
