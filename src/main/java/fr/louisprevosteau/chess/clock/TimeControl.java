package fr.louisprevosteau.chess.clock;

import java.time.Duration;

public class TimeControl {

    private Duration initialTime, increment;

    public TimeControl(Duration initialTime) {
        this.initialTime = initialTime;
    }

    public TimeControl(Duration initialTime, Duration increment) {
        this.initialTime = initialTime;
        this.increment = increment;
    }

    public Duration getInitialTime() {
        return initialTime;
    }

    public Duration getIncrement() {
        return increment;
    }
}
