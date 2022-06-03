package tiny1.asint.nodos.campos;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.TieneTamanio;

public abstract class Campos extends Nodo implements TieneTamanio {
    // Asignación de espacio
    private int tamanio;

    protected Campos() {
        tamanio = -1;
    }

    @Override
    public void setTamanio(int tamanio) {
        if (!tamanioAsignado()) {
            this.tamanio = tamanio;
        } else {
            throw new IllegalStateException("El tamaño ya ha sido asignado");
        }
    }

    @Override
    public int tamanio() {
        if (!tamanioAsignado())
            throw new IllegalStateException("El tamaño no ha sido asignado");
        return tamanio;
    }

    @Override
    public boolean tamanioAsignado() {
        return tamanio != -1;
    }
}
