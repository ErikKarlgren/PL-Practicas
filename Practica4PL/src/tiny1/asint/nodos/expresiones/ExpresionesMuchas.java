package tiny1.asint.nodos.expresiones;

import tiny1.procesamientos.Procesador;

public class ExpresionesMuchas implements Expresiones {
    private Expresiones expresiones;
    private Expresion expresion;
    
    public ExpresionesMuchas(Expresiones expresiones, Expresion expresion) {
        this.expresiones = expresiones;
        this.expresion = expresion;
    }

    public Expresiones expresiones() { return expresiones; }

    public Expresion expresion() { return expresion; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
