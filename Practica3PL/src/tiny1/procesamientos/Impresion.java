package tiny1.procesamientos;

import java.io.PrintStream;

import tiny1.asint.nodos.bloques.BloqueLleno;
import tiny1.asint.nodos.bloques.BloqueVacio;
import tiny1.asint.nodos.campos.Campo;
import tiny1.asint.nodos.campos.CamposMuchos;
import tiny1.asint.nodos.campos.CamposUno;
import tiny1.asint.nodos.declaraciones.Declaracion;
import tiny1.asint.nodos.declaraciones.DecsMuchas;
import tiny1.asint.nodos.declaraciones.DecsUna;
import tiny1.asint.nodos.expresiones.AccesoArray;
import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.asint.nodos.expresiones.ExpresionesMuchas;
import tiny1.asint.nodos.expresiones.ExpresionesUna;
import tiny1.asint.nodos.expresiones.Flecha;
import tiny1.asint.nodos.expresiones.Punto;
import tiny1.asint.nodos.expresiones.aritmeticas.Division;
import tiny1.asint.nodos.expresiones.aritmeticas.Menos;
import tiny1.asint.nodos.expresiones.aritmeticas.Multiplicacion;
import tiny1.asint.nodos.expresiones.aritmeticas.PorCiento;
import tiny1.asint.nodos.expresiones.aritmeticas.Resta;
import tiny1.asint.nodos.expresiones.aritmeticas.Suma;
import tiny1.asint.nodos.expresiones.basicas.Cadena;
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
import tiny1.asint.nodos.instrucciones.InstruccionNew;
import tiny1.asint.nodos.instrucciones.InstruccionRead;
import tiny1.asint.nodos.instrucciones.InstruccionWhile;
import tiny1.asint.nodos.instrucciones.InstruccionWrite;
import tiny1.asint.nodos.parametros.ListaParamsMuchos;
import tiny1.asint.nodos.parametros.ListaParamsUno;
import tiny1.asint.nodos.parametros.ParamRef;
import tiny1.asint.nodos.parametros.ParamValor;
import tiny1.asint.nodos.parametros.ParamsCon;
import tiny1.asint.nodos.parametros.ParamsRealMuchas;
import tiny1.asint.nodos.parametros.ParamsRealNinguna;
import tiny1.asint.nodos.parametros.ParamsSin;
import tiny1.asint.nodos.programa.ProgramaConDecs;
import tiny1.asint.nodos.programa.ProgramaSinDecs;

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

	@Override
	public void procesa(ProgramaSinDecs programa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ParamsSin parametros) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ParamsCon parametros) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ParamValor parametro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ParamRef parametro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ListaParamsUno listaParametros) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ListaParamsMuchos listaParametros) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(TipoBasico tipo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(TipoArray tipo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(TipoPointer tipo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(TipoRecord tipo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Campo campo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(CamposUno campos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(CamposMuchos campos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstrAsignacion instruccion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstrOptNinguna instruccionOpt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstrOptMuchas instruccionesOpt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstruccionIf instruccion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstruccionIfElse instruccion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstruccionWhile instruccion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstruccionRead instruccion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstruccionWrite instruccion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstruccionNL instruccion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstruccionNew instruccion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstruccionDelete instruccion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstruccionCall instruccion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(InstruccionBloque instrucciones) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ParamsRealNinguna parametrosReal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ParamsRealMuchas parametrosReal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(BloqueVacio bloque) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(BloqueLleno bloques) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ExpresionesUna expresiones) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(ExpresionesMuchas expresiones) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(PorCiento porCiento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Cadena cadena) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(AccesoArray accesoArray) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Punto punto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Flecha flecha) {
		// TODO Auto-generated method stub
		
	}

}
