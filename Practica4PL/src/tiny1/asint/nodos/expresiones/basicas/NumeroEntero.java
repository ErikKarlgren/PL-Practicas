package tiny1.asint.nodos.expresiones.basicas;

import tiny1.asint.StringLocalizado;
import tiny1.procesamientos.Procesador;

public class NumeroEntero extends ExpresionBasica {
    private StringLocalizado num;

    public NumeroEntero(StringLocalizado num) {
        try {
            Integer.parseInt(num.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "El valor de la expresion entera debe ser un numero entero pero fue '" + num + "'");
        }
        this.num = num;
    }

    public StringLocalizado num() {
        return num;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
