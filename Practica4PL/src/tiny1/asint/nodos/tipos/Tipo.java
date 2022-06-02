package tiny1.asint.nodos.tipos;

import tiny1.asint.nodos.Nodo;

public abstract class Tipo extends Nodo {

    protected Tipo() {
        super.setTipo(this);
    }

    @Override
    public void setTipo(Tipo tipo) {
        throw new UnsupportedOperationException("No se puede cambiar el tipo de un tipo");
    }

    @Override
    public boolean esTipo() {
        return true;
    }
}
