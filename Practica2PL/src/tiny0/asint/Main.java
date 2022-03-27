package tiny0.asint;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Expected a file to be specified");
            System.exit(1);
        }

        for (String file : args) {
            try (Reader input = new InputStreamReader(new FileInputStream(file))) {
                AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(input);
                asint.ProgramaP();
                System.out.println("Finished reading " + file);
            } catch (IOException e) {
                System.err.println("Error while reading file " + file);
                e.printStackTrace();
            }
        }
    }
}

