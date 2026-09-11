package fr.louisprevosteau.chess.ui.view;

import fr.louisprevosteau.chess.enums.Color;

import java.time.Duration;

public class ClockView {

    private Color color;

    public ClockView(Color color) {
        this.color = color;
    }

    public void update(Duration duration) {}

    public void highlight() {}

    public void stop() {}
}
