package tiny1.asint.nodos.tipos;

import tiny1.asint.nodos.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class TipoNuevo implements Tipo {
    private StringLocalizado nombre;

    public TipoNuevo(StringLocalizado nombre) {
        this.nombre = nombre;
    }

    public StringLocalizado nombre() { return nombre; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
