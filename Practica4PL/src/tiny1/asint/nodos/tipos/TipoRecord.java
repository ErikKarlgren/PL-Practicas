package tiny1.asint.nodos.tipos;

import tiny1.asint.nodos.campos.Campos;
import tiny1.procesamientos.Procesador;

public class TipoRecord extends Tipo {
    private Campos campos;

    public TipoRecord(Campos campos) { this.campos = campos; }

    public Campos campos() { return campos; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }

    public boolean equals(Object obj) {
        if (!this.getClass().equals(obj.getClass()))
            return false;
        TipoRecord otro = (TipoRecord) obj;
        return this.campos == otro.campos;
    }

    @Override
    public String toString() {
        return "record (y campos)";
    }
}
