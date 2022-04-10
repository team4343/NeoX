package com.maxtech.lib.scheduling.auto.actions;

import edu.wpi.first.wpilibj.Timer;

public class WaitAction implements Action {
    private final double timeToWait;
    private double startTime;

    public WaitAction(double timeToWait) {
        this.timeToWait = timeToWait;
    }

    @Override
    public void start() {
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void update() {}

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime >= timeToWait;
    }

    @Override
    public void done() {}
}
