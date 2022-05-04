package tiny0.asint.nodos.declaraciones;

import tiny0.asint.StringLocalizado;
import tiny0.asint.nodos.Nodo;
import tiny0.asint.nodos.tipos.Tipo;
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
