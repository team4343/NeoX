package com.maxtech.lib.scheduling;

import edu.wpi.first.wpilibj.Notifier;

import java.util.ArrayList;

public class Looper implements ILooper {
    private final ArrayList<Loop> loops = new ArrayList<>();
    protected final Notifier notifier = new Notifier(() -> loops.forEach(Loop::onLoop));

    public Looper(String name) {
        notifier.setName(name);
    }

    public void register(Loop loop) {
        loops.add(loop);
    }

    public void start() {
        loops.forEach(Loop::onStart);
        notifier.startPeriodic(0.02);
    }

    public void stop() {
        notifier.stop();
        loops.forEach(Loop::onEnd);
    }
}
