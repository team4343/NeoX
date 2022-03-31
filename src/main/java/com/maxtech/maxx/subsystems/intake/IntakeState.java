package com.maxtech.maxx.subsystems.intake;

public enum IntakeState {
    RAISED(0, 0), LOWERED(1200, 1000);

    public final int position;
    public final int velocity;

    IntakeState(int position, int velocity) {
        this.position = position;
        this.velocity = velocity;
    }
}
