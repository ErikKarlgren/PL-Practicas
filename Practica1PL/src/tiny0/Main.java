package tiny0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
   public static void main(String[] args) throws FileNotFoundException, IOException {
     Reader input = new InputStreamReader(new FileInputStream("./Practica1PL/ñsrc/tiny0/input.txt"));
     AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
     UnidadLexica unidad;
     do {
       unidad = al.sigToken();
       System.out.println(unidad);
     }
     while (unidad.clase() != ClaseLexica.EOF);
    }        
} 
