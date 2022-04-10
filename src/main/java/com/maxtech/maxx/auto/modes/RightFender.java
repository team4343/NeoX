package com.maxtech.maxx.auto.modes;

import com.maxtech.lib.scheduling.auto.AutonomousMode;
import com.maxtech.lib.scheduling.auto.actions.NoopAction;
import com.maxtech.maxx.auto.actions.SetFlywheelHigh;
import com.maxtech.maxx.auto.actions.TrajectoryRunner;

import static com.pathplanner.lib.PathPlanner.loadPath;

public class RightFender extends AutonomousMode {
    public RightFender() {
        super("Right Fender");
    }

    @Override
    public void run() {
        runAction(new NoopAction());
        runAction(new TrajectoryRunner(loadPath("Right Fender 0", getMaxVel(), getMaxAccel())));
        runAction(new SetFlywheelHigh());
    }

    @Override
    public double getMaxVel() {
        return 5;
    }

    @Override
    public double getMaxAccel() {
        return 2;
    }
}
