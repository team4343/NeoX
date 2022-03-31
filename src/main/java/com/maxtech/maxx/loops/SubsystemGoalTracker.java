package com.maxtech.maxx.loops;

import com.maxtech.lib.scheduling.Loop;
import com.maxtech.maxx.subsystems.climber.Climber;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;

import static com.maxtech.maxx.Constants.Ports.Drive.MASTER_CONTROLLER;

public class SubsystemGoalTracker extends Loop {
    private static SubsystemGoalTracker instance;

    private final Climber climber = Climber.getInstance();
    private boolean climberPreviouslyAtGoal = false;

    public static SubsystemGoalTracker getInstance() {
        if (instance == null) instance = new SubsystemGoalTracker();
        return instance;
    }

    @Override
    public void onStart() {}

    @Override
    public void onLoop() {
        if (climber.atState() && !climberPreviouslyAtGoal) {
            MASTER_CONTROLLER.setRumble(GenericHID.RumbleType.kLeftRumble, 1.0);
            DriverStation.reportWarning("" + climberPreviouslyAtGoal + " " + climber.atState(), false);
        } else if (!climber.atState()) {
            climberPreviouslyAtGoal = false;
            MASTER_CONTROLLER.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
        }
    }

    @Override
    public void onEnd() {}
}
