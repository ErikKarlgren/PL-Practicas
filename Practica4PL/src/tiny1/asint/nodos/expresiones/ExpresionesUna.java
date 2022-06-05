package tiny1.asint.nodos.expresiones;

import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class ExpresionesUna extends Expresiones {

    private final Expresion exp;

    public ExpresionesUna(Expresion exp) {
        this.exp = Objects.requireNonNull(exp);
    }

    public Expresion exp() {
        return exp;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
