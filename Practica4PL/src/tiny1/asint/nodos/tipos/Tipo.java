package tiny1.asint.nodos.tipos;

import tiny1.asint.nodos.Nodo;

public abstract class Tipo extends Nodo {

    public boolean isOk() {
        return true;
    }

    @Override
    public boolean esTipo() {
        return true;
    }
}
