package tiny1.asint.nodos.tipos;

import tiny1.asint.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class TipoArray extends Tipo {
    private Tipo tipoBase;
    private int longitud;

    public TipoArray(StringLocalizado longitud, Tipo tipo) {
        this.tipoBase = tipo;
        this.longitud = Integer.parseInt(longitud.toString());
    }

    public TipoArray(int longitud, Tipo tipo) {
        this.tipoBase = tipo;
        this.longitud = longitud;
    }

    public Tipo tipoBase() { return tipoBase; }

    public int longitud() { return longitud; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }

    @Override
    public boolean equals(Object obj) {
        if (!this.getClass().equals(obj.getClass()))
            return false;
        TipoArray otro = (TipoArray) obj;
        return this.tipoBase.equals(otro.tipoBase) && this.longitud == otro.longitud;
    }

    @Override
    public String toString() {
        return String.format("array [%d] of %s", longitud, tipoBase);
    }
}
