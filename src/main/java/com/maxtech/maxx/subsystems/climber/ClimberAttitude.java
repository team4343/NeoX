package com.maxtech.maxx.subsystems.climber;

public class ClimberAttitude {
    private static ClimberAttitude instance;

    public static ClimberAttitude getInstance() {
        if (instance == null) instance = new ClimberAttitude();
        return instance;
    }
}
