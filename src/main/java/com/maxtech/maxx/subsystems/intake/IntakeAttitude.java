package com.maxtech.maxx.subsystems.intake;

import com.maxtech.lib.drivers.LazyTalonSRX;

public class IntakeAttitude {
    private static IntakeAttitude instance;

    public static IntakeAttitude getInstance() {
        if (instance == null) instance = new IntakeAttitude();
        return instance;
    }

    private int desiredPosition = 0;
    private int desiredVelocity = 0;

    private IntakeAttitude() {};

    public void setDesiredPosition(int desiredPosition) {
        this.desiredPosition = desiredPosition;
    }

    public void setDesiredVelocity(int desiredVelocity) {
        this.desiredVelocity = desiredVelocity;
    }

    public int getDesiredPosition() {
        return desiredPosition;
    }

    public int getDesiredVelocity() {
        return desiredVelocity;
    }
}
