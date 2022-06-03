package tiny1.procesamientos.vinculacion;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import tiny1.asint.nodos.Nodo;

class TablaSimbolos {
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

    public boolean declaracionDuplicada(String id) {
        Objects.requireNonNull(id);
        return pila.peek().containsKey(id);
    }

    public Nodo get(Object key) {
        Objects.requireNonNull(key);
        return pila.stream()
                .map(map -> map.get(key))
                .filter(n -> n != null)
                .findFirst()
                .orElse(null);
    }

    public boolean existeId(Object key) {
        Objects.requireNonNull(key);
        return pila.stream().anyMatch(map -> map.containsKey(key));
    }

    public void clear() {
        pila.peek().clear();
    }

    public boolean containsValue(Object arg0) {
        Objects.requireNonNull(arg0);
        return pila.stream().anyMatch(map -> map.containsValue(arg0));
    }

    public Set<Entry<String, Nodo>> entrySet() {
        return pila.stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toSet());
    }

    public boolean isEmpty() {
        return pila.stream().allMatch(map -> map.isEmpty());
    }

    public Set<String> keySet() {
        return pila.stream()
                .flatMap(map -> map.keySet().stream())
                .collect(Collectors.toSet());
    }

    public Nodo put(String arg0, Nodo arg1) {
        Objects.requireNonNull(arg0);
        Objects.requireNonNull(arg1);
        return pila.peek().put(arg0, arg1);
    }

    public void putAll(Map<? extends String, ? extends Nodo> arg0) {
        Objects.requireNonNull(arg0);
        pila.peek().putAll(arg0);
    }

    public Nodo remove(Object arg0) {
        Objects.requireNonNull(arg0);
        return pila.stream()
                .map(map -> map.remove(arg0))
                .filter(n -> n != null)
                .findFirst()
                .orElse(null);
    }

    public int size() {
        return pila.stream().mapToInt(map -> map.size()).sum();
    }

    public Collection<Nodo> values() {
        return pila.stream()
                .flatMap(map -> map.values().stream())
                .collect(Collectors.toSet());
    }
}
