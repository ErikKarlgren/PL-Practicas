package tiny1.errors;

import tiny1.alex.UnidadLexica;

public class GestionErroresTiny {
   public void errorLexico(int fila, int columna, String lexema) {
     System.out.println("ERROR fila "+fila+", columna "+columna+": Caracter inexperado: "+lexema); 
     System.exit(1);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     System.out.println("ERROR fila "+unidadLexica.fila()+", columna "+unidadLexica.columna()+" : Elemento inexperado "+unidadLexica.value);
     System.exit(1);
   }
}
