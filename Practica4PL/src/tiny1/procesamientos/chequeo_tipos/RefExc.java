package tiny1.procesamientos.chequeo_tipos;

import tiny1.asint.nodos.tipos.TBool;
import tiny1.asint.nodos.tipos.TInt;
import tiny1.asint.nodos.tipos.TReal;
import tiny1.asint.nodos.tipos.TString;
import tiny1.asint.nodos.tipos.Tipo;
import tiny1.asint.nodos.tipos.TipoArray;
import tiny1.asint.nodos.tipos.TipoNuevo;
import tiny1.asint.nodos.tipos.TipoPointer;
import tiny1.asint.nodos.tipos.TipoRecord;
import tiny1.procesamientos.Procesador;

public class RefExc implements Procesador {
    private Tipo tipoReferido;

    public Tipo ref() {
        return tipoReferido;
    }

    @Override
    public void procesa(TInt tipo) {
        tipoReferido = tipo;
    }

    @Override
    public void procesa(TReal tipo) {
        tipoReferido = tipo;
    }

    @Override
    public void procesa(TString tipo) {
        tipoReferido = tipo;
    }

    @Override
    public void procesa(TBool tipo) {
        tipoReferido = tipo;
    }

    @Override
    public void procesa(TipoArray tipo) {
        tipoReferido = tipo;
    }

    @Override
    public void procesa(TipoPointer tipo) {
        tipoReferido = tipo;
    }

    @Override
    public void procesa(TipoRecord tipo) {
        tipoReferido = tipo;
    }

    @Override
    public void procesa(TipoNuevo tipoNuevo) {
        tipoNuevo.vinculo().procesa(this);
    }

}
