package tiny1.asint.nodos.expresiones.basicas;

import tiny1.asint.nodos.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class True extends ExpresionBasica {
    private StringLocalizado bool;

    public True(StringLocalizado bool) {
        if (!bool.toString().equals("true"))
            throw new IllegalArgumentException("El valor de la expresion booleana debe ser 'true' pero fue '" + bool + "'");
        this.bool = bool;
    }

    public StringLocalizado bool() { return bool; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
