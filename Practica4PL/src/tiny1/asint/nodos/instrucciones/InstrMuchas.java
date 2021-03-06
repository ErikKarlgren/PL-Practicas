package tiny1.asint.nodos.instrucciones;

import java.util.Objects;

import tiny1.procesamientos.Procesador;

public class InstrMuchas extends Instrucciones {

    private final Instrucciones instrucciones;
    private final Instruccion instruccion;

    public InstrMuchas(Instrucciones instrucciones, Instruccion instruccion) {
        this.instrucciones = Objects.requireNonNull(instrucciones);
        this.instruccion = Objects.requireNonNull(instruccion);
    }

    public Instrucciones instrs() {
        return instrucciones;
    }

    public Instruccion instr() {
        return instruccion;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
