package tiny1.asint.nodos.declaraciones;

import tiny1.asint.StringLocalizado;
import tiny1.asint.nodos.tipos.Tipo;
import tiny1.procesamientos.Procesador;

import java.util.Objects;

public class DecVar extends Declaracion {

    private final Tipo tipo;
    private final StringLocalizado string;

    // Asignación de espacio
    private int direccion;
    private int nivel;

    public DecVar(Tipo tipo, StringLocalizado string) {
        this.tipo = Objects.requireNonNull(tipo);
        this.string = Objects.requireNonNull(string);
        this.direccion = -1;
        this.nivel = -1;
    }

    public Tipo tipo() {
        return tipo;
    }

    public StringLocalizado id() {
        return string;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }

    public void setDireccion(int direccion) {
        if (this.direccion == -1) {
            this.direccion = direccion;
        } else {
            throw new IllegalStateException("La direccion ya ha sido asignada");
        }
    }

    public int direccion() {
        if (this.direccion == -1)
            throw new IllegalStateException("La direccion no ha sido asignada");
        return direccion;
    }

    public void setNivel(int nivel) {
        if (this.nivel == -1) {
            this.nivel = nivel;
        } else {
            throw new IllegalStateException("El nivel ya ha sido asignado");
        }
    }

    public int nivel() {
        if (this.nivel == -1)
            throw new IllegalStateException("El nivel no ha sido asignado");
        return nivel;
    }
}
