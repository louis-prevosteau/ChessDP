package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.history.MoveHistory;

public class DrawDetector {

    public boolean isDrawByInsufficientMaterial(Board board) {
        return false;
    }

    public boolean isDrawByFiftyMoves(Board board) {
        return false;
    }

    public boolean isThreefoldRepetition(MoveHistory moveHistory) {
        return false;
    }

    public boolean isDraw(Board board, MoveHistory moveHistory) {
        return false;
    }
}
