package tiny1.asint.nodos;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import tiny1.asint.nodos.otros.LazyFinalField;
import tiny1.asint.nodos.otros.LazyFinalInt;
import tiny1.asint.nodos.tipos.Tipo;
import tiny1.procesamientos.Impresion;
import tiny1.procesamientos.Procesador;

public abstract class Nodo {

    // Comprobación de tipos
    private final LazyFinalField<Tipo> tipoNodo;

    // Asignación de espacio
    private final LazyFinalInt direccion;
    private final LazyFinalInt nivel;
    private final LazyFinalInt tamanio;

    // Etiquetado
    private final LazyFinalInt dirInicio;
    private final LazyFinalInt dirSiguiente;

    protected Nodo() {
        this.tipoNodo = new LazyFinalField<>();
        this.direccion = new LazyFinalInt();
        this.nivel = new LazyFinalInt();
        this.dirInicio = new LazyFinalInt();
        this.dirSiguiente = new LazyFinalInt();
        this.tamanio = new LazyFinalInt();
    }

    public abstract void procesa(Procesador p);

    public boolean esTipo() {
        return false;
    }

    public Nodo vinculo() {
        throw new UnsupportedOperationException("Este nodo no puede vincularse a otro");
    }

    public void setVinculo(Nodo nodo) {
        throw new UnsupportedOperationException("Este nodo no puede vincularse a otro");
    }

    public void setTipoNodo(Tipo tipo) {
        tipoNodo.set(tipo);
    }

    public Tipo tipoNodo() {
        return tipoNodo.get();
    }

    public LazyFinalInt direccion() {
        return direccion;
    }

    public LazyFinalInt nivel() {
        return nivel;
    }

    public LazyFinalInt tam() {
        return tamanio;
    }

    public LazyFinalInt dirInicio() {
        return dirInicio;
    }

    public LazyFinalInt dirSiguiente() {
        return dirSiguiente;
    }

    @Override
    public String toString() {
        // Usamos el procesador de impresión para imprimir sobre un PrintStream,
        // el cual creamos usando un ByteArrayOutputStream como buffer porque
        // podemos usar el método toString() para obtener el resultado de la
        // impresión como un String.

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String utf8 = StandardCharsets.UTF_8.name();

        try (PrintStream ps = new PrintStream(baos, true, utf8)) {
            this.procesa(new Impresion(ps));
            return String.format("Class: %s\nValue: %s\n", this.getClass(), baos.toString(utf8));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
