package tiny1.asint.nodos.programa;

import tiny1.asint.nodos.declaraciones.Declaraciones;
import tiny1.asint.nodos.instrucciones.Instrucciones;
import tiny1.procesamientos.Procesador;

public class ProgramaConDecs implements Programa {
    private Declaraciones declaraciones;
    private Instrucciones instrucciones;

    public ProgramaConDecs(Declaraciones declaraciones, Instrucciones instrucciones) {
        this.declaraciones = declaraciones;
        this.instrucciones = instrucciones;
    }

    public Declaraciones declaraciones() { return declaraciones; }

    @Override
    public Instrucciones instrucciones() { return instrucciones; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }

}
