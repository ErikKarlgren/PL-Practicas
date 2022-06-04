package tiny1.asint;

import java.util.Objects;

public class StringLocalizado {

    private String s;
    private int fila;
    private int col;

    public StringLocalizado(String s, int fila, int col) {
        this.s = Objects.requireNonNull(s);
        this.fila = fila;
        this.col = col;
    }

    public int fila() { return fila; }

    public int col() { return col; }

    public String toString() { return s; }

    public boolean equals(Object o) {
        return (o == this) || ((o instanceof StringLocalizado) && (((StringLocalizado) o).s.equals(s)));
    }

    public int hashCode() { return s.hashCode(); }
}
