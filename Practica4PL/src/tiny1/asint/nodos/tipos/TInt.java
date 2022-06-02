package tiny1.asint.nodos.tipos;

import tiny1.procesamientos.Procesador;

public class TInt extends Tipo {
    @Override
    public void procesa(Procesador p) { p.procesa(this);}

    public boolean equals(Object obj){
        return this.getClass().equals(obj.getClass());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
