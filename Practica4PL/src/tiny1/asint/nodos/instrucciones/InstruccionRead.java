package tiny1.asint.nodos.instrucciones;

import java.util.Objects;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.procesamientos.Procesador;

public class InstruccionRead extends Instruccion {

    private final Expresion expresion;

    public InstruccionRead(Expresion expresion) {
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
