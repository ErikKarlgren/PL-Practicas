package tiny0.asint.nodos;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import tiny0.procesamientos.Procesador;

public class Declaraciones implements Nodo {
    private List<Declaracion> declaraciones;

    public Declaraciones(Declaracion declaracion) {
        List<Declaracion> aux = new LinkedList<>();
        aux.add(declaracion);
        this.declaraciones = Collections.unmodifiableList(aux);
    }

    public Declaraciones(Declaraciones declaraciones, Declaracion declaracion) {
        List<Declaracion> aux = new LinkedList<>();
        aux.addAll(declaraciones.declaraciones);
        aux.add(declaracion);
        this.declaraciones = Collections.unmodifiableList(aux);
    }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
