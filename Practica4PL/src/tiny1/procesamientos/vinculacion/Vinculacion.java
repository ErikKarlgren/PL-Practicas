package tiny1.procesamientos.vinculacion;

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

public class Vinculacion implements Procesador {

    private final TablaSimbolos tablaSimbolos;
    private final VinculacionDecs1 vinculacionDecs1;
    private final VinculacionDecs2 vinculacionDecs2;
    private final VinculacionProcs vinculacionProcs;

    public Vinculacion() {
        tablaSimbolos = new TablaSimbolos();
        vinculacionDecs1 = new VinculacionDecs1(tablaSimbolos);
        vinculacionDecs2 = new VinculacionDecs2(tablaSimbolos);
        vinculacionProcs = new VinculacionProcs(tablaSimbolos, this, vinculacionDecs1, vinculacionDecs2);
    }

    @Override
    public void procesa(ProgramaConDecs programa) {
        programa.declaraciones().procesa(vinculacionDecs1);
        programa.declaraciones().procesa(vinculacionDecs2);
        programa.declaraciones().procesa(vinculacionProcs);
        programa.instrucciones().procesa(this);
    }

    @Override
    public void procesa(ProgramaSinDecs programa) {
        programa.instrucciones().procesa(this);
    }

    @Override
    public void procesa(DecsUna declaraciones) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(DecsMuchas declaraciones) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(DecType decType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(DecVar decVar) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(DecProc decProc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(ParamsSin parametros) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(ParamValor parametro) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(ParamRef parametro) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(ListaParamsUno listaParametros) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(ListaParamsMuchos listaParametros) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Int tipo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Real tipo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(TString tipo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Bool tipo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(TipoArray tipo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(TipoPointer tipo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(TipoRecord tipo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(TipoNuevo tipoNuevo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(Campo campo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(CamposUno campos) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(CamposMuchos campos) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void procesa(InstrUna instrucciones) {
        instrucciones.instruccion().procesa(this);
    }

    @Override
    public void procesa(InstrMuchas instrucciones) {
        instrucciones.instrucciones().procesa(this);
        instrucciones.instruccion().procesa(this);
    }

    @Override
    public void procesa(InstrAsignacion instruccion) {
        instruccion.expresionIzquierda().procesa(this);
        instruccion.expresionDerecha().procesa(this);
    }

    @Override
    public void procesa(InstrOptNinguna instruccionOpt) {
        // No hacer nada
    }

    @Override
    public void procesa(InstrOptMuchas instruccionesOpt) {
        instruccionesOpt.instrucciones().procesa(this);
    }

    @Override
    public void procesa(InstruccionIf instruccion) {
        instruccion.expresion().procesa(this);
        instruccion.instruccionesOpt().procesa(this);
    }

    @Override
    public void procesa(InstruccionIfElse instruccion) {
        instruccion.expresion().procesa(this);
        instruccion.instruccionesOptIf().procesa(this);
        instruccion.instruccionesOptElse().procesa(this);
    }

    @Override
    public void procesa(InstruccionWhile instruccion) {
        instruccion.expresion().procesa(this);
        instruccion.instruccionesOpt().procesa(this);
    }

    @Override
    public void procesa(InstruccionRead instruccion) {
        instruccion.expresion().procesa(this);
    }

    @Override
    public void procesa(InstruccionWrite instruccion) {
        instruccion.expresion().procesa(this);
    }

    @Override
    public void procesa(InstruccionNL instruccion) {
        // No hacer nada
    }

    @Override
    public void procesa(InstruccionNew instruccion) {
        instruccion.expresion().procesa(this);
    }

    @Override
    public void procesa(InstruccionDelete instruccion) {
        instruccion.expresion().procesa(this);
    }

    @Override
    public void procesa(InstruccionCall instruccion) {
        String nombreFuncion = instruccion.funcion().toString();
        if (tablaSimbolos.containsKey(nombreFuncion)) {
            instruccion.setVinculo(tablaSimbolos.get(nombreFuncion));
        } else {
            throw new IllegalStateException("No se encontró la función " + nombreFuncion);
        }
        instruccion.parametros().procesa(this);
    }

    @Override
    public void procesa(InstruccionBloque instrucciones) {
        instrucciones.bloque().procesa(this);
    }

    @Override
    public void procesa(BloqueVacio bloque) {
        // No hacer nada
    }

    @Override
    public void procesa(BloqueLleno bloque) {
        tablaSimbolos.abrirNivel();
        bloque.programa().procesa(this);
        tablaSimbolos.cerrarNivel();
    }

    @Override
    public void procesa(ExpresionesNinguna expresiones) {
        // No hacer nada
    }

    @Override
    public void procesa(ExpresionesUna expresiones) {
        expresiones.expresion().procesa(this);
    }

    @Override
    public void procesa(ExpresionesMuchas expresiones) {
        expresiones.expresiones().procesa(this);
        expresiones.expresion().procesa(this);
    }

    @Override
    public void procesa(Suma suma) {
        suma.arg0().procesa(this);
        suma.arg1().procesa(this);
    }

    @Override
    public void procesa(Resta resta) {
        resta.arg0().procesa(this);
        resta.arg1().procesa(this);
    }

    @Override
    public void procesa(Multiplicacion multiplicacion) {
        multiplicacion.arg0().procesa(this);
        multiplicacion.arg1().procesa(this);
    }

    @Override
    public void procesa(Division division) {
        division.arg0().procesa(this);
        division.arg1().procesa(this);
    }

    @Override
    public void procesa(PorCiento porCiento) {
        porCiento.arg0().procesa(this);
        porCiento.arg1().procesa(this);
    }

    @Override
    public void procesa(Menos menos) {
        menos.arg().procesa(this);
    }

    @Override
    public void procesa(NumeroEntero numero) {
        // No hacer nada
    }

    @Override
    public void procesa(NumeroReal numero) {
        // No hacer nada
    }

    @Override
    public void procesa(Identificador identificador) {
        String idString = identificador.id().toString();
        if (tablaSimbolos.containsKey(idString)) {
            identificador.setVinculo(tablaSimbolos.get(idString));
        }
    }

    @Override
    public void procesa(True booleanoTrue) {
        // No hacer nada
    }

    @Override
    public void procesa(False booleanoFalse) {
        // No hacer nada
    }

    @Override
    public void procesa(Null nulo) {
        // No hacer nada
    }

    @Override
    public void procesa(Cadena cadena) {
        // No hacer nada
    }

    @Override
    public void procesa(And and) {
        and.arg0().procesa(this);
        and.arg1().procesa(this);
    }

    @Override
    public void procesa(Or or) {
        or.arg0().procesa(this);
        or.arg1().procesa(this);
    }

    @Override
    public void procesa(Not not) {
        not.arg().procesa(this);
    }

    @Override
    public void procesa(Menor menor) {
        menor.arg0().procesa(this);
        menor.arg1().procesa(this);
    }

    @Override
    public void procesa(MenorIgual menorIgual) {
        menorIgual.arg0().procesa(this);
        menorIgual.arg1().procesa(this);
    }

    @Override
    public void procesa(Mayor mayor) {
        mayor.arg0().procesa(this);
        mayor.arg1().procesa(this);
    }

    @Override
    public void procesa(MayorIgual mayorIgual) {
        mayorIgual.arg0().procesa(this);
        mayorIgual.arg1().procesa(this);
    }

    @Override
    public void procesa(Igual igual) {
        igual.arg0().procesa(this);
        igual.arg1().procesa(this);
    }

    @Override
    public void procesa(Distinto distinto) {
        distinto.arg0().procesa(this);
        distinto.arg1().procesa(this);
    }

    @Override
    public void procesa(AccesoArray accesoArray) {
        accesoArray.arg0().procesa(this);
        accesoArray.arg1().procesa(this);
    }

    @Override
    public void procesa(Punto punto) {
        punto.arg0().procesa(this);
    }

    @Override
    public void procesa(Flecha flecha) {
        flecha.arg0().procesa(this);
    }

    @Override
    public void procesa(ValorPuntero valorPuntero) {
        valorPuntero.arg().procesa(this);
    }

}