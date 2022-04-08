package com.maxtech.maxx.subsystems.drive;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.maxtech.lib.drivers.LazyTalonFX;
import com.maxtech.lib.scheduling.IO;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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

    private final CANSparkMax LEFT_BACK = new CANSparkMax(11, kBrushless);
    private final CANSparkMax LEFT_FRONT = new CANSparkMax(12, kBrushless);
    private final CANSparkMax RIGHT_BACK = new CANSparkMax(13, kBrushless);
    private final CANSparkMax RIGHT_FRONT = new CANSparkMax(14, kBrushless);

    private final SparkMaxPIDController LEFT_CONTROLLER = LEFT_BACK.getPIDController();
    private final SparkMaxPIDController RIGHT_CONTROLLER = RIGHT_BACK.getPIDController();

    public DriveIO() {
        LEFT_FRONT.follow(LEFT_BACK);
        RIGHT_FRONT.follow(RIGHT_BACK);
    }

    public void stop() {
    }

    public void setVoltages(Pair<Double, Double> voltages) {
        LEFT_CONTROLLER.setReference(voltages.getFirst(), kVoltage);
        RIGHT_CONTROLLER.setReference(voltages.getSecond(), kVoltage);
    }

    public void setWheelSpeeds(DifferentialDrive.WheelSpeeds speeds) {
        LEFT_CONTROLLER.setReference(speeds.left * 12, kVoltage);
        RIGHT_CONTROLLER.setReference(speeds.right * 12, kVoltage);
    }
}
