package tiny1.asint.nodos.tipos;

import java.util.Objects;

import tiny1.asint.StringLocalizado;
import tiny1.asint.nodos.Nodo;
import tiny1.procesamientos.Procesador;

public class TipoNuevo extends Tipo {

    private final StringLocalizado nombre;
    private Nodo vinculo;

    public TipoNuevo(StringLocalizado nombre) {
        this.nombre = Objects.requireNonNull(nombre);
    }

    public StringLocalizado nombre() {
        return nombre;
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
    public void setVinculo(Nodo nodo) {
        vinculo = nodo;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        TipoNuevo otro = (TipoNuevo) obj;
        return this.nombre.equals(otro.nombre) && this.vinculo == otro.vinculo;
    }
}
