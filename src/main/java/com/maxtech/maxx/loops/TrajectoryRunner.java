package com.maxtech.maxx.loops;

import com.maxtech.lib.drivers.Pathweaver;
import com.maxtech.lib.scheduling.Loop;
import com.maxtech.maxx.planners.RamseteMotionPlanner;

public class TrajectoryRunner extends Loop {
    private final RamseteMotionPlanner motionPlanner = RamseteMotionPlanner.getInstance();
    private final String trajectoryName;

    public TrajectoryRunner(String trajectoryName) {
        this.trajectoryName = trajectoryName;
    }

    @Override
    public void onStart() {
        motionPlanner.setTrajectory(Pathweaver.loadPathweaverTrajectory(trajectoryName));
    }

    @Override
    public void onLoop() {
        motionPlanner.run();
    }

    @Override
    public void onEnd() {
    }
}
