package tiny0.procesamientos;

import java.io.PrintStream;

import tiny0.asint.nodos.*;
import tiny0.asint.nodos.declaraciones.*;
import tiny0.asint.nodos.expresiones.*;
import tiny0.asint.nodos.expresiones.basicas.*;
import tiny0.asint.nodos.expresiones.aritmeticas.*;
import tiny0.asint.nodos.expresiones.booleanas.logicas.*;
import tiny0.asint.nodos.instrucciones.*;
import tiny0.asint.nodos.tipos.*;
import tiny0.asint.nodos.expresiones.booleanas.comparacion.*;

public class Impresion implements Procesador {
    private final PrintStream out;

    public Impresion(PrintStream out) { this.out = out; }

    @Override
    public void procesa(Programa programa) {
        programa.declaraciones().procesa(this);
        out.println();
        out.println("&&");
        programa.instrucciones().procesa(this);
    }

    @Override
    public void procesa(Declaracion declaracion) {
        declaracion.tipo().procesa(this);
        out.print(" " + declaracion.id());
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
        out.print("true");
    }

    @Override
    public void procesa(False booleanoFalse) {
        out.print("false");
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

    @Override
    public void procesa(Int entero) {
        out.print("int");
    }

    @Override
    public void procesa(Real real) {
        out.print("real");
    }

    @Override
    public void procesa(Bool bool) {
        out.print("bool");
    }

}
