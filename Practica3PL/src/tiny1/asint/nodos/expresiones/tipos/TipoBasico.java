package tiny1.asint.nodos.expresiones.tipos;

import tiny1.asint.nodos.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class TipoBasico implements Tipo {
    private StringLocalizado nombre;

    public TipoBasico(StringLocalizado tipo) { this.nombre = tipo; }

    public StringLocalizado nombre() { return nombre; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
