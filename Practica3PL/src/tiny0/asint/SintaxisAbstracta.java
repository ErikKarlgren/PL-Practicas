package tiny0.asint;

import tiny0.asint.nodos.StringLocalizado;
import tiny0.asint.nodos.expresiones.basicas.*;
import tiny0.asint.nodos.expresiones.Expresion;
import tiny0.asint.nodos.expresiones.aritmeticas.*;
import tiny0.asint.nodos.expresiones.booleanas.logicas.*;
import tiny0.asint.nodos.expresiones.booleanas.comparacion.*;

public final class SintaxisAbstracta {

    // prog: Decs x Instr → Prog
    // decs_una: Dec → Decs
    // decs_muchas: Decs x Dec → Decs
    // dec: Tipo x string → Dec
    // tipo: string → Tipo
    // instr_una: Instr → Instrs
    // instr_muchas: Instrs x Instr → Instrs
    // instr: string x Exp → Instr

    // expresionbasica: string → Exp
    // TODO: ¿Ponemos algo aquí?

    // num_entero: string → Exp
    Expresion num_entero(StringLocalizado s) { return new NumeroEntero(s); }

    // num_real: string → Exp
    Expresion num_real(StringLocalizado s) { return new NumeroReal(s); }

    // id: string → Exp
    Expresion id(StringLocalizado s) { return new Identificador(s); }

    // true: string → Exp
    Expresion true_(StringLocalizado s) { return new True(s); }

    // false: string → Exp
    Expresion false_(StringLocalizado s) { return new False(s); }

    // suma: Exp x Exp → Exp
    Expresion suma(Expresion e1, Expresion e2) { return new Suma(e1, e2); }

    // resta: Exp x Exp → Exp
    Expresion resta(Expresion e1, Expresion e2) { return new Resta(e1, e2); }

    // mul: Exp x Exp → Exp
    Expresion mul(Expresion e1, Expresion e2) { return new Multiplicacion(e1, e2); }

    // div: Exp x Exp → Exp
    Expresion div(Expresion e1, Expresion e2) { return new Division(e1, e2); }

    // mayor : Exp x Exp → Exp
    Expresion mayor(Expresion e1, Expresion e2) { return new Mayor(e1, e2); }

    // menor: Exp x Exp → Exp
    Expresion menor(Expresion e1, Expresion e2) { return new Menor(e1, e2); }

    // mayorigual: Exp x Exp → Exp
    Expresion mayorigual(Expresion e1, Expresion e2) { return new MayorIgual(e1, e2); }

    // menorigual: Exp x Exp → Exp
    Expresion menorigual(Expresion e1, Expresion e2) { return new MenorIgual(e1, e2); }

    // igualcomp: Exp x Exp → Exp
    Expresion igualcomp(Expresion e1, Expresion e2) { return new Igual(e1, e2); }

    // distinto: Exp x Exp → Exp
    Expresion distinto(Expresion e1, Expresion e2) { return new Distinto(e1, e2); }

    // menos : Exp → Exp
    Expresion menos(Expresion e1) { return new Menos(e1); }

    // and : Exp x Exp → Exp
    Expresion and(Expresion e1, Expresion e2) { return new And(e1, e2); }

    // or : Exp x Exp → Exp
    Expresion or(Expresion e1, Expresion e2) { return new Or(e1, e2); }

    // not : Exp → Exp
    Expresion not(Expresion e1) { return new Not(e1); }

}
