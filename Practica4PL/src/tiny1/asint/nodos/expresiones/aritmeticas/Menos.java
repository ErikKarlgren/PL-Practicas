package tiny1.asint.nodos.expresiones.aritmeticas;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.asint.nodos.expresiones.ExpresionUnaria;
import tiny1.procesamientos.Procesador;

public class Menos extends ExpresionUnaria {

    public Menos(Expresion arg) {
        super(4, arg);
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
