package tiny1.procesamientos;

import tiny1.asint.nodos.Nodo;

public interface ProcesadorConRetorno<T> extends Procesador {
    
    default public T procesar(Nodo nodo) {
        nodo.procesa(this);
        return resultado();
    }

    T resultado();
}
