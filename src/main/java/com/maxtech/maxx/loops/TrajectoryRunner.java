package com.maxtech.maxx.loops;

import com.maxtech.lib.scheduling.Loop;
import com.maxtech.maxx.planners.RamseteMotionPlanner;
import com.pathplanner.lib.PathPlanner;

public class TrajectoryRunner extends Loop {
    private final RamseteMotionPlanner motionPlanner = RamseteMotionPlanner.getInstance();
    private final String trajectoryName;

    public TrajectoryRunner(String trajectoryName) {
        this.trajectoryName = trajectoryName;
    }

    @Override
    public void onStart() {
        motionPlanner.setTrajectory(PathPlanner.loadPath(trajectoryName, 5, 3));
    }

    @Override
    public void onLoop() {
        motionPlanner.run();
    }

    @Override
    public void onEnd() {}
}
