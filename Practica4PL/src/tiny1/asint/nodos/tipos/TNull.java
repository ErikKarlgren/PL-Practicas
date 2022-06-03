package tiny1.asint.nodos.tipos;

import tiny1.procesamientos.Procesador;

public class TNull extends Tipo {
    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }

    @Override
    public boolean equals(Object obj){
        return this.getClass().equals(obj.getClass());
    }
}
