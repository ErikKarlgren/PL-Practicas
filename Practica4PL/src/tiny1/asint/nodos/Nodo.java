package tiny1.asint.nodos;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import tiny1.asint.nodos.tipos.Tipo;
import tiny1.procesamientos.Impresion;
import tiny1.procesamientos.Procesador;

public abstract class Nodo {
    private Tipo tipoNodo;

    public abstract void procesa(Procesador p);

    public Nodo vinculo() {
        throw new UnsupportedOperationException("Este nodo no puede vincularse a otro");
    }

    public void setVinculo(Nodo nodo) {
        throw new UnsupportedOperationException("Este nodo no puede vincularse a otro");
    }

    public void setTipoNodo(Tipo tipo) {
        if (this.tipoNodo != null) {
            throw new IllegalStateException("No se puede cambiar el tipo de un nodo que ya tiene tipo\n"
                    + this);
        }
        this.tipoNodo = Objects.requireNonNull(tipo);
    }

    public Tipo tipoNodo() {
        return tipoNodo;
    }

    public boolean esTipo() {
        return false;
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
