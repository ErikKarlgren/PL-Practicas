package tiny1.asint.nodos.campos;

import tiny1.asint.StringLocalizado;
import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.TieneTamanio;
import tiny1.asint.nodos.tipos.Tipo;
import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class Campo extends Nodo implements TieneTamanio {

    private final StringLocalizado nombre;
    private final Tipo tipo;

    // Asignación de espacio
    private int tamanio;

    public Campo(Tipo tipo, StringLocalizado nombre) {
        this.tipo = Objects.requireNonNull(tipo);
        this.nombre = Objects.requireNonNull(nombre);
        this.tamanio = -1;
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

    @Override
    public void setTamanio(int tamanio) {
        if (!tamanioAsignado()) {
            this.tamanio = tamanio;
        } else {
            throw new IllegalStateException("El tamaño ya ha sido asignado");
        }
    }

    @Override
    public int tamanio() {
        if (!tamanioAsignado())
            throw new IllegalStateException("El tamaño no ha sido asignado");
        return tamanio;
    }

    @Override
    public boolean tamanioAsignado() {
        return tamanio != -1;
    }
}
