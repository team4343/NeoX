package com.maxtech.maxx.subsystems.drive;

import com.maxtech.lib.scheduling.Loop;
import com.maxtech.lib.scheduling.Looper;
import com.maxtech.lib.scheduling.Subsystem;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class Drive extends Subsystem<DriveState, DriveAttitude, DriveIO> {
    private static Drive instance;

    public static Drive getInstance() {
        if (instance == null) {
            instance = new Drive();
        }

        return instance;
    }

    private DriveState state       = DriveState.OPEN_LOOP;
    private DriveAttitude attitude = DriveAttitude.getInstance();
    private DriveIO io             = DriveIO.getInstance();

    public void register(Looper looper) {
        var loop = new Loop() {
            @Override
            public void onStart() {
                attitude.start(new Pose2d(), new Rotation2d());
            }

            @Override
            public void onLoop() {
                attitude.updateObservations();
            }

            @Override
            public void onEnd() {
                io.stop();
            }
        };

        looper.register(loop);
    }
}
