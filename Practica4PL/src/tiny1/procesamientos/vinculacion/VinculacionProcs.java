package tiny1.procesamientos.vinculacion;

import tiny1.asint.nodos.declaraciones.*;
import tiny1.errors.GestionErroresTiny;
import tiny1.procesamientos.Procesador;

public class VinculacionProcs implements Procesador {
    private final GestionErroresTiny err;
    private final TablaSimbolos tablaSimbolos;
    private final Vinculacion vinculacion;
    private final VinculacionDecs1 vinculacionDecs1;
    private final VinculacionDecs2 vinculacionDecs2;

    public VinculacionProcs(GestionErroresTiny err, TablaSimbolos tablaSimbolos,
            Vinculacion vinculacion, VinculacionDecs1 vinculacionDecs1, VinculacionDecs2 vinculacionDecs2) {
        this.err = err;
        this.tablaSimbolos = tablaSimbolos;
        this.vinculacion = vinculacion;
        this.vinculacionDecs1 = vinculacionDecs1;
        this.vinculacionDecs2 = vinculacionDecs2;
    }

    @Override
    public void procesa(DecsUna declaraciones) {
        declaraciones.declaracion().procesa(this);
    }

    @Override
    public void procesa(DecsMuchas declaraciones) {
        declaraciones.declaraciones().procesa(this);
        declaraciones.declaracion().procesa(this);
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
        tablaSimbolos.abrirNivel();

        decProc.listaParams().procesa(vinculacionDecs1);
        decProc.listaParams().procesa(vinculacionDecs2);

        decProc.bloque().procesa(vinculacion);

        tablaSimbolos.cerrarNivel();
    }
}
