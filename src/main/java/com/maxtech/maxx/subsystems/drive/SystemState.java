package com.maxtech.maxx.subsystems.drive;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Twist2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class SystemState {
    private static SystemState instance;

    public static SystemState getInstance() {
        if (instance == null) {
            instance = new SystemState();
        }

        return instance;
    }

    private DifferentialDriveOdometry odometry;
    private double leftAppliedVoltage;
    private double rightAppliedVoltage;

    private SystemState() {
        odometry = new DifferentialDriveOdometry(new Rotation2d());
        leftAppliedVoltage = 0;
        rightAppliedVoltage = 0;
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

    public Twist2d getHeading() {
        return new Twist2d();
    }
}
