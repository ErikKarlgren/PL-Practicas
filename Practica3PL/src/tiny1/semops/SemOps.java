package tiny1.semops;

import tiny1.asint.SintaxisAbstracta;
import tiny1.asint.nodos.StringLocalizado;
import tiny1.asint.nodos.declaraciones.DecVar;
import tiny1.asint.nodos.declaraciones.Declaracion;
import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.asint.nodos.tipos.Tipo;

public class SemOps extends SintaxisAbstracta {
	public Expresion exp(String op, Expresion arg0, Expresion arg1) {
		switch (op) {
		case "and":
			return and(arg0, arg1);
		case "or":
			return or(arg0, arg1);
		case ">":
			return mayor(arg0, arg1);
		case "<":
			return menor(arg0, arg1);
		case ">=":
			return mayorigual(arg0, arg1);
		case "<=":
			return menorigual(arg0, arg1);
		case "==":
			return igualcomp(arg0, arg1);
		case "!=":
			return distinto(arg0, arg1);
		case "*":
			return mul(arg0, arg1);
		case "/":
			return div(arg0, arg1);
		case "%":
			return porciento(arg0, arg1);
		default:
			throw new UnsupportedOperationException("exp " + op);
		}
	}

	public Expresion acceso_registro(String op, Expresion arg0, String id) {
		switch (op) {
		case "->":
			return flecha(arg0, id);
		case ".":
			return punto(arg0, id);
		default:
			throw new UnsupportedOperationException("exp " + op);
		}
	}
	

}
