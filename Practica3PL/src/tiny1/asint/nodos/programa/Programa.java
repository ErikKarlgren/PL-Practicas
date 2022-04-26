package tiny1.asint.nodos.programa;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.instrucciones.Instrucciones;

public interface Programa extends Nodo {
    public Instrucciones instrucciones();
}
