package tiny1.asint.nodos.tipos;

import tiny1.asint.nodos.campos.Campos;
import tiny1.procesamientos.Procesador;

public class TipoRecord implements Tipo {
    private Campos campos;

    public TipoRecord(Campos campos) { this.campos = campos; }

    public Campos campos() { return campos; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
