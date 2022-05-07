package tiny0.semops;

import tiny0.asint.SintaxisAbstracta;
import tiny0.asint.nodos.expresiones.Expresion;

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
        default:
            throw new UnsupportedOperationException("exp " + op);
        }
    }


}
