package tiny1.asint.nodos.expresiones;

import java.util.Objects;

import tiny1.procesamientos.Procesador;

public class ExpresionesMuchas extends Expresiones {

    private Expresiones expresiones;
    private Expresion expresion;

    public ExpresionesMuchas(Expresiones expresiones, Expresion expresion) {
        this.expresiones = Objects.requireNonNull(expresiones);
        this.expresion = Objects.requireNonNull(expresion);
    }

    public Expresiones expresiones() {
        return expresiones;
    }

    public Expresion expresion() {
        return expresion;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
