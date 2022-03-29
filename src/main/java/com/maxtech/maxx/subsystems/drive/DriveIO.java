package com.maxtech.maxx.subsystems.drive;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.maxtech.lib.drivers.LazyTalonFX;
import com.maxtech.lib.scheduling.IO;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveIO extends IO {
    private static DriveIO instance;

    public static DriveIO getInstance() {
        if (instance == null) {
            instance = new DriveIO();
        }

        return instance;
    }

    private final LazyTalonFX LEFT_BACK = new LazyTalonFX(1);
    private final LazyTalonFX LEFT_FRONT = new LazyTalonFX(2);
    private final LazyTalonFX RIGHT_BACK = new LazyTalonFX(3);
    private final LazyTalonFX RIGHT_FRONT = new LazyTalonFX(4);

    private final ProfiledPIDController LEFT_CONTROLLER = new ProfiledPIDController(1, 0, 0, new TrapezoidProfile.Constraints(10, 5));
    private final ProfiledPIDController RIGHT_CONTROLLER = new ProfiledPIDController(1, 0, 0, new TrapezoidProfile.Constraints(10, 5));

    public void stop() {
    }

    public void setVoltages(Pair<Double, Double> voltages) {
        LEFT_BACK.set(TalonFXControlMode.PercentOutput, voltages.getFirst() / 12);
        LEFT_FRONT.set(TalonFXControlMode.PercentOutput, voltages.getFirst() / 12);
        RIGHT_BACK.set(TalonFXControlMode.PercentOutput, voltages.getSecond() / 12);
        RIGHT_FRONT.set(TalonFXControlMode.PercentOutput, voltages.getSecond() / 12);
    }

    public void setWheelSpeeds(DifferentialDrive.WheelSpeeds speeds) {
        LEFT_BACK.set(TalonFXControlMode.PercentOutput, speeds.left);
        LEFT_FRONT.set(TalonFXControlMode.PercentOutput, speeds.left);
        RIGHT_BACK.set(TalonFXControlMode.PercentOutput, speeds.right);
        RIGHT_FRONT.set(TalonFXControlMode.PercentOutput, speeds.right);
    }
}
