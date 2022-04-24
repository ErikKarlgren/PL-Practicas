package tiny0.asint.nodos.expresiones.booleanas.logicas;

import tiny0.asint.nodos.expresiones.Expresion;
import tiny0.asint.nodos.expresiones.ExpresionUnaria;
import tiny0.procesamientos.Procesador;

public class Not extends ExpresionUnaria {

    protected Not(Expresion arg) { super(arg); }

    @Override
    public void procesa(Procesador p) { this.procesa(p); }

    @Override
    public int prioridad() { return 4; }
}
