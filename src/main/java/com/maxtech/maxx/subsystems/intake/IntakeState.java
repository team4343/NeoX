package com.maxtech.maxx.subsystems.intake;

public enum IntakeState {
    RAISED(0, 0), LOWERED(-1200, -.5);

    public final int position;
    public final double percentOut;

    IntakeState(int position, double percentOut) {
        this.position = position;
        this.percentOut = percentOut;
    }
}
