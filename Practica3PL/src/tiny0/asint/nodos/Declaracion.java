package tiny0.asint.nodos;

import tiny0.asint.nodos.expresiones.Tipo;
import tiny0.procesamientos.Procesador;

public class Declaracion implements Nodo {
    private Tipo tipo;
    private StringLocalizado string;

    public Declaracion(Tipo tipo, StringLocalizado string) {
        this.tipo = tipo;
        this.string = string;
    }

    public Tipo tipo() { return tipo; }

    public StringLocalizado id() { return string; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
