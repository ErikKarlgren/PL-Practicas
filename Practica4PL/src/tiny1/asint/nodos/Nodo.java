package tiny1.asint.nodos;

import tiny1.asint.nodos.tipos.Tipo;
import tiny1.procesamientos.Procesador;

public interface Nodo {
    public void procesa(Procesador p);

    default public Nodo vinculo() {
        throw new UnsupportedOperationException();
    }

    default public void setVinculo(Nodo nodo) {
        throw new UnsupportedOperationException();
    }

    public void setTipo(Tipo tipo);

    public Tipo getTipo();

    default public boolean esTipo() {
        return false;
    }
}
