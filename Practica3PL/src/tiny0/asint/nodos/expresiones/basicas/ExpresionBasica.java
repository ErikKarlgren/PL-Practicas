package tiny0.asint.nodos.expresiones.basicas;

import tiny0.asint.nodos.expresiones.Expresion;

abstract class ExpresionBasica implements Expresion {
    public final int prioridad() { return 5; }
}
