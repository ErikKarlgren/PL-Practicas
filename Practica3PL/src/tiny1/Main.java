package tiny1;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import tiny1.asint.nodos.programa.Programa;
import tiny1.ast_descendente.ConstructorAST;
import tiny1.procesamientos.Impresion;

public class Main {
	public static void main(String[] args) throws Exception {
		Programa prog = null;
		if (args[0].equals("asc"))
			// prog = ejecuta_ascendente(args[1]);
			;
		else if (args[0].equals("desc"))
			prog = ejecuta_descendente(args[1]);

		prog.procesa(new Impresion(System.out));
		

	}

//   private static Programa ejecuta_ascendente(String in) throws Exception {       
//     Reader input = new InputStreamReader(new FileInputStream(in));
//     AnalizadorLexico alex = new AnalizadorLexico(input);
//     ConstructorAST constructorast = new ConstructorAST(alex);
//     return (Prog)constructorast.parse().value;
//  }
	private static Programa ejecuta_descendente(String in) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(in));
		ConstructorAST constructorast = new ConstructorAST(input);
		return constructorast.ProgramaP();
	}

}
