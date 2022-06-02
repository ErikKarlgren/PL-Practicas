package tiny1.procesamientos.vinculacion;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.campos.*;
import tiny1.asint.nodos.declaraciones.*;
import tiny1.asint.nodos.parametros.*;
import tiny1.asint.nodos.tipos.*;
import tiny1.errors.GestionErroresTiny;
import tiny1.procesamientos.Procesador;

public class VinculacionDecs1 implements Procesador {
    private final GestionErroresTiny err;
    private final TablaSimbolos tablaSimbolos;

    public VinculacionDecs1(GestionErroresTiny err, TablaSimbolos tablaSimbolos) {
        this.err = err;
        this.tablaSimbolos = tablaSimbolos;
    }

    private void recolecta(String id, Nodo nodo) {
        if (tablaSimbolos.declaracionDuplicada(id)) {
            err.errorProcesamiento(String.format("El identificador %s ya está definido\n", id));
        }
        tablaSimbolos.put(id, nodo);
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
        recolecta(decType.id().toString(), decType);
    }

    @Override
    public void procesa(DecVar decVar) {
        decVar.tipo().procesa(this);
        recolecta(decVar.id().toString(), decVar);
    }

    @Override
    public void procesa(DecProc decProc) {
        recolecta(decProc.id().toString(), decProc);
    }

    @Override
    public void procesa(ParamsSin parametros) {
        // No hacer nada
    }

    @Override
    public void procesa(ParamValor parametro) {
        parametro.tipo().procesa(this);
        recolecta(parametro.nombre(), parametro);
    }

    @Override
    public void procesa(ParamRef parametro) {
        recolecta(parametro.nombre(), parametro);
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
        // todo: CAMBIAR
        if (!(tipo.tipoBase() instanceof TipoNuevo)) {
            tipo.tipoBase().procesa(this);
        }
    }

    @Override
    public void procesa(TipoRecord tipo) {
        tipo.campos().procesa(this);
    }

    @Override
    public void procesa(TipoNuevo tipoNuevo) {
        if (tablaSimbolos.containsKey(tipoNuevo.nombre().toString())) {
            Nodo nodo = tablaSimbolos.get(tipoNuevo.nombre().toString());
            tipoNuevo.setVinculo(nodo);
            // TODO: comprobar que nodo es un tipo
        } else {
            err.errorProcesamiento(String.format("El tipo %s no está definido\n", tipoNuevo.nombre()));
        }
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
