package tiny0.asint.nodos.expresiones.aritmeticas;

import tiny0.asint.nodos.expresiones.Expresion;
import tiny0.asint.nodos.expresiones.ExpresionUnaria;
import tiny0.procesamientos.Procesador;

public class Menos extends ExpresionUnaria {

    public Menos(Expresion arg) { super(arg); }

    @Override
    public int prioridad() { return 4; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
