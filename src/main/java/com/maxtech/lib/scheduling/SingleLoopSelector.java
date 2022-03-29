package com.maxtech.lib.scheduling;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class SingleLoopSelector extends Looper {
    private final SendableChooser<Loop> chooser = new SendableChooser<>();
    private Loop lastSelection;

    @Override
    public void register(Loop loop) {
        chooser.addOption(loop.getClass().getName(), loop);
    }

    public void registerDefault(Loop loop) {
        chooser.setDefaultOption(loop.getClass().getName(), loop);
    }

    @Override
    public void start() {
        lastSelection = chooser.getSelected();

        notifier.setHandler(lastSelection::onLoop);
        lastSelection.onStart();
        notifier.startPeriodic(0.02);
    }

    @Override
    public void stop() {
        notifier.stop();
        lastSelection.onEnd();
    }
}
