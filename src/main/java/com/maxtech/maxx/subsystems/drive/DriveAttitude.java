package com.maxtech.maxx.subsystems.drive;

import com.maxtech.lib.RobotLogger;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;

public class DriveAttitude {
    private static DriveAttitude instance;

    public static DriveAttitude getInstance() {
        if (instance == null) {
            instance = new DriveAttitude();
        }

        return instance;
    }

    private final DifferentialDriveOdometry odometry;
    private final Field2d field;

    private DriveAttitude() {
        odometry = new DifferentialDriveOdometry(new Rotation2d());
        field = new Field2d();

        field.setRobotPose(odometry.getPoseMeters());

        RobotLogger.sendWidget(field);
    }

    /**
     * To be called periodically in order to update the observations based on encoder values.
     */
    public void updateObservations() {
        odometry.update(new Rotation2d(), 0, 0);
        field.setRobotPose(odometry.getPoseMeters());
    }

    public void start(Pose2d startingPose, Rotation2d startingRotation) {
        odometry.resetPosition(startingPose, startingRotation);
    }

    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    public Field2d getField() {
        return field;
    }
}
