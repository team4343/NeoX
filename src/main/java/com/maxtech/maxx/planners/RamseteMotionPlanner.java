package com.maxtech.maxx.planners;

import com.maxtech.maxx.subsystems.drive.DriveAttitude;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import static com.maxtech.maxx.Constants.Drive.KINEMATICS;

public class RamseteMotionPlanner {
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

        startTime = Timer.getFPGATimestamp();
    }

    public void run() {
        var goal = trajectory.sample(Timer.getFPGATimestamp() - startTime);
        var chassisSpeeds = controller.calculate(driveAttitude.getPose(), goal);
        var wheelSpeeds = KINEMATICS.toWheelSpeeds(chassisSpeeds);
        driveAttitude.setWheelSpeeds(new DifferentialDrive.WheelSpeeds(wheelSpeeds.leftMetersPerSecond, wheelSpeeds.rightMetersPerSecond));
    }

    public void stop() {
        trajectory = null;
    }
}
