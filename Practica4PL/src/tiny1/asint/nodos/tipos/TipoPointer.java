package tiny1.asint.nodos.tipos;

import tiny1.procesamientos.Procesador;

public class TipoPointer implements Tipo {
    private Tipo tipoBase;

    public TipoPointer(Tipo tipo) { this.tipoBase = tipo; }

    public Tipo tipoBase() { return tipoBase; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }

    @Override
    public boolean equals(Object obj) {
        if (!this.getClass().equals(obj.getClass()))
            return false;
        TipoPointer otro = (TipoPointer) obj;
        return this.tipoBase.equals(otro.tipoBase);
    }
}
