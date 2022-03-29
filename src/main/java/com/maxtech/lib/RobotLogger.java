package com.maxtech.lib;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotLogger {
    public static void sendWidget(Sendable widget) {
        SmartDashboard.putData(widget);
    }
}
