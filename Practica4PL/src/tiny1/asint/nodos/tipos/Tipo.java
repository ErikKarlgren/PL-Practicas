package tiny1.asint.nodos.tipos;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.TieneTamanio;

public abstract class Tipo extends Nodo implements TieneTamanio{

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
    public boolean tamanioAsignado(){
        return tamanio != -1;
    }
}
