package tiny1.asint.nodos.campos;

import tiny1.procesamientos.Procesador;

public class CamposUno implements Campos {
    private Campo campo;

    public CamposUno(Campo campo) { this.campo = campo; }

    public Campo campo() { return campo; }

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
