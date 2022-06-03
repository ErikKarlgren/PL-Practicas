package tiny1.errors;

import tiny1.alex.UnidadLexica;
import tiny1.asint.nodos.Nodo;

public class GestionErroresTiny {

    private boolean foundError;

    public GestionErroresTiny() {
        foundError = false;
    }

    public boolean foundError() {
        return foundError;
    }

    public void errorLexico(int fila, int columna, String lexema) {
        foundError = true;
        System.out.println("ERROR fila " + fila + ", columna " + columna + ": Caracter inexperado: " + lexema);
        System.exit(1);
    }

    public void errorSintactico(UnidadLexica unidadLexica) {
        foundError = true;
        System.out.println("ERROR fila " + unidadLexica.fila() + ", columna " + unidadLexica.columna()
                + " : Elemento ineXperado " + unidadLexica.value);
        System.exit(1);
    }

    public void errorProcesamiento(String mensaje, Nodo... nodos) {
        foundError = true;
        StringBuilder msjError = new StringBuilder(mensaje + '\n');
        for (Nodo n : nodos) {
            msjError.append(n);
        }
        new ErrorProcesamiento(msjError.toString()).printStackTrace();
        System.out.println();
    }

    private class ErrorProcesamiento extends Throwable {
        public ErrorProcesamiento(String mensaje) {
            super(mensaje);
        }
    }
}
