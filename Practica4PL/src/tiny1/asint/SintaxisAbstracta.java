package tiny1.asint;

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

public class SintaxisAbstracta {

    // Programa

    public Programa prog_con_decs(Declaraciones decs, Instrucciones instr) { return new ProgramaConDecs(decs, instr); }

    public Programa prog_sin_decs(Instrucciones instr) { return new ProgramaSinDecs(instr); }

    // Declaraciones

    public Declaraciones decs_una(Declaracion dec) { return new DecsUna(dec); }

    public Declaraciones decs_muchas(Declaraciones decs, Declaracion dec) { return new DecsMuchas(decs, dec); }

    public Declaracion dec_var(Tipo tipo, StringLocalizado id) { return new DecVar(tipo, id); }

    public Declaracion dec_type(Tipo tipo, StringLocalizado id) { return new DecType(tipo, id); }

    public Declaracion dec_proc(StringLocalizado id, ListaParams lp, Bloque b) { return new DecProc(id, lp, b); }

    // Parametros

    public ListaParams params_sin() { return new ParamsSin(); }

    public ListaParams lista_params_uno(Parametro parametro) { return new ListaParamsUno(parametro); }

    public ListaParams lista_params_muchos(ListaParams parametros, Parametro parametro) {
        return new ListaParamsMuchos(parametros, parametro);
    }

    public Parametro param_ref(Tipo tipo, String nombre) { return new ParamRef(tipo, nombre); }

    public Parametro param_valor(Tipo tipo, String nombre) { return new ParamValor(tipo, nombre); }

    // Tipos

    public Tipo tipo_nuevo(StringLocalizado nombre) { return new TipoNuevo(nombre); }

    public Tipo int_() { return new TInt(); }

    public Tipo real_() { return new TReal(); }

    public Tipo bool_() { return new TBool(); }

    public Tipo string_() { return new TString(); }

    public Tipo tipo_array(StringLocalizado longitud, Tipo tipo) { return new TipoArray(longitud, tipo); }

    public Tipo tipo_pointer(Tipo tipo) { return new TipoPointer(tipo); }

    public Tipo tipo_record(Campos campos) { return new TipoRecord(campos); }

    // Campos

    public Campo campo(Tipo tipo, StringLocalizado id) { return new Campo(tipo, id); }

    public Campos campos_uno(Campo campo) { return new CamposUno(campo); }

    public Campos campos_muchos(Campos campos, Campo campo) { return new CamposMuchos(campos, campo); }

    // Instrucciones

    public Instrucciones instr_una(Instruccion instr) { return new InstrUna(instr); }

    public Instrucciones instr_muchas(Instrucciones instrs, Instruccion instr) {
        return new InstrMuchas(instrs, instr);
    }

    public Instruccion instr_asig(Expresion izquierda, Expresion derecha) {
        return new InstrAsignacion(izquierda, derecha);
    }

    public InstruccionesOpt instr_opt_muchas(Instrucciones instrucciones) { return new InstrOptMuchas(instrucciones); }

    public InstruccionesOpt instr_opt_ninguna() { return new InstrOptNinguna(); }

    public Instruccion instr_if(Expresion exp, InstruccionesOpt instr1) { return new InstruccionIf(exp, instr1); }

    public Instruccion instr_if_else(Expresion exp, InstruccionesOpt instr1, InstruccionesOpt instr2) {
        return new InstruccionIfElse(exp, instr1, instr2);
    }

    public Instruccion instr_while(Expresion exp, InstruccionesOpt instr1) { return new InstruccionWhile(exp, instr1); }

    public Instruccion instr_read(Expresion exp) { return new InstruccionRead(exp); }

    public Instruccion instr_write(Expresion exp) { return new InstruccionWrite(exp); }

    public Instruccion instr_nl() { return new InstruccionNL(); }

    public Instruccion instr_new(Expresion exp) { return new InstruccionNew(exp); }

    public Instruccion instr_delete(Expresion exp) { return new InstruccionDelete(exp); }

    public Instruccion instr_call(StringLocalizado str, Expresiones p) { return new InstruccionCall(str, p); }

    public Instruccion instr_bloque(Bloque b) { return new InstruccionBloque(b); }

    // Expresiones

    public Expresiones expresiones_ninguna() { return new ExpresionesNinguna(); }

    public Expresiones expresiones_una(Expresion exp) { return new ExpresionesUna(exp); }

    public Expresiones expresiones_muchas(Expresiones exps, Expresion exp) { return new ExpresionesMuchas(exps, exp); }

    // Bloques

    public Bloque bloque_vacio() { return new BloqueVacio(); }

    public Bloque bloque_lleno(Programa p) { return new BloqueLleno(p); }

    // Expresiones básicas

    public Expresion num_entero(StringLocalizado s) { return new NumeroEntero(s); }

    public Expresion num_real(StringLocalizado s) { return new NumeroReal(s); }

    public Expresion id(StringLocalizado s) { return new Identificador(s); }

    public Expresion true_() { return new True(); }

    public Expresion false_() { return new False(); }

    public Expresion nulo() { return new Null(); }

    public Expresion cadena(StringLocalizado s) { return new Cadena(s); }

    // Expresiones aritméticas

    public Expresion suma(Expresion e1, Expresion e2) { return new Suma(e1, e2); }

    public Expresion resta(Expresion e1, Expresion e2) { return new Resta(e1, e2); }

    public Expresion mul(Expresion e1, Expresion e2) { return new Multiplicacion(e1, e2); }

    public Expresion div(Expresion e1, Expresion e2) { return new Division(e1, e2); }

    public Expresion porciento(Expresion e1, Expresion e2) { return new PorCiento(e1, e2); }

    public Expresion menos(Expresion e1) { return new Menos(e1); }

    // Expresiones booleanas

    public Expresion mayor(Expresion e1, Expresion e2) { return new Mayor(e1, e2); }

    public Expresion menor(Expresion e1, Expresion e2) { return new Menor(e1, e2); }

    public Expresion mayorigual(Expresion e1, Expresion e2) { return new MayorIgual(e1, e2); }

    public Expresion menorigual(Expresion e1, Expresion e2) { return new MenorIgual(e1, e2); }

    public Expresion igualcomp(Expresion e1, Expresion e2) { return new Igual(e1, e2); }

    public Expresion distinto(Expresion e1, Expresion e2) { return new Distinto(e1, e2); }

    public Expresion and(Expresion e1, Expresion e2) { return new And(e1, e2); }

    public Expresion or(Expresion e1, Expresion e2) { return new Or(e1, e2); }

    public Expresion not(Expresion e1) { return new Not(e1); }

    // Expresiones de acceso a campos

    public Expresion punto(Expresion e1, String e2) { return new Punto(e1, e2); }

    public Expresion flecha(Expresion e1, String e2) { return new Flecha(e1, e2); }

    public Expresion acceso_array(Expresion e1, Expresion e2) { return new AccesoArray(e1, e2); }

    public Expresion valor_puntero(Expresion e1) { return new ValorPuntero(e1); }

    // StringLocalizado

    public StringLocalizado str(String s, int fila, int col) { return new StringLocalizado(s, fila, col); }

}
