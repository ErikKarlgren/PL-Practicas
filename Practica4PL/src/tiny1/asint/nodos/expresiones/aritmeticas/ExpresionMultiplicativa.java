package tiny1.asint.nodos.expresiones.aritmeticas;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.asint.nodos.expresiones.ExpresionBinaria;

abstract class ExpresionMultiplicativa extends ExpresionBinaria {

    protected ExpresionMultiplicativa(Expresion arg0, Expresion arg1) {
        super(3, arg0, arg1);
    }
}
