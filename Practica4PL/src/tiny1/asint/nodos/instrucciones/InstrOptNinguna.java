package tiny1.asint.nodos.instrucciones;

import tiny1.procesamientos.Procesador;

public class InstrOptNinguna extends InstruccionesOpt {

    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
