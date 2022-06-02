package tiny1.asint.nodos.instrucciones;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.procesamientos.Procesador;

public class InstruccionIfElse extends Instruccion {

    private final Expresion expresion;
    private final InstruccionesOpt instruccionesOptIf;
    private final InstruccionesOpt instruccionesOptElse;

    public InstruccionIfElse(Expresion expresion,
            InstruccionesOpt instruccionesOptIf,
            InstruccionesOpt instruccionesOptElse) {
        this.expresion = expresion;
        this.instruccionesOptIf = instruccionesOptIf;
        this.instruccionesOptElse = instruccionesOptElse;
    }

    public Expresion expresion() {
        return expresion;
    }

    public InstruccionesOpt instruccionesOptIf() {
        return instruccionesOptIf;
    }

    public InstruccionesOpt instruccionesOptElse() {
        return instruccionesOptElse;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
