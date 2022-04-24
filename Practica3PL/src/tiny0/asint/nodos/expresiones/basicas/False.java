package tiny0.asint.nodos.expresiones.basicas;

import tiny0.asint.nodos.StringLocalizado;
import tiny0.procesamientos.Procesador;

public class False extends ExpresionBasica {
    private StringLocalizado bool;

    public False(int fila, int col) { this.bool = new StringLocalizado("false", fila, col); }

    public StringLocalizado bool() { return bool; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
