package tiny1.asint.nodos.parametros;

import tiny1.procesamientos.Procesador;

public class ParamsSin implements ListaParams {
    
    @Override
    public void procesa(Procesador p) { p.procesa(this);}
}
