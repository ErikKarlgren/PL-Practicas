package tiny1.asint.nodos.instrucciones;

import tiny1.procesamientos.Procesador;

public class InstrMuchas extends Instrucciones {

    private final Instrucciones instrucciones;
    private final Instruccion instruccion;

    public InstrMuchas(Instrucciones instrucciones, Instruccion instruccion) {
        this.instrucciones = instrucciones;
        this.instruccion = instruccion;
    }

    public Instrucciones instrucciones() {
        return instrucciones;
    }

    public Instruccion instruccion() {
        return instruccion;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
