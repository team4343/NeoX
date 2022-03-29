package com.maxtech.maxx.subsystems.drive;

import com.maxtech.lib.RobotLogger;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Twist2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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
    public double leftAppliedVoltage, rightAppliedVoltage;

    private DriveAttitude() {
        odometry = new DifferentialDriveOdometry(new Rotation2d());
        field = new Field2d();
        leftAppliedVoltage = 0;
        rightAppliedVoltage = 0;

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

    public void setVoltages(double leftAppliedVoltage, double rightAppliedVoltage) {
        this.leftAppliedVoltage = leftAppliedVoltage;
        this.rightAppliedVoltage = rightAppliedVoltage;
    }

    public void setWheelSpeeds(DifferentialDrive.WheelSpeeds speeds) {
        this.leftAppliedVoltage = speeds.left * 12;
        this.rightAppliedVoltage = speeds.right * 12;
    }

    public DifferentialDriveOdometry getOdometry() {
        return odometry;
    }

    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    public Twist2d getHeading() {
        return new Twist2d();
    }

    public Field2d getField() {
        return field;
    }

    public Pair<Double, Double> getVoltages() {
        return new Pair<>(leftAppliedVoltage, rightAppliedVoltage);
    }
}
