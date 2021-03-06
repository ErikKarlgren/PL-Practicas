/* Generated By:JavaCC: Do not edit this line. ConstructorAST.java */
package tiny1.ast_descendente;

import tiny1.asint.nodos.bloques.*;
import tiny1.asint.nodos.campos.*;
import tiny1.asint.nodos.declaraciones.*;
import tiny1.asint.nodos.expresiones.*;
import tiny1.asint.nodos.instrucciones.*;
import tiny1.asint.nodos.parametros.*;
import tiny1.asint.nodos.programa.*;
import tiny1.asint.nodos.tipos.*;
import tiny1.semops.SemOps;

public class ConstructorAST implements ConstructorASTConstants {
     private SemOps sem = new SemOps();

  final public Programa ProgramaP() throws ParseException {
      Programa prog;
    prog = Programa();
    jj_consume_token(0);
                            {if (true) return prog;}
    throw new Error("Missing return statement in function");
  }

  final public Programa Programa() throws ParseException {
      Declaraciones decs; Instrucciones inst;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case proc:
    case var:
    case type:
      decs = Declaraciones();
      jj_consume_token(46);
      inst = Instrucciones();
                                                     {if (true) return sem.prog_con_decs(decs,inst);}
      break;
    case numeroEntero:
    case numeroReal:
    case True:
    case False:
    case not:
    case Null:
    case If:
    case While:
    case call:
    case New:
    case delete:
    case read:
    case write:
    case nl:
    case identificador:
    case cadena:
    case 48:
    case 54:
    case 58:
    case 59:
      inst = Instrucciones();
                           {if (true) return sem.prog_sin_decs(inst);}
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Declaraciones Declaraciones() throws ParseException {
      Declaracion dec; Declaraciones decs;
    dec = Declaracion();
    decs = REDeclaraciones(sem.decs_una(dec));
                                                                {if (true) return decs;}
    throw new Error("Missing return statement in function");
  }

  final public Declaraciones REDeclaraciones(Declaraciones decsh) throws ParseException {
      Declaracion dec; Declaraciones decs;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 47:
      jj_consume_token(47);
      dec = Declaracion();
      decs = REDeclaraciones(sem.decs_muchas(decsh,dec));
                                                                             {if (true) return decs;}
      break;
    default:
      jj_la1[1] = jj_gen;
      {if (true) return decsh;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Declaracion Declaracion() throws ParseException {
      Tipo tipo; Token t; ListaParams params; Bloque bloque;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case var:
      jj_consume_token(var);
      tipo = Tipo();
      t = jj_consume_token(identificador);
                                          {if (true) return sem.dec_var(tipo,sem.str(t.image,t.beginLine,t.beginColumn));}
      break;
    case type:
      jj_consume_token(type);
      tipo = Tipo();
      t = jj_consume_token(identificador);
                                           {if (true) return sem.dec_type(tipo,sem.str(t.image,t.beginLine,t.beginColumn));}
      break;
    case proc:
      jj_consume_token(proc);
      t = jj_consume_token(identificador);
      params = Params();
      bloque = Bloque();
                                                               {if (true) return sem.dec_proc(sem.str(t.image,t.beginLine,t.beginColumn),params,bloque);}
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public ListaParams Params() throws ParseException {
      ListaParams lp;
    jj_consume_token(48);
    lp = REParams();
                        {if (true) return lp;}
    throw new Error("Missing return statement in function");
  }

  final public ListaParams REParams() throws ParseException {
      ListaParams lp;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Int:
    case bool:
    case real:
    case string:
    case record:
    case array:
    case pointer:
    case identificador:
      lp = ListaParams();
      jj_consume_token(49);
                           {if (true) return lp;}
      break;
    case 49:
      jj_consume_token(49);
          {if (true) return sem.params_sin();}
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public ListaParams ListaParams() throws ParseException {
      Parametro p; ListaParams lp;
    p = Param();
    lp = REListaParams(sem.lista_params_uno(p));
                                                          {if (true) return lp;}
    throw new Error("Missing return statement in function");
  }

  final public ListaParams REListaParams(ListaParams lph) throws ParseException {
      Parametro p; ListaParams lp;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 50:
      jj_consume_token(50);
      p = Param();
      lp = REListaParams(sem.lista_params_muchos(lph,p));
                                                                     {if (true) return lp;}
      break;
    default:
      jj_la1[4] = jj_gen;

         {if (true) return lph;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Parametro Param() throws ParseException {
      Tipo tipo; Parametro p;
    tipo = Tipo();
    p = AmpersandOp(tipo);
                                      {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  final public Parametro AmpersandOp(Tipo tipoh) throws ParseException {
      Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 51:
      jj_consume_token(51);
      t = jj_consume_token(identificador);
                            {if (true) return sem.param_ref(tipoh,t.image);}
      break;
    case identificador:
      t = jj_consume_token(identificador);
                        {if (true) return sem.param_valor(tipoh,t.image);}
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Tipo Tipo() throws ParseException {
      Token t; Tipo tipo; Campos campos;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Int:
      t = jj_consume_token(Int);
              {if (true) return sem.int_();}
      break;
    case real:
      t = jj_consume_token(real);
               {if (true) return sem.real_();}
      break;
    case bool:
      t = jj_consume_token(bool);
                {if (true) return sem.bool_();}
      break;
    case string:
      t = jj_consume_token(string);
                 {if (true) return sem.string_();}
      break;
    case identificador:
      t = jj_consume_token(identificador);
                        {if (true) return sem.tipo_nuevo(sem.str(t.image,t.beginLine,t.beginColumn));}
      break;
    case array:
      jj_consume_token(array);
      jj_consume_token(52);
      t = jj_consume_token(numeroEntero);
      jj_consume_token(53);
      jj_consume_token(of);
      tipo = Tipo();
                                                     {if (true) return sem.tipo_array(sem.str(t.image,t.beginLine,t.beginColumn),tipo);}
      break;
    case pointer:
      jj_consume_token(pointer);
      tipo = Tipo();
                            {if (true) return sem.tipo_pointer(tipo);}
      break;
    case record:
      jj_consume_token(record);
      jj_consume_token(54);
      campos = Campos();
      jj_consume_token(55);
                                     {if (true) return sem.tipo_record(campos);}
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Campos Campos() throws ParseException {
      Campo c; Campos campos;
    c = Campo();
    campos = RECampos(sem.campos_uno(c));
                                                   {if (true) return campos;}
    throw new Error("Missing return statement in function");
  }

  final public Campos RECampos(Campos camposh) throws ParseException {
      Campo c; Campos campos;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 47:
      jj_consume_token(47);
      c = Campo();
      campos = RECampos(sem.campos_muchos(camposh,c));
                                                                  {if (true) return campos;}
      break;
    default:
      jj_la1[7] = jj_gen;

         {if (true) return camposh;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Campo Campo() throws ParseException {
      Tipo tipo; Token t;
    tipo = Tipo();
    t = jj_consume_token(identificador);
                                    {if (true) return sem.campo(tipo,sem.str(t.image,t.beginLine,t.beginColumn));}
    throw new Error("Missing return statement in function");
  }

  final public Instrucciones Instrucciones() throws ParseException {
      Instruccion inst; Instrucciones instrs;
    inst = Instruccion();
    instrs = REInstrucciones(sem.instr_una(inst));
                                                                     {if (true) return instrs;}
    throw new Error("Missing return statement in function");
  }

  final public Instrucciones REInstrucciones(Instrucciones instrsh) throws ParseException {
      Instruccion inst; Instrucciones instrs;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 47:
      jj_consume_token(47);
      inst = Instruccion();
      instrs = REInstrucciones(sem.instr_muchas(instrsh,inst));
                                                                                    {if (true) return instrs;}
      break;
    default:
      jj_la1[8] = jj_gen;

         {if (true) return instrsh;}
    }
    throw new Error("Missing return statement in function");
  }

  final public InstruccionesOpt InstruccionesOpt() throws ParseException {
      Instrucciones instrs;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numeroEntero:
    case numeroReal:
    case True:
    case False:
    case not:
    case Null:
    case If:
    case While:
    case call:
    case New:
    case delete:
    case read:
    case write:
    case nl:
    case identificador:
    case cadena:
    case 48:
    case 54:
    case 58:
    case 59:
      instrs = Instrucciones();
                             {if (true) return sem.instr_opt_muchas(instrs);}
      break;
    default:
      jj_la1[9] = jj_gen;

         {if (true) return sem.instr_opt_ninguna();}
    }
    throw new Error("Missing return statement in function");
  }

  final public Instruccion Instruccion() throws ParseException {
      Expresion e1,e2; InstruccionesOpt instrsOpt; Instruccion inst; Token t; Expresiones params; Bloque bloque;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numeroEntero:
    case numeroReal:
    case True:
    case False:
    case not:
    case Null:
    case identificador:
    case cadena:
    case 48:
    case 58:
    case 59:
      e1 = Expresion();
      jj_consume_token(56);
      e2 = Expresion();
                                        {if (true) return sem.instr_asig(e1,e2);}
      break;
    case If:
      jj_consume_token(If);
      e1 = Expresion();
      jj_consume_token(then);
      instrsOpt = InstruccionesOpt();
      inst = REInstruccion(e1,instrsOpt);
                                                                                               {if (true) return inst;}
      break;
    case While:
      jj_consume_token(While);
      e1 = Expresion();
      jj_consume_token(Do);
      instrsOpt = InstruccionesOpt();
      jj_consume_token(endwhile);
                                                                          {if (true) return sem.instr_while(e1,instrsOpt);}
      break;
    case read:
      jj_consume_token(read);
      e1 = Expresion();
                            {if (true) return sem.instr_read(e1);}
      break;
    case write:
      jj_consume_token(write);
      e1 = Expresion();
                             {if (true) return sem.instr_write(e1);}
      break;
    case nl:
      jj_consume_token(nl);
           {if (true) return sem.instr_nl();}
      break;
    case New:
      jj_consume_token(New);
      e1 = Expresion();
                           {if (true) return sem.instr_new(e1);}
      break;
    case delete:
      jj_consume_token(delete);
      e1 = Expresion();
                              {if (true) return sem.instr_delete(e1);}
      break;
    case call:
      jj_consume_token(call);
      t = jj_consume_token(identificador);
      params = ParamReal();
                                                  {if (true) return sem.instr_call(sem.str(t.image,t.beginLine,t.beginColumn),params);}
      break;
    case 54:
      bloque = Bloque();
                      {if (true) return sem.instr_bloque(bloque);}
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Instruccion REInstruccion(Expresion eh,InstruccionesOpt instrsOpth) throws ParseException {
      InstruccionesOpt instrsOpt;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Else:
      jj_consume_token(Else);
      instrsOpt = InstruccionesOpt();
      jj_consume_token(endif);
                                                  {if (true) return sem.instr_if_else(eh,instrsOpth,instrsOpt);}
      break;
    case endif:
      jj_consume_token(endif);
              {if (true) return sem.instr_if(eh,instrsOpth);}
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresiones ParamReal() throws ParseException {
      Expresiones params;
    jj_consume_token(48);
    params = REParamsReal();
                                {if (true) return params;}
    throw new Error("Missing return statement in function");
  }

  final public Expresiones REParamsReal() throws ParseException {
      Expresiones e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 49:
      jj_consume_token(49);
          {if (true) return sem.expresiones_ninguna();}
      break;
    case numeroEntero:
    case numeroReal:
    case True:
    case False:
    case not:
    case Null:
    case identificador:
    case cadena:
    case 48:
    case 58:
    case 59:
      e = Expresiones();
      jj_consume_token(49);
                          {if (true) return e;}
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresiones Expresiones() throws ParseException {
      Expresion e; Expresiones exprs;
    e = Expresion();
    exprs = REExpresiones(sem.expresiones_una(e));
                                                                {if (true) return exprs;}
    throw new Error("Missing return statement in function");
  }

  final public Expresiones REExpresiones(Expresiones exprh) throws ParseException {
      Expresion e; Expresiones exprs;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 50:
      jj_consume_token(50);
      e = Expresion();
      exprs = REExpresiones(sem.expresiones_muchas(exprh,e));
                                                                             {if (true) return exprs;}
      break;
    default:
      jj_la1[13] = jj_gen;

         {if (true) return exprh;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Bloque Bloque() throws ParseException {
      Bloque bloque;
    jj_consume_token(54);
    bloque = REBloque();
                            {if (true) return bloque;}
    throw new Error("Missing return statement in function");
  }

  final public Bloque REBloque() throws ParseException {
      Programa p;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 55:
      jj_consume_token(55);
          {if (true) return sem.bloque_vacio();}
      break;
    case numeroEntero:
    case numeroReal:
    case True:
    case False:
    case not:
    case Null:
    case proc:
    case If:
    case While:
    case call:
    case New:
    case delete:
    case read:
    case write:
    case nl:
    case var:
    case type:
    case identificador:
    case cadena:
    case 48:
    case 54:
    case 58:
    case 59:
      p = Programa();
      jj_consume_token(55);
                      {if (true) return sem.bloque_lleno(p);}
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion Expresion() throws ParseException {
      Expresion e;
    e = E0();
             {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  final public Expresion ExpresionBasica() throws ParseException {
      Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numeroEntero:
      t = jj_consume_token(numeroEntero);
                       {if (true) return sem.num_entero(sem.str(t.image,t.beginLine,t.beginColumn));}
      break;
    case numeroReal:
      t = jj_consume_token(numeroReal);
                     {if (true) return sem.num_real(sem.str(t.image,t.beginLine,t.beginColumn));}
      break;
    case identificador:
      t = jj_consume_token(identificador);
                         {if (true) return sem.id(sem.str(t.image,t.beginLine,t.beginColumn));}
      break;
    case True:
      t = jj_consume_token(True);
               {if (true) return sem.true_();}
      break;
    case False:
      t = jj_consume_token(False);
                {if (true) return sem.false_();}
      break;
    case Null:
      t = jj_consume_token(Null);
               {if (true) return sem.nulo();}
      break;
    case cadena:
      t = jj_consume_token(cadena);
                 {if (true) return sem.cadena(sem.str(t.image,t.beginLine,t.beginColumn));}
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion E0() throws ParseException {
      Expresion e1,e2;
    e1 = E1();
    e2 = RE0(e1);
                         {if (true) return e2;}
    throw new Error("Missing return statement in function");
  }

  final public Expresion RE0(Expresion eh) throws ParseException {
      Expresion e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 57:
      jj_consume_token(57);
      e = E0();
                 {if (true) return sem.suma(eh,e);}
      break;
    case 58:
      jj_consume_token(58);
      e = E1();
                 {if (true) return sem.resta(eh,e);}
      break;
    default:
      jj_la1[16] = jj_gen;

         {if (true) return eh;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion E1() throws ParseException {
      Expresion e1,e2;
    e1 = E2();
    e2 = RE1(e1);
                         {if (true) return e2;}
    throw new Error("Missing return statement in function");
  }

  final public Expresion RE1(Expresion eh) throws ParseException {
      String op; Expresion e1,e2;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case or:
    case and:
      op = OP1();
      e1 = E2();
      e2 = RE1(sem.exp(op,eh,e1));
                                                 {if (true) return e2;}
      break;
    default:
      jj_la1[17] = jj_gen;

         {if (true) return eh;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion E2() throws ParseException {
      Expresion e1,e2;
    e1 = E3();
    e2 = RE2(e1);
                         {if (true) return e2;}
    throw new Error("Missing return statement in function");
  }

  final public Expresion RE2(Expresion eh) throws ParseException {
      String op; Expresion e1,e2;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 60:
    case 61:
    case 62:
    case 63:
    case 64:
    case 65:
      op = OP2();
      e1 = E3();
      e2 = RE2(sem.exp(op,eh,e1));
                                                 {if (true) return e2;}
      break;
    default:
      jj_la1[18] = jj_gen;

         {if (true) return eh;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion E3() throws ParseException {
      Expresion e1,e2;
    e1 = E4();
    e2 = RE3(e1);
                         {if (true) return e2;}
    throw new Error("Missing return statement in function");
  }

  final public Expresion RE3(Expresion eh) throws ParseException {
      String op; Expresion e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 59:
    case 66:
    case 67:
      op = OP3();
      e = E4();
                      {if (true) return sem.exp(op,eh,e);}
      break;
    default:
      jj_la1[19] = jj_gen;

         {if (true) return eh;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion E4() throws ParseException {
      Expresion e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case not:
      jj_consume_token(not);
      e = E4();
                   {if (true) return sem.not(e);}
      break;
    case 58:
      jj_consume_token(58);
      e = E5();
                 {if (true) return sem.menos(e);}
      break;
    case numeroEntero:
    case numeroReal:
    case True:
    case False:
    case Null:
    case identificador:
    case cadena:
    case 48:
    case 59:
      e = E5();
             {if (true) return e;}
      break;
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion E5() throws ParseException {
      Expresion e1,e2;
    e1 = E6();
    e2 = RE5p(e1);
                          {if (true) return e2;}
    throw new Error("Missing return statement in function");
  }

  final public Expresion RE5p(Expresion eh) throws ParseException {
      Expresion e1,e2;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 52:
    case 68:
    case 69:
      e1 = RE5(eh);
      e2 = RE5p(e1);
                             {if (true) return e2;}
      break;
    default:
      jj_la1[21] = jj_gen;

         {if (true) return eh;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion RE5(Expresion eh) throws ParseException {
      Expresion e; String op; Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 52:
      jj_consume_token(52);
      e = Expresion();
      jj_consume_token(53);
                            {if (true) return sem.acceso_array(eh,e);}
      break;
    case 68:
    case 69:
      op = OP5();
      t = jj_consume_token(identificador);
                                 {if (true) return sem.acceso_registro(op,eh,t.image);}
      break;
    default:
      jj_la1[22] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion E6() throws ParseException {
      Expresion e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 59:
      jj_consume_token(59);
      e = E6();
                 {if (true) return sem.valor_puntero(e);}
      break;
    case numeroEntero:
    case numeroReal:
    case True:
    case False:
    case Null:
    case identificador:
    case cadena:
    case 48:
      e = E7();
             {if (true) return e;}
      break;
    default:
      jj_la1[23] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Expresion E7() throws ParseException {
      Expresion e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 48:
      jj_consume_token(48);
      e = E0();
      jj_consume_token(49);
                     {if (true) return e;}
      break;
    case numeroEntero:
    case numeroReal:
    case True:
    case False:
    case Null:
    case identificador:
    case cadena:
      e = ExpresionBasica();
                          {if (true) return e;}
      break;
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String OP1() throws ParseException {
      Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case and:
      t = jj_consume_token(and);
              {if (true) return t.image;}
      break;
    case or:
      t = jj_consume_token(or);
             {if (true) return t.image;}
      break;
    default:
      jj_la1[25] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String OP2() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 60:
      jj_consume_token(60);
          {if (true) return ">";}
      break;
    case 61:
      jj_consume_token(61);
          {if (true) return "<";}
      break;
    case 62:
      jj_consume_token(62);
           {if (true) return ">=";}
      break;
    case 63:
      jj_consume_token(63);
           {if (true) return "<=";}
      break;
    case 64:
      jj_consume_token(64);
           {if (true) return "==";}
      break;
    case 65:
      jj_consume_token(65);
           {if (true) return "!=";}
      break;
    default:
      jj_la1[26] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String OP3() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 59:
      jj_consume_token(59);
          {if (true) return "*";}
      break;
    case 66:
      jj_consume_token(66);
          {if (true) return "/";}
      break;
    case 67:
      jj_consume_token(67);
         {if (true) return "%";}
      break;
    default:
      jj_la1[27] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String OP5() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 68:
      jj_consume_token(68);
          {if (true) return ".";}
      break;
    case 69:
      jj_consume_token(69);
           {if (true) return "->";}
      break;
    default:
      jj_la1[28] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  public ConstructorASTTokenManager token_source;
  SimpleCharStream jj_input_stream;
  public Token token, jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[29];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static {
      jj_la1_0();
      jj_la1_1();
      jj_la1_2();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x23a63000,0x0,0x1000000,0x41c000,0x0,0x0,0x41c000,0x0,0x0,0x22a63000,0x22a63000,0x18000000,0xa63000,0x0,0x23a63000,0x863000,0x0,0x180000,0x0,0x0,0xa63000,0x0,0x0,0x863000,0x863000,0x180000,0x0,0x0,0x0,};
   }
   private static void jj_la1_1() {
      jj_la1_1 = new int[] {0xc413fe1,0x8000,0xc00,0x21016,0x40000,0x81000,0x1016,0x8000,0x8000,0xc4133e1,0xc4133e1,0x0,0xc033000,0x40000,0xcc13fe1,0x3000,0x6000000,0x0,0xf0000000,0x8000000,0xc013000,0x100000,0x100000,0x8013000,0x13000,0x0,0xf0000000,0x8000000,0x0,};
   }
   private static void jj_la1_2() {
      jj_la1_2 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x3,0xc,0x0,0x30,0x30,0x0,0x0,0x0,0x3,0xc,0x30,};
   }

  public ConstructorAST(java.io.InputStream stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ConstructorASTTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  public void ReInit(java.io.InputStream stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  public ConstructorAST(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ConstructorASTTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  public ConstructorAST(ConstructorASTTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  public void ReInit(ConstructorASTTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.Vector jj_expentries = new java.util.Vector();
  private int[] jj_expentry;
  private int jj_kind = -1;

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[70];
    for (int i = 0; i < 70; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 29; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
          if ((jj_la1_2[i] & (1<<j)) != 0) {
            la1tokens[64+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 70; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

}
