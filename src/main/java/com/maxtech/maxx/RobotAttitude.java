package com.maxtech.maxx;

/**
 * Robot-to-field: the zero is the robot starting position.
 * Robot-to-absolute: the zero is the center of the field.
 * Field-to-robot: the zero is the center of the robot.
 */
public class RobotAttitude {
    private static RobotAttitude instance;

    public static RobotAttitude getInstance() {
        if (instance == null) instance = new RobotAttitude();
        return instance;
    }
}
