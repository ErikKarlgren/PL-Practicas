package tiny1.asint.nodos.otros;

import java.util.Objects;

public class LazyFinalField<T> {

    private T value;

    final public void set(T value) {
        if (!isInitialized()) {
            this.value = Objects.requireNonNull(value);
        } else {
            throw new IllegalStateException("El valor ya ha sido asignado");
        }
    }

    final public void set(LazyFinalField<T> field) {
        Objects.requireNonNull(field);
        set(field.value);
    }

    final public T get() {
        if (!isInitialized()) {
            throw new IllegalStateException("El valor no ha sido asignado");
        }
        return value;
    }

    final public boolean isInitialized() {
        return this.value != null && !this.value.equals(nullValue());
    }

    protected T nullValue() {
        return null;
    }
}
