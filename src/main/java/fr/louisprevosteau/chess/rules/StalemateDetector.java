package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.enums.Color;

public class StalemateDetector {

    public boolean isStalemate(Board board, Color color) {
        if (board == null || color == null) return false;
        CheckDetector checkDetector = new CheckDetector();
        MoveValidator moveValidator = new MoveValidator();
        CheckmateDetector checkmateDetector = new CheckmateDetector(moveValidator, checkDetector);
        return !checkDetector.isInCheck(board, color) && !checkmateDetector.hasLegalMoves(board, color);
    }
}
