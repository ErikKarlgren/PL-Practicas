package tiny1.asint.nodos.bloques;

import tiny1.procesamientos.Procesador;

public class BloqueVacio extends Bloque {

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
