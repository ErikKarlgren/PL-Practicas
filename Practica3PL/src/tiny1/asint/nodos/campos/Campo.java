package tiny1.asint.nodos.campos;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.StringLocalizado;
import tiny1.asint.nodos.expresiones.tipos.Tipo;
import tiny1.procesamientos.Procesador;

public class Campo implements Nodo {
    private StringLocalizado nombre;
    private Tipo tipo;

    public Campo(Tipo tipo, StringLocalizado nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public StringLocalizado nombre() { return nombre; }

    public Tipo tipo() { return tipo; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
