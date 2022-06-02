package tiny1.asint.nodos.instrucciones;

import tiny1.asint.nodos.bloques.Bloque;
import tiny1.procesamientos.Procesador;

public class InstruccionBloque extends Instruccion {

    private final Bloque bloque;

    public InstruccionBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public Bloque bloque() {
        return bloque;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
