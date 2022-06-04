package tiny1.asint.nodos.tipos;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.interfaces.TieneTamanio;
import tiny1.asint.nodos.otros.LazyFinalInt;

public abstract class Tipo extends Nodo implements TieneTamanio {

    // Asignación de espacio
    private final LazyFinalInt tamanio;

    protected Tipo() {
        tamanio = new LazyFinalInt();
    }

    public boolean isOk() {
        return true;
    }

    @Override
    public boolean esTipo() {
        return true;
    }

    @Override
    public LazyFinalInt tamanio() {
        return tamanio;
    }

    @Override
    public boolean equals(Object obj){
        return obj != null && this.getClass().equals(obj.getClass());
    }
}
