package tiny1.procesamientos.vinculacion;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import tiny1.asint.nodos.Nodo;

class TablaSimbolos implements Map<String, Nodo> {
    private final Stack<HashMap<String, Nodo>> pila;

    public TablaSimbolos() {
        pila = new Stack<>();
        pila.push(new HashMap<>());
    }

    public void abrirNivel() {
        pila.push(new HashMap<>());
    }

    public void cerrarNivel() {
        pila.pop();
    }

    @Override
    public Nodo get(Object key) {
        return pila.peek().get(key);
    }

    @Override
    public boolean containsKey(Object key) {
        return pila.peek().containsKey(key);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsValue(Object arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entry<String, Nodo>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Nodo put(String arg0, Nodo arg1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends String, ? extends Nodo> arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Nodo remove(Object arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Nodo> values() {
        throw new UnsupportedOperationException();
    }

}
