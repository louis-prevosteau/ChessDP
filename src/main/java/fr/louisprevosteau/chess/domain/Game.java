package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.clock.ChessClock;
import fr.louisprevosteau.chess.clock.TimeControl;
import fr.louisprevosteau.chess.command.CommandInvoker;
import fr.louisprevosteau.chess.enums.GameStatus;
import fr.louisprevosteau.chess.history.MoveHistory;
import fr.louisprevosteau.chess.observer.GameListener;
import fr.louisprevosteau.chess.state.GameState;
import fr.louisprevosteau.chess.state.PlayingState;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Board board;
    private final Player black, white;
    private Player current;
    private GameState state;
    private GameStatus status;
    private final ChessClock clock;
    private final MoveHistory moveHistory;
    private CommandInvoker invoker;
    private List<GameListener> listeners;

    public Game(Player black, Player white, TimeControl timeControl) {
        this.white = white;
        this.black = black;
        this.current = white;
        this.board = new Board();
        this.state = new PlayingState();
        this.board.initialize();
        this.clock = new ChessClock(timeControl);
        this.moveHistory = new MoveHistory();
        this.status = GameStatus.NOT_STARTED;
        this.invoker = new CommandInvoker();
        this.listeners = new ArrayList<>();
    }

    public void start() {
        clock.start();
        status = GameStatus.PLAYING;
    }

    public void playMove(Move move) {
        state.playMove(this, move);
        moveHistory.add(move);
        clock.switchPlayer();
        switchPlayer();
        notifyMovePlayed(move);
    }

    private void notifyMovePlayed(Move move) {
        for (GameListener listener : listeners)
            listener.onMovePlayed(move);
    }

    public void resign() {
        status = GameStatus.RESIGNED;
    }

    public void offerDraw() {
        status = GameStatus.DRAW_OFFERED;
    }

    public void acceptDraw() {
        status = GameStatus.DRAW;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrent() {
        return current;
    }

    public Player getOpponent() {
        return current == white
                ? black
                : white;
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

    public void switchPlayer() {
        current = (current == white)
                ? black
                : white;
    }

    public void addListener(GameListener listener) {
        listeners.add(listener);
    }

    public void removeListener(GameListener listener) {
        listeners.remove(listener);
    }

    public boolean isGameOver() {
        return status == GameStatus.CHECKMATE
                || status == GameStatus.STALEMATE
                || status == GameStatus.RESIGNED
                || status == GameStatus.TIMEOUT
                || status == GameStatus.DRAW;
    }

    public GameResult getResult() {
        if (status.equals(GameStatus.DRAW))
            return new GameResult(status);
        else
            return new GameResult(status, getOpponent().getColor());
    }

    public List<GameListener> getListeners() {
        return listeners;
    }
}
