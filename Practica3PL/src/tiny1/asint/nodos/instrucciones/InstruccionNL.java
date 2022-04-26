package tiny1.asint.nodos.instrucciones;

import tiny1.procesamientos.Procesador;

public class InstruccionNL implements Instruccion {

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
