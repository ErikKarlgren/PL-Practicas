package tiny1.asint.nodos.instrucciones;

import java.util.Objects;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.procesamientos.Procesador;

public class InstruccionIfElse extends Instruccion {

    private final Expresion expresion;
    private final InstruccionesOpt instruccionesOptIf;
    private final InstruccionesOpt instruccionesOptElse;

    public InstruccionIfElse(Expresion expresion,
            InstruccionesOpt instruccionesOptIf,
            InstruccionesOpt instruccionesOptElse) {
        this.expresion = Objects.requireNonNull(expresion);
        this.instruccionesOptIf = Objects.requireNonNull(instruccionesOptIf);
        this.instruccionesOptElse = Objects.requireNonNull(instruccionesOptElse);
    }

    public Expresion exp() {
        return expresion;
    }

    public InstruccionesOpt instrsOptIf() {
        return instruccionesOptIf;
    }

    public InstruccionesOpt instrsOptElse() {
        return instruccionesOptElse;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
