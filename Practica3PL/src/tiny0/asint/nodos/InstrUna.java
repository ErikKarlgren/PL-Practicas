package tiny0.asint.nodos;

import tiny0.procesamientos.Procesador;

public class InstrUna implements Instrucciones {
    private Instruccion instruccion;

    public InstrUna(Instruccion instruccion) {
        this.instruccion = instruccion;
    }

    public Instruccion instruccion() { return instruccion; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
    
}
