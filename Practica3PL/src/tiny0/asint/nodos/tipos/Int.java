package tiny0.asint.nodos.tipos;

import tiny0.procesamientos.Procesador;

public class Int implements Tipo {
    @Override
    public void procesa(Procesador p) { p.procesa(this);}
}
