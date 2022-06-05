package tiny1.procesamientos.gen_codigo;

import tiny1.asint.nodos.tipos.TInt;
import tiny1.asint.nodos.tipos.TReal;
import tiny1.asint.nodos.tipos.TString;
import tiny1.maquinaP.MaquinaP;
import tiny1.maquinaP.MaquinaP.Instruccion;
import tiny1.procesamientos.ProcesadorConRetorno;

public class GenInsRead extends ProcesadorConRetorno<MaquinaP.Instruccion> {

    private final MaquinaP maq;
    private MaquinaP.Instruccion instruccion;

    public GenInsRead(MaquinaP maq) {
        this.maq = maq;
    }

    @Override
    protected Instruccion resultado() {
        return instruccion;
    }

    @Override
    public void procesa(TInt tipo) {
        instruccion = maq.leerInt();
    }

    @Override
    public void procesa(TReal tipo) {
        instruccion = maq.leerReal();
    }

    @Override
    public void procesa(TString tipo) {
        instruccion = maq.leerString();
    }

}
