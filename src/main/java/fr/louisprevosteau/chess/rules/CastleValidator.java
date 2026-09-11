package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.enums.Color;

public class CastleValidator {

    public boolean canCastleKingside(Board board, Color color) {
        return false;
    }

    public boolean canCastleQueenside(Board board, Color color) {
        return false;
    }

    public boolean isPathClear(Board board, Color color) {
        return false;
    }

    public boolean isSafe(Board board, Color color) {
        return false;
    }
}
