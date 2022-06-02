package tiny1.procesamientos.chequeo_tipos;

import tiny1.asint.nodos.tipos.Tipo;
import tiny1.procesamientos.Procesador;

class TipoError extends Tipo {

    @Override
    public void procesa(Procesador p) {
        throw new UnsupportedOperationException("No procesable");
    }
}
