package com.maxtech.maxx;

import com.maxtech.lib.scheduling.Looper;
import com.maxtech.lib.scheduling.SingleLoopSelector;
import com.maxtech.maxx.loops.OperatorInput;
import com.maxtech.maxx.loops.TrajectoryRunner;
import com.maxtech.maxx.planners.RamseteMotionPlanner;
import com.maxtech.maxx.subsystems.drive.Drive;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.TimedRobot;

import java.io.IOException;
import java.nio.file.Path;

import static com.maxtech.lib.drivers.Pathweaver.loadPathweaverTrajectory;

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
    private final SingleLoopSelector autonomousLooper = new SingleLoopSelector();

    private final RobotAttitude attitude = RobotAttitude.getInstance();

    public Robot() {
        drive.register(enabledLooper);
        oi.register(enabledLooper);

        autonomousLooper.registerDefault(new TrajectoryRunner("A to B.wpilib.json"));
    }

    @Override
    public void robotInit() {
        enabledLooper.start();
    }

    @Override
    public void disabledInit() {
        enabledLooper.stop();
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
