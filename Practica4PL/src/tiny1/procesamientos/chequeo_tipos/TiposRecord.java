package tiny1.procesamientos.chequeo_tipos;

import java.util.ArrayList;
import java.util.List;

import tiny1.asint.nodos.campos.Campo;
import tiny1.asint.nodos.campos.CamposMuchos;
import tiny1.asint.nodos.campos.CamposUno;
import tiny1.asint.nodos.tipos.Tipo;
import tiny1.asint.nodos.tipos.TipoRecord;
import tiny1.procesamientos.ProcesadorConRetorno;

class TiposRecord extends ProcesadorConRetorno<List<Class<? extends Tipo>>> {

    private final List<Class<? extends Tipo>> tipos;

    TiposRecord() {
        this.tipos = new ArrayList<>();
    }

    @Override
    public List<Class<? extends Tipo>> resultado() {
        return tipos;
    }

    @Override
    public void procesa(Campo campo) {
        tipos.add(campo.tipo().getClass());
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

    @Override
    public void procesa(TipoRecord tipo) {
        tipo.campos().procesa(this);
    }
}
