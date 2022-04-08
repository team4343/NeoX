package com.maxtech.maxx.subsystems.climber;

import com.maxtech.lib.scheduling.IO;
import com.maxtech.lib.scheduling.Loop;
import com.maxtech.lib.scheduling.Looper;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;

public class ClimberIO extends IO {
    private static ClimberIO instance;

    public static ClimberIO getInstance() {
        if (instance == null) instance = new ClimberIO();
        return instance;
    }

    private final ClimberAttitude attitude = ClimberAttitude.getInstance();

    private final CANSparkMax LEFT = new CANSparkMax(19, kBrushless);
    private final CANSparkMax RIGHT = new CANSparkMax(17, kBrushless);

    private final SparkMaxPIDController LEFT_CONTROLLER = LEFT.getPIDController();
    private final SparkMaxPIDController RIGHT_CONTROLLER = RIGHT.getPIDController();

    private ClimberIO() {
        LEFT.restoreFactoryDefaults();
        RIGHT.restoreFactoryDefaults();

        LEFT.setInverted(true);

        LEFT.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 70);
        LEFT.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);

        RIGHT.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 70);
        RIGHT.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);

        LEFT.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, -5);
        LEFT.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

        RIGHT.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, -5);
        RIGHT.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

        LEFT_CONTROLLER.setP(0.6);
        LEFT_CONTROLLER.setI(0);
        LEFT_CONTROLLER.setIZone(0);
        LEFT_CONTROLLER.setD(0);
        LEFT_CONTROLLER.setFF(0);

        RIGHT_CONTROLLER.setP(0.6);
        RIGHT_CONTROLLER.setI(0);
        RIGHT_CONTROLLER.setIZone(0);
        RIGHT_CONTROLLER.setD(0);
        RIGHT_CONTROLLER.setFF(0);

        var tab = Shuffleboard.getTab("Climber");
        tab.addNumber("Position", () -> LEFT.getEncoder().getPosition());
    }

    public void register(Looper looper) {
        looper.register(new Loop() {
            @Override
            public void onStart() {

            }

            @Override
            public void onLoop() {
                LEFT_CONTROLLER.setReference(attitude.getDesired(), CANSparkMax.ControlType.kPosition);
                RIGHT_CONTROLLER.setReference(attitude.getDesired(), CANSparkMax.ControlType.kPosition);
            }

            @Override
            public void onEnd() {

            }
        });
    }

    public double getPosition() {
        return RIGHT.getEncoder().getPosition();
    }
}
