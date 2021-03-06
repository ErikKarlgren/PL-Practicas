package tiny1.asint.nodos.expresiones.acceso_campo;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.procesamientos.Procesador;

public class Punto implements Expresion {

    private final Expresion arg0;
    private final String arg1;

    public Punto(Expresion arg0, String arg1) {
        this.arg0 = arg0;
        this.arg1 = arg1;
    }

    public Expresion arg0() { return arg0; }

    public String arg1() { return this.arg1; }

    @Override
    public int prioridad() { return 5; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
