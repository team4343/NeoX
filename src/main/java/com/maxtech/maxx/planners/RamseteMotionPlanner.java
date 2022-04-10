package com.maxtech.maxx.planners;

import com.maxtech.lib.scheduling.Planner;
import com.maxtech.maxx.subsystems.drive.DriveAttitude;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj.Timer;

import static com.maxtech.maxx.Constants.Drive.KINEMATICS;

public class RamseteMotionPlanner implements Planner {
    private static RamseteMotionPlanner instance;

    public static RamseteMotionPlanner getInstance() {
        if (instance == null) instance = new RamseteMotionPlanner();
        return instance;
    }

    private RamseteMotionPlanner() {}

    private final DriveAttitude driveAttitude = DriveAttitude.getInstance();

    private Trajectory trajectory;
    private double startTime;
    private final RamseteController controller = new RamseteController();

    public void setTrajectory(Trajectory trajectory) {
        this.trajectory = trajectory;
        driveAttitude.getField().getObject("TRAJECTORY").setTrajectory(trajectory);
    }

    @Override
    public void init() {
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void run() {
        var goal = trajectory.sample(Timer.getFPGATimestamp() - startTime);
        var chassisSpeeds = controller.calculate(driveAttitude.getPose(), goal);
        var wheelSpeeds = KINEMATICS.toWheelSpeeds(chassisSpeeds);
    }

    @Override
    public boolean atReference() {
        return controller.atReference();
    }

    @Override
    public void stop() {
        trajectory = null;
    }
}
