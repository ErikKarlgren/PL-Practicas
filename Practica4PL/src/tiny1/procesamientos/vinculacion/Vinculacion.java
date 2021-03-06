package tiny1.procesamientos.vinculacion;

import tiny1.asint.nodos.bloques.*;
import tiny1.asint.nodos.expresiones.basicas.*;
import tiny1.asint.nodos.expresiones.*;
import tiny1.asint.nodos.expresiones.acceso_campo.*;
import tiny1.asint.nodos.expresiones.aritmeticas.*;
import tiny1.asint.nodos.expresiones.booleanas.logicas.*;
import tiny1.asint.nodos.expresiones.booleanas.comparacion.*;
import tiny1.asint.nodos.instrucciones.*;
import tiny1.asint.nodos.programa.*;
import tiny1.errors.GestionErroresTiny;
import tiny1.procesamientos.Procesador;

public class Vinculacion extends Procesador {

    private final GestionErroresTiny err;
    private final TablaSimbolos tablaSimbolos;
    private final VinculacionDecs1 vinculacionDecs1;
    private final VinculacionDecs2 vinculacionDecs2;
    private final VinculacionProcs vinculacionProcs;

    public Vinculacion() {
        this.err = new GestionErroresTiny();
        tablaSimbolos = new TablaSimbolos();
        vinculacionDecs1 = new VinculacionDecs1(err, tablaSimbolos);
        vinculacionDecs2 = new VinculacionDecs2(err, tablaSimbolos);
        vinculacionProcs = new VinculacionProcs(tablaSimbolos, this, vinculacionDecs1, vinculacionDecs2);
    }

    @Override
    public boolean foundErrors() {
        return err.foundError();
    }

    @Override
    public void procesa(ProgramaConDecs programa) {
        programa.decs().procesa(vinculacionDecs1);
        programa.decs().procesa(vinculacionDecs2);
        programa.decs().procesa(vinculacionProcs);
        programa.instrs().procesa(this);
    }

    @Override
    public void procesa(ProgramaSinDecs programa) {
        programa.instrs().procesa(this);
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
        String nombreFuncion = instruccion.funcion().toString();
        if (tablaSimbolos.existeId(nombreFuncion)) {
            instruccion.setVinculo(tablaSimbolos.get(nombreFuncion));
        } else {
            int fila = instruccion.funcion().fila();
            int col = instruccion.funcion().col();
            err.errorProcesamiento(
                    String.format("(Fila %d, Col %d) La funci??n %s no est?? definida\n", fila, col, nombreFuncion));
        }
        instruccion.params().procesa(this);
    }

    @Override
    public void procesa(InstruccionBloque instrucciones) {
        tablaSimbolos.abrirNivel();
        instrucciones.bloque().procesa(this);
        tablaSimbolos.cerrarNivel();
    }

    @Override
    public void procesa(BloqueVacio bloque) {
        // No hacer nada
    }

    @Override
    public void procesa(BloqueLleno bloque) {
        bloque.programa().procesa(this);
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
        String idString = identificador.id().toString();
        if (tablaSimbolos.existeId(idString)) {
            identificador.setVinculo(tablaSimbolos.get(idString));
        } else {
            err.errorProcesamiento("No existe el identificador " + idString, identificador);
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
        punto.exp().procesa(this);
    }

    @Override
    public void procesa(Flecha flecha) {
        flecha.exp().procesa(this);
    }

    @Override
    public void procesa(ValorPuntero valorPuntero) {
        valorPuntero.arg().procesa(this);
    }
}
