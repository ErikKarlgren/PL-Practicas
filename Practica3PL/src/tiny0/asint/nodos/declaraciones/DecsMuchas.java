package tiny0.asint.nodos.declaraciones;

import tiny0.procesamientos.Procesador;

public class DecsMuchas implements Declaraciones {
    private Declaraciones declaraciones;
    private Declaracion declaracion;

    public DecsMuchas(Declaraciones declaraciones, Declaracion declaracion) {
        this.declaraciones = declaraciones;
        this.declaracion = declaracion;
    }

    public Declaraciones declaraciones() { return declaraciones; }
    public Declaracion declaracion() { return declaracion; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
