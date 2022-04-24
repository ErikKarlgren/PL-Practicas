package tiny0.asint.nodos.expresiones.booleanas.comparacion;

import tiny0.asint.nodos.expresiones.Expresion;
import tiny0.procesamientos.Procesador;

public class Distinto extends ExpresionComparativa {
    public Distinto(Expresion arg0, Expresion arg1) {
        super(arg0, arg1);
    }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
