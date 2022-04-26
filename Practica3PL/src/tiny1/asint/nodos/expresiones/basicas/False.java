package tiny1.asint.nodos.expresiones.basicas;

import tiny1.asint.nodos.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class False extends ExpresionBasica {
    private StringLocalizado bool;

    public False(StringLocalizado bool) {
        if (!bool.toString().equals("false"))
            throw new IllegalArgumentException("El valor de la expresion booleana debe ser 'false', pero fue '" + bool + "'");
        this.bool = bool;
    }

    public StringLocalizado bool() { return bool; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
