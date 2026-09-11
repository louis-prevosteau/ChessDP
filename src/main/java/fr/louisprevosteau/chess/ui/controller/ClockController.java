package fr.louisprevosteau.chess.ui.controller;

import java.time.Clock;

public class ClockController {

    private Clock clock;

    public ClockController(Clock clock) {
        this.clock = clock;
    }

    public void start() {}

    public void pause() {}

    public void resume() {}
}
