package tiny1.asint.nodos.parametros;

import tiny1.procesamientos.Procesador;

public class ParamsCon implements ListaParams {
    private ListaParams lp;

    public ParamsCon(ListaParams lp) {
        this.lp = lp;
    }

    public ListaParams listaParams() { return lp; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
