package tiny1.asint.nodos.declaraciones;

import tiny1.asint.StringLocalizado;
import tiny1.asint.nodos.tipos.Tipo;
import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class DecVar extends Declaracion {

    private final Tipo tipo;
    private final StringLocalizado string;

    public DecVar(Tipo tipo, StringLocalizado string) {
        this.tipo = Objects.requireNonNull(tipo);
        this.string = Objects.requireNonNull(string);
    }

    public Tipo tipo() {
        return tipo;
    }

    public StringLocalizado id() {
        return string;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
