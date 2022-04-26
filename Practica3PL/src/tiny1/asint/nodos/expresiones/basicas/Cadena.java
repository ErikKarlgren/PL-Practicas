package tiny1.asint.nodos.expresiones.basicas;

import tiny1.asint.nodos.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class Cadena extends ExpresionBasica {
    private StringLocalizado string;

    public Cadena(StringLocalizado cadena) {
        this.string = cadena;
    }

    public StringLocalizado cadena() { return string; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
