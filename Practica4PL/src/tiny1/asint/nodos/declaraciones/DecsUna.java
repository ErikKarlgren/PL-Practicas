package tiny1.asint.nodos.declaraciones;

import tiny1.procesamientos.Procesador;

public class DecsUna extends Declaraciones {

    private final Declaracion declaracion;

    public DecsUna(Declaracion declaracion) {
        this.declaracion = declaracion;
    }

    public Declaracion declaracion() {
        return declaracion;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
