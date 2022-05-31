package tiny1.asint.nodos.expresiones.basicas;

import tiny1.asint.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class NumeroReal extends ExpresionBasica {
    private StringLocalizado num;

    public NumeroReal(StringLocalizado num) {
        try {
            Double.parseDouble(num.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El valor de la expresion real debe ser un numero real pero fue '" + num + "'");
        }
        this.num = num;
    }

    public StringLocalizado num() { return num; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
