package tiny1.procesamientos.vinculacion;

import tiny1.asint.nodos.campos.*;
import tiny1.asint.nodos.declaraciones.*;
import tiny1.asint.nodos.parametros.*;
import tiny1.asint.nodos.tipos.*;
import tiny1.errors.GestionErroresTiny;
import tiny1.procesamientos.Procesador;

public class VinculacionDecs2 implements Procesador {
    private final GestionErroresTiny err;
    private final TablaSimbolos tablaSimbolos;

    public VinculacionDecs2(GestionErroresTiny err, TablaSimbolos tablaSimbolos) {
        this.err = err;
        this.tablaSimbolos = tablaSimbolos;
    }

    @Override
    public void procesa(DecsUna declaraciones) {
        declaraciones.declaracion().procesa(this);
    }

    @Override
    public void procesa(DecsMuchas declaraciones) {
        declaraciones.declaraciones().procesa(this);
        declaraciones.declaracion().procesa(this);
    }

    @Override
    public void procesa(DecType decType) {
        decType.tipo().procesa(this);
    }

    @Override
    public void procesa(DecVar decVar) {
        decVar.tipo().procesa(this);
    }

    @Override
    public void procesa(DecProc decProc) {
        // No hacer nada
    }

    @Override
    public void procesa(ParamsSin parametros) {
        // No hacer nada
    }

    @Override
    public void procesa(ParamValor parametro) {
        parametro.tipo().procesa(this);
    }

    @Override
    public void procesa(ParamRef parametro) {
        parametro.tipo().procesa(this);
    }

    @Override
    public void procesa(ListaParamsUno listaParametros) {
        listaParametros.parametro().procesa(this);
    }

    @Override
    public void procesa(ListaParamsMuchos listaParametros) {
        listaParametros.listaParametros().procesa(this);
        listaParametros.parametro().procesa(this);
    }

    @Override
    public void procesa(TInt tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(TReal tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(TString tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(TBool tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(TipoArray tipo) {
        tipo.tipoBase().procesa(this);
    }

    @Override
    public void procesa(TipoPointer tipo) {
        if (tipo.tipoBase() instanceof TipoNuevo) {
            TipoNuevo tipoNuevo = (TipoNuevo) tipo.tipoBase();
            String nombreTipo = tipoNuevo.nombre().toString();
            if (tablaSimbolos.containsKey(nombreTipo)) {
                tipoNuevo.setVinculo(tablaSimbolos.get(nombreTipo));
            } else {
                err.errorProcesamiento(String.format("El tipo %s no está definido", nombreTipo));
            }
        } else {
            tipo.tipoBase().procesa(this);
        }
    }

    @Override
    public void procesa(TipoRecord tipo) {
        tipo.campos().procesa(this);
    }

    @Override
    public void procesa(TipoNuevo tipoNuevo) {
        // No hacer nada
    }

    @Override
    public void procesa(Campo campo) {
        campo.tipo().procesa(this);
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
