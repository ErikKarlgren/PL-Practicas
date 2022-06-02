package tiny1.asint.nodos.expresiones.booleanas.logicas;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.asint.nodos.expresiones.ExpresionUnaria;
import tiny1.procesamientos.Procesador;

public class Not extends ExpresionUnaria {

    public Not(Expresion arg) {
        super(4, arg);
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
