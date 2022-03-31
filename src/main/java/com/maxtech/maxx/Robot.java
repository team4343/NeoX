package com.maxtech.maxx;

import com.maxtech.lib.scheduling.Looper;
import com.maxtech.lib.scheduling.SingleLoopSelector;
import com.maxtech.maxx.loops.OperatorInput;
import com.maxtech.maxx.loops.SubsystemGoalTracker;
import com.maxtech.maxx.loops.TrajectoryRunner;
import com.maxtech.maxx.subsystems.climber.Climber;
import com.maxtech.maxx.subsystems.drive.Drive;
import com.maxtech.maxx.subsystems.intake.Intake;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * This class is scheduled by the Java VM.
 */
public class Robot extends TimedRobot {
    // Subsystems
    private final Drive drive = Drive.getInstance();
    private final Climber climber = Climber.getInstance();
    private final Intake intake = Intake.getInstance();

    // Loops
    private final OperatorInput oi = OperatorInput.getInstance();
    private final SubsystemGoalTracker goalTracker = SubsystemGoalTracker.getInstance();

    // Loopers
    private final Looper enabledLooper = new Looper("Enabled");
    private final Looper disabledLooper = new Looper("Disabled");
    private final SingleLoopSelector autonomousLooper = new SingleLoopSelector("Autonomous");

    private final RobotAttitude attitude = RobotAttitude.getInstance();

    public Robot() {
        drive.register(enabledLooper);
        climber.register(enabledLooper);
        intake.register(enabledLooper);

        oi.register(enabledLooper);
        goalTracker.register(enabledLooper);

        autonomousLooper.registerDefault(new TrajectoryRunner("A to B.wpilib.json"));
    }

    @Override
    public void robotInit() {
        disabledLooper.stop();
        autonomousLooper.stop();
        enabledLooper.start();
    }

    @Override
    public void disabledInit() {
        enabledLooper.stop();
        autonomousLooper.stop();
        disabledLooper.start();
    }

    @Override
    public void disabledExit() {
        disabledLooper.stop();
        enabledLooper.start();
    }

    @Override
    public void autonomousInit() {
        autonomousLooper.start();
    }

    @Override
    public void autonomousExit() {
        autonomousLooper.stop();
    }
}
