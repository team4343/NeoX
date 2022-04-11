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

    public IntakeState state = IntakeState.RAISED;
    public IntakeState previousState = IntakeState.RAISED;
    private final IntakeAttitude attitude = IntakeAttitude.getInstance();
    private final IntakeIO io = IntakeIO.getInstance();

    public Intake() {
        var tab = Shuffleboard.getTab("Intake");
        tab.addString("State", () -> state.toString());
        tab.addString("Previous state", () -> previousState.toString());
        tab.addNumber("Desired position", () -> state.position);
        tab.addNumber("Desired velocity", () -> state.percentOut);
        tab.addNumber("Position", io::getPosition);
        tab.addNumber("Velocity", io::getVelocity);
    }

    @Override
    public void register(Looper looper) {
        io.register(looper);
    }

    public void raise() {
        previousState = state;
        state = IntakeState.RAISED;
        attitude.setDesiredPosition(state.position);
        attitude.setDesiredPercentOut(state.percentOut);
    }

    public void lower() {
        previousState = state;
        state = IntakeState.LOWERED;
        attitude.setDesiredPosition(state.position);
        attitude.setDesiredPercentOut(state.percentOut);
    }

    public boolean atGoal() {
        return io.getPosition() == state.position && io.getVelocity() == state.percentOut;
    }
}
