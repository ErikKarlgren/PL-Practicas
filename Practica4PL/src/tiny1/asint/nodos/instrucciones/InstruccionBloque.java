package tiny1.asint.nodos.instrucciones;

import tiny1.asint.nodos.bloques.Bloque;
import tiny1.asint.nodos.otros.LazyFinalInt;
import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class InstruccionBloque extends Instruccion  {

    private final Bloque bloque;

    public InstruccionBloque(Bloque bloque) {
        this.bloque = Objects.requireNonNull(bloque);
    }

    public Bloque bloque() {
        return bloque;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
