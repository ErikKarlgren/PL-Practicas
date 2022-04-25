package tiny0.asint;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import tiny0.ast.ConstructorAST;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Expected a file to be specified");
            System.exit(1);
        }

        for (String file : args) {
            try (Reader input = new InputStreamReader(new FileInputStream(file))) {
                ConstructorAST asint = new ConstructorAST(input);
                asint.programaP();
                System.out.println("Finished reading " + file);
            } catch (IOException e) {
                System.err.println("Error while reading file " + file);
                e.printStackTrace();
            }
        }
    }
}

