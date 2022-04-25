package tiny0.asint;

import tiny0.asint.nodos.*;
import tiny0.asint.nodos.expresiones.basicas.*;
import tiny0.asint.nodos.expresiones.*;
import tiny0.asint.nodos.expresiones.aritmeticas.*;
import tiny0.asint.nodos.expresiones.booleanas.logicas.*;
import tiny0.asint.nodos.expresiones.booleanas.comparacion.*;

public class SintaxisAbstracta {

    // prog: Decs x Instr → Prog
    public Programa prog(Declaraciones decs, Instrucciones instr) { return new Programa(decs, instr); }

    // decs_una: Dec → Decs
    public Declaraciones decs_una(Declaracion dec) { return new DecsUna(dec); }

    // decs_muchas: Decs x Dec → Decs
    public Declaraciones decs_muchas(Declaraciones decs, Declaracion dec) { return new DecsMuchas(decs, dec); }

    // dec: Tipo x string → Dec
    public Declaracion dec(Tipo tipo, StringLocalizado id) { return new Declaracion(tipo, id); }

    // tipo: string → Tipo
    // TODO: ¿String o StringLocalizado?
    public Tipo tipo(StringLocalizado tipo) { return new Tipo(tipo); }

    // instr_una: Instr → Instrs
    public Instrucciones instr_una(Instruccion instr) { return new InstrUna(instr); }

    // instr_muchas: Instrs x Instr → Instrs
    public Instrucciones instr_muchas(Instrucciones instrs, Instruccion instr) { return new InstrMuchas(instrs, instr); }

    // instr: string x Exp → Instr
    public Instruccion instr(StringLocalizado id, Expresion exp) { return new Instruccion(id, exp); }

    // expresionbasica: string → Exp
    // TODO: ¿Ponemos algo aquí?

    // num_entero: string → Exp
    public Expresion num_entero(StringLocalizado s) { return new NumeroEntero(s); }

    // num_real: string → Exp
    public Expresion num_real(StringLocalizado s) { return new NumeroReal(s); }

    // id: string → Exp
    public Expresion id(StringLocalizado s) { return new Identificador(s); }

    // true: string → Exp
    public Expresion true_(StringLocalizado s) { return new True(s); }

    // false: string → Exp
    public Expresion false_(StringLocalizado s) { return new False(s); }

    // suma: Exp x Exp → Exp
    public Expresion suma(Expresion e1, Expresion e2) { return new Suma(e1, e2); }

    // resta: Exp x Exp → Exp
    public Expresion resta(Expresion e1, Expresion e2) { return new Resta(e1, e2); }

    // mul: Exp x Exp → Exp
    public Expresion mul(Expresion e1, Expresion e2) { return new Multiplicacion(e1, e2); }

    // div: Exp x Exp → Exp
    public Expresion div(Expresion e1, Expresion e2) { return new Division(e1, e2); }

    // mayor : Exp x Exp → Exp
    public Expresion mayor(Expresion e1, Expresion e2) { return new Mayor(e1, e2); }

    // menor: Exp x Exp → Exp
    public Expresion menor(Expresion e1, Expresion e2) { return new Menor(e1, e2); }

    // mayorigual: Exp x Exp → Exp
    public Expresion mayorigual(Expresion e1, Expresion e2) { return new MayorIgual(e1, e2); }

    // menorigual: Exp x Exp → Exp
    public Expresion menorigual(Expresion e1, Expresion e2) { return new MenorIgual(e1, e2); }

    // igualcomp: Exp x Exp → Exp
    public Expresion igualcomp(Expresion e1, Expresion e2) { return new Igual(e1, e2); }

    // distinto: Exp x Exp → Exp
    public Expresion distinto(Expresion e1, Expresion e2) { return new Distinto(e1, e2); }

    // menos : Exp → Exp
    public Expresion menos(Expresion e1) { return new Menos(e1); }

    // and : Exp x Exp → Exp
    public Expresion and(Expresion e1, Expresion e2) { return new And(e1, e2); }

    // or : Exp x Exp → Exp
    public Expresion or(Expresion e1, Expresion e2) { return new Or(e1, e2); }

    // not : Exp → Exp
    public Expresion not(Expresion e1) { return new Not(e1); }

    public StringLocalizado str(String s, int fila, int col) { return new StringLocalizado(s, fila, col); }
}
