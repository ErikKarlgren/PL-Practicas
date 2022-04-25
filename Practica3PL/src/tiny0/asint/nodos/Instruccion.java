package tiny0.asint.nodos;

import tiny0.asint.nodos.expresiones.Expresion;
import tiny0.procesamientos.Procesador;

public class Instruccion implements Nodo {
    private StringLocalizado string;
    private Expresion expresion;

    public Instruccion(StringLocalizado string, Expresion expresion) {
        this.string = string;
        this.expresion = expresion;
    }

    public StringLocalizado string() { return string; }

    public Expresion expresion() { return expresion; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
