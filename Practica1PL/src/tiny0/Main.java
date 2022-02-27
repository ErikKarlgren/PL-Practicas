package tiny0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
  public static void main(String[] args) throws FileNotFoundException, IOException {
    String file;

    if (args.length > 0)
      file = args[0];
    else
      file = "./Practica1PL/bin/tiny0/input.txt";

    Reader input = new InputStreamReader(new FileInputStream(file));
    AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
    UnidadLexica unidad;
    do {
      unidad = al.sigToken();
      System.out.println(unidad);
    } while (unidad.clase() != ClaseLexica.EOF);
  }
}
