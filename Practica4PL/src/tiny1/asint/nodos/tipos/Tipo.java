package tiny1.asint.nodos.tipos;

import tiny1.asint.nodos.Nodo;

public interface Tipo extends Nodo {

    @Override
    default Tipo getTipo() {
        return this;
    }

    @Override
    default void setTipo(Tipo tipo) {
        throw new UnsupportedOperationException("No se puede cambiar el tipo de un tipo");
    }

    @Override
    default boolean esTipo() {
        return true;
    }

}
