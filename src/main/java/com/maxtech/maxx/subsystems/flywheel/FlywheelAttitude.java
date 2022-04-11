package com.maxtech.maxx.subsystems.flywheel;

public class FlywheelAttitude {
    private static FlywheelAttitude instance;

    public static FlywheelAttitude getInstance() {
        if (instance == null) instance = new FlywheelAttitude();
        return instance;
    }

    public Double desired = null;
}
