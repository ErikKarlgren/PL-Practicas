package tiny1.asint.nodos.tipos;

import tiny1.asint.nodos.Nodo;

public abstract class Tipo extends Nodo {

    // Asignación de espacio
    private int tamanio;

    protected Tipo() {
        tamanio = -1;
    }

    public boolean isOk() {
        return true;
    }

    @Override
    public boolean esTipo() {
        return true;
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
