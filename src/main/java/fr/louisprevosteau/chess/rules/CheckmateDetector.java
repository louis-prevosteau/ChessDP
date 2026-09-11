package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.enums.Color;

public class CheckmateDetector {

    private MoveValidator moveValidator;
    private CheckDetector checkDetector;

    public CheckmateDetector(MoveValidator moveValidator, CheckDetector checkDetector) {
        this.moveValidator = moveValidator;
        this.checkDetector = checkDetector;
    }

    public boolean isCheckmate(Board board, Color color) {
        return false;
    }

    public boolean hasLegalMoves(Board board, Color color) {
        return false;
    }
}
