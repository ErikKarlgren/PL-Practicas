package tiny1.asint;

import tiny1.asint.nodos.*;
import tiny1.asint.nodos.bloques.Bloque;
import tiny1.asint.nodos.bloques.BloqueLleno;
import tiny1.asint.nodos.bloques.BloqueVacio;
import tiny1.asint.nodos.campos.Campo;
import tiny1.asint.nodos.campos.Campos;
import tiny1.asint.nodos.campos.CamposMuchos;
import tiny1.asint.nodos.campos.CamposUno;
import tiny1.asint.nodos.declaraciones.DecProc;
import tiny1.asint.nodos.declaraciones.DecType;
import tiny1.asint.nodos.declaraciones.DecVar;
import tiny1.asint.nodos.declaraciones.Declaracion;
import tiny1.asint.nodos.declaraciones.Declaraciones;
import tiny1.asint.nodos.declaraciones.DecsMuchas;
import tiny1.asint.nodos.declaraciones.DecsUna;
import tiny1.asint.nodos.expresiones.basicas.*;
import tiny1.asint.nodos.expresiones.*;
import tiny1.asint.nodos.expresiones.aritmeticas.*;
import tiny1.asint.nodos.expresiones.booleanas.logicas.*;
import tiny1.asint.nodos.expresiones.tipos.Tipo;
import tiny1.asint.nodos.expresiones.tipos.TipoArray;
import tiny1.asint.nodos.expresiones.tipos.TipoBasico;
import tiny1.asint.nodos.expresiones.tipos.TipoPointer;
import tiny1.asint.nodos.expresiones.tipos.TipoRecord;
import tiny1.asint.nodos.instrucciones.InstrAsignacion;
import tiny1.asint.nodos.instrucciones.InstrMuchas;
import tiny1.asint.nodos.instrucciones.InstrOptMuchas;
import tiny1.asint.nodos.instrucciones.InstrOptNinguna;
import tiny1.asint.nodos.instrucciones.InstrUna;
import tiny1.asint.nodos.instrucciones.Instruccion;
import tiny1.asint.nodos.instrucciones.InstruccionBloque;
import tiny1.asint.nodos.instrucciones.InstruccionCall;
import tiny1.asint.nodos.instrucciones.InstruccionDelete;
import tiny1.asint.nodos.instrucciones.InstruccionIf;
import tiny1.asint.nodos.instrucciones.InstruccionIfElse;
import tiny1.asint.nodos.instrucciones.InstruccionNL;
import tiny1.asint.nodos.instrucciones.InstruccionRead;
import tiny1.asint.nodos.instrucciones.InstruccionWhile;
import tiny1.asint.nodos.instrucciones.InstruccionWrite;
import tiny1.asint.nodos.instrucciones.Instrucciones;
import tiny1.asint.nodos.instrucciones.InstruccionesOpt;
import tiny1.asint.nodos.parametros.ListaParams;
import tiny1.asint.nodos.parametros.ListaParamsMuchos;
import tiny1.asint.nodos.parametros.ListaParamsUno;
import tiny1.asint.nodos.parametros.ParamRef;
import tiny1.asint.nodos.parametros.ParamValor;
import tiny1.asint.nodos.parametros.Parametro;
import tiny1.asint.nodos.parametros.ParamsCon;
import tiny1.asint.nodos.parametros.ParamsReal;
import tiny1.asint.nodos.parametros.ParamsRealMuchas;
import tiny1.asint.nodos.parametros.ParamsRealNinguna;
import tiny1.asint.nodos.parametros.ParamsSin;
import tiny1.asint.nodos.programa.Programa;
import tiny1.asint.nodos.programa.ProgramaConDecs;
import tiny1.asint.nodos.programa.ProgramaSinDecs;
import tiny1.asint.nodos.expresiones.booleanas.comparacion.*;

public class SintaxisAbstracta {

    // prog: Decs x Instr → Prog
    public Programa prog_con_decs(Declaraciones decs, Instrucciones instr) { return new ProgramaConDecs(decs, instr); }

    public Programa prog_sin_decs(Instrucciones instr) { return new ProgramaSinDecs(instr); }

    // decs_una: Dec → Decs
    public Declaraciones decs_una(Declaracion dec) { return new DecsUna(dec); }

    // decs_muchas: Decs x Dec → Decs
    public Declaraciones decs_muchas(Declaraciones decs, Declaracion dec) { return new DecsMuchas(decs, dec); }

    // dec: Tipo x string → Dec
    //public Declaracion dec(Tipo tipo, StringLocalizado id) { return new Declaracion(tipo, id); }
    
    public Declaracion dec_var(Tipo tipo, StringLocalizado id) { return new DecVar(tipo, id); }
    
    public Declaracion dec_type(Tipo tipo, StringLocalizado id) { return new DecType(tipo, id); }
    
    public Declaracion dec_proc( StringLocalizado id,ListaParams lp, Bloque b) { return new DecProc( id,lp,b); }
    
    public ListaParams params_sin() { return new ParamsSin(); }

    public ListaParams params_con(ListaParams parametros) { return new ParamsCon(parametros); }

    public ListaParams lista_params_uno(Parametro parametro) { return new ListaParamsUno(parametro); }

    public ListaParams lista_params_muchos(ListaParams parametros, Parametro parametro) { return new ListaParamsMuchos(parametros, parametro); }
    
    public Parametro param_ref(Tipo tipo, String nombre) {return new ParamRef(tipo,nombre);}
    
    public Parametro param_valor(Tipo tipo, String nombre) {return new ParamValor(tipo,nombre);}


    // tipo: string → Tipo
    // TODO: ¿String o StringLocalizado?
    public Tipo tipo(StringLocalizado tipo) { return new TipoBasico(tipo); }

    public Tipo tipo_array(StringLocalizado longitud, Tipo tipo) { return new TipoArray(longitud, tipo); }

    public Tipo tipo_pointer(Tipo tipo) { return new TipoPointer(tipo); }

    public Tipo tipo_record(Campos campos) { return new TipoRecord(campos); }

    public Campo campo(Tipo tipo, StringLocalizado id) { return new Campo(tipo, id); }

    public Campos campos_uno(Campo campo) { return new CamposUno(campo); }

    public Campos campos_muchos(Campos campos, Campo campo) { return new CamposMuchos(campos, campo); }

    // instr_una: Instr → Instrs
    public Instrucciones instr_una(Instruccion instr) { return new InstrUna(instr); }

    // instr_muchas: Instrs x Instr → Instrs
    public Instrucciones instr_muchas(Instrucciones instrs, Instruccion instr) { return new InstrMuchas(instrs, instr); }

    // instr: string x Exp → Instr
    public Instruccion instr(Expresion izquierda, Expresion derecha) { return new InstrAsignacion(izquierda, derecha); }
    
    public InstruccionesOpt instr_opt_muchas(Instrucciones instrucciones) { return new InstrOptMuchas(instrucciones);}
    
    public InstruccionesOpt instr_opt_ninguna() { return new InstrOptNinguna();}

    public Instruccion instr_if(Expresion exp, InstruccionesOpt instr1) { return new InstruccionIf(exp,instr1); }

    public Instruccion instr_if_else(Expresion exp, InstruccionesOpt instr1,InstruccionesOpt instr2) { return new InstruccionIfElse(exp,instr1,instr2); }

    public Instruccion instr_while(Expresion exp, InstruccionesOpt instr1) { return new InstruccionWhile(exp,instr1); }
    
    public Instruccion instr_read(Expresion exp) { return new InstruccionRead(exp); }
    
    public Instruccion instr_write(Expresion exp) { return new InstruccionWrite(exp); }
    
    public Instruccion instr_nl() { return new InstruccionNL(); }
    
    public Instruccion instr_delete(Expresion exp) { return new InstruccionDelete(exp); }
    
    public Instruccion instr_call(StringLocalizado str,ParamsReal p) { return new InstruccionCall(str,p); }
    
    public Instruccion instr_bloque(Bloque b) { return new InstruccionBloque(b); }

    public ParamsReal params_real_ninguna() { return new ParamsRealNinguna(); }
    
    public ParamsReal params_real_muchas(Expresiones exps) { return new ParamsRealMuchas(exps); }
    
    public Expresiones expresiones_una (Expresion exp) {return new ExpresionesUna(exp);}
    
    public Expresiones expresiones_muchas (Expresiones exps,Expresion exp) {return new ExpresionesMuchas(exps,exp);}
    
    public Bloque bloque_vacio() { return new BloqueVacio();}
    
    public Bloque bloque_lleno(Programa p) { return new BloqueLleno(p);}


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
    
    // porciento : Exp x Exp -> Exp
    public Expresion porciento(Expresion e1, Expresion e2) { return new PorCiento(e1, e2); }    
    
    // punto : Exp x String -> Exp
    public Expresion punto(Expresion e1, String e2) { return new Punto(e1, e2); }    

    //flecha : Exp x String -> Exp
    public Expresion flecha(Expresion e1, String e2) { return new Flecha(e1, e2); }    

    
    public StringLocalizado str(String s, int fila, int col) { return new StringLocalizado(s, fila, col); }
    
}
