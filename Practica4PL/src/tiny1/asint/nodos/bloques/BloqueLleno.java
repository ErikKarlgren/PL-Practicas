package tiny1.asint.nodos.bloques;

import java.util.Objects;

import tiny1.asint.nodos.programa.Programa;
import tiny1.procesamientos.Procesador;

public class BloqueLleno extends Bloque {

    private final Programa programa;

    public BloqueLleno(Programa programa) {
        this.programa = Objects.requireNonNull(programa);
    }

    public Programa programa() {
        return programa;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
