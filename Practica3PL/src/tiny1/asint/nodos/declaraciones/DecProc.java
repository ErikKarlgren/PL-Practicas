package tiny1.asint.nodos.declaraciones;

import tiny1.asint.nodos.StringLocalizado;
import tiny1.asint.nodos.bloques.Bloque;
import tiny1.asint.nodos.expresiones.tipos.Tipo;
import tiny1.asint.nodos.parametros.ListaParams;
import tiny1.procesamientos.Procesador;

public class DecProc implements Declaracion {
	
    private StringLocalizado string;
    private ListaParams listaParametros;
    private Bloque bloque;
    public DecProc( StringLocalizado string, ListaParams lp, Bloque bloque) {
        this.string = string;
        this.listaParametros=lp;
        this.bloque=bloque;
    }

    public StringLocalizado id() { return string; }
    
    public ListaParams listaParams() {return listaParametros;}
    
    public Bloque bloque() {return bloque;}

    @Override
    public void procesa(Procesador p) { p.procesa(this); }
}
