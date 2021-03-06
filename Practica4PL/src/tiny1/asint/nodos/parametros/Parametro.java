package tiny1.asint.nodos.parametros;

import java.util.Objects;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.otros.LazyFinalInt;
import tiny1.asint.nodos.tipos.Tipo;

public abstract class Parametro extends Nodo {

    private final Tipo tipo;
    private final String nombre;

    protected Parametro(Tipo tipo, String nombre) {
        this.tipo = Objects.requireNonNull(tipo);
        this.nombre = Objects.requireNonNull(nombre);
    }

    public Tipo tipo() {
        return tipo;
    }

    public String nombre() {
        return nombre;
    }
}
