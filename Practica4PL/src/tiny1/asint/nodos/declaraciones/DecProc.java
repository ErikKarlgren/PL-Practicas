package tiny1.asint.nodos.declaraciones;

import java.util.Objects;

import tiny1.asint.StringLocalizado;
import tiny1.asint.nodos.bloques.Bloque;
import tiny1.asint.nodos.interfaces.TieneTamanio;
import tiny1.asint.nodos.otros.LazyFinalInt;
import tiny1.asint.nodos.parametros.ListaParams;
import tiny1.procesamientos.Procesador;

public class DecProc extends Declaracion implements TieneTamanio {

    private final StringLocalizado string;
    private final ListaParams listaParametros;
    private final Bloque bloque;

    // Asignación de espacio
    private LazyFinalInt nivel;
    private LazyFinalInt tamanio;

    public DecProc(StringLocalizado string, ListaParams lp, Bloque bloque) {
        this.string = Objects.requireNonNull(string);
        this.listaParametros = Objects.requireNonNull(lp);
        this.bloque = Objects.requireNonNull(bloque);
        this.nivel = new LazyFinalInt();
        this.tamanio = new LazyFinalInt();
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

    public LazyFinalInt nivel() {
        return nivel;
    }

    @Override
    public LazyFinalInt tamanio() {
        return tamanio;
    }
}
