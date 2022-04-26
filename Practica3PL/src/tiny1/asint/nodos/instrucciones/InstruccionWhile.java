package tiny1.asint.nodos.instrucciones;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.procesamientos.Procesador;

public class InstruccionWhile implements Instruccion {
    private Expresion expresion;
    private InstruccionesOpt instruccionesOpt;

    public InstruccionWhile(Expresion expresion, InstruccionesOpt instruccionesOpt) {
        this.expresion = expresion;
        this.instruccionesOpt = instruccionesOpt;
    }

    public Expresion expresion() { return expresion; }

    public InstruccionesOpt instruccionesOpt() { return instruccionesOpt; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
