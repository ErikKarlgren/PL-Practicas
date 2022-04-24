package tiny0.asint.nodos;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import tiny0.procesamientos.Procesador;

public class Instrucciones implements Nodo {
    private List<Instruccion> instrucciones;

    public Instrucciones(Instruccion instruccion) {
        List<Instruccion> aux = new LinkedList<>();
        aux.add(instruccion);
        this.instrucciones = Collections.unmodifiableList(aux);
    }

    public Instrucciones(Instrucciones instrucciones, Instruccion instruccion) {
        List<Instruccion> aux = new LinkedList<>();
        aux.addAll(instrucciones.instrucciones);
        aux.add(instruccion);
        this.instrucciones = Collections.unmodifiableList(aux);
    }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
