package tiny1.asint.nodos.tipos;

import tiny1.asint.nodos.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class TipoNuevo implements Tipo {
    private StringLocalizado string;

    public TipoNuevo(StringLocalizado string) {
        this.string = string;
    }

    public StringLocalizado id() { return string; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
