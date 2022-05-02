package tiny1.asint.nodos.expresiones.basicas;

import tiny1.asint.nodos.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class Null extends ExpresionBasica{
	private StringLocalizado nulo;

    public Null(StringLocalizado nulo) {
        if (!nulo.toString().equals("null"))
            throw new IllegalArgumentException("El valor de la expresion booleana debe ser 'null', pero fue '" + nulo + "'");
        this.nulo = nulo;
    }

    public StringLocalizado bool() { return nulo; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}