package com.maxtech.maxx.auto.actions;

import com.maxtech.lib.scheduling.auto.actions.Action;
import com.maxtech.maxx.subsystems.flywheel.FlywheelAttitude;
import com.maxtech.maxx.subsystems.flywheel.FlywheelController;

public class SetFlywheelHigh implements Action {
    private final FlywheelAttitude flywheelAttitude = FlywheelAttitude.getInstance();
    private final FlywheelController flywheelController = FlywheelController.getInstance();

    @Override
    public void start() {
        flywheelAttitude.desired = 1000;
    }

    @Override
    public void update() {}

    @Override
    public void done() {}

    @Override
    public boolean isFinished() {
        return flywheelController.atGoal();
    }
}
