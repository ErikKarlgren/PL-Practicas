package tiny1.asint.nodos.expresiones;

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
        this.arg0 = arg0;
        this.arg1 = arg1;
    }
}
