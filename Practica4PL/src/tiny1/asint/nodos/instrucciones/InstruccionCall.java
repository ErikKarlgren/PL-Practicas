package tiny1.asint.nodos.instrucciones;

import java.util.Objects;

import tiny1.asint.StringLocalizado;
import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.expresiones.Expresiones;
import tiny1.procesamientos.Procesador;

public class InstruccionCall extends Instruccion {

    private final StringLocalizado funcion;
    private final Expresiones parametros;
    private Nodo vinculo;

    public InstruccionCall(StringLocalizado funcion, Expresiones parametros) {
        this.funcion = Objects.requireNonNull(funcion);
        this.parametros = Objects.requireNonNull(parametros);
    }

    public StringLocalizado funcion() {
        return funcion;
    }

    public Expresiones parametros() {
        return parametros;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }

    @Override
    public Nodo vinculo() {
        return vinculo;
    }

    @Override
    public void setVinculo(Nodo vinculo) {
        this.vinculo = Objects.requireNonNull(vinculo);
    }
}
