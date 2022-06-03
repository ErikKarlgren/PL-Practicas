package tiny1.asint.nodos.instrucciones;

import tiny1.asint.nodos.TieneTamanio;
import tiny1.asint.nodos.bloques.Bloque;
import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class InstruccionBloque extends Instruccion implements TieneTamanio {

    private final Bloque bloque;

    // Asignación de espacio
    private int tamanio;

    public InstruccionBloque(Bloque bloque) {
        this.bloque = Objects.requireNonNull(bloque);
        this.tamanio = -1;
    }

    public Bloque bloque() {
        return bloque;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
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
        return this.tamanio != -1;
    }
}
