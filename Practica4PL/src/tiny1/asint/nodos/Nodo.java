package tiny1.asint.nodos;

import tiny1.procesamientos.Procesador;

public interface Nodo {
    public void procesa(Procesador p);

    default public Nodo vinculo() {
        throw new UnsupportedOperationException();
    }

    default public void setVinculo(Nodo nodo) {
        throw new UnsupportedOperationException();
    }
}
