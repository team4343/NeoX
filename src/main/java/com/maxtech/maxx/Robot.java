package com.maxtech.maxx;

import com.maxtech.lib.scheduling.Looper;
import com.maxtech.maxx.loops.OperatorInput;
import com.maxtech.maxx.subsystems.drive.Drive;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * This class is scheduled by the Java VM.
 */
public class Robot extends TimedRobot {
    // Subsystems
    private final Drive drive = Drive.getInstance();

    // Loops
    private final OperatorInput oi = OperatorInput.getInstance();

    // Loopers
    private final Looper enabledLooper = new Looper();
    private final Looper disabledLooper = new Looper();

    public Robot() {
        drive.register(enabledLooper);

        enabledLooper.register(oi);
    }

    @Override
    public void robotInit() {
        disabledLooper.stop();
        enabledLooper.start();
    }

    @Override
    public void disabledInit() {
        enabledLooper.stop();
        disabledLooper.start();
    }
}
