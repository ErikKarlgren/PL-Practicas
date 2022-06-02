package tiny1.asint.nodos.instrucciones;

import tiny1.procesamientos.Procesador;

public class InstrOptMuchas extends InstruccionesOpt {

    private final Instrucciones instrucciones;

    public InstrOptMuchas(Instrucciones instrucciones) {
        this.instrucciones = instrucciones;
    }

    public Instrucciones instrucciones() {
        return instrucciones;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
