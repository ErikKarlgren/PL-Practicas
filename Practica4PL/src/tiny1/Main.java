package tiny1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

import tiny1.alex.AnalizadorLexicoTiny;
import tiny1.asint.nodos.programa.Programa;
import tiny1.ast_descendente.ParseException;
import tiny1.procesamientos.Impresion;
import tiny1.procesamientos.Procesador;
import tiny1.procesamientos.chequeo_tipos.ChequeoTipos;
import tiny1.procesamientos.vinculacion.Vinculacion;

public class Main {
	public static void main(String[] args) throws Exception {
		try {
			Programa prog = crearPrograma(args);

			//procesar(prog, new Impresion(System.out));
			System.out.println("Vinculando...");
			procesar(prog, new Vinculacion());
			System.out.println("Chequeando tipos...");
			procesar(prog, new ChequeoTipos());

		} catch (Exception e) {
			//System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static Programa crearPrograma(String[] args) throws Exception {
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

	private static Programa ascendente(String in) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(in));
		AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		tiny1.ast_ascendente.ConstructorAST constructorast = new tiny1.ast_ascendente.ConstructorAST(alex);
		return (Programa) constructorast.parse().value;
	}

	private static Programa descendente(String in) throws ParseException, FileNotFoundException {
		Reader input = new InputStreamReader(new FileInputStream(in));
		tiny1.ast_descendente.ConstructorAST constructorast = new tiny1.ast_descendente.ConstructorAST(input);
		return constructorast.ProgramaP();
	}

	private static void procesar(Programa prog, Procesador proc) {
		prog.procesa(proc);
		if (proc.foundErrors())
			System.exit(1);
	}
}
