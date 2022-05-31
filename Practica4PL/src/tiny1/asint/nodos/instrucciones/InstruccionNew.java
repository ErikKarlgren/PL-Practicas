package tiny1.asint.nodos.instrucciones;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.procesamientos.Procesador;

public class InstruccionNew implements Instruccion {
    private Expresion expresion;

    public InstruccionNew(Expresion expresion) {
        this.expresion = expresion;
    }

    public Expresion expresion() { return expresion; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
