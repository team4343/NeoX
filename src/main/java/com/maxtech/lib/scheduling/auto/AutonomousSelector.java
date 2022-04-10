package com.maxtech.lib.scheduling.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutonomousSelector {
    private static AutonomousSelector instance;

    public static AutonomousSelector getInstance() {
        if (instance == null) instance = new AutonomousSelector();
        return instance;
    }

    private AutonomousSelector() {}

    private final SendableChooser<AutonomousMode> chooser = new SendableChooser<>();

    public void register(AutonomousMode autonomousMode) {
        chooser.addOption(autonomousMode.getClass().getName(), autonomousMode);
    }

    public void registerDefault(AutonomousMode action) {
        chooser.setDefaultOption(action.getClass().getName(), action);
    }

    public AutonomousMode getSelection() {
        return chooser.getSelected();
    }
}
