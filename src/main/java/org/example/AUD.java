package org.example;

import java.util.Objects;

public class AUD {
    private final double value;

    public AUD(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AUD aud = (AUD) o;
        return Double.compare(aud.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public AUD add(AUD aud) {
        return new AUD(this.value + aud.value);
    }

    public AUD Subtract(AUD aud) {
        return new AUD( this.value - aud.value);
    }

    public boolean greaterThanOrEqual(AUD amount) {
        return this.value >= amount.value;
    }

    @Override
    public String toString() {
        return "AUD{" +
                "value=" + value +
                '}';
    }
}
