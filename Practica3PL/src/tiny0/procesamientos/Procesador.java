package tiny0.procesamientos;

import tiny0.asint.nodos.*;
import tiny0.asint.nodos.expresiones.basicas.*;
import tiny0.asint.nodos.expresiones.aritmeticas.*;
import tiny0.asint.nodos.expresiones.booleanas.logicas.*;
import tiny0.asint.nodos.expresiones.booleanas.comparacion.*;

public interface Procesador {

    void procesa(Programa programa);

    void procesa(Declaracion declaracion);

    void procesa(DecsUna declaraciones);

    void procesa(DecsMuchas declaraciones);

    void procesa(Instruccion instruccion);

    void procesa(InstrUna instrucciones);

    void procesa(InstrMuchas instrucciones);

    // Expresiones aritméticas

    void procesa(Suma suma);

    void procesa(Resta resta);

    void procesa(Multiplicacion multiplicacion);

    void procesa(Division division);

    void procesa(Menos menos);

    // Expresiones básicas

    void procesa(NumeroEntero numero);

    void procesa(NumeroReal numero);

    void procesa(Identificador identificador);

    void procesa(True booleanoTrue);

    void procesa(False booleanoFalse);

    // Expresiones lógicas

    void procesa(And and);

    void procesa(Or or);

    void procesa(Not not);

    // Expresiones comparativas

    void procesa(Menor menor);

    void procesa(MenorIgual menorIgual);

    void procesa(Mayor mayor);

    void procesa(MayorIgual mayorIgual);

    void procesa(Igual igual);

    void procesa(Distinto distinto);

}
