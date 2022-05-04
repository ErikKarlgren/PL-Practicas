package tiny1.asint.nodos.parametros;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.tipos.Tipo;

public abstract class Parametro implements Nodo {
    protected Tipo tipo;
    protected String nombre;

    protected Parametro(Tipo tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public Tipo tipo() { return tipo; }

    public String nombre() { return nombre; }
}
