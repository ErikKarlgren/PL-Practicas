package tiny1.asint.nodos.programa;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.instrucciones.Instrucciones;

public abstract class Programa extends Nodo {
    private Instrucciones instrucciones;

    protected Programa(Instrucciones instrucciones) {
        this.instrucciones = instrucciones;
    }

    public Instrucciones instrucciones() {
        return instrucciones;
    }
}
