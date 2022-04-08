package com.maxtech.maxx.subsystems.indexer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.maxtech.lib.scheduling.IO;
import com.maxtech.lib.scheduling.Loop;
import com.maxtech.lib.scheduling.Looper;
import edu.wpi.first.wpilibj.DigitalInput;

public class IndexerIO extends IO {
    private static IndexerIO instance;

    public static IndexerIO getInstance() {
        if (instance == null) instance = new IndexerIO();
        return instance;
    }

    private final IndexerAttitude attitude = IndexerAttitude.getInstance();

    private final VictorSPX BOTTOM = new VictorSPX(8);
    private final VictorSPX TOP    = new VictorSPX(9);

    private final DigitalInput BOTTOM_SENSOR = new DigitalInput(1);
    private final DigitalInput TOP_SENSOR    = new DigitalInput(0);

    private IndexerIO() {}

    public void register(Looper looper) {
        looper.register(new Loop() {
            @Override
            public void onStart() {

            }

            @Override
            public void onLoop() {
                BOTTOM.set(ControlMode.PercentOutput, attitude.getDesiredBottomPercentOutput());
                TOP.set(ControlMode.PercentOutput, attitude.getDesiredTopPercentOutput());
            }

            @Override
            public void onEnd() {

            }
        });
    }

    public boolean getTopSensorLoaded() {
        return !TOP_SENSOR.get();
    }

    public boolean getBottomSensorLoaded() {
        return !BOTTOM_SENSOR.get();
    }
}
