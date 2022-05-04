package tiny0.asint.nodos.expresiones.basicas;

import tiny0.procesamientos.Procesador;

public class True extends ExpresionBasica {

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
