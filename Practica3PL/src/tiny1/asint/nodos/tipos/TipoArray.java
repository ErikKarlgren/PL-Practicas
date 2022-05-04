package tiny1.asint.nodos.tipos;

import tiny1.asint.nodos.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class TipoArray implements Tipo {
    private Tipo tipoBase;
    private int longitud;

    public TipoArray(StringLocalizado longitud, Tipo tipo) {
        this.tipoBase = tipo;
        this.longitud = Integer.parseInt(longitud.toString());
    }

    public Tipo tipoBase() { return tipoBase; }

    public int longitud() { return longitud; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
