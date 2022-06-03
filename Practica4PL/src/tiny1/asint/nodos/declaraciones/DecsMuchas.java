package tiny1.asint.nodos.declaraciones;

import java.util.Objects;

import tiny1.procesamientos.Procesador;

public class DecsMuchas extends Declaraciones {

    private final Declaraciones declaraciones;
    private final Declaracion declaracion;

    public DecsMuchas(Declaraciones declaraciones, Declaracion declaracion) {
        this.declaraciones = Objects.requireNonNull(declaraciones);
        this.declaracion = Objects.requireNonNull(declaracion);
    }

    public Declaraciones declaraciones() {
        return declaraciones;
    }

    public Declaracion declaracion() {
        return declaracion;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
