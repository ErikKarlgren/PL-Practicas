package tiny1.procesamientos;

import java.io.PrintStream;

import tiny1.asint.nodos.declaraciones.Declaracion;
import tiny1.asint.nodos.declaraciones.DecsMuchas;
import tiny1.asint.nodos.declaraciones.DecsUna;
import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.asint.nodos.expresiones.aritmeticas.Division;
import tiny1.asint.nodos.expresiones.aritmeticas.Menos;
import tiny1.asint.nodos.expresiones.aritmeticas.Multiplicacion;
import tiny1.asint.nodos.expresiones.aritmeticas.Resta;
import tiny1.asint.nodos.expresiones.aritmeticas.Suma;
import tiny1.asint.nodos.expresiones.basicas.False;
import tiny1.asint.nodos.expresiones.basicas.Identificador;
import tiny1.asint.nodos.expresiones.basicas.NumeroEntero;
import tiny1.asint.nodos.expresiones.basicas.NumeroReal;
import tiny1.asint.nodos.expresiones.basicas.True;
import tiny1.asint.nodos.expresiones.booleanas.comparacion.Distinto;
import tiny1.asint.nodos.expresiones.booleanas.comparacion.Igual;
import tiny1.asint.nodos.expresiones.booleanas.comparacion.Mayor;
import tiny1.asint.nodos.expresiones.booleanas.comparacion.MayorIgual;
import tiny1.asint.nodos.expresiones.booleanas.comparacion.Menor;
import tiny1.asint.nodos.expresiones.booleanas.comparacion.MenorIgual;
import tiny1.asint.nodos.expresiones.booleanas.logicas.And;
import tiny1.asint.nodos.expresiones.booleanas.logicas.Not;
import tiny1.asint.nodos.expresiones.booleanas.logicas.Or;
import tiny1.asint.nodos.instrucciones.InstrMuchas;
import tiny1.asint.nodos.instrucciones.InstrUna;
import tiny1.asint.nodos.instrucciones.Instruccion;
import tiny1.asint.nodos.programa.ProgramaConDecs;

public class Impresion implements Procesador {
    private PrintStream out;

    public Impresion(PrintStream out) {
        this.out = out;
    }

    @Override
    public void procesa(ProgramaConDecs programa) {
        programa.declaraciones().procesa(this);
        out.println();
        out.println("&&");
        programa.instrucciones().procesa(this);
    }

    @Override
    public void procesa(Declaracion declaracion) {
        out.print(declaracion.tipo() + " " + declaracion.id());
    }

    @Override
    public void procesa(DecsUna declaraciones) {
        declaraciones.declaracion().procesa(this);
    }

    @Override
    public void procesa(DecsMuchas declaraciones) {
        declaraciones.declaraciones().procesa(this);
        out.println(";");
        declaraciones.declaracion().procesa(this);
    }

    @Override
    public void procesa(Instruccion instruccion) {
        out.print(instruccion.string() + " = ");
        instruccion.expresion().procesa(this);
    }

    @Override
    public void procesa(InstrUna instrucciones) {
        instrucciones.instruccion().procesa(this);
    }

    @Override
    public void procesa(InstrMuchas instrucciones) {
        instrucciones.instrucciones().procesa(this);
        out.println(";");
        instrucciones.instruccion().procesa(this);
    }

    private void imprimeArgumento(Expresion arg, int prioridad) {
        if (arg.prioridad() < prioridad) {
            out.print("(");
            arg.procesa(this);
            out.print(")");
        } else {
            arg.procesa(this);
        }
    }

    @Override
    public void procesa(Suma suma) {
        imprimeArgumento(suma.arg0(), 1);
        out.print(" + ");
        imprimeArgumento(suma.arg1(), 0);
    }

    @Override
    public void procesa(Resta resta) {
        imprimeArgumento(resta.arg0(), 1);
        out.print(" - ");
        imprimeArgumento(resta.arg1(), 1);
    }

    @Override
    public void procesa(Multiplicacion multiplicacion) {
        imprimeArgumento(multiplicacion.arg0(), 4);
        out.print(" * ");
        imprimeArgumento(multiplicacion.arg1(), 4);
    }

    @Override
    public void procesa(Division division) {
        imprimeArgumento(division.arg0(), 4);
        out.print(" / ");
        imprimeArgumento(division.arg1(), 4);
    }

    @Override
    public void procesa(Menos menos) {
        out.print(" - ");
        imprimeArgumento(menos.arg(), 5);
    }

    @Override
    public void procesa(NumeroEntero numero) {
        out.print(numero.num());
    }

    @Override
    public void procesa(NumeroReal numero) {
        out.print(numero.num());
    }

    @Override
    public void procesa(Identificador identificador) {
        out.print(identificador.id());
    }

    @Override
    public void procesa(True booleanoTrue) {
        out.print(booleanoTrue.bool());
    }

    @Override
    public void procesa(False booleanoFalse) {
        out.print(booleanoFalse.bool());
    }

    @Override
    public void procesa(And and) {
        imprimeArgumento(and.arg0(), 1);
        out.print(" and ");
        imprimeArgumento(and.arg1(), 2);
    }

    @Override
    public void procesa(Or or) {
        imprimeArgumento(or.arg0(), 1);
        out.print(" or ");
        imprimeArgumento(or.arg1(), 2);
    }

    @Override
    public void procesa(Not not) {
        out.print("not ");
        imprimeArgumento(not.arg(), 4);
    }

    @Override
    public void procesa(Menor menor) {
        imprimeArgumento(menor.arg0(), 2);
        out.print(" < ");
        imprimeArgumento(menor.arg1(), 3);
    }

    @Override
    public void procesa(MenorIgual menorIgual) {
        imprimeArgumento(menorIgual.arg0(), 2);
        out.print(" <= ");
        imprimeArgumento(menorIgual.arg1(), 3);
    }

    @Override
    public void procesa(Mayor mayor) {
        imprimeArgumento(mayor.arg0(), 2);
        out.print(" > ");
        imprimeArgumento(mayor.arg1(), 3);
    }

    @Override
    public void procesa(MayorIgual mayorIgual) {
        imprimeArgumento(mayorIgual.arg0(), 2);
        out.print(" >= ");
        imprimeArgumento(mayorIgual.arg1(), 3);
    }

    @Override
    public void procesa(Igual igual) {
        imprimeArgumento(igual.arg0(), 2);
        out.print(" == ");
        imprimeArgumento(igual.arg1(), 3);
    }

    @Override
    public void procesa(Distinto distinto) {
        imprimeArgumento(distinto.arg0(), 2);
        out.print(" != ");
        imprimeArgumento(distinto.arg1(), 3);
    }

}
