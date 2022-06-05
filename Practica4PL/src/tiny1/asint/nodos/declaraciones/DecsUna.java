package tiny1.asint.nodos.declaraciones;

import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class DecsUna extends Declaraciones {

    private final Declaracion declaracion;

    public DecsUna(Declaracion declaracion) {
        this.declaracion = Objects.requireNonNull(declaracion);
    }

    public Declaracion dec() {
        return declaracion;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
