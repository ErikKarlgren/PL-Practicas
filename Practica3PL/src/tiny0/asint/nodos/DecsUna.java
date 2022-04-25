package tiny0.asint.nodos;

import tiny0.procesamientos.Procesador;

public class DecsUna implements Declaraciones {
    private Declaracion declaracion;

    public DecsUna(Declaracion declaracion) {
        this.declaracion = declaracion;
    }

    public Declaracion declaracion() { return declaracion; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
