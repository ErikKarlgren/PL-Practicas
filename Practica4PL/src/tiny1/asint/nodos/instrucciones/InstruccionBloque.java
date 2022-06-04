package tiny1.asint.nodos.instrucciones;

import tiny1.asint.nodos.bloques.Bloque;
import tiny1.asint.nodos.interfaces.TieneTamanio;
import tiny1.asint.nodos.otros.LazyFinalInt;
import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class InstruccionBloque extends Instruccion implements TieneTamanio {

    private final Bloque bloque;

    // Asignación de espacio
    private final LazyFinalInt tamanio;

    public InstruccionBloque(Bloque bloque) {
        this.bloque = Objects.requireNonNull(bloque);
        this.tamanio = new LazyFinalInt();
    }

    public Bloque bloque() {
        return bloque;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }

    @Override
    public LazyFinalInt tamanio() {
        return tamanio;
    }
}
