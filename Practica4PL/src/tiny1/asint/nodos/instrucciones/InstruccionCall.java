package tiny1.asint.nodos.instrucciones;

import tiny1.asint.StringLocalizado;
import tiny1.asint.nodos.expresiones.Expresiones;
import tiny1.procesamientos.Procesador;

public class InstruccionCall implements Instruccion {
    private StringLocalizado funcion;
    private Expresiones parametros;

    public InstruccionCall(StringLocalizado funcion, Expresiones parametros) {
        this.funcion = funcion;
        this.parametros = parametros;
    }

    public StringLocalizado funcion() { return funcion; }

    public Expresiones parametros() { return parametros; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
