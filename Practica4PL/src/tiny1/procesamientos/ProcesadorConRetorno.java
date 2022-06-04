package tiny1.procesamientos;

import tiny1.asint.nodos.Nodo;

public abstract class ProcesadorConRetorno<T> extends Procesador {

    public T procesar(Nodo nodo) {
        nodo.procesa(this);
        return resultado();
    }

    protected abstract T resultado();
}
