package com.maxtech.maxx.subsystems.climber;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.maxtech.lib.drivers.LazyTalonSRX;
import com.maxtech.lib.scheduling.IO;
import com.maxtech.lib.scheduling.Loop;
import com.maxtech.lib.scheduling.Looper;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class ClimberIO extends IO {
    private static ClimberIO instance;

    public static ClimberIO getInstance() {
        if (instance == null) instance = new ClimberIO();
        return instance;
    }

    private final ClimberAttitude attitude = ClimberAttitude.getInstance();

    private final LazyTalonSRX LEFT = new LazyTalonSRX(11);
    private final LazyTalonSRX RIGHT = new LazyTalonSRX(12);

    private final ProfiledPIDController LEFT_CONTROLLER = new ProfiledPIDController(1, 0, 0, new TrapezoidProfile.Constraints(10, 5));
    private final ProfiledPIDController RIGHT_CONTROLLER = new ProfiledPIDController(1, 0, 0, new TrapezoidProfile.Constraints(10, 5));

    private final ElevatorFeedforward LEFT_FEEDFORWARD = new ElevatorFeedforward(1, 1, 1, 1);
    private final ElevatorFeedforward RIGHT_FEEDFORWARD = new ElevatorFeedforward(1, 1, 1, 1);

    private ClimberIO() {
        var tab = Shuffleboard.getTab("Climber");
        tab.addNumber("Position", LEFT::getSelectedSensorPosition);
    }

    public void register(Looper looper) {
        looper.register(new Loop() {
            @Override
            public void onStart() {

            }

            @Override
            public void onLoop() {
                LEFT.set(TalonSRXControlMode.Position, LEFT_CONTROLLER.calculate(LEFT.getSelectedSensorPosition(), attitude.getDesired()) + LEFT_FEEDFORWARD.calculate(LEFT.getSelectedSensorVelocity()));
                RIGHT.set(TalonSRXControlMode.Position, RIGHT_CONTROLLER.calculate(RIGHT.getSelectedSensorPosition(), attitude.getDesired()) + RIGHT_FEEDFORWARD.calculate(RIGHT.getSelectedSensorVelocity()));
            }

            @Override
            public void onEnd() {

            }
        });
    }

    public double getPosition() {
        return LEFT.getSelectedSensorPosition();
    }
}
