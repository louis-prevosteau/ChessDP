package fr.louisprevosteau.chess.clock;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.observer.ClockListener;

import java.time.Duration;
import java.util.List;

public class ChessClock {

    private Duration whiteTime, blackTime;
    private Color runningColor;
    private boolean running;
    private TimeControl timeControl;
    private List<ClockListener> listeners;

    public ChessClock(TimeControl timeControl) {
        this.timeControl = timeControl;
    }

    public void start() {}

    public void stop() {}

    public void pause() {}

    public void resume() {}

    public void tick() {}

    public void switchPlayer() {}

    public void addIncrement() {}

    public Duration getWhiteTime() {
        return whiteTime;
    }

    public Duration getBlackTime() {
        return blackTime;
    }

    public Duration getRemainingTime(Color color) {
        return null;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isTimeout() {
        return false;
    }

    public void addListener(ClockListener listener) {}

    public void removeListener(ClockListener listener) {}
}
