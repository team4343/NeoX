package com.maxtech.lib.drivers;

import com.revrobotics.CANSparkMax;

public class AdvancedSparkMax extends CANSparkMax {
    /**
     * Create a new object to control a SPARK MAX motor Controller
     *
     * @param deviceId The device ID.
     * @param type     The motor type connected to the controller. Brushless motor wires must be connected
     *                 to their matching colors and the hall sensor must be plugged in. Brushed motors must be
     */
    public AdvancedSparkMax(int deviceId, MotorType type) {
        super(deviceId, type);
    }

    public double getDistanceTravelled(double gearing, double wheelDiameter) {
        var motorRotations = this.getEncoder().getPosition();
        var wheelRotations = motorRotations / gearing;

        return wheelRotations * Math.PI * wheelDiameter;
    }
}
