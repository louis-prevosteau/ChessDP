package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.clock.ChessClock;
import fr.louisprevosteau.chess.clock.TimeControl;
import fr.louisprevosteau.chess.command.CommandInvoker;
import fr.louisprevosteau.chess.enums.GameStatus;
import fr.louisprevosteau.chess.history.MoveHistory;
import fr.louisprevosteau.chess.observer.GameListener;
import fr.louisprevosteau.chess.state.GameState;

import java.util.List;

public class Game {

    private Board board;
    private Player black, white, current;
    private GameState state;
    private GameStatus status;
    private ChessClock clock;
    private MoveHistory moveHistory;
    private CommandInvoker invoker;
    private List<GameListener> listeners;

    public Game(Player black, Player white, TimeControl timeControl) {

    }

    public void start() {}

    public void playMove(Move move) {}

    public void resign() {}

    public void offerDraw() {}

    public void acceptDraw() {}

    public Board getBoard() {
        return board;
    }

    public Player getCurrent() {
        return current;
    }

    public Player getOpponent() {
        return null;
    }

    public GameStatus getStatus() {
        return status;
    }

    public ChessClock getClock() {
        return clock;
    }

    public MoveHistory getMoveHistory() {
        return moveHistory;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void switchOPlayer() {}

    public void addListener(GameListener listener) {}

    public void removeListener(GameListener listener) {}

    public boolean isGameOver() {
        return false;
    }

    public GameResult getResult() {
        return null;
    }
}
