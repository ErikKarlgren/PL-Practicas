package tiny1.asint.nodos.campos;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.interfaces.TieneTamanio;
import tiny1.asint.nodos.otros.LazyFinalInt;

public abstract class Campos extends Nodo implements TieneTamanio {

    // Asignación de espacio
    private final LazyFinalInt tamanio;

    protected Campos() {
        tamanio = new LazyFinalInt();
    }

    @Override
    public LazyFinalInt tamanio() {
        return tamanio;
    }
}
