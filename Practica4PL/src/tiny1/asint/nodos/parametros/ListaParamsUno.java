package tiny1.asint.nodos.parametros;

import tiny1.procesamientos.Procesador;

public class ListaParamsUno extends ListaParams {
    private Parametro parametro;

    public ListaParamsUno(Parametro parametro) {
        this.parametro = parametro;
    }

    public Parametro parametro() { return parametro; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
    
}
