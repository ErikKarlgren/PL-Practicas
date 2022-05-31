package tiny1.asint.nodos.tipos;

import tiny1.asint.StringLocalizado;
import tiny1.asint.nodos.Nodo;
import tiny1.procesamientos.Procesador;

public class TipoNuevo implements Tipo {
    private StringLocalizado nombre;
    private Nodo vinculo;

    public TipoNuevo(StringLocalizado nombre) {
        this.nombre = nombre;
    }

    public StringLocalizado nombre() { return nombre; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }

    @Override
    public Nodo vinculo() { return vinculo; }

    @Override
    public void setVinculo(Nodo nodo) { vinculo = nodo; }
}
