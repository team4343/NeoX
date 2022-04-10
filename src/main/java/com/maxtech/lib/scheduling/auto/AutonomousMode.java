package com.maxtech.lib.scheduling.auto;

import com.maxtech.lib.scheduling.Looper;
import com.maxtech.lib.scheduling.auto.actions.Action;

public abstract class AutonomousMode extends Looper {
    protected AutonomousMode(String name) {
        super(name);
    }

    public void run() {};

    public void runAction(Action action) {
        action.start();

        do {
            action.update();
        } while (!action.isFinished());

        action.done();
    }

    public abstract double getMaxVel();
    public abstract double getMaxAccel();
}
