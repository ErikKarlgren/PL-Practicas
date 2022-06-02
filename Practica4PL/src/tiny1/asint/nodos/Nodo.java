package tiny1.asint.nodos;

import tiny1.asint.nodos.tipos.Tipo;
import tiny1.procesamientos.Procesador;

public abstract class Nodo {
    private Tipo tipo;

    public abstract void procesa(Procesador p);

    public Nodo vinculo() {
        throw new UnsupportedOperationException("Este nodo no puede tener un vínculo");
    }

    public void setVinculo(Nodo nodo) {
        throw new UnsupportedOperationException("Este nodo no puede tener un vínculo");
    }

    public void setTipo(Tipo tipo) {
        if (tipo != null) {
            throw new IllegalStateException("No se puede cambiar el tipo de un nodo que ya tiene tipo");
        }
        this.tipo = tipo;
    }

    final public Tipo getTipo() {
        return tipo;
    }

    public boolean esTipo() {
        return false;
    }
}
