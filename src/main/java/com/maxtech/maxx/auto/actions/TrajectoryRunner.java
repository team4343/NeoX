package com.maxtech.maxx.auto.actions;

import com.maxtech.lib.scheduling.auto.actions.Action;
import com.maxtech.maxx.planners.RamseteMotionPlanner;
import edu.wpi.first.math.trajectory.Trajectory;

public class TrajectoryRunner implements Action {
    private final RamseteMotionPlanner planner = RamseteMotionPlanner.getInstance();

    public TrajectoryRunner(Trajectory trajectory) {
        planner.setTrajectory(trajectory);
    }

    @Override
    public void start() {
        planner.init();
    }

    @Override
    public void update() {
        planner.run();
    }

    @Override
    public void done() {
        planner.stop();
    }

    @Override
    public boolean isFinished() {
        return planner.atReference();
    }
}
