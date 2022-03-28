package com.maxtech.lib.scheduling;

import edu.wpi.first.wpilibj.Notifier;

import java.util.ArrayList;

public class Looper {
    private final ArrayList<Loop> loops = new ArrayList<>();
    private final Notifier notifier = new Notifier(() -> loops.forEach(Loop::onLoop));

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
