package tiny0.asint.nodos;

import tiny0.procesamientos.Procesador;

public class InstrMuchas implements Instrucciones {
    private Instrucciones instrucciones;
    private Instruccion instruccion;

    public InstrMuchas(Instrucciones instrucciones, Instruccion instruccion) {
        this.instrucciones = instrucciones;
        this.instruccion = instruccion;
    }

    public Instrucciones instrucciones() { return instrucciones; }
    public Instruccion instruccion() { return instruccion; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
