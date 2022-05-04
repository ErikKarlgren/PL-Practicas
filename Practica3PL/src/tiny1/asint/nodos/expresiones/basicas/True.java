package tiny1.asint.nodos.expresiones.basicas;

import tiny1.asint.nodos.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class True extends ExpresionBasica {

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
