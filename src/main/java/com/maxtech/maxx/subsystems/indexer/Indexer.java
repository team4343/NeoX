package com.maxtech.maxx.subsystems.indexer;

import com.maxtech.lib.scheduling.Loop;
import com.maxtech.lib.scheduling.Looper;
import com.maxtech.lib.scheduling.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class Indexer extends Subsystem<IndexerState, IndexerAttitude, IndexerIO> {
    private static Indexer instance;

    public static Indexer getInstance() {
        if (instance == null) instance = new Indexer();
        return instance;
    }

    public IndexerState state              = IndexerState.OFF;
    public IndexerState previousOnState    = IndexerState.NO_BALLS;
    private final IndexerAttitude attitude = IndexerAttitude.getInstance();
    private final IndexerIO io             = IndexerIO.getInstance();

    private Indexer() {
        var tab = Shuffleboard.getTab("Indexer");
        tab.addString("State", () -> state.toString());
        tab.addBoolean("Bottom", io::getBottomSensorLoaded);
        tab.addBoolean("Top", io::getTopSensorLoaded);
    }

    @Override
    public void register(Looper looper) {
        io.register(looper);
        looper.register(new Loop() {
            @Override
            public void onStart() {

            }

            @Override
            public void onLoop() {
                attitude.setDesiredBottomPercentOutput(state.bottomPercentOutput);
                attitude.setDesiredTopPercentOutput(state.topPercentOutput);

                switch (state) {
                    case NO_BALLS:
                    case ONE_BALL:
                    case TWO_BALL:
                        previousOnState = state;
                        break;
                }

                switch (state) {
                    case NO_BALLS:
                        if (io.getTopSensorLoaded()) state = IndexerState.ONE_BALL;
                        break;
                    case ONE_BALL:
                        if (io.getBottomSensorLoaded()) state = IndexerState.TWO_BALL;
                        break;
                }
            }

            @Override
            public void onEnd() {

            }
        });
    }

    public void turnOn() {
        state = previousOnState;
    }

    public void turnOff() {
        state = IndexerState.OFF;
    }
}
