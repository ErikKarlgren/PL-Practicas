package tiny1.procesamientos.gen_codigo;

import java.util.Stack;

import tiny1.asint.nodos.declaraciones.DecProc;
import tiny1.asint.nodos.declaraciones.DecType;
import tiny1.asint.nodos.declaraciones.DecVar;
import tiny1.asint.nodos.declaraciones.DecsMuchas;
import tiny1.asint.nodos.declaraciones.DecsUna;
import tiny1.procesamientos.ProcesadorConRetorno;

class RecolectaProcs extends ProcesadorConRetorno<Stack<DecProc>> {

    private final Stack<DecProc> pilaProcs;

    public RecolectaProcs() {
        this.pilaProcs = new Stack<>();
    }

    @Override
    protected Stack<DecProc> resultado() {
        return pilaProcs;
    }

    @Override
    public void procesa(DecsUna declaraciones) {
        declaraciones.dec().procesa(this);
    }

    @Override
    public void procesa(DecsMuchas declaraciones) {
        declaraciones.decs().procesa(this);
        declaraciones.dec().procesa(this);
    }

    @Override
    public void procesa(DecType decType) {
        // No hacer nada
    }

    @Override
    public void procesa(DecVar decVar) {
        // No hacer nada
    }

    @Override
    public void procesa(DecProc decProc) {
        pilaProcs.push(decProc);
    }
    
}
