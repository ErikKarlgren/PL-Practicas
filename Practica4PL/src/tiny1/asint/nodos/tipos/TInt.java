package tiny1.asint.nodos.tipos;

import tiny1.procesamientos.Procesador;

public class TInt extends Tipo {

    @Override
    public void procesa(Procesador p) { p.procesa(this);}
}
