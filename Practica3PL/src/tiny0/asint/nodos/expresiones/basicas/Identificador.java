package tiny0.asint.nodos.expresiones.basicas;

import tiny0.asint.nodos.StringLocalizado;
import tiny0.procesamientos.Procesador;

public class Identificador extends ExpresionBasica {
    private StringLocalizado id;

    public Identificador(StringLocalizado id) { this.id = id; }

    public StringLocalizado id() { return id; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
