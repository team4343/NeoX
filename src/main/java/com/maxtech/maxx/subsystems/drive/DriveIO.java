package com.maxtech.maxx.subsystems.drive;

import com.kauailabs.navx.frc.AHRS;
import com.maxtech.lib.drivers.AdvancedSparkMax;
import com.maxtech.lib.scheduling.IO;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import static com.revrobotics.CANSparkMax.ControlType.kVelocity;
import static com.revrobotics.CANSparkMax.ControlType.kVoltage;
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;

public class DriveIO extends IO {
    private static DriveIO instance;

    public static DriveIO getInstance() {
        if (instance == null) {
            instance = new DriveIO();
        }

        return instance;
    }

    private final AdvancedSparkMax LEFT_BACK = new AdvancedSparkMax(11, kBrushless);
    private final AdvancedSparkMax LEFT_FRONT = new AdvancedSparkMax(12, kBrushless);
    private final AdvancedSparkMax RIGHT_BACK = new AdvancedSparkMax(13, kBrushless);
    private final AdvancedSparkMax RIGHT_FRONT = new AdvancedSparkMax(14, kBrushless);

    private final SparkMaxPIDController LEFT_CONTROLLER = LEFT_BACK.getPIDController();
    private final SparkMaxPIDController RIGHT_CONTROLLER = RIGHT_BACK.getPIDController();

    private final AHRS gyroscope = new AHRS();

    public DriveIO() {
        LEFT_FRONT.follow(LEFT_BACK);
        RIGHT_FRONT.follow(RIGHT_BACK);
    }

    public void stop() {
        setVoltages(new Pair<>(0., 0.));
    }

    public void setVoltages(Pair<Double, Double> voltages) {
        LEFT_CONTROLLER.setReference(voltages.getFirst(), kVoltage);
        RIGHT_CONTROLLER.setReference(voltages.getSecond(), kVoltage);
    }

    public void setWheels(DifferentialDrive.WheelSpeeds speeds) {
        LEFT_CONTROLLER.setReference(speeds.left * 12, kVoltage);
        RIGHT_CONTROLLER.setReference(speeds.right * 12, kVoltage);
    }

    public void setWheelSpeeds(DifferentialDriveWheelSpeeds speeds) {
        LEFT_CONTROLLER.setReference(speeds.leftMetersPerSecond, kVelocity);
        RIGHT_CONTROLLER.setReference(speeds.rightMetersPerSecond, kVelocity);
    }

    public Pair<Double, Double> getDistances() {
        // TODO: MEASURE
        var gearing = 10.71 / 2;
        var wheelDiameter = 10;

        return new Pair<>(LEFT_BACK.getDistanceTravelled(gearing, wheelDiameter), RIGHT_BACK.getDistanceTravelled(gearing, wheelDiameter));
    }

    public Rotation2d getRotation() {
        return new Rotation2d(gyroscope.getAngle());
    }
}
