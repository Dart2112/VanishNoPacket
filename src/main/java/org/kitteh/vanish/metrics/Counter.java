package org.kitteh.vanish.metrics;

public final class Counter extends Metrics.Plotter {
    Counter(String name) {
        super(name);
    }

    @Override
    public int getValue() {
        return 1;
    }
}