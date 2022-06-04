package tiny1.asint.nodos.expresiones;

import java.util.Objects;

public abstract class ExpresionUnaria extends Expresion {

    private final Expresion arg;

    public Expresion arg() { return arg; }

    protected ExpresionUnaria(int prioridad, Expresion arg) {
        super(prioridad);
        this.arg = Objects.requireNonNull(arg);
    }
}
