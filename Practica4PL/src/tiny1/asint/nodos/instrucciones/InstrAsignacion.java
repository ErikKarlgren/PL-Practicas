package tiny1.asint.nodos.instrucciones;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.procesamientos.Procesador;

public class InstrAsignacion extends Instruccion {
    
    private final Expresion expresionIzquierda;
    private final Expresion expresionDerecha;

    public InstrAsignacion(Expresion expresionIzquierda, Expresion expresionDerecha) {
        this.expresionIzquierda = expresionIzquierda;
        this.expresionDerecha = expresionDerecha;
    }

    public Expresion expresionIzquierda() {
        return expresionIzquierda;
    }

    public Expresion expresionDerecha() {
        return expresionDerecha;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
