package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Piece;

import java.util.List;

public class CheckDetector {

    public boolean isInCheck(Board board, Color color) {
        return false;
    }

    public boolean isSquareAttacked(Board board, Position position, Color byColor) {
        return false;
    }

    public List<Piece> getAttackers(Board board, Position position, Color color) {
        return null;
    }
}
