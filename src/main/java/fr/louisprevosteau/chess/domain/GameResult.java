package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.GameStatus;

public class GameResult {

    private final GameStatus status;
    private Color winner;
    private String reason;

    public GameResult(GameStatus status) {
        this.status = status;
    }

    public GameResult(GameStatus status, Color winner) {
        this.status = status;
        this.winner = winner;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Color getWinner() {
        return winner;
    }

    public String getReason() {
        return reason;
    }

    public boolean isDraw() {
        return false;
    }

    public boolean hasWinner() {
        return false;
    }
}
