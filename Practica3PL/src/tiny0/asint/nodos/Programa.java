package tiny0.asint.nodos;

import tiny0.procesamientos.Procesador;

public class Programa implements Nodo {
    private Declaraciones declaraciones;
    private Instrucciones instrucciones;

    public Programa(Declaraciones declaraciones, Instrucciones instrucciones) {
        this.declaraciones = declaraciones;
        this.instrucciones = instrucciones;
    }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }

}
