package tiny1.asint.nodos.programa;

import tiny1.asint.nodos.instrucciones.Instrucciones;
import tiny1.procesamientos.Procesador;

public class ProgramaSinDecs extends Programa {

    public ProgramaSinDecs(Instrucciones instrucciones) {
        super(instrucciones);
    }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
