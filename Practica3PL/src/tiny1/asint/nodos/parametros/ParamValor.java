package tiny1.asint.nodos.parametros;

import tiny1.asint.nodos.expresiones.tipos.Tipo;
import tiny1.procesamientos.Procesador;

public class ParamValor extends Parametro {

    public ParamValor(Tipo tipo, String nombre) {
        super(tipo, nombre);
    }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
