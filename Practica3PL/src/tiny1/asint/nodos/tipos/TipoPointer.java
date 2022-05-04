package tiny1.asint.nodos.tipos;

import tiny1.procesamientos.Procesador;

public class TipoPointer implements Tipo {
    private Tipo tipoBase;

    public TipoPointer(Tipo tipo) { this.tipoBase = tipo; }

    public Tipo tipoBase() { return tipoBase; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
