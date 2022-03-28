package com.maxtech.maxx.subsystems.drive;

import com.maxtech.lib.scheduling.IO;
import edu.wpi.first.math.geometry.Twist2d;

public class DriveIO extends IO {
    private static DriveIO instance;

    public static DriveIO getInstance() {
        if (instance == null) {
            instance = new DriveIO();
        }

        return instance;
    }

    public void setHeading(Twist2d heading) {

    }

    public void stop() {

    }
}
