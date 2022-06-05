package tiny1.asint.nodos.expresiones.acceso_campo;

import java.util.Objects;

import tiny1.asint.nodos.expresiones.Expresion;
import tiny1.asint.nodos.otros.LazyFinalInt;
import tiny1.procesamientos.Procesador;

public class Flecha extends Expresion {

    private final Expresion exp;
    private final String campo;

    // Asignación de espacio
    private final LazyFinalInt desplazamiento;

    public Flecha(Expresion arg0, String arg1) {
        super(5);
        this.exp = Objects.requireNonNull(arg0);
        this.campo = Objects.requireNonNull(arg1);
        desplazamiento = new LazyFinalInt();
    }

    public Expresion exp() {
        return exp;
    }

    public String campo() {
        return this.campo;
    }

    @Override
    public void procesa(Procesador p) {
        p.procesa(this);
    }

    @Override
    public boolean esDesignador() {
        return true;
    }

    public LazyFinalInt desplazamiento() {
        return desplazamiento;
    }
}
