package tiny1.asint.nodos.expresiones;

import java.util.Objects;

import tiny1.asint.nodos.Nodo;

public abstract class Expresion extends Nodo {

    private final int prioridad;

    protected Expresion(int prioridad) {
        this.prioridad = Objects.requireNonNull(prioridad);
    }

    final public int prioridad() {
        return prioridad;
    }

    public boolean esDesignador() {
        return false;
    }
}
