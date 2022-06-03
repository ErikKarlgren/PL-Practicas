package tiny1.asint.nodos.campos;

import java.util.Objects;

import tiny1.procesamientos.Procesador;

public class CamposMuchos extends Campos {

    private final Campos campos;
    private final Campo campo;

    public CamposMuchos(Campos campos, Campo campo) {
        this.campos = Objects.requireNonNull(campos);
        this.campo = Objects.requireNonNull(campo);
    }

    public Campos campos() {
        return campos;
    }

    public Campo campo() {
        return campo;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
