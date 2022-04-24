package tiny0.asint.nodos.expresiones;

public abstract class ExpresionBinaria implements Expresion {
    private Expresion arg0;
    private Expresion arg1;

    public Expresion arg0() { return arg0; }
    public Expresion arg1() { return arg1; }

    protected ExpresionBinaria(Expresion arg0, Expresion arg1) {
        this.arg0 = arg0;
        this.arg1 = arg1;
    }
}
