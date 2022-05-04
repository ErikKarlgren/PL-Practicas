package tiny0.asint;

import tiny0.asint.nodos.*;
import tiny0.asint.nodos.declaraciones.*;
import tiny0.asint.nodos.expresiones.basicas.*;
import tiny0.asint.nodos.expresiones.*;
import tiny0.asint.nodos.expresiones.aritmeticas.*;
import tiny0.asint.nodos.expresiones.booleanas.logicas.*;
import tiny0.asint.nodos.instrucciones.*;
import tiny0.asint.nodos.tipos.*;
import tiny0.asint.nodos.expresiones.booleanas.comparacion.*;

public class SintaxisAbstracta {

    public Programa prog(Declaraciones decs, Instrucciones instr) {
        return new Programa(decs, instr);
    }

    // Declaraciones

    public Declaraciones decs_una(Declaracion dec) {
        return new DecsUna(dec);
    }

    public Declaraciones decs_muchas(Declaraciones decs, Declaracion dec) {
        return new DecsMuchas(decs, dec);
    }

    public Declaracion dec(Tipo tipo, StringLocalizado id) {
        return new Declaracion(tipo, id);
    }

    // Tipos

    public Tipo int_() { return new Int(); }

    public Tipo real_() { return new Real(); }

    public Tipo bool_() { return new Bool(); }

    // Instrucciones

    public Instrucciones instr_una(Instruccion instr) {
        return new InstrUna(instr);
    }

    public Instrucciones instr_muchas(Instrucciones instrs, Instruccion instr) {
        return new InstrMuchas(instrs, instr);
    }

    public Instruccion instr(StringLocalizado id, Expresion exp) {
        return new Instruccion(id, exp);
    }

    // Expresiones básicas

    public Expresion num_entero(StringLocalizado s) {
        return new NumeroEntero(s);
    }

    public Expresion num_real(StringLocalizado s) {
        return new NumeroReal(s);
    }

    public Expresion id(StringLocalizado s) {
        return new Identificador(s);
    }

    public Expresion true_() { return new True(); }

    public Expresion false_() { return new False(); }

    // Expresiones aritméticas

    public Expresion suma(Expresion e1, Expresion e2) { return new Suma(e1, e2); }

    public Expresion resta(Expresion e1, Expresion e2) { return new Resta(e1, e2); }

    public Expresion mul(Expresion e1, Expresion e2) { return new Multiplicacion(e1, e2); }

    public Expresion div(Expresion e1, Expresion e2) { return new Division(e1, e2); }

    // Expresiones booleanas

    public Expresion mayor(Expresion e1, Expresion e2) { return new Mayor(e1, e2); }

    public Expresion menor(Expresion e1, Expresion e2) { return new Menor(e1, e2); }

    public Expresion mayorigual(Expresion e1, Expresion e2) { return new MayorIgual(e1, e2); }

    public Expresion menorigual(Expresion e1, Expresion e2) { return new MenorIgual(e1, e2); }

    public Expresion igualcomp(Expresion e1, Expresion e2) { return new Igual(e1, e2); }

    public Expresion distinto(Expresion e1, Expresion e2) { return new Distinto(e1, e2); }

    public Expresion menos(Expresion e1) { return new Menos(e1); }

    public Expresion and(Expresion e1, Expresion e2) { return new And(e1, e2); }

    public Expresion or(Expresion e1, Expresion e2) { return new Or(e1, e2); }

    public Expresion not(Expresion e1) { return new Not(e1); }

    // StringLocalizado

    public StringLocalizado str(String s, int fila, int col) {
        return new StringLocalizado(s, fila, col);
    }
}
