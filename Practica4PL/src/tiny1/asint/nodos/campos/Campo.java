package tiny1.asint.nodos.campos;

import tiny1.asint.StringLocalizado;
import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.tipos.Tipo;
import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class Campo extends Nodo {

    private final StringLocalizado nombre;
    private final Tipo tipo;

    public Campo(Tipo tipo, StringLocalizado nombre) {
        this.tipo = Objects.requireNonNull(tipo);
        this.nombre = Objects.requireNonNull(nombre);
    }

    public StringLocalizado nombre() {
        return nombre;
    }

    public Tipo tipo() {
        return tipo;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
