package tiny1.asint.nodos.parametros;

import tiny1.asint.nodos.tipos.Tipo;
import tiny1.procesamientos.Procesador;

public class ParamRef extends Parametro {

    public ParamRef(Tipo tipo, String nombre) {
        super(tipo, nombre);
    }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
