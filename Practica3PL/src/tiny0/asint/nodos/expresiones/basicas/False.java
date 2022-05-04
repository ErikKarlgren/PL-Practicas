package tiny0.asint.nodos.expresiones.basicas;

import tiny0.asint.nodos.StringLocalizado;
import tiny0.procesamientos.Procesador;

public class False extends ExpresionBasica {
   
    public False() {}

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
