package tiny0.asint.nodos.expresiones.booleanas.logicas;

import tiny0.asint.nodos.expresiones.Expresion;
import tiny0.procesamientos.Procesador;

public class And extends ExpresionLogicaBinaria {
    public And(Expresion arg0, Expresion arg1) { super(arg0, arg1); }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
