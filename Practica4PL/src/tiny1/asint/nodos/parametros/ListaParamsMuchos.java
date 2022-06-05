package tiny1.asint.nodos.parametros;

import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class ListaParamsMuchos extends ListaParams {

    private ListaParams listaParametros;
    private Parametro parametro;

    public ListaParamsMuchos(ListaParams listaParametros, Parametro parametro) {
        this.listaParametros = Objects.requireNonNull(listaParametros);
        this.parametro = Objects.requireNonNull(parametro);
    }

    public ListaParams params() {
        return listaParametros;
    }

    public Parametro param() {
        return parametro;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }

}
