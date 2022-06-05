package tiny1.asint.nodos.instrucciones;

import java.util.Objects;

import tiny1.procesamientos.Procesador;

public class InstrOptMuchas extends InstruccionesOpt {

    private final Instrucciones instrucciones;

    public InstrOptMuchas(Instrucciones instrucciones) {
        this.instrucciones = Objects.requireNonNull(instrucciones);
    }

    public Instrucciones instrs() {
        return instrucciones;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
