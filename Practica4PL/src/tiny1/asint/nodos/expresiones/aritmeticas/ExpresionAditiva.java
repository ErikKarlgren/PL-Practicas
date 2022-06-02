package tiny1.asint.nodos.expresiones.aritmeticas;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.asint.nodos.expresiones.ExpresionBinaria;

abstract class ExpresionAditiva extends ExpresionBinaria {

    protected ExpresionAditiva(Expresion arg0, Expresion arg1) {
        super(0, arg0, arg1);
    }
}
