package tiny0.asint.nodos.expresiones.aritmeticas;

import tiny0.asint.nodos.expresiones.Expresion;
import tiny0.asint.nodos.expresiones.ExpresionBinaria;

abstract class ExpresionAditiva extends ExpresionBinaria {
    protected ExpresionAditiva(Expresion arg0, Expresion arg1) { super(arg0, arg1); }

    @Override
    public final int prioridad() { return 0; }
}
