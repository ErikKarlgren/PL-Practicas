package tiny1.asint.nodos.instrucciones;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class InstruccionWhile extends Instruccion {

    private final Expresion expresion;
    private final InstruccionesOpt instruccionesOpt;

    public InstruccionWhile(Expresion expresion, InstruccionesOpt instruccionesOpt) {
        this.expresion = Objects.requireNonNull(expresion);
        this.instruccionesOpt = Objects.requireNonNull(instruccionesOpt);
    }

    public Expresion exp() {
        return expresion;
    }

    public InstruccionesOpt instrsOpt() {
        return instruccionesOpt;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
