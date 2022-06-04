package tiny1.asint.nodos.programa;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.instrucciones.Instrucciones;

import java.util.Objects;

public abstract class Programa extends Nodo {

    private Instrucciones instrucciones;

    protected Programa(Instrucciones instrucciones) {
        this.instrucciones = Objects.requireNonNull(instrucciones);
    }

    public Instrucciones instrucciones() {
        return instrucciones;
    }
}
