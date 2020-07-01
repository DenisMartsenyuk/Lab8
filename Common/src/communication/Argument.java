package communication;

import java.io.Serializable;

public class Argument<T> implements Serializable {
    private T value;

    public Argument(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
