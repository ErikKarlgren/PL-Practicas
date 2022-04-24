package tiny0.asint.nodos.expresiones.basicas;

import tiny0.asint.nodos.StringLocalizado;
import tiny0.procesamientos.Procesador;

public class NumeroReal extends ExpresionBasica {
    private StringLocalizado num;

    public NumeroReal(StringLocalizado num) { this.num = num; }

    public StringLocalizado num() { return num; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
