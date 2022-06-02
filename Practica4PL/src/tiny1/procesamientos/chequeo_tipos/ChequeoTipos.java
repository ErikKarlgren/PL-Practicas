package tiny1.procesamientos.chequeo_tipos;

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

public class ChequeoTipos implements Procesador {

    private Tipo refExc(Tipo tipo) {
    }

    private Tipo ambosTiposOk(Nodo nodo1, Nodo nodo2) {
        if (nodo1.getTipo() instanceof TipoOk && nodo2.getTipo() instanceof TipoOk)
            return new TipoOk();
        else
            return new TipoError();
    }

    private boolean sonCompatibles(Tipo tipo1, Tipo tipo2) {
        throw new UnsupportedOperationException("son compatibles");
    }

    @Override
    public void procesa(ProgramaConDecs programa) {
        programa.declaraciones().procesa(this);
        programa.instrucciones().procesa(this);
        programa.setTipo(ambosTiposOk(programa.declaraciones(), programa.instrucciones()));
    }

    @Override
    public void procesa(ProgramaSinDecs programa) {
        programa.instrucciones().procesa(this);
        programa.setTipo(programa.instrucciones().getTipo());
    }

    @Override
    public void procesa(DecsUna declaraciones) {
        declaraciones.declaracion().procesa(this);
        declaraciones.setTipo(declaraciones.declaracion().getTipo());
    }

    @Override
    public void procesa(DecsMuchas declaraciones) {
        declaraciones.declaraciones().procesa(this);
        declaraciones.declaracion().procesa(this);
        declaraciones.setTipo(ambosTiposOk(declaraciones.declaraciones(), declaraciones.declaracion()));
    }

    @Override
    public void procesa(DecType decType) {
        decType.tipo().procesa(this);
        decType.setTipo(decType.tipo().getTipo());
    }

    @Override
    public void procesa(DecVar decVar) {
        decVar.tipo().procesa(this);
        decVar.setTipo(decVar.tipo().getTipo());
    }

    @Override
    public void procesa(DecProc decProc) {
        decProc.listaParams().procesa(this);
        decProc.bloque().procesa(this);
        decProc.setTipo(ambosTiposOk(decProc.listaParams(), decProc.bloque()));
    }

    @Override
    public void procesa(ParamsSin parametros) {
        parametros.setTipo(new TipoOk());
    }

    @Override
    public void procesa(ParamValor parametro) {
        parametro.tipo().procesa(this);
        parametro.setTipo(parametro.tipo().getTipo());
    }

    @Override
    public void procesa(ParamRef parametro) {
        parametro.tipo().procesa(this);
        parametro.setTipo(parametro.tipo().getTipo());
    }

    @Override
    public void procesa(ListaParamsUno listaParametros) {
        listaParametros.parametro().procesa(this);
        listaParametros.setTipo(listaParametros.parametro().getTipo());
    }

    @Override
    public void procesa(ListaParamsMuchos listaParametros) {
        listaParametros.listaParametros().procesa(this);
        listaParametros.parametro().procesa(this);
        listaParametros.setTipo(ambosTiposOk(listaParametros.listaParametros(), listaParametros.parametro()));
    }

    @Override
    public void procesa(TInt tipo) {
        tipo.setTipo(new TInt());
    }

    @Override
    public void procesa(TReal tipo) {
        tipo.setTipo(new TReal());
    }

    @Override
    public void procesa(TString tipo) {
        tipo.setTipo(new TString());
    }

    @Override
    public void procesa(TBool tipo) {
        tipo.setTipo(new TBool());
    }

    @Override
    public void procesa(TipoArray tipo) {
        tipo.tipoBase().procesa(this);
        if (tipo.longitud() < 0)
            tipo.setTipo(new TipoError());
        else
            tipo.setTipo(new TipoArray(tipo.longitud(), tipo.tipoBase()));
    }

    @Override
    public void procesa(TipoPointer tipo) {
        tipo.tipoBase().procesa(this);
        tipo.setTipo(tipo.tipoBase());
    }

    @Override
    public void procesa(TipoRecord tipo) {
        tipo.campos().procesa(this);
        tipo.setTipo(tipo.campos().getTipo());
    }

    @Override
    public void procesa(TipoNuevo tipoNuevo) {
        if (tipoNuevo.vinculo() instanceof DecType)
            tipoNuevo.setTipo(new TipoOk());
        else
            tipoNuevo.setTipo(new TipoError());
        // TODO: ¿Hay que lanzar una excepción?
    }

    @Override
    public void procesa(Campo campo) {
        campo.tipo().procesa(this);
        campo.setTipo(campo.tipo().getTipo());
    }

    @Override
    public void procesa(CamposUno campos) {
        campos.campo().procesa(this);
        campos.setTipo(campos.campo().getTipo());
    }

    @Override
    public void procesa(CamposMuchos campos) {
        campos.campos().procesa(this);
        campos.campo().procesa(this);
        campos.setTipo(ambosTiposOk(campos.campos(), campos.campo()));
    }

    @Override
    public void procesa(InstrUna instrucciones) {
        instrucciones.instruccion().procesa(this);
        instrucciones.setTipo(instrucciones.instruccion().getTipo());
    }

    @Override
    public void procesa(InstrMuchas instrucciones) {
        instrucciones.instrucciones().procesa(this);
        instrucciones.instruccion().procesa(this);
        instrucciones.setTipo(ambosTiposOk(instrucciones.instrucciones(), instrucciones.instruccion()));
    }

    @Override
    public void procesa(InstrAsignacion instruccion) {
        instruccion.expresionIzquierda().procesa(this);
        instruccion.expresionDerecha().procesa(this);
        if (sonCompatibles(instruccion.expresionIzquierda().getTipo(), instruccion.expresionDerecha().getTipo()))
            instruccion.setTipo(new TipoOk());
        else
            instruccion.setTipo(new TipoError());
    }

    @Override
    public void procesa(InstrOptNinguna instruccionOpt) {
        instruccionOpt.setTipo(new TipoOk());
    }

    @Override
    public void procesa(InstrOptMuchas instruccionesOpt) {
        instruccionesOpt.instrucciones().procesa(this);
        instruccionesOpt.setTipo(instruccionesOpt.instrucciones().getTipo());
    }

    @Override
    public void procesa(InstruccionIf instruccion) {
        instruccion.expresion().procesa(this);
        instruccion.instruccionesOpt().procesa(this);
        Tipo t = refExc(instruccion.expresion().getTipo());
        if (t instanceof TBool &&
                instruccion.instruccionesOpt().getTipo() instanceof TipoOk)
            instruccion.setTipo(new TipoError());
        else
            instruccion.setTipo(new TipoOk());
    }

    @Override
    public void procesa(InstruccionIfElse instruccion) {
        instruccion.expresion().procesa(this);
        instruccion.instruccionesOptIf().procesa(this);
        instruccion.instruccionesOptElse().procesa(this);
        Tipo t = refExc(instruccion.expresion().getTipo());
        if (t instanceof TBool
                && instruccion.instruccionesOptIf().getTipo() instanceof TipoOk
                && instruccion.instruccionesOptElse().getTipo() instanceof TipoOk)
            instruccion.setTipo(new TipoError());
        else
            instruccion.setTipo(new TipoOk());
    }

    @Override
    public void procesa(InstruccionWhile instruccion) {
        instruccion.expresion().procesa(this);
        instruccion.instruccionesOpt().procesa(this);
        Tipo t = refExc(instruccion.expresion().getTipo());
        if (t instanceof TBool &&
                instruccion.instruccionesOpt().getTipo() instanceof TipoOk)
            instruccion.setTipo(new TipoError());
        else
            instruccion.setTipo(new TipoOk());
    }

    @Override
    public void procesa(InstruccionRead instruccion) {
        instruccion.expresion().procesa(this);
        Tipo t = refExc(instruccion.expresion().getTipo());
        if (instruccion.expresion().esDesignador()
                && (t instanceof TInt
                        || t instanceof TReal
                        || t instanceof TBool))
            instruccion.setTipo(new TipoOk());
        else
            instruccion.setTipo(new TipoError());
    }

    @Override
    public void procesa(InstruccionWrite instruccion) {
        instruccion.expresion().procesa(this);
        Tipo t = refExc(instruccion.expresion().getTipo());
        if (t instanceof TInt
                || t instanceof TReal
                || t instanceof TString
                || t instanceof TBool)
            instruccion.setTipo(new TipoOk());
        else
            instruccion.setTipo(new TipoError());
    }

    @Override
    public void procesa(InstruccionNL instruccion) {
        instruccion.setTipo(new TipoOk());
    }

    @Override
    public void procesa(InstruccionNew instruccion) {
        instruccion.expresion().procesa(this);
        Tipo t = refExc(instruccion.expresion().getTipo());
        if (t instanceof TipoPointer)
            instruccion.setTipo(new TipoOk());
        else
            instruccion.setTipo(new TipoError());
    }

    @Override
    public void procesa(InstruccionDelete instruccion) {
        instruccion.expresion().procesa(this);
        Tipo t = refExc(instruccion.expresion().getTipo());
        if (t instanceof TipoPointer)
            instruccion.setTipo(new TipoOk());
        else
            instruccion.setTipo(new TipoError());
    }

    @Override
    public void procesa(InstruccionCall instruccion) {
        if (instruccion.vinculo() instanceof DecProc) {
            DecProc proc = (DecProc) (instruccion.vinculo());
            if (proc.listaParams().size() != instruccion.parametros().size()){

            } else {
                for (int i = 0; i < proc.parametros().size(); i++) {
                    instruccion.expresiones().get(i).procesa(this);
                    Tipo t = refExc(instruccion.expresiones().get(i).getTipo());
                    if (!sonCompatibles(proc.parametros().get(i).getTipo(), t)) {
                        instruccion.setTipo(new TipoError());
                        return;
                    }
                }
                instruccion.setTipo(new TipoOk());
            } else {
                instruccion.setTipo(new TipoError());
            }
        }
    }

    private Tipo chequeoParametros(ExpresionesNinguna exps, ParamsSin params) {
        return new TipoOk();
    }

    private Tipo chequeoParametros(ExpresionesUna exp, ListaParamsUno param) {
        return chequeoParametro(exp.expresion(), param.parametro());
    }

    private Tipo chequeoParametros(ExpresionesMuchas exps, ListaParamsMuchos params) {
        return ambosOk(chequeoParametros(exps.expresiones(), params.listaParametros()),
                chequeoParametro(exps.expresion(), params.parametro()));
    }

    private Tipo chequeoParametro(Expresion exp, Parametro param) {
        if (param instanceof ParamValor)
            return chequeoParametro(exp, ((ParamValor) param));
        else
            return chequeoParametro(exp, ((ParamRef) param));
    }

    private Tipo chequeoParametro(Expresion exp, ParamValor param) {

    }

    private Tipo chequeoParametro(Expresion exp, ParamRef param) {

    }

    @Override
    public void procesa(InstruccionBloque instrucciones) {
        instrucciones.bloque().procesa(this);
        instrucciones.setTipo(instrucciones.bloque().getTipo());
    }

    @Override
    public void procesa(BloqueVacio bloque) {
        bloque.setTipo(new TipoOk());
    }

    @Override
    public void procesa(BloqueLleno bloques) {
        bloques.programa().procesa(this);
        bloques.setTipo(bloques.programa().getTipo());
    }

    @Override
    public void procesa(ExpresionesNinguna expresiones) {
        expresiones.setTipo(new TipoOk());
    }

    @Override
    public void procesa(ExpresionesUna expresiones) {
        expresiones.expresion().procesa(this);
        expresiones.setTipo(expresiones.expresion().getTipo());
    }

    @Override
    public void procesa(ExpresionesMuchas expresiones) {
        expresiones.expresiones().procesa(this);
        expresiones.expresion().procesa(this);
        expresiones.setTipo(ambosTiposOk(
                expresiones.expresiones().getTipo(),
                expresiones.expresion().getTipo()));
    }

    @Override
    public void procesa(Suma suma) {
        suma.arg0().procesa(this);
        suma.arg1().procesa(this);
        Tipo t1 = refExc(suma.arg0().getTipo());
        Tipo t2 = refExc(suma.arg1().getTipo());

        if (t1 instanceof TInt && t2 instanceof TInt)
            suma.setTipo(new TInt());
        else if (t1 instanceof TReal && (t2 instanceof TReal || t2 instanceof TInt))
            suma.setTipo(new TReal());
        else if (t2 instanceof TReal && (t1 instanceof TReal || t1 instanceof TInt))
            suma.setTipo(new TReal());
        else
            suma.setTipo(new TipoError());
    }

    @Override
    public void procesa(Resta resta) {
        resta.arg0().procesa(this);
        resta.arg1().procesa(this);
        Tipo t1 = refExc(resta.arg0().getTipo());
        Tipo t2 = refExc(resta.arg1().getTipo());

        if (t1 instanceof TInt && t2 instanceof TInt)
            resta.setTipo(new TInt());
        else if (t1 instanceof TReal && (t2 instanceof TReal || t2 instanceof TInt))
            resta.setTipo(new TReal());
        else if (t2 instanceof TReal && (t1 instanceof TReal || t1 instanceof TInt))
            resta.setTipo(new TReal());
        else
            resta.setTipo(new TipoError());
    }

    @Override
    public void procesa(Multiplicacion multiplicacion) {
        multiplicacion.arg0().procesa(this);
        multiplicacion.arg1().procesa(this);
        Tipo t1 = refExc(multiplicacion.arg0().getTipo());
        Tipo t2 = refExc(multiplicacion.arg1().getTipo());

        if (t1 instanceof TInt && t2 instanceof TInt)
            multiplicacion.setTipo(new TInt());
        else if (t1 instanceof TReal && (t2 instanceof TReal || t2 instanceof TInt))
            multiplicacion.setTipo(new TReal());
        else if (t2 instanceof TReal && (t1 instanceof TReal || t1 instanceof TInt))
            multiplicacion.setTipo(new TReal());
        else
            multiplicacion.setTipo(new TipoError());
    }

    @Override
    public void procesa(Division division) {
        division.arg0().procesa(this);
        division.arg1().procesa(this);
        Tipo t1 = refExc(division.arg0().getTipo());
        Tipo t2 = refExc(division.arg1().getTipo());

        if (t1 instanceof TInt && t2 instanceof TInt)
            division.setTipo(new TInt());
        else if (t1 instanceof TReal && (t2 instanceof TReal || t2 instanceof TInt))
            division.setTipo(new TReal());
        else if (t2 instanceof TReal && (t1 instanceof TReal || t1 instanceof TInt))
            division.setTipo(new TReal());
        else
            division.setTipo(new TipoError());
    }

    @Override
    public void procesa(PorCiento porciento) {
        porciento.arg0().procesa(this);
        porciento.arg1().procesa(this);
        Tipo t1 = refExc(porciento.arg0().getTipo());
        Tipo t2 = refExc(porciento.arg1().getTipo());

        if (t1 instanceof TInt && t2 instanceof TInt)
            porciento.setTipo(new TInt());
        else
            porciento.setTipo(new TipoError());
    }

    @Override
    public void procesa(Menos menos) {
        menos.arg().procesa(this);
        Tipo t = refExc(menos.arg().getTipo());
        if (t instanceof TInt || t instanceof TReal)
            menos.setTipo(t);
        else
            menos.setTipo(new TipoError());
    }

    @Override
    public void procesa(NumeroEntero numero) {
        numero.setTipo(new TInt());
    }

    @Override
    public void procesa(NumeroReal numero) {
        numero.setTipo(new TReal());
    }

    @Override
    public void procesa(Identificador identificador) {
        if (identificador.vinculo() instanceof DecVar) {
            DecVar dec = (DecVar) (identificador.vinculo());
            identificador.setTipo(dec.getTipo());
        } else {
            identificador.setTipo(new TipoError());
        }
    }

    @Override
    public void procesa(True booleanoTrue) {
        booleanoTrue.setTipo(new TBool());
    }

    @Override
    public void procesa(False booleanoFalse) {
        booleanoFalse.setTipo(new TBool());
    }

    @Override
    public void procesa(Null nulo) {
        nulo.setTipo(new TNull());
    }

    @Override
    public void procesa(Cadena cadena) {
        cadena.setTipo(new TString());
    }

    @Override
    public void procesa(And and) {
        and.arg0().procesa(this);
        and.arg1().procesa(this);
        Tipo t1 = refExc(and.arg0().getTipo());
        Tipo t2 = refExc(and.arg1().getTipo());

        if (t1 instanceof TBool && t2 instanceof TBool)
            and.setTipo(new TBool());
        else
            and.setTipo(new TipoError());
    }

    @Override
    public void procesa(Or or) {
        or.arg0().procesa(this);
        or.arg1().procesa(this);
        Tipo t1 = refExc(or.arg0().getTipo());
        Tipo t2 = refExc(or.arg1().getTipo());

        if (t1 instanceof TBool && t2 instanceof TBool)
            or.setTipo(new TBool());
        else
            or.setTipo(new TipoError());
    }

    @Override
    public void procesa(Not or) {
        or.arg().procesa(this);
        Tipo t = refExc(or.arg().getTipo());
        if (t instanceof TBool)
            or.setTipo(t);
        else
            or.setTipo(new TipoError());
    }

    @Override
    public void procesa(Menor menor) {
        menor.arg0().procesa(this);
        menor.arg1().procesa(this);
        Tipo t1 = refExc(menor.arg0().getTipo());
        Tipo t2 = refExc(menor.arg1().getTipo());

        if (t1 instanceof TBool && t2 instanceof TBool)
            menor.setTipo(new TBool());
        else if ((t1 instanceof TReal || t1 instanceof TInt) && (t2 instanceof TReal || t2 instanceof TInt))
            menor.setTipo(new TBool());
        else if (t1 instanceof TString && t2 instanceof TString)
            menor.setTipo(new TBool());
        else
            menor.setTipo(new TipoError());
    }

    @Override
    public void procesa(MenorIgual menorIgual) {
        menorIgual.arg0().procesa(this);
        menorIgual.arg1().procesa(this);
        Tipo t1 = refExc(menorIgual.arg0().getTipo());
        Tipo t2 = refExc(menorIgual.arg1().getTipo());

        if (t1 instanceof TBool && t2 instanceof TBool)
            menorIgual.setTipo(new TBool());
        else if ((t1 instanceof TReal || t1 instanceof TInt) && (t2 instanceof TReal || t2 instanceof TInt))
            menorIgual.setTipo(new TBool());
        else if (t1 instanceof TString && t2 instanceof TString)
            menorIgual.setTipo(new TBool());
        else
            menorIgual.setTipo(new TipoError());
    }

    @Override
    public void procesa(Mayor mayor) {
        mayor.arg0().procesa(this);
        mayor.arg1().procesa(this);
        Tipo t1 = refExc(mayor.arg0().getTipo());
        Tipo t2 = refExc(mayor.arg1().getTipo());

        if (t1 instanceof TBool && t2 instanceof TBool)
            mayor.setTipo(new TBool());
        else if ((t1 instanceof TReal || t1 instanceof TInt) && (t2 instanceof TReal || t2 instanceof TInt))
            mayor.setTipo(new TBool());
        else if (t1 instanceof TString && t2 instanceof TString)
            mayor.setTipo(new TBool());
        else
            mayor.setTipo(new TipoError());
    }

    @Override
    public void procesa(MayorIgual mayorIgual) {
        mayorIgual.arg0().procesa(this);
        mayorIgual.arg1().procesa(this);
        Tipo t1 = refExc(mayorIgual.arg0().getTipo());
        Tipo t2 = refExc(mayorIgual.arg1().getTipo());

        if (t1 instanceof TBool && t2 instanceof TBool)
            mayorIgual.setTipo(new TBool());
        else if ((t1 instanceof TReal || t1 instanceof TInt) && (t2 instanceof TReal || t2 instanceof TInt))
            mayorIgual.setTipo(new TBool());
        else if (t1 instanceof TString && t2 instanceof TString)
            mayorIgual.setTipo(new TBool());
        else
            mayorIgual.setTipo(new TipoError());
    }

    @Override
    public void procesa(Igual igual) {
        igual.arg0().procesa(this);
        igual.arg1().procesa(this);
        Tipo t1 = refExc(igual.arg0().getTipo());
        Tipo t2 = refExc(igual.arg1().getTipo());

        if (t1 instanceof TBool && t2 instanceof TBool) {
            igual.setTipo(new TBool());
        } else if ((t1 instanceof TReal || t1 instanceof TInt)
                && (t2 instanceof TReal || t2 instanceof TInt)) {
            igual.setTipo(new TBool());
        } else if (t1 instanceof TString && t2 instanceof TString) {
            igual.setTipo(new TBool());
        } else if ((t1 instanceof TNull || t1 instanceof TipoPointer)
                && (t2 instanceof TNull || t2 instanceof TipoPointer)) {
            igual.setTipo(new TBool());
        } else
            igual.setTipo(new TipoError());
    }

    @Override
    public void procesa(Distinto distinto) {
        distinto.arg0().procesa(this);
        distinto.arg1().procesa(this);
        Tipo t1 = refExc(distinto.arg0().getTipo());
        Tipo t2 = refExc(distinto.arg1().getTipo());

        if (t1 instanceof TBool && t2 instanceof TBool) {
            distinto.setTipo(new TBool());
        } else if ((t1 instanceof TReal || t1 instanceof TInt)
                && (t2 instanceof TReal || t2 instanceof TInt)) {
            distinto.setTipo(new TBool());
        } else if (t1 instanceof TString && t2 instanceof TString) {
            distinto.setTipo(new TBool());
        } else if ((t1 instanceof TNull || t1 instanceof TipoPointer)
                && (t2 instanceof TNull || t2 instanceof TipoPointer)) {
            distinto.setTipo(new TBool());
        } else
            distinto.setTipo(new TipoError());
    }

    @Override
    public void procesa(AccesoArray accesoArray) {
        accesoArray.arg0().procesa(this);
        accesoArray.arg1().procesa(this);
        Tipo t1 = refExc(accesoArray.arg0().getTipo());
        Tipo t2 = refExc(accesoArray.arg1().getTipo());

        if (t1 instanceof TipoArray && t2 instanceof TInt) {
            accesoArray.setTipo(((TipoArray) t1).getTipo());
        } else {
            accesoArray.setTipo(new TipoError());
        }
    }

    @Override
    public void procesa(Punto punto) {
        punto.arg0().procesa(this);
        String campo = punto.arg1();
        Tipo t = refExc(punto.arg0().getTipo());

        if (!(t instanceof TipoRecord)) {
            punto.setTipo(new TipoError());
            return;
        }

        TipoRecord tr = (TipoRecord) t;
        if (existeCampo(tr.campos(), campo)) {
            punto.setTipo(tipoDeCampo(tr.campos(), campo));
        } else {
            punto.setTipo(new TipoError());
        }
    }

    private boolean existeCampo(Campos campos, String campo) {

    }

    private Tipo tipoDeCampo(Campos campos, String campo) {

    }

    @Override
    public void procesa(Flecha flecha) {
        flecha.arg0().procesa(this);
        String campo = flecha.arg1();
        Tipo refT = refExc(flecha.arg0().getTipo());

        if (!(refT instanceof TipoPointer)) {
            flecha.setTipo(new TipoError());
            return;
        }

        TipoPointer tp = (TipoPointer) refT;
        Tipo refPointer = refExc(tp.tipoBase());
        if (!(refPointer instanceof TipoRecord)) {
            flecha.setTipo(new TipoError());
            return;
        }

        TipoRecord tr = (TipoRecord) refPointer;
        if (existeCampo(tr.campos(), campo)) {
            flecha.setTipo(tipoDeCampo(tr.campos(), campo));
        } else {
            flecha.setTipo(new TipoError());
        }
    }

    @Override
    public void procesa(ValorPuntero valorPuntero) {
        valorPuntero.arg().procesa(this);
        Tipo t = refExc(valorPuntero.arg().getTipo());

        if (t instanceof TipoPointer) {
            valorPuntero.setTipo(((TipoPointer) t).tipoBase());
        } else {
            valorPuntero.setTipo(new TipoError());
        }
    }

}
