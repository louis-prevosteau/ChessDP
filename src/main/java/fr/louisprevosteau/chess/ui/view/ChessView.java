package fr.louisprevosteau.chess.ui.view;

import fr.louisprevosteau.chess.domain.Board;

import java.time.Clock;

public class ChessView {

    private BoardView boardView;
    private ClockView whiteClockView, blackClockView;
    private MoveHistoryView moveHistoryView;

    public void render() {}

    public void updateBoard(Board board) {}

    public void updateClock(Clock clock) {}
}
