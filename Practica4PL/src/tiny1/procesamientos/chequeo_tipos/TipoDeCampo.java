package tiny1.procesamientos.chequeo_tipos;

import tiny1.asint.nodos.campos.Campo;
import tiny1.asint.nodos.campos.CamposMuchos;
import tiny1.asint.nodos.campos.CamposUno;
import tiny1.asint.nodos.tipos.Tipo;
import tiny1.procesamientos.ProcesadorConRetorno;

class TipoDeCampo implements ProcesadorConRetorno<Tipo> {
    private final String nombreCampo;
    private Tipo tipoDeCampo;

    TipoDeCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    @Override
    public Tipo resultado() {
        return tipoDeCampo;
    }

    @Override
    public void procesa(Campo campo) {
        if (campo.nombre().toString().equals(nombreCampo))
            this.tipoDeCampo = campo.tipo();
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
