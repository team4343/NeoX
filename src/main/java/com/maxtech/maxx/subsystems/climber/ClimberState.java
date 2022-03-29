package com.maxtech.maxx.subsystems.climber;

public enum ClimberState {
    RETRACTED(0), EXTENDED(100);

    public final double position;

    ClimberState(double position) {
        this.position = position;
    }
}
