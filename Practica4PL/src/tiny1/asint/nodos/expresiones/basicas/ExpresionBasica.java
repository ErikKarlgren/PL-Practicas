package tiny1.asint.nodos.expresiones.basicas;

import tiny1.asint.nodos.expresiones.Expresion;

abstract class ExpresionBasica implements Expresion {
    public final int prioridad() { return 5; }
}
