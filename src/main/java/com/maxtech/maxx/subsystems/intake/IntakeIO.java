package com.maxtech.maxx.subsystems.intake;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.maxtech.lib.drivers.LazyTalonFX;
import com.maxtech.lib.scheduling.IO;
import com.maxtech.lib.scheduling.Loop;
import com.maxtech.lib.scheduling.Looper;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class IntakeIO extends IO {
    private static IntakeIO instance;

    public static IntakeIO getInstance() {
        if (instance == null) instance = new IntakeIO();
        return instance;
    }

    private final IntakeAttitude attitude = IntakeAttitude.getInstance();

    private final LazyTalonFX PIVOT = new LazyTalonFX(5);
    private final LazyTalonFX MOTOR = new LazyTalonFX(6);

    private final ProfiledPIDController PIVOT_CONTROLLER = new ProfiledPIDController(1, 0, 0, new TrapezoidProfile.Constraints(10, 5));
    private final ProfiledPIDController MOTOR_CONTROLLER = new ProfiledPIDController(1, 0, 0, new TrapezoidProfile.Constraints(10, 5));

    private final ElevatorFeedforward PIVOT_FEEDFORWARD = new ElevatorFeedforward(1, 1, 1, 1);
    private final ElevatorFeedforward MOTOR_FEEDFORWARD = new ElevatorFeedforward(1, 1, 1, 1);

    public void register(Looper looper) {
        looper.register(new Loop() {
            @Override
            public void onStart() {

            }

            @Override
            public void onLoop() {
                PIVOT.set(TalonFXControlMode.Position, PIVOT_CONTROLLER.calculate(PIVOT.getSelectedSensorPosition(), attitude.getDesiredPosition()) + PIVOT_FEEDFORWARD.calculate(PIVOT.getSelectedSensorVelocity()));
                MOTOR.set(TalonFXControlMode.Velocity, MOTOR_CONTROLLER.calculate(MOTOR.getSelectedSensorVelocity(), attitude.getDesiredVelocity()) + MOTOR_FEEDFORWARD.calculate(MOTOR.getSelectedSensorVelocity()));
            }

            @Override
            public void onEnd() {

            }
        });
    }

    public double getPosition() {
        return PIVOT.getSelectedSensorPosition();
    }

    public double getVelocity() {
        return MOTOR.getSelectedSensorVelocity();
    }
}
