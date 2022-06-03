package tiny1.asint.nodos.instrucciones;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class InstruccionNew extends Instruccion {

    private final Expresion expresion;

    public InstruccionNew(Expresion expresion) {
        this.expresion = Objects.requireNonNull(expresion);
    }

    public Expresion expresion() {
        return expresion;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
