package tiny1.asint.nodos.declaraciones;

import tiny1.asint.nodos.StringLocalizado;
import tiny1.asint.nodos.expresiones.tipos.Tipo;
import tiny1.procesamientos.Procesador;

public class DecType implements Declaracion{

    private Tipo tipo;
    private StringLocalizado string;

    public DecType(Tipo tipo, StringLocalizado string) {
        this.tipo = tipo;
        this.string = string;
    }

    public Tipo tipo() { return tipo; }

    public StringLocalizado id() { return string; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }

}
