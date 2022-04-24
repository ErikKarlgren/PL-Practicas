package tiny0.asint.nodos.expresiones;

public abstract class ExpresionUnaria implements Expresion {
    private Expresion arg;

    public Expresion arg() { return arg; }

    protected ExpresionUnaria(Expresion arg) {
        this.arg = arg;
    }
}
