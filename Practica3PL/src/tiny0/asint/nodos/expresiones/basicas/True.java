package tiny0.asint.nodos.expresiones.basicas;

import tiny0.asint.nodos.StringLocalizado;
import tiny0.procesamientos.Procesador;

public class True extends ExpresionBasica {

    public True() {}


    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
