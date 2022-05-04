package tiny0.asint.nodos;

import tiny0.asint.nodos.declaraciones.Declaraciones;
import tiny0.asint.nodos.instrucciones.Instrucciones;
import tiny0.procesamientos.Procesador;

public class Programa implements Nodo {
    private Declaraciones declaraciones;
    private Instrucciones instrucciones;

    public Programa(Declaraciones declaraciones, Instrucciones instrucciones) {
        this.declaraciones = declaraciones;
        this.instrucciones = instrucciones;
    }

    public Declaraciones declaraciones() { return declaraciones; }

    public Instrucciones instrucciones() { return instrucciones; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }

}
