package tiny1.asint.nodos.expresiones.booleanas.logicas;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.asint.nodos.expresiones.ExpresionBinaria;

abstract class ExpresionLogicaBinaria extends ExpresionBinaria {
    protected ExpresionLogicaBinaria(Expresion arg0, Expresion arg1) { super(arg0, arg1); }

    @Override
    public final int prioridad() { return 1; }
}
