package tiny1.alex;

import java_cup.runtime.Symbol;
import tiny1.asint.StringLocalizado;

public class UnidadLexica extends Symbol {
   private int fila;
   private int col;
   private StringLocalizado lexema;

   public UnidadLexica(int fila, int col, int clase, String lexema) {
      super(clase, null);
      this.fila = fila;
      this.col = col;
      this.lexema = new StringLocalizado(lexema, fila, col);
   }

   public int clase () {return sym;}
   public StringLocalizado lexema() {return lexema;}
   public int fila() {return fila;}
   public int columna() {return col;}
}
