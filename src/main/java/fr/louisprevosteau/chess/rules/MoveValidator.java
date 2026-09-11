package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.pieces.Piece;

import java.util.List;

public class MoveValidator {

    public boolean isLegal(Move move, Board board) {
        return false;
    }

    public void validate(Move move, Board board) {}

    public boolean leavesKingInCheck(Move move, Board board) {
        return false;
    }

    public List<Move> getLegalMoves(Piece piece, Position position, Board board) {
        return null;
    }
}
