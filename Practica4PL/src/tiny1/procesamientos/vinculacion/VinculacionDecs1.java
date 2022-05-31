package tiny1.procesamientos.vinculacion;

import tiny1.asint.nodos.Nodo;
import tiny1.asint.nodos.bloques.*;
import tiny1.asint.nodos.campos.*;
import tiny1.asint.nodos.declaraciones.*;
import tiny1.asint.nodos.expresiones.basicas.*;
import tiny1.asint.nodos.expresiones.*;
import tiny1.asint.nodos.expresiones.acceso_campo.*;
import tiny1.asint.nodos.expresiones.aritmeticas.*;
import tiny1.asint.nodos.expresiones.booleanas.logicas.*;
import tiny1.asint.nodos.expresiones.booleanas.comparacion.*;
import tiny1.asint.nodos.instrucciones.*;
import tiny1.asint.nodos.parametros.*;
import tiny1.asint.nodos.programa.*;
import tiny1.asint.nodos.tipos.*;
import tiny1.procesamientos.Procesador;

public class VinculacionDecs1 implements Procesador {
    private final TablaSimbolos tablaSimbolos;

    public VinculacionDecs1(TablaSimbolos tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }

    private void recolecta(String id, Nodo nodo) {
        if (tablaSimbolos.containsKey(id)) {
            throw new IllegalArgumentException("El identificador " + id + " ya está definido");
        }
        tablaSimbolos.put(id, nodo);
    }

    @Override
    public void procesa(ProgramaConDecs programa) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(ProgramaSinDecs programa) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(DecsUna declaraciones) {
        declaraciones.declaracion().procesa(this);
    }

    @Override
    public void procesa(DecsMuchas declaraciones) {
        declaraciones.declaraciones().procesa(this);
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
    public void procesa(Int tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(Real tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(TString tipo) {
        // No hacer nada
    }

    @Override
    public void procesa(Bool tipo) {
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
            throw new IllegalArgumentException("El tipo '" + tipoNuevo.nombre() + "' no está declarado");
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

    @Override
    public void procesa(InstrUna instrucciones) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstrMuchas instrucciones) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstrAsignacion instruccion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstrOptNinguna instruccionOpt) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstrOptMuchas instruccionesOpt) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstruccionIf instruccion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstruccionIfElse instruccion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstruccionWhile instruccion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstruccionRead instruccion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstruccionWrite instruccion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstruccionNL instruccion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstruccionNew instruccion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstruccionDelete instruccion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstruccionCall instruccion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstruccionBloque instrucciones) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(BloqueVacio bloque) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(BloqueLleno bloques) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(ExpresionesNinguna expresiones) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(ExpresionesUna expresiones) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(ExpresionesMuchas expresiones) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Suma suma) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Resta resta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Multiplicacion multiplicacion) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Division division) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(PorCiento porCiento) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Menos menos) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(NumeroEntero numero) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(NumeroReal numero) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Identificador identificador) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(True booleanoTrue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(False booleanoFalse) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Null nulo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Cadena cadena) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(And and) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Or or) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Not not) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Menor menor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(MenorIgual menorIgual) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Mayor mayor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(MayorIgual mayorIgual) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Igual igual) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Distinto distinto) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(AccesoArray accesoArray) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Punto punto) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Flecha flecha) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(ValorPuntero valorPuntero) {
        throw new UnsupportedOperationException();
    }

}