package tiny1.asint.nodos.expresiones.aritmeticas;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.procesamientos.Procesador;

public class Division extends ExpresionMultiplicativa {
    
    public Division(Expresion arg0, Expresion arg1) {
        super(arg0, arg1);
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }
}
