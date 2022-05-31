package tiny1.asint.nodos.expresiones.acceso_campo;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.asint.nodos.expresiones.ExpresionUnaria;
import tiny1.procesamientos.Procesador;

public class ValorPuntero extends ExpresionUnaria {
	
	public ValorPuntero(Expresion arg) { super(arg); }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }

    @Override
    public int prioridad() { return 6; }
}