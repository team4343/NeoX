package com.maxtech.maxx.subsystems.intake;

import com.maxtech.lib.scheduling.Looper;
import com.maxtech.lib.scheduling.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class Intake extends Subsystem<IntakeState, IntakeAttitude, IntakeIO> {
    private static Intake instance;

    public static Intake getInstance() {
        if (instance == null) instance = new Intake();
        return instance;
    }

    private IntakeState state = IntakeState.RAISED;
    private final IntakeAttitude attitude = IntakeAttitude.getInstance();
    private final IntakeIO io = IntakeIO.getInstance();

    public Intake() {
        var tab = Shuffleboard.getTab("Intake");
        tab.addString("State", () -> state.toString());
        tab.addNumber("Desired position", () -> state.position);
        tab.addNumber("Desired velocity", () -> state.velocity);
        tab.addNumber("Position", io::getPosition);
        tab.addNumber("Velocity", io::getVelocity);
    }

    @Override
    public void register(Looper looper) {
        io.register(looper);
    }

    public void raise() {
        state = IntakeState.RAISED;
        attitude.setDesiredPosition(state.position);
        attitude.setDesiredVelocity(state.velocity);
    }

    public void lower() {
        state = IntakeState.LOWERED;
        attitude.setDesiredPosition(state.position);
        attitude.setDesiredVelocity(state.velocity);
    }

    public boolean atGoal() {
        return io.getPosition() == state.position && io.getVelocity() == state.velocity;
    }
}
