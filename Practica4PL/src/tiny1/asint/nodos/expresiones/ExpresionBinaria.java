package tiny1.asint.nodos.expresiones;

import java.util.Objects;

public abstract class ExpresionBinaria extends Expresion {

    private final Expresion arg0;
    private final Expresion arg1;

    public Expresion arg0() {
        return arg0;
    }

    public Expresion arg1() {
        return arg1;
    }

    protected ExpresionBinaria(int prioridad, Expresion arg0, Expresion arg1) {
        super(prioridad);
        this.arg0 = Objects.requireNonNull(arg0);
        this.arg1 = Objects.requireNonNull(arg1);
    }
}
