package tiny1.procesamientos.chequeo_tipos;

import tiny1.asint.nodos.campos.CamposMuchos;
import tiny1.asint.nodos.campos.CamposUno;
import tiny1.asint.nodos.declaraciones.DecsMuchas;
import tiny1.asint.nodos.declaraciones.DecsUna;
import tiny1.asint.nodos.expresiones.ExpresionesMuchas;
import tiny1.asint.nodos.expresiones.ExpresionesNinguna;
import tiny1.asint.nodos.expresiones.ExpresionesUna;
import tiny1.asint.nodos.instrucciones.InstrMuchas;
import tiny1.asint.nodos.instrucciones.InstrUna;
import tiny1.asint.nodos.parametros.ListaParamsMuchos;
import tiny1.asint.nodos.parametros.ListaParamsUno;
import tiny1.asint.nodos.parametros.ParamsSin;
import tiny1.procesamientos.ProcesadorConRetorno;

class ContarElementos implements ProcesadorConRetorno<Integer> {
    private int contador;

    public ContarElementos() {
        this.contador = 0;
    }

    @Override
    public Integer resultado() {
        return contador;
    }

    @Override
    public void procesa(DecsUna declaraciones) {
        contador++;
    }

    @Override
    public void procesa(DecsMuchas declaraciones) {
        declaraciones.declaraciones().procesa(this);
        contador++;
    }

    @Override
    public void procesa(ParamsSin parametros) {
        // No hacer nada
    }

    @Override
    public void procesa(ListaParamsUno listaParametros) {
        contador++;
    }

    @Override
    public void procesa(ListaParamsMuchos listaParametros) {
        listaParametros.listaParametros().procesa(this);
        contador++;
    }

    @Override
    public void procesa(CamposUno campos) {
        contador++;
    }

    @Override
    public void procesa(CamposMuchos campos) {
        campos.campos().procesa(this);
        contador++;
    }

    @Override
    public void procesa(InstrUna instrucciones) {
        contador++;
    }

    @Override
    public void procesa(InstrMuchas instrucciones) {
        instrucciones.instrucciones().procesa(this);
        contador++;
    }

    @Override
    public void procesa(ExpresionesNinguna expresiones) {
        // No hacer nada
    }

    @Override
    public void procesa(ExpresionesUna expresiones) {
        contador++;
    }

    @Override
    public void procesa(ExpresionesMuchas expresiones) {
        expresiones.expresiones().procesa(this);
        contador++;
    }

}
