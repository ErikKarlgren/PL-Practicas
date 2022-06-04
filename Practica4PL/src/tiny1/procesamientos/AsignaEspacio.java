package tiny1.procesamientos;

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
import tiny1.asint.nodos.interfaces.TieneTamanio;
import tiny1.asint.nodos.parametros.*;
import tiny1.asint.nodos.programa.*;
import tiny1.asint.nodos.tipos.*;

public class AsignaEspacio extends Procesador {

    private int direccion;
    private int nivel;

    public AsignaEspacio() {
        this.direccion = 0;
        this.nivel = 0;
    }

    private <T extends Nodo & TieneTamanio> void asignaTamanio(T tipo) {
        if (!tipo.tamanio().isInitialized()) {
            tipo.procesa(this);
        }
    }

    @Override
    public void procesa(ProgramaConDecs programa) {
        programa.declaraciones().procesa(this);
        programa.instrucciones().procesa(this);
    }

    @Override
    public void procesa(ProgramaSinDecs programa) {
        programa.instrucciones().procesa(this);
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
        asignaTamanio(decType.tipo());
    }

    @Override
    public void procesa(DecVar decVar) {
        decVar.direccion().set(direccion);
        decVar.nivel().set(nivel);
        asignaTamanio(decVar.tipo());
        direccion += decVar.tipo().tamanio().get();
    }

    @Override
    public void procesa(DecProc decProc) {
        int antiguaDir = direccion;

        nivel++;
        decProc.nivel().set(nivel);
        direccion = 0;
        decProc.listaParams().procesa(this);
        decProc.bloque().procesa(this);
        decProc.tamanio().set(direccion);

        direccion = antiguaDir;
        nivel--;
    }

    @Override
    public void procesa(ParamsSin parametros) {
        // No hacer nada
    }

    @Override
    public void procesa(ParamValor parametro) {
        parametro.direccion().set(direccion);
        parametro.nivel().set(nivel);
        asignaTamanio(parametro.tipo());
        direccion += parametro.tipo().tamanio().get();
    }

    @Override
    public void procesa(ParamRef parametro) {
        parametro.direccion().set(direccion);
        parametro.nivel().set(nivel);
        asignaTamanio(parametro.tipo());
        direccion += parametro.tipo().tamanio().get();
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
        tipo.tamanio().set(1);
    }

    @Override
    public void procesa(TReal tipo) {
        tipo.tamanio().set(1);
    }

    @Override
    public void procesa(TString tipo) {
        tipo.tamanio().set(1); // TODO: ¿?
    }

    @Override
    public void procesa(TBool tipo) {
        tipo.tamanio().set(1);
    }

    @Override
    public void procesa(TipoArray tipo) {
        asignaTamanio(tipo.tipoBase());
        tipo.tamanio().set(tipo.tipoBase().tamanio().get() * tipo.longitud());
    }

    @Override
    public void procesa(TipoPointer tipo) {
        // TODO: ¿?
        // asignaTamanio(tipo.tipoBase());
        tipo.tamanio().set(1);
    }

    @Override
    public void procesa(TipoRecord tipo) {
        asignaTamanio(tipo.campos());
        tipo.tamanio().set(tipo.campos().tamanio());
    }

    @Override
    public void procesa(TipoNuevo tipoNuevo) {
        tipoNuevo.vinculo().procesa(this);
        DecType decType = (DecType) tipoNuevo.vinculo();
        tipoNuevo.tamanio().set(decType.tipo().tamanio());
    }

    @Override
    public void procesa(TNull tNull) {
        tNull.tamanio().set(0); // TODO: ¿?
    }

    @Override
    public void procesa(Campo campo) {
        asignaTamanio(campo.tipo());
        campo.tamanio().set(campo.tipo().tamanio());
    }

    @Override
    public void procesa(CamposUno campos) {
        asignaTamanio(campos.campo());
        campos.tamanio().set(campos.campo().tamanio());
    }

    @Override
    public void procesa(CamposMuchos campos) {
        asignaTamanio(campos.campos());
        asignaTamanio(campos.campo());
        campos.tamanio().set(campos.campos().tamanio().get() + campos.campo().tamanio().get());
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
        // No hacer nada
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
        instruccion.instruccionesOpt().procesa(this);
    }

    @Override
    public void procesa(InstruccionIfElse instruccion) {
        instruccion.instruccionesOptIf().procesa(this);
        instruccion.instruccionesOptElse().procesa(this);
    }

    @Override
    public void procesa(InstruccionWhile instruccion) {
        instruccion.instruccionesOpt().procesa(this);
    }

    @Override
    public void procesa(InstruccionRead instruccion) {
        // No hacer nada
    }

    @Override
    public void procesa(InstruccionWrite instruccion) {
        // No hacer nada
    }

    @Override
    public void procesa(InstruccionNL instruccion) {
        // No hacer nada
    }

    @Override
    public void procesa(InstruccionNew instruccion) {
        // No hacer nada
    }

    @Override
    public void procesa(InstruccionDelete instruccion) {
        // No hacer nada
    }

    @Override
    public void procesa(InstruccionCall instruccion) {
        // No hacer nada
    }

    @Override
    public void procesa(InstruccionBloque instrucciones) {
        int antiguaDir = direccion;
        nivel++;
        direccion = 0;
        instrucciones.bloque().procesa(this);
        instrucciones.tamanio().set(direccion);
        direccion = antiguaDir;
        nivel--;
    }

    @Override
    public void procesa(BloqueVacio bloque) {
        // No hacer nada
    }

    @Override
    public void procesa(BloqueLleno bloques) {
        bloques.programa().procesa(this);
    }

    @Override
    public void procesa(ExpresionesNinguna expresiones) {
        // No hacer nada
    }

    @Override
    public void procesa(ExpresionesUna expresiones) {
        // No hacer nada
    }

    @Override
    public void procesa(ExpresionesMuchas expresiones) {
        // No hacer nada
    }

    @Override
    public void procesa(Suma suma) {
        // No hacer nada
    }

    @Override
    public void procesa(Resta resta) {
        // No hacer nada
    }

    @Override
    public void procesa(Multiplicacion multiplicacion) {
        // No hacer nada
    }

    @Override
    public void procesa(Division division) {
        // No hacer nada
    }

    @Override
    public void procesa(PorCiento porCiento) {
        // No hacer nada
    }

    @Override
    public void procesa(Menos menos) {
        // No hacer nada
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
        // No hacer nada
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
        // No hacer nada
    }

    @Override
    public void procesa(Or or) {
        // No hacer nada
    }

    @Override
    public void procesa(Not not) {
        // No hacer nada
    }

    @Override
    public void procesa(Menor menor) {
        // No hacer nada
    }

    @Override
    public void procesa(MenorIgual menorIgual) {
        // No hacer nada
    }

    @Override
    public void procesa(Mayor mayor) {
        // No hacer nada
    }

    @Override
    public void procesa(MayorIgual mayorIgual) {
        // No hacer nada
    }

    @Override
    public void procesa(Igual igual) {
        // No hacer nada
    }

    @Override
    public void procesa(Distinto distinto) {
        // No hacer nada
    }

    @Override
    public void procesa(AccesoArray accesoArray) {
        // No hacer nada
    }

    @Override
    public void procesa(Punto punto) {
        // No hacer nada
    }

    @Override
    public void procesa(Flecha flecha) {
        // No hacer nada
    }

    @Override
    public void procesa(ValorPuntero valorPuntero) {
        // No hacer nada
    }
}
