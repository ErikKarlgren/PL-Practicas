package tiny1.asint.nodos.parametros;

import tiny1.procesamientos.Procesador;

public class ParamsRealNinguna implements ParamsReal {

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
