package ru.innopolis.java.homework10_addition;

import java.util.Objects;

public class Pair<T, V> {
    private final T first;
    private final V second;

    private Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public static <T, V> Pair<T, V> of(T valueT, V valueV) {
        return new Pair<>(valueT, valueV);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(getFirst(), pair.getFirst()) && Objects.equals(getSecond(), pair.getSecond());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getSecond());
    }
}
