package com.maxtech.maxx.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.maxtech.lib.drivers.LazyTalonSRX;
import com.maxtech.lib.scheduling.IO;
import com.maxtech.lib.scheduling.Loop;
import com.maxtech.lib.scheduling.Looper;

public class IntakeIO extends IO {
    private static IntakeIO instance;

    public static IntakeIO getInstance() {
        if (instance == null) instance = new IntakeIO();
        return instance;
    }

    private final IntakeAttitude attitude = IntakeAttitude.getInstance();

    private final LazyTalonSRX PIVOT = new LazyTalonSRX(10);
    private final VictorSPX MOTOR = new VictorSPX(7);

    public IntakeIO() {
        PIVOT.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        PIVOT.config_kP(0, .7, 10);
        PIVOT.config_kF(0, 0, 10);
        PIVOT.config_kI(0, 0, 10);
        PIVOT.config_kD(0, 0, 10);
    }

    public void register(Looper looper) {
        looper.register(new Loop() {
            @Override
            public void onStart() {

            }

            @Override
            public void onLoop() {
                PIVOT.set(ControlMode.Position, attitude.getDesiredPosition());
                MOTOR.set(ControlMode.PercentOutput, attitude.getDesiredPercentOut());
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
