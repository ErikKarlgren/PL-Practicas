package tiny1.asint.nodos.campos;

import tiny1.asint.nodos.Nodo;

public abstract class Campos extends Nodo {
    // Asignación de espacio
    private int tamanio;

    protected Campos() {
        tamanio = -1;
    }

    public void setTamanio(int tamanio) {
        if (tamanio == -1) {
            this.tamanio = tamanio;
        } else {
            throw new IllegalStateException("El tamaño ya ha sido asignado");
        }
    }

    public int tamanio() {
        if (tamanio == -1)
            throw new IllegalStateException("El tamaño no ha sido asignado");
        return tamanio;
    }
}
