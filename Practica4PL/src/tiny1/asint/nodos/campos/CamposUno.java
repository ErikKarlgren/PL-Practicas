package tiny1.asint.nodos.campos;

import java.util.Objects;

import tiny1.procesamientos.Procesador;

public class CamposUno extends Campos {

    private final Campo campo;

    public CamposUno(Campo campo) {
        this.campo = Objects.requireNonNull(campo);
    }

    public Campo campo() {
        return campo;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
