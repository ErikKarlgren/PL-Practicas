package tiny1.asint.nodos.expresiones;

import tiny1.procesamientos.Procesador;

public class ExpresionesUna extends Expresiones {
    private final Expresion exp;

    public ExpresionesUna(Expresion exp) {
        this.exp = exp;
    }

    public Expresion expresion() {
        return exp;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }

}
