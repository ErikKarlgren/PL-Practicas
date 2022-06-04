package tiny1.asint.nodos.otros;

public class LazyFinalInt extends LazyFinalField<Integer> {

    public int add(LazyFinalInt other) {
        return get() + other.get();
    }

    public int sub(LazyFinalInt other) {
        return get() - other.get();
    }

    public int mul(LazyFinalInt other) {
        return get() * other.get();
    }

    public int div(LazyFinalInt other) {
        return get() / other.get();
    }

    public int mod(LazyFinalInt other) {
        return get() % other.get();
    }

    public int neg() {
        return -get();
    }
}
