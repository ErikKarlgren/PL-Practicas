package tiny1.asint.nodos.programa;

import tiny1.asint.nodos.instrucciones.Instrucciones;
import tiny1.procesamientos.Procesador;

public class ProgramaSinDecs implements Programa {
    private Instrucciones instrucciones;

    public ProgramaSinDecs(Instrucciones instrucciones) {
        this.instrucciones = instrucciones;
    }

    @Override
    public Instrucciones instrucciones() { return instrucciones;}

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
