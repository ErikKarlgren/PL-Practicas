package tiny1.asint.nodos.expresiones.basicas;

import java.util.Objects;

import tiny1.asint.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class Cadena extends ExpresionBasica {

    private final StringLocalizado string;

    public Cadena(StringLocalizado cadena) {
        this.string = Objects.requireNonNull(cadena);
    }

    public StringLocalizado cadena() {
        return string;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
