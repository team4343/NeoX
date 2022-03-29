package com.maxtech.maxx.subsystems.drive;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.maxtech.lib.drivers.LazyTalonFX;
import com.maxtech.lib.scheduling.IO;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Twist2d;
import edu.wpi.first.wpilibj.DriverStation;

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

    public void setHeading(Twist2d heading) {

    }

    public void stop() {
    }

    public void setVoltages(Pair<Double, Double> voltages) {
        LEFT_BACK.set(TalonFXControlMode.PercentOutput, voltages.getFirst() / 12);
        LEFT_FRONT.set(TalonFXControlMode.PercentOutput, voltages.getFirst() / 12);
        RIGHT_BACK.set(TalonFXControlMode.PercentOutput, voltages.getSecond() / 12);
        RIGHT_FRONT.set(TalonFXControlMode.PercentOutput, voltages.getSecond() / 12);

        DriverStation.reportWarning("Set voltages to " + voltages.getFirst() + " " + voltages.getSecond(), false);
    }
}
