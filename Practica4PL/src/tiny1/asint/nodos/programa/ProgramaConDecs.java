package tiny1.asint.nodos.programa;

import tiny1.asint.nodos.declaraciones.Declaraciones;
import tiny1.asint.nodos.instrucciones.Instrucciones;
import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class ProgramaConDecs extends Programa {
    private Declaraciones declaraciones;

    public ProgramaConDecs(Declaraciones declaraciones, Instrucciones instrucciones) {
        super(instrucciones);
        this.declaraciones = Objects.requireNonNull(declaraciones);
    }

    public Declaraciones declaraciones() {
        return declaraciones;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
