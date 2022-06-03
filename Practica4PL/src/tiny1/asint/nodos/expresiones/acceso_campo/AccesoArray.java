package tiny1.asint.nodos.expresiones.acceso_campo;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.asint.nodos.expresiones.ExpresionBinaria;
import tiny1.procesamientos.Procesador;

public class AccesoArray extends ExpresionBinaria {

    public AccesoArray(Expresion arg0, Expresion arg1) {
        super(5, arg0, arg1);
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }

    @Override
    public boolean esDesignador() {
        return true;
    }
}
