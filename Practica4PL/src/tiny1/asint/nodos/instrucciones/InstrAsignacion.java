package tiny1.asint.nodos.instrucciones;

import java.util.Objects;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.procesamientos.Procesador;

public class InstrAsignacion extends Instruccion {
    
    private final Expresion expresionIzquierda;
    private final Expresion expresionDerecha;

    public InstrAsignacion(Expresion expresionIzquierda, Expresion expresionDerecha) {
        this.expresionIzquierda = Objects.requireNonNull(expresionIzquierda);
        this.expresionDerecha = Objects.requireNonNull(expresionDerecha);
    }

    public Expresion expIzq() {
        return expresionIzquierda;
    }

    public Expresion expDer() {
        return expresionDerecha;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
