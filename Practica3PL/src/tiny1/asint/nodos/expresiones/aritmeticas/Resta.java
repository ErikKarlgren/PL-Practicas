package tiny1.asint.nodos.expresiones.aritmeticas;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.procesamientos.Procesador;

public class Resta extends ExpresionAditiva {
    public Resta(Expresion arg0, Expresion arg1) { super(arg0, arg1); }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }

}
