package tiny1.asint.nodos.expresiones;

import tiny1.procesamientos.Procesador;

public class AccesoArray extends ExpresionBinaria {

    public AccesoArray(Expresion arg0, Expresion arg1) {
        super(arg0, arg1);
    }

    @Override
    public int prioridad() { return 5; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
