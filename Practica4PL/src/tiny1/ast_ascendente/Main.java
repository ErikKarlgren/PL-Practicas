package tiny1.ast_ascendente;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import tiny1.alex.AnalizadorLexicoTiny;

public class Main {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.err.println("Expected a file to be specified");
			System.exit(1);
		}

		for (String file : args) {
			try (Reader input = new InputStreamReader(new FileInputStream(file))) {
				AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
				AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(alex);
				asint.parse();
				System.out.println("Finished reading " + file);
			} catch (IOException e) {
				System.err.println("Error while reading file " + file);
				e.printStackTrace();
			}
		}
	}
}
