package tiny1.asint.nodos.expresiones.basicas;

import tiny1.asint.StringLocalizado;
import tiny1.asint.nodos.Nodo;
import tiny1.procesamientos.Procesador;

public class Identificador extends ExpresionBasica {
    private StringLocalizado id;
    private Nodo vinculo;

    public Identificador(StringLocalizado id) { this.id = id; }

    public StringLocalizado id() { return id; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }

    @Override
    public Nodo vinculo() { return vinculo; }

    @Override
    public void setVinculo(Nodo vinculo) { this.vinculo = vinculo; }
}
