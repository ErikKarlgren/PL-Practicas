package tiny1.asint.nodos.parametros;

import tiny1.asint.nodos.expresiones.Expresiones;
import tiny1.procesamientos.Procesador;

public class ParamsRealMuchas implements ParamsReal {
    private Expresiones expresiones;

    public ParamsRealMuchas(Expresiones expresiones) {
        this.expresiones = expresiones;
    }

    public Expresiones expresiones() { return expresiones; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
