package tiny1.asint.nodos.instrucciones;

import tiny1.asint.nodos.StringLocalizado;
import tiny1.asint.nodos.parametros.ParamsReal;
import tiny1.procesamientos.Procesador;

public class InstruccionCall implements Instruccion {
    private StringLocalizado funcion;
    private ParamsReal parametros;

    public InstruccionCall(StringLocalizado funcion, ParamsReal parametros) {
        this.funcion = funcion;
        this.parametros = parametros;
    }

    public StringLocalizado funcion() { return funcion; }

    public ParamsReal parametros() { return parametros; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
