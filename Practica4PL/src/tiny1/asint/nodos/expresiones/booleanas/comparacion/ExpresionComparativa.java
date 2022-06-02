package tiny1.asint.nodos.expresiones.booleanas.comparacion;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.asint.nodos.expresiones.ExpresionBinaria;

abstract class ExpresionComparativa extends ExpresionBinaria {

    protected ExpresionComparativa(Expresion arg0, Expresion arg1) {
        super(2, arg0, arg1);
    }
}
