package tiny1.procesamientos.asigna_espacio;

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
import tiny1.procesamientos.RefExc;

public class AsignaEspacio extends Procesador {

    private int direccion;
    private int nivel;

    public AsignaEspacio() {
        this.direccion = 0;
        this.nivel = 0;
    }

    private void asignaTamanio(Nodo tipo) {
        if (!tipo.tam().isInitialized()) {
            tipo.procesa(this);
        }
    }

    private Tipo refExc(Tipo tipo) {
        return new RefExc().procesar(tipo);
    }

    @Override
    public void procesa(ProgramaConDecs programa) {
        programa.decs().procesa(this);
        programa.instrs().procesa(this);
    }

    @Override
    public void procesa(ProgramaSinDecs programa) {
        programa.instrs().procesa(this);
    }

    @Override
    public void procesa(DecsUna declaraciones) {
        declaraciones.dec().procesa(this);
    }

    @Override
    public void procesa(DecsMuchas declaraciones) {
        declaraciones.decs().procesa(this);
        declaraciones.dec().procesa(this);
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
        direccion += decVar.tipo().tam().get();
    }

    @Override
    public void procesa(DecProc decProc) {
        int antiguaDir = direccion;

        nivel++;
        decProc.nivel().set(nivel);
        direccion = 0;
        decProc.params().procesa(this);
        decProc.bloque().procesa(this);
        decProc.tam().set(direccion);

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
        direccion += parametro.tipo().tam().get();
    }

    @Override
    public void procesa(ParamRef parametro) {
        parametro.direccion().set(direccion);
        parametro.nivel().set(nivel);
        asignaTamanio(parametro.tipo());
        direccion += parametro.tipo().tam().get();
    }

    @Override
    public void procesa(ListaParamsUno listaParametros) {
        listaParametros.param().procesa(this);
    }

    @Override
    public void procesa(ListaParamsMuchos listaParametros) {
        listaParametros.params().procesa(this);
        listaParametros.param().procesa(this);
    }

    @Override
    public void procesa(TInt tipo) {
        tipo.tam().set(1);
    }

    @Override
    public void procesa(TReal tipo) {
        tipo.tam().set(1);
    }

    @Override
    public void procesa(TString tipo) {
        tipo.tam().set(1); // TODO: ¿?
    }

    @Override
    public void procesa(TBool tipo) {
        tipo.tam().set(1);
    }

    @Override
    public void procesa(TipoArray tipo) {
        asignaTamanio(tipo.tipoBase());
        tipo.tam().set(tipo.tipoBase().tam().get() * tipo.longitud());
    }

    @Override
    public void procesa(TipoPointer tipo) {
        // TODO: ¿?
        // asignaTamanio(tipo.tipoBase());
        tipo.tam().set(1);
    }

    @Override
    public void procesa(TipoRecord tipo) {
        asignaTamanio(tipo.campos());
        // if (!tipo.tam().isInitialized()) {
        tipo.tam().set(tipo.campos().tam().get());
        // }
    }

    @Override
    public void procesa(TipoNuevo tipoNuevo) {
        tipoNuevo.vinculo().procesa(this);
        DecType decType = (DecType) tipoNuevo.vinculo();
        tipoNuevo.tam().set(decType.tipo().tam());
    }

    @Override
    public void procesa(TNull tNull) {
        tNull.tam().set(1); // TODO: ¿?
    }

    @Override
    public void procesa(Campo campo) {
        asignaTamanio(campo.tipo());
        campo.tam().set(campo.tipo().tam());
    }

    @Override
    public void procesa(CamposUno campos) {
        asignaTamanio(campos.campo());
        campos.tam().set(campos.campo().tam());
    }

    @Override
    public void procesa(CamposMuchos campos) {
        asignaTamanio(campos.campos());
        asignaTamanio(campos.campo());
        campos.tam().set(campos.campos().tam().get() + campos.campo().tam().get());
    }

    @Override
    public void procesa(InstrUna instrucciones) {
        instrucciones.instr().procesa(this);
    }

    @Override
    public void procesa(InstrMuchas instrucciones) {
        instrucciones.instrs().procesa(this);
        instrucciones.instr().procesa(this);
    }

    @Override
    public void procesa(InstrAsignacion instruccion) {
        instruccion.expIzq().procesa(this);
        instruccion.expDer().procesa(this);
    }

    @Override
    public void procesa(InstrOptNinguna instruccionOpt) {
        // No hacer nada
    }

    @Override
    public void procesa(InstrOptMuchas instruccionesOpt) {
        instruccionesOpt.instrs().procesa(this);
    }

    @Override
    public void procesa(InstruccionIf instruccion) {
        instruccion.exp().procesa(this);
        instruccion.instrsOpt().procesa(this);
    }

    @Override
    public void procesa(InstruccionIfElse instruccion) {
        instruccion.exp().procesa(this);
        instruccion.instrsOptIf().procesa(this);
        instruccion.instrsOptElse().procesa(this);
    }

    @Override
    public void procesa(InstruccionWhile instruccion) {
        instruccion.exp().procesa(this);
        instruccion.instrsOpt().procesa(this);
    }

    @Override
    public void procesa(InstruccionRead instruccion) {
        instruccion.exp().procesa(this);
    }

    @Override
    public void procesa(InstruccionWrite instruccion) {
        instruccion.exp().procesa(this);
    }

    @Override
    public void procesa(InstruccionNL instruccion) {
        // No hacer nada
    }

    @Override
    public void procesa(InstruccionNew instruccion) {
        instruccion.exp().procesa(this);
    }

    @Override
    public void procesa(InstruccionDelete instruccion) {
        instruccion.exp().procesa(this);
    }

    @Override
    public void procesa(InstruccionCall instruccion) {
        instruccion.params().procesa(this);
    }

    @Override
    public void procesa(InstruccionBloque instrucciones) {
        int antiguaDir = direccion;
        nivel++;
        direccion = 0;
        instrucciones.bloque().procesa(this);
        instrucciones.tam().set(direccion);
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
        expresiones.exp().procesa(this);
    }

    @Override
    public void procesa(ExpresionesMuchas expresiones) {
        expresiones.exps().procesa(this);
        expresiones.exp().procesa(this);
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
        asignaTamanio(punto.exp());
        Tipo tipoRefExc = refExc(punto.exp().tipoNodo());
        asignaTamanio(tipoRefExc);
        int desp = new CalcDesplazamiento(punto.campo()).procesar(tipoRefExc);
        punto.desplazamiento().set(desp);
    }

    @Override
    public void procesa(Flecha flecha) {
        asignaTamanio(flecha.exp());
        TipoPointer tipoRefExc = (TipoPointer) refExc(flecha.exp().tipoNodo());
        asignaTamanio(tipoRefExc);
        Tipo tipoBaseRefExc = refExc(tipoRefExc.tipoBase());
        int desp = new CalcDesplazamiento(flecha.campo()).procesar(tipoBaseRefExc);
        flecha.desplazamiento().set(desp);
    }

    @Override
    public void procesa(ValorPuntero valorPuntero) {
        // No hacer nada
    }
}
