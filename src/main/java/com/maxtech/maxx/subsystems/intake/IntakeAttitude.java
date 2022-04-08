package com.maxtech.maxx.subsystems.intake;

public class IntakeAttitude {
    private static IntakeAttitude instance;

    public static IntakeAttitude getInstance() {
        if (instance == null) instance = new IntakeAttitude();
        return instance;
    }

    private int desiredPosition = 0;
    private double desiredPercentOut = 0;

    private IntakeAttitude() {};

    public void setDesiredPosition(int desiredPosition) {
        this.desiredPosition = desiredPosition;
    }

    public void setDesiredPercentOut(double desiredPercentOut) {
        this.desiredPercentOut = desiredPercentOut;
    }

    public int getDesiredPosition() {
        return desiredPosition;
    }

    public double getDesiredPercentOut() {
        return desiredPercentOut;
    }
}
