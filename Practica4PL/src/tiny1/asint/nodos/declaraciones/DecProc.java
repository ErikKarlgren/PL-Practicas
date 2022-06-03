package tiny1.asint.nodos.declaraciones;

import java.util.Objects;

import tiny1.asint.StringLocalizado;
import tiny1.asint.nodos.bloques.Bloque;
import tiny1.asint.nodos.parametros.ListaParams;
import tiny1.procesamientos.Procesador;

public class DecProc extends Declaracion {

    private final StringLocalizado string;
    private final ListaParams listaParametros;
    private final Bloque bloque;

    // Asignación de espacio
    private int nivel;
    private int tamanio;

    public DecProc(StringLocalizado string, ListaParams lp, Bloque bloque) {
        this.string = Objects.requireNonNull(string);
        this.listaParametros = Objects.requireNonNull(lp);
        this.bloque = Objects.requireNonNull(bloque);
        this.nivel = -1;
        this.tamanio = -1;
    }

    public StringLocalizado id() {
        return string;
    }

    public ListaParams listaParams() {
        return listaParametros;
    }

    public Bloque bloque() {
        return bloque;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
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

    public void setTamanio(int tamanio) {
        if (tamanio == -1) {
            this.tamanio = tamanio;
        } else {
            throw new IllegalStateException("El tamanio ya ha sido asignado");
        }
    }

    public int tamanio() {
        if (tamanio == -1)
            throw new IllegalStateException("El tamaño no ha sido asignado");
        return tamanio;
    }
}
