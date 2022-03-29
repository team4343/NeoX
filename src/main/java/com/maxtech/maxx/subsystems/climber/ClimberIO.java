package com.maxtech.maxx.subsystems.climber;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.maxtech.lib.drivers.LazyTalonFX;
import com.maxtech.lib.scheduling.IO;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class ClimberIO extends IO {
    private static ClimberIO instance;

    public static ClimberIO getInstance() {
        if (instance == null) instance = new ClimberIO();
        return instance;
    }

    private final LazyTalonFX LEFT = new LazyTalonFX(11);
    private final LazyTalonFX RIGHT = new LazyTalonFX(12);

    private final ProfiledPIDController LEFT_CONTROLLER = new ProfiledPIDController(1, 0, 0, new TrapezoidProfile.Constraints(10, 5));
    private final ProfiledPIDController RIGHT_CONTROLLER = new ProfiledPIDController(1, 0, 0, new TrapezoidProfile.Constraints(10, 5));

    public void setPosition(double position) {
        LEFT.set(TalonFXControlMode.Position, LEFT_CONTROLLER.calculate(LEFT.getSelectedSensorPosition(), position));
        RIGHT.set(TalonFXControlMode.Position, RIGHT_CONTROLLER.calculate(RIGHT.getSelectedSensorPosition(), position));
    }
}
