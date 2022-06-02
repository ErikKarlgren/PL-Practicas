package tiny1.asint.nodos;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import tiny1.asint.nodos.tipos.Tipo;
import tiny1.procesamientos.Impresion;
import tiny1.procesamientos.Procesador;

public abstract class Nodo {
    private Tipo tipo;

    public abstract void procesa(Procesador p);

    public Nodo vinculo() {
        throw new UnsupportedOperationException("Este nodo no puede tener un vínculo");
    }

    public void setVinculo(Nodo nodo) {
        throw new UnsupportedOperationException("Este nodo no puede tener un vínculo");
    }

    public void setTipo(Tipo tipo) {
        if (tipo != null) {
            throw new IllegalStateException("No se puede cambiar el tipo de un nodo que ya tiene tipo");
        }
        this.tipo = tipo;
    }

    final public Tipo getTipo() {
        return tipo;
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
            return baos.toString(utf8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
