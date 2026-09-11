package fr.louisprevosteau.chess.command;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;

public class CastleCommand implements Command {

    private Board board;
    private Move move;

    public CastleCommand(Board board, Move move) {
        this.board = board;
        this.move = move;
    }

    @Override
    public void execute() {

    }
}
