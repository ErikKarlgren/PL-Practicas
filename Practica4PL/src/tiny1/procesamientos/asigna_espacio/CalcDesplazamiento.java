package tiny1.procesamientos.asigna_espacio;

import java.util.Objects;

import tiny1.asint.nodos.campos.Campo;
import tiny1.asint.nodos.campos.CamposMuchos;
import tiny1.asint.nodos.campos.CamposUno;
import tiny1.asint.nodos.expresiones.acceso_campo.Flecha;
import tiny1.asint.nodos.expresiones.acceso_campo.Punto;
import tiny1.asint.nodos.tipos.TipoPointer;
import tiny1.asint.nodos.tipos.TipoRecord;
import tiny1.procesamientos.ProcesadorConRetorno;

class CalcDesplazamiento extends ProcesadorConRetorno<Integer> {

    private final String nombreCampo;
    private int desplazamiento;
    private boolean campoEncontrado;

    CalcDesplazamiento(String nombreCampo) {
        this.nombreCampo = Objects.requireNonNull(nombreCampo);
        this.desplazamiento = 0;
    }

    @Override
    protected Integer resultado() {
        if (!campoEncontrado) {
            throw new IllegalStateException("No se encontró el campo " + nombreCampo);
        }
        return desplazamiento;
    }

    @Override
    public void procesa(Campo campo) {
        if (!campoEncontrado && !campo.nombre().toString().equals(nombreCampo)) {
            desplazamiento += campo.tam().get();
        } else {
            campoEncontrado = true;
        }
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

    // @Override
    // public void procesa(Punto punto) {
    //     punto.exp().tipoNodo().procesa(this);
    // }

    // @Override
    // public void procesa(Flecha flecha) {
    //     flecha.exp().tipoNodo().procesa(this);
    // }

    // @Override
    // public void procesa(TipoPointer tipo) {
    //     tipo.tipoBase().procesa(this);
    // }
}
