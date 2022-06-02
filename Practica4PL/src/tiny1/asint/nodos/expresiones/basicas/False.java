package tiny1.asint.nodos.expresiones.basicas;

import tiny1.procesamientos.Procesador;

public class False extends ExpresionBasica {

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
