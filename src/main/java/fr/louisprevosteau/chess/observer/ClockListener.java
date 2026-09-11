package fr.louisprevosteau.chess.observer;

import fr.louisprevosteau.chess.enums.Color;

import java.time.Duration;

public interface ClockListener {

    void onClockTick(Color color, Duration time);

    void onTimeout(Color color);
}
