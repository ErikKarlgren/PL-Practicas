package tiny1.asint.nodos.instrucciones;

import tiny1.procesamientos.Procesador;

public class InstrUna extends Instrucciones {

    private final Instruccion instruccion;

    public InstrUna(Instruccion instruccion) {
        this.instruccion = instruccion;
    }

    public Instruccion instruccion() {
        return instruccion;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
