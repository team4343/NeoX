package com.maxtech.maxx.subsystems.climber;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class ClimberAttitude {
    private static ClimberAttitude instance;

    public static ClimberAttitude getInstance() {
        if (instance == null) instance = new ClimberAttitude();
        return instance;
    }

    private ClimberAttitude() {
        var tab = Shuffleboard.getTab("Climber");
        tab.addNumber("Desired", this::getDesired);
    }

    private double desired = 0;

    public void setDesired(double desired) {
        this.desired = desired;
    }

    public double getDesired() {
        return desired;
    }
}
