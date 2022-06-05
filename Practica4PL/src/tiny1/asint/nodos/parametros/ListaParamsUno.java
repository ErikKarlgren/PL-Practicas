package tiny1.asint.nodos.parametros;

import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class ListaParamsUno extends ListaParams {

    private Parametro parametro;

    public ListaParamsUno(Parametro parametro) {
        this.parametro = Objects.requireNonNull(parametro);
    }

    public Parametro param() {
        return parametro;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }

}
