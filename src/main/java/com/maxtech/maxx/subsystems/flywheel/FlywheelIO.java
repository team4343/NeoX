package com.maxtech.maxx.subsystems.flywheel;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.maxtech.lib.drivers.LazyTalonFX;
import com.maxtech.lib.scheduling.IO;

public class FlywheelIO extends IO {
    private static FlywheelIO instance;

    public static FlywheelIO getInstance() {
        if (instance == null) instance = new FlywheelIO();
        return instance;
    }

    private final LazyTalonFX master = new LazyTalonFX(5);
    private final LazyTalonFX slave = new LazyTalonFX(6);

    private FlywheelIO() {
        slave.follow(master, FollowerType.PercentOutput);
        slave.setInverted(InvertType.OpposeMaster);
    }

    public void setVelocity(double velocity) {
        master.set(TalonFXControlMode.Velocity, velocity);
    }

    public double getVelocity() {
        return master.getSelectedSensorVelocity();
    }
}
