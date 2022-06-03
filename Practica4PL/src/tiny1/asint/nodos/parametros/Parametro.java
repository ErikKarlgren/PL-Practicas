package tiny1.asint.nodos.parametros;

import java.util.Objects;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.tipos.Tipo;

public abstract class Parametro extends Nodo {

    private final Tipo tipo;
    private final String nombre;

    // Asignación de espacio
    private int direccion;
    private int nivel;

    protected Parametro(Tipo tipo, String nombre) {
        this.tipo = Objects.requireNonNull(tipo);
        this.nombre = Objects.requireNonNull(nombre);
        this.direccion = -1;
        this.nivel = -1;
    }

    public Tipo tipo() {
        return tipo;
    }

    public String nombre() {
        return nombre;
    }

    public void setDireccion(int direccion) {
        if (direccion == -1) {
            this.direccion = direccion;
        } else {
            throw new IllegalStateException("La direccion ya ha sido asignada");
        }
    }

    public int direccion() {
        if (direccion == -1)
            throw new IllegalStateException("La direccion no ha sido asignada");
        return direccion;
    }

    public void setNivel(int nivel) {
        if (nivel == -1) {
            this.nivel = nivel;
        } else {
            throw new IllegalStateException("El nivel ya ha sido asignado");
        }
    }

    public int nivel() {
        if (nivel == -1)
            throw new IllegalStateException("El nivel no ha sido asignado");
        return nivel;
    }
}
