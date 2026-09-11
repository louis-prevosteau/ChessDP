package fr.louisprevosteau.chess.history;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Player;
import fr.louisprevosteau.chess.enums.GameStatus;

import java.time.Duration;

public class GameMemento {

    private Board board;
    private Player currentPlayer;
    private Duration whiteTime, blackTime;
    private GameStatus status;

    public GameMemento(Board board, Player currentPlayer, Duration whiteTime, Duration blackTime, GameStatus status) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.whiteTime = whiteTime;
        this.blackTime = blackTime;
        this.status = status;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Duration getWhiteTime() {
        return whiteTime;
    }

    public Duration getBlackTime() {
        return blackTime;
    }

    public GameStatus getStatus() {
        return status;
    }
}
