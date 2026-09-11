package fr.louisprevosteau.chess.command;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.state.GameState;

public class MoveCommand implements Command {

    private Board board;
    private Move move;
    private GameState previousState;

    public MoveCommand(Board board, Move move) {
        this.board = board;
        this.move = move;
    }

    @Override
    public void execute() {

    }
}
