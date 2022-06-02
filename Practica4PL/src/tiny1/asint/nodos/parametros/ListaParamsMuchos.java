package tiny1.asint.nodos.parametros;

import tiny1.procesamientos.Procesador;

public class ListaParamsMuchos extends ListaParams {
    private ListaParams listaParametros;
    private Parametro parametro;

    public ListaParamsMuchos(ListaParams listaParametros, Parametro parametro) {
        this.listaParametros = listaParametros;
        this.parametro = parametro;
    }

    public ListaParams listaParametros() { return listaParametros; }

    public Parametro parametro() { return parametro; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
    
}
