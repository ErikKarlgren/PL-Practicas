package tiny1.asint.nodos.declaraciones;

import java.util.Objects;

import tiny1.asint.StringLocalizado;
import tiny1.asint.nodos.bloques.Bloque;
import tiny1.asint.nodos.parametros.ListaParams;
import tiny1.procesamientos.Procesador;

public class DecProc extends Declaracion {

    private final StringLocalizado string;
    private final ListaParams listaParametros;
    private final Bloque bloque;

    public DecProc(StringLocalizado string, ListaParams lp, Bloque bloque) {
        this.string = Objects.requireNonNull(string);
        this.listaParametros = Objects.requireNonNull(lp);
        this.bloque = Objects.requireNonNull(bloque);
    }

    public StringLocalizado id() {
        return string;
    }

    public ListaParams listaParams() {
        return listaParametros;
    }

    public Bloque bloque() {
        return bloque;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
