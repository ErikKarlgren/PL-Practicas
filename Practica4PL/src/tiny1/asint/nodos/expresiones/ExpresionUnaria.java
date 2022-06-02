package tiny1.asint.nodos.expresiones;

public abstract class ExpresionUnaria extends Expresion {
    private final Expresion arg;

    public Expresion arg() { return arg; }

    protected ExpresionUnaria(int prioridad, Expresion arg) {
        super(prioridad);
        this.arg = arg;
    }
}
