package tiny0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

import tiny0.asint.nodos.Programa;
import tiny0.ast.ConstructorAST;
import tiny0.procesamientos.Impresion;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            System.err.println("Expected a file to be specified");
            System.exit(1);
        }
        for (String file : args) {
            Programa prog = descendenteManual(file);
            prog.procesa(new Impresion(System.out));
        }
    }

    private static Programa descendenteManual(String in) throws FileNotFoundException {
        Reader input = new InputStreamReader(new FileInputStream(in));
        ConstructorAST constructor = new ConstructorAST(input);
        return constructor.programaP();
    }
}
