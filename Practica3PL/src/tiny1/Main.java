package tiny1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import tiny1.asint.nodos.programa.Programa;
import tiny1.ast_descendente.ConstructorAST;
import tiny1.ast_descendente.ParseException;
import tiny1.procesamientos.Impresion;

public class Main {
	public static void main(String[] args) throws Exception {
		try {
			Programa prog = crearPrograma(args);
			prog.procesa(new Impresion(System.out));
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
	}

	private static Programa crearPrograma(String[] args) throws ParseException, FileNotFoundException {
		Programa prog = null;
		if (args[0].equals("asc"))
			prog = ascendente(args[1]);
		else if (args[0].equals("desc"))
			prog = descendente(args[1]);
		else {
			System.err.println("Uso: java tiny1.Main <asc|desc> <archivo>");
			System.exit(1);
		}
		return prog;
	}

	private static Programa ascendente(String in) throws ParseException, FileNotFoundException {
		// Reader input = new InputStreamReader(new FileInputStream(in));
		// AnalizadorLexico alex = new AnalizadorLexico(input);
		// ConstructorAST constructorast = new ConstructorAST(alex);
		// return (Prog)constructorast.parse().value;
		throw new UnsupportedOperationException("No implementado para ascendente");
	}

	private static Programa descendente(String in) throws ParseException, FileNotFoundException {
		Reader input = new InputStreamReader(new FileInputStream(in));
		ConstructorAST constructorast = new ConstructorAST(input);
		return constructorast.ProgramaP();
	}

}
