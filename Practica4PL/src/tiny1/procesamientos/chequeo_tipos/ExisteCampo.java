package tiny1.procesamientos.chequeo_tipos;

import java.util.Objects;

import tiny1.asint.nodos.campos.Campo;
import tiny1.asint.nodos.campos.CamposMuchos;
import tiny1.asint.nodos.campos.CamposUno;
import tiny1.procesamientos.ProcesadorConRetorno;

class ExisteCampo extends ProcesadorConRetorno<Boolean> {

    private final String nombreCampo;
    private boolean existe;

    ExisteCampo(String nombreCampo) {
        this.nombreCampo = Objects.requireNonNull(nombreCampo);
    }

    @Override
    public Boolean resultado() {
        return existe;
    }

    @Override
    public void procesa(Campo campo) {
        existe |= campo.nombre().toString().equals(nombreCampo);
    }

    @Override
    public void procesa(CamposUno campos) {
        campos.campo().procesa(this);
    }

    @Override
    public void procesa(CamposMuchos campos) {
        campos.campos().procesa(this);
        campos.campo().procesa(this);
    }
}
