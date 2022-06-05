package tiny1.procesamientos.chequeo_tipos;

import java.util.List;

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
import tiny1.procesamientos.RefExc;

public class ChequeoTipos extends Procesador {

    private final GestionErroresTiny err;

    public ChequeoTipos() {
        this.err = new GestionErroresTiny();
    }

    @Override
    public boolean foundErrors() {
        return err.foundError();
    }

    /* Métodos privados */

    private Tipo refExc(Tipo tipo) {
        // TipoOk y TipoError no se pueden procesar, porque solo se usan
        // para comprobar tipos.
        if (tipo instanceof TipoOk || tipo instanceof TipoError)
            return tipo;
        else
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
        if (tipo1.isOk() && tipo2.isOk())
            return new TipoOk();
        else {
            err.errorProcesamiento("Ambos tipos no son OK ", tipo1, tipo2);
            return new TipoError();
        }
    }

    private boolean sonCompatibles(Tipo tipo1, Tipo tipo2) {
        Tipo ref1 = refExc(tipo1);
        Tipo ref2 = refExc(tipo2);

        if (ref1 instanceof TInt)
            return ref2 instanceof TInt;
        if (ref1 instanceof TBool)
            return ref2 instanceof TBool;
        if (ref1 instanceof TReal)
            return ref2 instanceof TReal || ref2 instanceof TInt;
        if (ref1 instanceof TString)
            return ref2 instanceof TString;
        if (ref1 instanceof TipoArray && ref2 instanceof TipoArray) {
            TipoArray arr1 = (TipoArray) ref1;
            TipoArray arr2 = (TipoArray) ref2;
            return arr1.longitud() == arr2.longitud()
                    && sonCompatibles(arr1.tipoBase(), arr2.tipoBase());
        }
        if (ref1 instanceof TipoRecord && ref2 instanceof TipoRecord) {
            TipoRecord rec1 = (TipoRecord) ref1;
            TipoRecord rec2 = (TipoRecord) ref2;
            if (numElementos(rec1.campos()) != numElementos(rec2.campos()))
                return false;
            List<Class<? extends Tipo>> tipos1 = new TiposRecord().procesar(rec1);
            List<Class<? extends Tipo>> tipos2 = new TiposRecord().procesar(rec2);

            for (int i = 0; i < tipos1.size(); i++)
                if (!tipos1.get(i).equals(tipos2.get(i)))
                    return false;
            return true;
        }
        if (ref1 instanceof TipoPointer && ref2 instanceof TNull) {
            return true;
        }
        if (ref1 instanceof TipoPointer && ref2 instanceof TipoPointer) {
            TipoPointer ptr1 = (TipoPointer) ref1;
            TipoPointer ptr2 = (TipoPointer) ref2;
            return sonCompatibles(ptr1.tipoBase(), ptr2.tipoBase());
        }
        return false;
    }

    /* Métodos del procesador */

    @Override
    public void procesa(ProgramaConDecs programa) {
        programa.decs().procesa(this);
        programa.instrs().procesa(this);
        programa.setTipoNodo(ambosOk(
                programa.decs().tipoNodo(),
                programa.instrs().tipoNodo()));
    }

    @Override
    public void procesa(ProgramaSinDecs programa) {
        programa.instrs().procesa(this);
        programa.setTipoNodo(programa.instrs().tipoNodo());
    }

    @Override
    public void procesa(DecsUna declaraciones) {
        declaraciones.dec().procesa(this);
        declaraciones.setTipoNodo(declaraciones.dec().tipoNodo());
    }

    @Override
    public void procesa(DecsMuchas declaraciones) {
        declaraciones.decs().procesa(this);
        declaraciones.dec().procesa(this);
        declaraciones.setTipoNodo(ambosOk(
                declaraciones.decs().tipoNodo(),
                declaraciones.dec().tipoNodo()));
    }

    @Override
    public void procesa(DecType decType) {
        decType.tipo().procesa(this);
        decType.setTipoNodo(decType.tipo().tipoNodo());
    }

    @Override
    public void procesa(DecVar decVar) {
        decVar.tipo().procesa(this);
        decVar.setTipoNodo(decVar.tipo().tipoNodo());
    }

    @Override
    public void procesa(DecProc decProc) {
        decProc.params().procesa(this);
        decProc.bloque().procesa(this);
        decProc.setTipoNodo(ambosOk(
                decProc.params().tipoNodo(),
                decProc.bloque().tipoNodo()));
    }

    @Override
    public void procesa(ParamsSin parametros) {
        parametros.setTipoNodo(new TipoOk());
    }

    @Override
    public void procesa(ParamValor parametro) {
        parametro.tipo().procesa(this);
        parametro.setTipoNodo(parametro.tipo().tipoNodo());
    }

    @Override
    public void procesa(ParamRef parametro) {
        parametro.tipo().procesa(this);
        parametro.setTipoNodo(parametro.tipo().tipoNodo());
    }

    @Override
    public void procesa(ListaParamsUno listaParametros) {
        listaParametros.param().procesa(this);
        listaParametros.setTipoNodo(listaParametros.param().tipoNodo());
    }

    @Override
    public void procesa(ListaParamsMuchos listaParametros) {
        listaParametros.params().procesa(this);
        listaParametros.param().procesa(this);
        listaParametros.setTipoNodo(ambosOk(
                listaParametros.params().tipoNodo(),
                listaParametros.param().tipoNodo()));
    }

    @Override
    public void procesa(TInt tipo) {
        tipo.setTipoNodo(new TInt());
    }

    @Override
    public void procesa(TReal tipo) {
        tipo.setTipoNodo(new TReal());
    }

    @Override
    public void procesa(TString tipo) {
        tipo.setTipoNodo(new TString());
    }

    @Override
    public void procesa(TBool tipo) {
        tipo.setTipoNodo(new TBool());
    }

    @Override
    public void procesa(TipoArray tipo) {
        tipo.tipoBase().procesa(this);
        if (tipo.longitud() < 0) {
            err.errorProcesamiento("Longitud negativa para tipo array", tipo);
            tipo.setTipoNodo(new TipoError());
        } else
            tipo.setTipoNodo(new TipoArray(tipo.longitud(), tipo.tipoBase()));
    }

    @Override
    public void procesa(TipoPointer tipo) {
        tipo.tipoBase().procesa(this);
        tipo.setTipoNodo(tipo.tipoBase());
    }

    @Override
    public void procesa(TipoRecord tipo) {
        tipo.campos().procesa(this);
        tipo.setTipoNodo(tipo.campos().tipoNodo());
    }

    @Override
    public void procesa(TipoNuevo tipoNuevo) {
        if (tipoNuevo.vinculo() instanceof DecType) {
            DecType dec = (DecType) tipoNuevo.vinculo();
            tipoNuevo.setTipoNodo(dec.tipo());
        } else {
            err.errorProcesamiento("Tipo nuevo no está vinculado a un tipo", tipoNuevo);
            tipoNuevo.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(TNull tipo) {
        tipo.setTipoNodo(new TNull());
    }

    @Override
    public void procesa(Campo campo) {
        campo.tipo().procesa(this);
        campo.setTipoNodo(campo.tipo().tipoNodo());
    }

    @Override
    public void procesa(CamposUno campos) {
        campos.campo().procesa(this);
        campos.setTipoNodo(campos.campo().tipoNodo());
    }

    @Override
    public void procesa(CamposMuchos campos) {
        campos.campos().procesa(this);
        campos.campo().procesa(this);
        campos.setTipoNodo(ambosOk(
                campos.campos().tipoNodo(),
                campos.campo().tipoNodo()));
    }

    @Override
    public void procesa(InstrUna instrucciones) {
        instrucciones.instr().procesa(this);
        instrucciones.setTipoNodo(instrucciones.instr().tipoNodo());
    }

    @Override
    public void procesa(InstrMuchas instrucciones) {
        instrucciones.instrs().procesa(this);
        instrucciones.instr().procesa(this);
        instrucciones.setTipoNodo(ambosOk(
                instrucciones.instrs().tipoNodo(),
                instrucciones.instr().tipoNodo()));
    }

    @Override
    public void procesa(InstrAsignacion instruccion) {
        instruccion.expIzq().procesa(this);
        instruccion.expDer().procesa(this);
        if (sonCompatibles(instruccion.expIzq().tipoNodo(), instruccion.expDer().tipoNodo()))
            instruccion.setTipoNodo(new TipoOk());
        else {
            err.errorProcesamiento("No se puede asignar una expresión de tipo "
                    + instruccion.expDer().tipoNodo()
                    + " a una expresión de tipo "
                    + instruccion.expIzq().tipoNodo());
            instruccion.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(InstrOptNinguna instruccionOpt) {
        instruccionOpt.setTipoNodo(new TipoOk());
    }

    @Override
    public void procesa(InstrOptMuchas instruccionesOpt) {
        instruccionesOpt.instrs().procesa(this);
        instruccionesOpt.setTipoNodo(instruccionesOpt.instrs().tipoNodo());
    }

    @Override
    public void procesa(InstruccionIf instruccion) {
        instruccion.exp().procesa(this);
        instruccion.instrsOpt().procesa(this);
        Tipo t = refExc(instruccion.exp().tipoNodo());

        if (t instanceof TBool && instruccion.instrsOpt().tipoNodo().isOk()) {
            instruccion.setTipoNodo(new TipoOk());
            return;
        } else if (!(t instanceof TBool)) {
            err.errorProcesamiento("Tipo de expresión de if no es booleana", instruccion);
        } else {
            err.errorProcesamiento("Tipo de instrucciones if no es correcto", instruccion);
        }
        instruccion.setTipoNodo(new TipoError());
    }

    @Override
    public void procesa(InstruccionIfElse instruccion) {
        instruccion.exp().procesa(this);
        instruccion.instrsOptIf().procesa(this);
        instruccion.instrsOptElse().procesa(this);
        Tipo t = refExc(instruccion.exp().tipoNodo());

        if (t instanceof TBool
                && instruccion.instrsOptIf().tipoNodo().isOk()
                && instruccion.instrsOptElse().tipoNodo().isOk()) {
            instruccion.setTipoNodo(new TipoOk());
            return;
        } else if (!(t instanceof TBool)) {
            err.errorProcesamiento("Tipo de expresión de if no es booleana", instruccion);
        } else if (!(instruccion.instrsOptIf().tipoNodo().isOk())) {
            err.errorProcesamiento("Tipo de instrucciones if no es correcto", instruccion);
        } else if (!(instruccion.instrsOptElse().tipoNodo().isOk())) {
            err.errorProcesamiento("Tipo de instrucciones else no es correcto", instruccion);
        }
        instruccion.setTipoNodo(new TipoError());
    }

    @Override
    public void procesa(InstruccionWhile instruccion) {
        instruccion.exp().procesa(this);
        instruccion.instrsOpt().procesa(this);
        Tipo t = refExc(instruccion.exp().tipoNodo());

        if (t instanceof TBool && instruccion.instrsOpt().tipoNodo().isOk()) {
            instruccion.setTipoNodo(new TipoOk());
            return;
        } else if (!(t instanceof TBool)) {
            err.errorProcesamiento("Tipo de expresión de while no es booleana", instruccion);
        } else {
            err.errorProcesamiento("Tipo de instrucciones while no es correcto", instruccion);
        }
        instruccion.setTipoNodo(new TipoError());
    }

    @Override
    public void procesa(InstruccionRead instruccion) {
        instruccion.exp().procesa(this);
        Tipo t = refExc(instruccion.exp().tipoNodo());

        if (instruccion.exp().esDesignador()
                && (t instanceof TInt || t instanceof TReal || t instanceof TString)) {
            instruccion.setTipoNodo(new TipoOk());
            return;
        } else if (!instruccion.exp().esDesignador()) {
            err.errorProcesamiento("La expresión de read no es un designador", instruccion);
        } else {
            err.errorProcesamiento("La expresión de read no es de un tipo válido. Tipo = " + t, instruccion);
        }
        instruccion.setTipoNodo(new TipoError());
    }

    @Override
    public void procesa(InstruccionWrite instruccion) {
        instruccion.exp().procesa(this);
        Tipo t = refExc(instruccion.exp().tipoNodo());
        if (t instanceof TInt || t instanceof TReal || t instanceof TString || t instanceof TBool) {
            instruccion.setTipoNodo(new TipoOk());
        } else {
            err.errorProcesamiento("La expresión de write no es de un tipo válido. Tipo = " + t, instruccion);
            instruccion.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(InstruccionNL instruccion) {
        instruccion.setTipoNodo(new TipoOk());
    }

    @Override
    public void procesa(InstruccionNew instruccion) {
        instruccion.exp().procesa(this);
        Tipo t = refExc(instruccion.exp().tipoNodo());

        if (t instanceof TipoPointer)
            instruccion.setTipoNodo(new TipoOk());
        else {
            err.errorProcesamiento("La expresión de new no es de un tipo pointer. Tipo = " + t, instruccion);
            instruccion.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(InstruccionDelete instruccion) {
        instruccion.exp().procesa(this);
        Tipo t = refExc(instruccion.exp().tipoNodo());

        if (t instanceof TipoPointer)
            instruccion.setTipoNodo(new TipoOk());
        else {
            err.errorProcesamiento("La expresión de delete no es de un tipo pointer. Tipo = " + t, instruccion);
            instruccion.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(InstruccionCall instruccion) {
        if (instruccion.vinculo() instanceof DecProc) {
            DecProc proc = (DecProc) (instruccion.vinculo());
            if (numElementos(instruccion.params()) == numElementos(proc.params())) {
                instruccion.setTipoNodo(chequeoParametros(instruccion.params(), proc.params()));
            } else {
                err.errorProcesamiento("El número de parámetros de la llamada no es correcto", instruccion);
                instruccion.setTipoNodo(new TipoError());
            }
        } else {
            err.errorProcesamiento("El vínculo de la llamada no es un procedimiento", instruccion);
            instruccion.setTipoNodo(new TipoError());
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
            err.errorProcesamiento("El número de parámetros de la llamada no es correcto", exps, params);
            return new TipoError();
        }
    }

    private Tipo chequeoParametros(ExpresionesNinguna exps, ParamsSin params) {
        return new TipoOk();
    }

    private Tipo chequeoParametros(ExpresionesUna exp, ListaParamsUno param) {
        return chequeoParametro(exp.exp(), param.param());
    }

    private Tipo chequeoParametros(ExpresionesMuchas exps, ListaParamsMuchos params) {
        Tipo tipoMuchos;
        Expresiones exps1 = exps.exps();
        ListaParams params1 = params.params();

        if (exps1 instanceof ExpresionesMuchas && params1 instanceof ListaParamsMuchos)
            tipoMuchos = chequeoParametros((ExpresionesMuchas) exps, (ListaParamsMuchos) params1);
        else if (exps1 instanceof ExpresionesUna && params1 instanceof ListaParamsUno)
            tipoMuchos = chequeoParametros((ExpresionesUna) exps1, (ListaParamsUno) params1);
        else if (exps1 instanceof ExpresionesNinguna && params1 instanceof ParamsSin)
            tipoMuchos = chequeoParametros((ExpresionesNinguna) exps1, (ParamsSin) params1);
        else {
            err.errorProcesamiento("El número de parámetros de la llamada no es correcto", exps, params);
            return new TipoError();
        }
        return ambosOk(tipoMuchos, chequeoParametro(exps.exp(), params.param()));
    }

    private Tipo chequeoParametro(Expresion exp, Parametro param) {
        if (param instanceof ParamValor)
            return chequeoParametro(exp, ((ParamValor) param));
        else
            return chequeoParametro(exp, ((ParamRef) param));
    }

    private Tipo chequeoParametro(Expresion exp, ParamValor param) {
        exp.procesa(this);
        if (sonCompatibles(param.tipoNodo(), exp.tipoNodo()))
            return new TipoOk();
        else {
            err.errorProcesamiento(
                    String.format("El tipo %s del parámetro %s no es compatible con el tipo %s de la expresión",
                            param.nombre(),
                            param.tipoNodo(),
                            exp.tipoNodo()),
                    exp, param);
            return new TipoError();
        }
    }

    private Tipo chequeoParametro(Expresion exp, ParamRef param) {
        exp.procesa(this);
        if (exp.esDesignador() && sonCompatibles(param.tipoNodo(), exp.tipoNodo())) {
            return new TipoOk();
        } else if (!exp.esDesignador()) {
            err.errorProcesamiento(
                    String.format("La expresión para el parámetro %s no es un designador",
                            param.nombre()),
                    exp, param);
        } else {
            err.errorProcesamiento(
                    String.format("El tipo de parámetro %s no es compatible con el tipo %s de la expresión",
                            param.tipoNodo(),
                            exp.tipoNodo()),
                    exp, param);
        }
        return new TipoError();
    }

    @Override
    public void procesa(InstruccionBloque instrucciones) {
        instrucciones.bloque().procesa(this);
        instrucciones.setTipoNodo(instrucciones.bloque().tipoNodo());
    }

    @Override
    public void procesa(BloqueVacio bloque) {
        bloque.setTipoNodo(new TipoOk());
    }

    @Override
    public void procesa(BloqueLleno bloques) {
        bloques.programa().procesa(this);
        bloques.setTipoNodo(bloques.programa().tipoNodo());
    }

    @Override
    public void procesa(ExpresionesNinguna expresiones) {
        expresiones.setTipoNodo(new TipoOk());
    }

    @Override
    public void procesa(ExpresionesUna expresiones) {
        expresiones.exp().procesa(this);
        expresiones.setTipoNodo(expresiones.exp().tipoNodo());
    }

    @Override
    public void procesa(ExpresionesMuchas expresiones) {
        expresiones.exps().procesa(this);
        expresiones.exp().procesa(this);
        expresiones.setTipoNodo(ambosOk(
                expresiones.exps().tipoNodo(),
                expresiones.exp().tipoNodo()));
    }

    @Override
    public void procesa(Suma suma) {
        suma.arg0().procesa(this);
        suma.arg1().procesa(this);
        Tipo t1 = refExc(suma.arg0().tipoNodo());
        Tipo t2 = refExc(suma.arg1().tipoNodo());

        if (t1 instanceof TInt && t2 instanceof TInt)
            suma.setTipoNodo(new TInt());
        else if (t1 instanceof TReal && (t2 instanceof TReal || t2 instanceof TInt))
            suma.setTipoNodo(new TReal());
        else if (t2 instanceof TReal && (t1 instanceof TReal || t1 instanceof TInt))
            suma.setTipoNodo(new TReal());
        else {
            err.errorProcesamiento(String.format("No se puede sumar %s con %s", t1, t2));
            suma.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(Resta resta) {
        resta.arg0().procesa(this);
        resta.arg1().procesa(this);
        Tipo t1 = refExc(resta.arg0().tipoNodo());
        Tipo t2 = refExc(resta.arg1().tipoNodo());

        if (t1 instanceof TInt && t2 instanceof TInt)
            resta.setTipoNodo(new TInt());
        else if (t1 instanceof TReal && (t2 instanceof TReal || t2 instanceof TInt))
            resta.setTipoNodo(new TReal());
        else if (t2 instanceof TReal && (t1 instanceof TReal || t1 instanceof TInt))
            resta.setTipoNodo(new TReal());
        else {
            err.errorProcesamiento(String.format("No se puede restar %s con %s", t1, t2));
            resta.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(Multiplicacion multiplicacion) {
        multiplicacion.arg0().procesa(this);
        multiplicacion.arg1().procesa(this);
        Tipo t1 = refExc(multiplicacion.arg0().tipoNodo());
        Tipo t2 = refExc(multiplicacion.arg1().tipoNodo());

        if (t1 instanceof TInt && t2 instanceof TInt)
            multiplicacion.setTipoNodo(new TInt());
        else if (t1 instanceof TReal && (t2 instanceof TReal || t2 instanceof TInt))
            multiplicacion.setTipoNodo(new TReal());
        else if (t2 instanceof TReal && (t1 instanceof TReal || t1 instanceof TInt))
            multiplicacion.setTipoNodo(new TReal());
        else {
            err.errorProcesamiento(String.format("No se puede multiplicar %s con %s", t1, t2));
            multiplicacion.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(Division division) {
        division.arg0().procesa(this);
        division.arg1().procesa(this);
        Tipo t1 = refExc(division.arg0().tipoNodo());
        Tipo t2 = refExc(division.arg1().tipoNodo());

        if (t1 instanceof TInt && t2 instanceof TInt)
            division.setTipoNodo(new TInt());
        else if (t1 instanceof TReal && (t2 instanceof TReal || t2 instanceof TInt))
            division.setTipoNodo(new TReal());
        else if (t2 instanceof TReal && (t1 instanceof TReal || t1 instanceof TInt))
            division.setTipoNodo(new TReal());
        else {
            err.errorProcesamiento(String.format("No se puede dividir %s con %s", t1, t2));
            division.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(PorCiento porciento) {
        porciento.arg0().procesa(this);
        porciento.arg1().procesa(this);
        Tipo t1 = refExc(porciento.arg0().tipoNodo());
        Tipo t2 = refExc(porciento.arg1().tipoNodo());

        if (t1 instanceof TInt && t2 instanceof TInt)
            porciento.setTipoNodo(new TInt());
        else {
            err.errorProcesamiento(String.format("No se puede calcular el módulo de %s con %s", t1, t2));
            porciento.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(Menos menos) {
        menos.arg().procesa(this);
        Tipo t = refExc(menos.arg().tipoNodo());
        if (t instanceof TInt || t instanceof TReal)
            menos.setTipoNodo(t);
        else {
            err.errorProcesamiento(String.format("No se puede calcular el negativo de %s", t));
            menos.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(NumeroEntero numero) {
        numero.setTipoNodo(new TInt());
    }

    @Override
    public void procesa(NumeroReal numero) {
        numero.setTipoNodo(new TReal());
    }

    @Override
    public void procesa(Identificador identificador) {
        if (identificador.vinculo() instanceof DecVar) {
            DecVar dec = (DecVar) (identificador.vinculo());
            identificador.setTipoNodo(dec.tipoNodo());
        } else {
            err.errorProcesamiento(
                    String.format("El identificador %s no se corresponde con una variable",
                            identificador.id()));
            identificador.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(True booleanoTrue) {
        booleanoTrue.setTipoNodo(new TBool());
    }

    @Override
    public void procesa(False booleanoFalse) {
        booleanoFalse.setTipoNodo(new TBool());
    }

    @Override
    public void procesa(Null nulo) {
        nulo.setTipoNodo(new TNull());
    }

    @Override
    public void procesa(Cadena cadena) {
        cadena.setTipoNodo(new TString());
    }

    @Override
    public void procesa(And and) {
        and.arg0().procesa(this);
        and.arg1().procesa(this);
        Tipo t1 = refExc(and.arg0().tipoNodo());
        Tipo t2 = refExc(and.arg1().tipoNodo());

        if (t1 instanceof TBool && t2 instanceof TBool)
            and.setTipoNodo(new TBool());
        else {
            err.errorProcesamiento(String.format("No se puede operar '%s and %s'", t1, t2));
            and.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(Or or) {
        or.arg0().procesa(this);
        or.arg1().procesa(this);
        Tipo t1 = refExc(or.arg0().tipoNodo());
        Tipo t2 = refExc(or.arg1().tipoNodo());

        if (t1 instanceof TBool && t2 instanceof TBool)
            or.setTipoNodo(new TBool());
        else {
            err.errorProcesamiento(String.format("No se puede operar '%s or %s'", t1, t2));
            or.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(Not or) {
        or.arg().procesa(this);
        Tipo t = refExc(or.arg().tipoNodo());
        if (t instanceof TBool)
            or.setTipoNodo(t);
        else {
            err.errorProcesamiento(String.format("No se puede operar 'not %s'", t));
            or.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(Menor menor) {
        menor.arg0().procesa(this);
        menor.arg1().procesa(this);
        Tipo t1 = refExc(menor.arg0().tipoNodo());
        Tipo t2 = refExc(menor.arg1().tipoNodo());

        if (t1 instanceof TBool && t2 instanceof TBool)
            menor.setTipoNodo(new TBool());
        else if ((t1 instanceof TReal || t1 instanceof TInt) && (t2 instanceof TReal || t2 instanceof TInt))
            menor.setTipoNodo(new TBool());
        else if (t1 instanceof TString && t2 instanceof TString)
            menor.setTipoNodo(new TBool());
        else {
            err.errorProcesamiento(String.format("No se puede operar '%s < %s'", t1, t2));
            menor.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(MenorIgual menorIgual) {
        menorIgual.arg0().procesa(this);
        menorIgual.arg1().procesa(this);
        Tipo t1 = refExc(menorIgual.arg0().tipoNodo());
        Tipo t2 = refExc(menorIgual.arg1().tipoNodo());

        if (t1 instanceof TBool && t2 instanceof TBool)
            menorIgual.setTipoNodo(new TBool());
        else if ((t1 instanceof TReal || t1 instanceof TInt) && (t2 instanceof TReal || t2 instanceof TInt))
            menorIgual.setTipoNodo(new TBool());
        else if (t1 instanceof TString && t2 instanceof TString)
            menorIgual.setTipoNodo(new TBool());
        else {
            err.errorProcesamiento(String.format("No se puede operar '%s <= %s'", t1, t2));
            menorIgual.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(Mayor mayor) {
        mayor.arg0().procesa(this);
        mayor.arg1().procesa(this);
        Tipo t1 = refExc(mayor.arg0().tipoNodo());
        Tipo t2 = refExc(mayor.arg1().tipoNodo());

        if (t1 instanceof TBool && t2 instanceof TBool)
            mayor.setTipoNodo(new TBool());
        else if ((t1 instanceof TReal || t1 instanceof TInt) && (t2 instanceof TReal || t2 instanceof TInt))
            mayor.setTipoNodo(new TBool());
        else if (t1 instanceof TString && t2 instanceof TString)
            mayor.setTipoNodo(new TBool());
        else {
            err.errorProcesamiento(String.format("No se puede operar '%s > %s'", t1, t2));
            mayor.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(MayorIgual mayorIgual) {
        mayorIgual.arg0().procesa(this);
        mayorIgual.arg1().procesa(this);
        Tipo t1 = refExc(mayorIgual.arg0().tipoNodo());
        Tipo t2 = refExc(mayorIgual.arg1().tipoNodo());

        if (t1 instanceof TBool && t2 instanceof TBool)
            mayorIgual.setTipoNodo(new TBool());
        else if ((t1 instanceof TReal || t1 instanceof TInt) && (t2 instanceof TReal || t2 instanceof TInt))
            mayorIgual.setTipoNodo(new TBool());
        else if (t1 instanceof TString && t2 instanceof TString)
            mayorIgual.setTipoNodo(new TBool());
        else {
            err.errorProcesamiento(String.format("No se puede operar '%s >= %s'", t1, t2));
            mayorIgual.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(Igual igual) {
        igual.arg0().procesa(this);
        igual.arg1().procesa(this);
        Tipo t1 = refExc(igual.arg0().tipoNodo());
        Tipo t2 = refExc(igual.arg1().tipoNodo());

        if (t1 instanceof TBool && t2 instanceof TBool) {
            igual.setTipoNodo(new TBool());
        } else if ((t1 instanceof TReal || t1 instanceof TInt)
                && (t2 instanceof TReal || t2 instanceof TInt)) {
            igual.setTipoNodo(new TBool());
        } else if (t1 instanceof TString && t2 instanceof TString) {
            igual.setTipoNodo(new TBool());
        } else if ((t1 instanceof TNull || t1 instanceof TipoPointer)
                && (t2 instanceof TNull || t2 instanceof TipoPointer)) {
            igual.setTipoNodo(new TBool());
        } else {
            err.errorProcesamiento(String.format("No se puede operar '%s == %s'", t1, t2));
            igual.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(Distinto distinto) {
        distinto.arg0().procesa(this);
        distinto.arg1().procesa(this);
        Tipo t1 = refExc(distinto.arg0().tipoNodo());
        Tipo t2 = refExc(distinto.arg1().tipoNodo());

        if (t1 instanceof TBool && t2 instanceof TBool) {
            distinto.setTipoNodo(new TBool());
        } else if ((t1 instanceof TReal || t1 instanceof TInt)
                && (t2 instanceof TReal || t2 instanceof TInt)) {
            distinto.setTipoNodo(new TBool());
        } else if (t1 instanceof TString && t2 instanceof TString) {
            distinto.setTipoNodo(new TBool());
        } else if ((t1 instanceof TNull || t1 instanceof TipoPointer)
                && (t2 instanceof TNull || t2 instanceof TipoPointer)) {
            distinto.setTipoNodo(new TBool());
        } else {
            err.errorProcesamiento(String.format("No se puede operar '%s != %s'", t1, t2));
            distinto.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(AccesoArray accesoArray) {
        accesoArray.arg0().procesa(this);
        accesoArray.arg1().procesa(this);
        Tipo t1 = refExc(accesoArray.arg0().tipoNodo());
        Tipo t2 = refExc(accesoArray.arg1().tipoNodo());

        if (t1 instanceof TipoArray && t2 instanceof TInt) {
            accesoArray.setTipoNodo(((TipoArray) t1).tipoBase());
        } else if (!(t1 instanceof TipoArray)) {
            err.errorProcesamiento(String
                    .format("No se puede acceder a un elemento de una variable que no es un array '%s[%s]'", t1, t2),
                    accesoArray);
            accesoArray.setTipoNodo(new TipoError());
        } else {
            err.errorProcesamiento(String.format("No se puede acceder a un elemento de un array '%s[%s]'", t1, t2),
                    accesoArray);
            accesoArray.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(Punto punto) {
        punto.exp().procesa(this);
        String campo = punto.campo();
        Tipo t = refExc(punto.exp().tipoNodo());

        if (!(t instanceof TipoRecord)) {
            err.errorProcesamiento(
                    String.format("No se puede acceder a un campo de una variable que no es un record"),
                    punto);
            punto.setTipoNodo(new TipoError());
            return;
        }

        TipoRecord tr = (TipoRecord) t;
        if (existeCampo(tr.campos(), campo)) {
            punto.setTipoNodo(tipoDeCampo(tr.campos(), campo));
        } else {
            err.errorProcesamiento(
                    String.format("No se puede acceder a un campo que no existe en un record '%s.%s'",
                            t, campo),
                    punto);
            punto.setTipoNodo(new TipoError());
        }
    }

    @Override
    public void procesa(Flecha flecha) {
        flecha.exp().procesa(this);
        String campo = flecha.campo();
        Tipo refT = refExc(flecha.exp().tipoNodo());

        if (!(refT instanceof TipoPointer)) {
            flecha.setTipoNodo(new TipoError());
            err.errorProcesamiento(
                    String.format("No se puede acceder a un campo de una variable que no es un puntero '%s->%s'",
                            refT, campo),
                    flecha);
            return;
        }

        TipoPointer tp = (TipoPointer) refT;
        Tipo refPointer = refExc(tp.tipoBase());
        if (!(refPointer instanceof TipoRecord)) {
            flecha.setTipoNodo(new TipoError());
            err.errorProcesamiento(
                    String.format(
                            "No se puede acceder a un campo de una variable que no es un puntero a un record '%s->%s'",
                            refPointer, campo),
                    flecha);
            return;
        }

        TipoRecord tr = (TipoRecord) refPointer;
        if (existeCampo(tr.campos(), campo)) {
            flecha.setTipoNodo(tipoDeCampo(tr.campos(), campo));
        } else {
            flecha.setTipoNodo(new TipoError());
            err.errorProcesamiento(
                    String.format("No se puede acceder a un campo que no existe en un record '%s->%s'",
                            refPointer, campo),
                    flecha);
        }
    }

    @Override
    public void procesa(ValorPuntero valorPuntero) {
        valorPuntero.arg().procesa(this);
        Tipo t = refExc(valorPuntero.arg().tipoNodo());

        if (t instanceof TipoPointer) {
            valorPuntero.setTipoNodo(((TipoPointer) t).tipoBase());
        } else {
            valorPuntero.setTipoNodo(new TipoError());
            err.errorProcesamiento(
                    String.format("No se puede acceder al valor apuntado por una variable que no sea un puntero '%s'",
                            t),
                    valorPuntero);
        }
    }
}
