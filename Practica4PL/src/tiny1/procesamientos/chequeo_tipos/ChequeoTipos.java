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
import tiny1.errors.GestionErroresTiny;
import tiny1.procesamientos.Procesador;

public class ChequeoTipos implements Procesador {
    private final GestionErroresTiny err;

    public ChequeoTipos(GestionErroresTiny err) {
        this.err = err;
    }


    /* Métodos privados */

    private Tipo refExc(Tipo tipo) {
        return new RefExc().procesar(tipo);
    }

    private int numElementos(Nodo nodo) {
        return new ContarElementos().procesar(nodo);
    }

    private Tipo tipoDeCampo(Campos campos, String nombre) {
        return new TipoDeCampo(nombre).procesar(campos);
    }

    private boolean existeCampo(Campos campos, String nombre) {
        return new ExisteCampo(nombre).procesar(campos);
    }

    private Tipo ambosOk(Tipo tipo1, Tipo tipo2) {
        if (tipo1 instanceof TipoOk && tipo2 instanceof TipoOk)
            return new TipoOk();
        else {
            err.errorProcesamiento("Ambos tipos no son OK " +
                    String.format("(tipo1: %s, tipo2: %s)", tipo1, tipo2));
            return new TipoError();
        }
    }

    private boolean sonCompatibles(Tipo tipo1, Tipo tipo2) {
        throw new UnsupportedOperationException("son compatibles");
    }


    /* Métodos del procesador */

    @Override
    public void procesa(ProgramaConDecs programa) {
        programa.declaraciones().procesa(this);
        programa.instrucciones().procesa(this);
        programa.setTipo(ambosOk(
                programa.declaraciones().getTipo(),
                programa.instrucciones().getTipo()));
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
        declaraciones.setTipo(ambosOk(
                declaraciones.declaraciones().getTipo(),
                declaraciones.declaracion().getTipo()));
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
        decProc.setTipo(ambosOk(
                decProc.listaParams().getTipo(),
                decProc.bloque().getTipo()));
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
        listaParametros.setTipo(ambosOk(
                listaParametros.listaParametros().getTipo(),
                listaParametros.parametro().getTipo()));
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
        if (tipo.longitud() < 0) {
            err.errorProcesamiento("Longitud negativa para tipo array = " + tipo.longitud());
            tipo.setTipo(new TipoError());
        } else
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
        else {
            err.errorProcesamiento("Tipo nuevo no está vinculado a un tipo");
            tipoNuevo.setTipo(new TipoError());
        }
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
        campos.setTipo(ambosOk(
                campos.campos().getTipo(),
                campos.campo().getTipo()));
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
        instrucciones.setTipo(ambosOk(
                instrucciones.instrucciones().getTipo(),
                instrucciones.instruccion().getTipo()));
    }

    @Override
    public void procesa(InstrAsignacion instruccion) {
        instruccion.expresionIzquierda().procesa(this);
        instruccion.expresionDerecha().procesa(this);
        if (sonCompatibles(instruccion.expresionIzquierda().getTipo(), instruccion.expresionDerecha().getTipo()))
            instruccion.setTipo(new TipoOk());
        else {
            err.errorProcesamiento("No se puede asignar una expresión de tipo "
                    + instruccion.expresionDerecha().getTipo()
                    + " a una expresión de tipo "
                    + instruccion.expresionIzquierda().getTipo());
            instruccion.setTipo(new TipoError());
        }
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

        if (t instanceof TBool && instruccion.instruccionesOpt().getTipo() instanceof TipoOk) {
            instruccion.setTipo(new TipoOk());
            return;
        } else if (!(t instanceof TBool)) {
            err.errorProcesamiento("Tipo de expresión de if no es booleana");
        } else {
            err.errorProcesamiento("Tipo de instrucciones if no es correcto");
        }
        instruccion.setTipo(new TipoError());
    }

    @Override
    public void procesa(InstruccionIfElse instruccion) {
        instruccion.expresion().procesa(this);
        instruccion.instruccionesOptIf().procesa(this);
        instruccion.instruccionesOptElse().procesa(this);
        Tipo t = refExc(instruccion.expresion().getTipo());

        if (t instanceof TBool
                && instruccion.instruccionesOptIf().getTipo() instanceof TipoOk
                && instruccion.instruccionesOptElse().getTipo() instanceof TipoOk) {
            instruccion.setTipo(new TipoOk());
            return;
        } else if (!(t instanceof TBool)) {
            err.errorProcesamiento("Tipo de expresión de if no es booleana");
        } else if (!(instruccion.instruccionesOptIf().getTipo() instanceof TipoOk)) {
            err.errorProcesamiento("Tipo de instrucciones if no es correcto");
        } else if (!(instruccion.instruccionesOptElse().getTipo() instanceof TipoOk)) {
            err.errorProcesamiento("Tipo de instrucciones else no es correcto");
        }
        instruccion.setTipo(new TipoError());
    }

    @Override
    public void procesa(InstruccionWhile instruccion) {
        instruccion.expresion().procesa(this);
        instruccion.instruccionesOpt().procesa(this);
        Tipo t = refExc(instruccion.expresion().getTipo());

        if (t instanceof TBool && instruccion.instruccionesOpt().getTipo() instanceof TipoOk) {
            instruccion.setTipo(new TipoOk());
            return;
        } else if (!(t instanceof TBool)) {
            err.errorProcesamiento("Tipo de expresión de while no es booleana");
        } else {
            err.errorProcesamiento("Tipo de instrucciones while no es correcto");
        }
        instruccion.setTipo(new TipoError());
    }

    @Override
    public void procesa(InstruccionRead instruccion) {
        instruccion.expresion().procesa(this);
        Tipo t = refExc(instruccion.expresion().getTipo());

        if (instruccion.expresion().esDesignador()
                && (t instanceof TInt || t instanceof TReal || t instanceof TBool)) {
            instruccion.setTipo(new TipoOk());
            return;
        } else if (!instruccion.expresion().esDesignador()) {
            err.errorProcesamiento("La expresión de read no es un designador");
        } else {
            err.errorProcesamiento("La expresión de read no es de un tipo válido. Tipo = " + t);
        }
        instruccion.setTipo(new TipoError());
    }

    @Override
    public void procesa(InstruccionWrite instruccion) {
        instruccion.expresion().procesa(this);
        Tipo t = refExc(instruccion.expresion().getTipo());
        if (t instanceof TInt || t instanceof TReal || t instanceof TString || t instanceof TBool) {
            instruccion.setTipo(new TipoOk());
        } else {
            err.errorProcesamiento("La expresión de write no es de un tipo válido. Tipo = " + t);
            instruccion.setTipo(new TipoError());
        }
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
        else {
            err.errorProcesamiento("La expresión de new no es de un tipo pointer. Tipo = " + t);
            instruccion.setTipo(new TipoError());
        }
    }

    @Override
    public void procesa(InstruccionDelete instruccion) {
        instruccion.expresion().procesa(this);
        Tipo t = refExc(instruccion.expresion().getTipo());

        if (t instanceof TipoPointer)
            instruccion.setTipo(new TipoOk());
        else {
            err.errorProcesamiento("La expresión de delete no es de un tipo pointer. Tipo = " + t);
            instruccion.setTipo(new TipoError());
        }
    }

    @Override
    public void procesa(InstruccionCall instruccion) {
        if (instruccion.vinculo() instanceof DecProc) {
            DecProc proc = (DecProc) (instruccion.vinculo());
            if (numElementos(instruccion.parametros()) == numElementos(proc.listaParams())) {
                instruccion.setTipo(chequeoParametros(instruccion.parametros(), proc.listaParams()));
            } else {
                err.errorProcesamiento("El número de parámetros de la llamada no es correcto");
                instruccion.setTipo(new TipoError());
            }
        } else {
            err.errorProcesamiento("El vínculo de la llamada no es un procedimiento");
            instruccion.setTipo(new TipoError());
        }
    }

    private Tipo chequeoParametros(Expresiones exps, ListaParams params) {
        if (exps instanceof ExpresionesMuchas && params instanceof ListaParamsMuchos)
            return chequeoParametros((ExpresionesMuchas) exps, (ListaParamsMuchos) params);
        else if (exps instanceof ExpresionesUna && params instanceof ListaParamsUno)
            return chequeoParametros((ExpresionesUna) exps, (ListaParamsUno) params);
        else if (exps instanceof ExpresionesNinguna && params instanceof ParamsSin)
            return chequeoParametros((ExpresionesNinguna) exps, (ParamsSin) params);
        else {
            err.errorProcesamiento("El número de parámetros de la llamada no es correcto");
            return new TipoError();
        }
    }

    private Tipo chequeoParametros(ExpresionesNinguna exps, ParamsSin params) {
        return new TipoOk();
    }

    private Tipo chequeoParametros(ExpresionesUna exp, ListaParamsUno param) {
        return chequeoParametro(exp.expresion(), param.parametro());
    }

    private Tipo chequeoParametros(ExpresionesMuchas exps, ListaParamsMuchos params) {
        Tipo tipoMuchos;
        Expresiones exps1 = exps.expresiones();
        ListaParams params1 = params.listaParametros();

        if (exps1 instanceof ExpresionesMuchas && params1 instanceof ListaParamsMuchos)
            tipoMuchos = chequeoParametros((ExpresionesMuchas) exps, (ListaParamsMuchos) params1);
        else if (exps1 instanceof ExpresionesUna && params1 instanceof ListaParamsUno)
            tipoMuchos = chequeoParametros((ExpresionesUna) exps1, (ListaParamsUno) params1);
        else if (exps1 instanceof ExpresionesNinguna && params1 instanceof ParamsSin)
            tipoMuchos = chequeoParametros((ExpresionesNinguna) exps1, (ParamsSin) params1);
        else {
            err.errorProcesamiento("El número de parámetros de la llamada no es correcto");
            return new TipoError();
        }
        return ambosOk(tipoMuchos, chequeoParametro(exps.expresion(), params.parametro()));
    }

    private Tipo chequeoParametro(Expresion exp, Parametro param) {
        if (param instanceof ParamValor)
            return chequeoParametro(exp, ((ParamValor) param));
        else
            return chequeoParametro(exp, ((ParamRef) param));
    }

    private Tipo chequeoParametro(Expresion exp, ParamValor param) {
        exp.procesa(this);
        if (sonCompatibles(param.getTipo(), exp.getTipo()))
            return new TipoOk();
        else {
            err.errorProcesamiento(
                    String.format("El tipo %s del parámetro %s no es compatible con el tipo %s de la expresión",
                            param.nombre(),
                            param.getTipo(),
                            exp.getTipo()));
            return new TipoError();
        }
    }

    private Tipo chequeoParametro(Expresion exp, ParamRef param) {
        exp.procesa(this);
        if (exp.esDesignador() && sonCompatibles(param.getTipo(), exp.getTipo())) {
            return new TipoOk();
        } else if (!exp.esDesignador()) {
            err.errorProcesamiento(
                    String.format("La expresión para el parámetro %s no es un designador",
                            param.nombre()));
        } else {
            err.errorProcesamiento(
                    String.format("El tipo de parámetro %s no es compatible con el tipo %s de la expresión",
                            param.getTipo(),
                            exp.getTipo()));
        }
        return new TipoError();
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
        expresiones.setTipo(ambosOk(
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
        else {
            err.errorProcesamiento(String.format("No se puede sumar %s con %s", t1, t2));
            suma.setTipo(new TipoError());
        }
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
        else {
            err.errorProcesamiento(String.format("No se puede restar %s con %s", t1, t2));
            resta.setTipo(new TipoError());
        }
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
        else {
            err.errorProcesamiento(String.format("No se puede multiplicar %s con %s", t1, t2));
            multiplicacion.setTipo(new TipoError());
        }
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
        else {
            err.errorProcesamiento(String.format("No se puede dividir %s con %s", t1, t2));
            division.setTipo(new TipoError());
        }
    }

    @Override
    public void procesa(PorCiento porciento) {
        porciento.arg0().procesa(this);
        porciento.arg1().procesa(this);
        Tipo t1 = refExc(porciento.arg0().getTipo());
        Tipo t2 = refExc(porciento.arg1().getTipo());

        if (t1 instanceof TInt && t2 instanceof TInt)
            porciento.setTipo(new TInt());
        else {
            err.errorProcesamiento(String.format("No se puede calcular el módulo de %s con %s", t1, t2));
            porciento.setTipo(new TipoError());
        }
    }

    @Override
    public void procesa(Menos menos) {
        menos.arg().procesa(this);
        Tipo t = refExc(menos.arg().getTipo());
        if (t instanceof TInt || t instanceof TReal)
            menos.setTipo(t);
        else {
            err.errorProcesamiento(String.format("No se puede calcular el negativo de %s", t));
            menos.setTipo(new TipoError());
        }
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
            err.errorProcesamiento(
                    String.format("El identificador %s no se corresponde con una variable",
                            identificador.id()));
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
        else {
            err.errorProcesamiento(String.format("No se puede operar '%s and %s'", t1, t2));
            and.setTipo(new TipoError());
        }
    }

    @Override
    public void procesa(Or or) {
        or.arg0().procesa(this);
        or.arg1().procesa(this);
        Tipo t1 = refExc(or.arg0().getTipo());
        Tipo t2 = refExc(or.arg1().getTipo());

        if (t1 instanceof TBool && t2 instanceof TBool)
            or.setTipo(new TBool());
        else {
            err.errorProcesamiento(String.format("No se puede operar '%s or %s'", t1, t2));
            or.setTipo(new TipoError());
        }
    }

    @Override
    public void procesa(Not or) {
        or.arg().procesa(this);
        Tipo t = refExc(or.arg().getTipo());
        if (t instanceof TBool)
            or.setTipo(t);
        else {
            err.errorProcesamiento(String.format("No se puede operar 'not %s'", t));
            or.setTipo(new TipoError());
        }
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
        else {
            err.errorProcesamiento(String.format("No se puede operar '%s < %s'", t1, t2));
            menor.setTipo(new TipoError());
        }
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
        else {
            err.errorProcesamiento(String.format("No se puede operar '%s <= %s'", t1, t2));
            menorIgual.setTipo(new TipoError());
        }
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
        else {
            err.errorProcesamiento(String.format("No se puede operar '%s > %s'", t1, t2));
            mayor.setTipo(new TipoError());
        }
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
        else {
            err.errorProcesamiento(String.format("No se puede operar '%s >= %s'", t1, t2));
            mayorIgual.setTipo(new TipoError());
        }
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
        } else {
            err.errorProcesamiento(String.format("No se puede operar '%s == %s'", t1, t2));
            igual.setTipo(new TipoError());
        }
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
        } else {
            err.errorProcesamiento(String.format("No se puede operar '%s != %s'", t1, t2));
            distinto.setTipo(new TipoError());
        }
    }

    @Override
    public void procesa(AccesoArray accesoArray) {
        accesoArray.arg0().procesa(this);
        accesoArray.arg1().procesa(this);
        Tipo t1 = refExc(accesoArray.arg0().getTipo());
        Tipo t2 = refExc(accesoArray.arg1().getTipo());

        if (t1 instanceof TipoArray && t2 instanceof TInt) {
            accesoArray.setTipo(((TipoArray) t1).getTipo());
        } else if (!(t1 instanceof TipoArray)) {
            err.errorProcesamiento(String
                    .format("No se puede acceder a un elemento de una variable que no es un array '%s[%s]'", t1, t2));
            accesoArray.setTipo(new TipoError());
        } else {
            err.errorProcesamiento(String.format("No se puede acceder a un elemento de un array '%s[%s]'", t1, t2));
            accesoArray.setTipo(new TipoError());
        }
    }

    @Override
    public void procesa(Punto punto) {
        punto.arg0().procesa(this);
        String campo = punto.arg1();
        Tipo t = refExc(punto.arg0().getTipo());

        if (!(t instanceof TipoRecord)) {
            err.errorProcesamiento(
                    String.format("No se puede acceder a un campo de una variable que no es un record '%s.%s'",
                            t, campo));
            punto.setTipo(new TipoError());
            return;
        }

        TipoRecord tr = (TipoRecord) t;
        if (existeCampo(tr.campos(), campo)) {
            punto.setTipo(tipoDeCampo(tr.campos(), campo));
        } else {
            err.errorProcesamiento(
                    String.format("No se puede acceder a un campo que no existe en un record '%s.%s'",
                            t, campo));
            punto.setTipo(new TipoError());
        }
    }

    @Override
    public void procesa(Flecha flecha) {
        flecha.arg0().procesa(this);
        String campo = flecha.arg1();
        Tipo refT = refExc(flecha.arg0().getTipo());

        if (!(refT instanceof TipoPointer)) {
            flecha.setTipo(new TipoError());
            err.errorProcesamiento(
                    String.format("No se puede acceder a un campo de una variable que no es un puntero '%s->%s'",
                            refT, campo));
            return;
        }

        TipoPointer tp = (TipoPointer) refT;
        Tipo refPointer = refExc(tp.tipoBase());
        if (!(refPointer instanceof TipoRecord)) {
            flecha.setTipo(new TipoError());
            err.errorProcesamiento(
                    String.format(
                            "No se puede acceder a un campo de una variable que no es un puntero a un record '%s->%s'",
                            refPointer, campo));
            return;
        }

        TipoRecord tr = (TipoRecord) refPointer;
        if (existeCampo(tr.campos(), campo)) {
            flecha.setTipo(tipoDeCampo(tr.campos(), campo));
        } else {
            flecha.setTipo(new TipoError());
            err.errorProcesamiento(
                    String.format("No se puede acceder a un campo que no existe en un record '%s->%s'",
                            refPointer, campo));
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
            err.errorProcesamiento(
                    String.format("No se puede acceder al valor apuntado por una variable que no sea un puntero '%s'",
                            t));
        }
    }

}
