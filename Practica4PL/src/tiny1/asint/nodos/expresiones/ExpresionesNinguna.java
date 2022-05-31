package tiny1.asint.nodos.expresiones;

import tiny1.procesamientos.Procesador;

public class ExpresionesNinguna implements Expresiones {

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
