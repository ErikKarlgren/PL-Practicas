package tiny1.asint.nodos.tipos;

import java.util.Objects;

import tiny1.asint.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class TipoArray extends Tipo {

    private Tipo tipoBase;
    private int longitud;

    public TipoArray(StringLocalizado longitud, Tipo tipo) {
        this.tipoBase = Objects.requireNonNull(tipo);
        this.longitud = Integer.parseInt(longitud.toString());
    }

    public TipoArray(int longitud, Tipo tipo) {
        this.tipoBase = Objects.requireNonNull(tipo);
        this.longitud = Objects.requireNonNull(longitud);
    }

    public Tipo tipoBase() { return tipoBase; }

    public int longitud() { return longitud; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        TipoArray otro = (TipoArray) obj;
        return this.tipoBase.equals(otro.tipoBase) && this.longitud == otro.longitud;
    }

    @Override
    public String toString() {
        return String.format("array [%d] of %s", longitud, tipoBase);
    }
}
