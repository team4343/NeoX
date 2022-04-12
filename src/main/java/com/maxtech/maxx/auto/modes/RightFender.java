package com.maxtech.maxx.auto.modes;

import com.maxtech.lib.scheduling.auto.AutonomousMode;
import com.maxtech.lib.scheduling.auto.actions.NoopAction;
import com.maxtech.lib.scheduling.auto.actions.WaitAction;
import com.maxtech.maxx.auto.actions.SetFlywheelHigh;
import com.maxtech.maxx.auto.actions.TrajectoryRunner;

import static com.pathplanner.lib.PathPlanner.loadPath;

public class RightFender extends AutonomousMode {
    public RightFender() {
        super("Right Fender");

        addActions(
                new SetFlywheelHigh(),
                new NoopAction(),
                new WaitAction(1),
                new TrajectoryRunner(loadPath("Right Fender 0", 5, 3))
        );
    }
}
