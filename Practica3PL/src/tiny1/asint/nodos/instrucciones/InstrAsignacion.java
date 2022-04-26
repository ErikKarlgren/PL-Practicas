package tiny1.asint.nodos.instrucciones;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.procesamientos.Procesador;

public class InstrAsignacion implements Instruccion {
    private Expresion expresionIzquierda;
    private Expresion expresionDerecha;

    public InstrAsignacion(Expresion expresionIzquierda, Expresion expresionDerecha) {
        this.expresionIzquierda = expresionIzquierda;
        this.expresionDerecha = expresionDerecha;
    }

    public Expresion expresionIzquierda() { return expresionIzquierda; }

    public Expresion expresionDerecha() { return expresionDerecha; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
