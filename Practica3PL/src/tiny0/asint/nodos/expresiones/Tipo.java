package tiny0.asint.nodos.expresiones;

import tiny0.asint.nodos.StringLocalizado;

public class Tipo {
    private StringLocalizado nombre;

    public Tipo(StringLocalizado tipo) {
        this.nombre = tipo;
    }

    @Override
    public String toString() { return nombre.toString(); }
}
