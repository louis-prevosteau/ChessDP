package fr.louisprevosteau.chess.strategy;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.pieces.King;
import fr.louisprevosteau.chess.pieces.Knight;
import fr.louisprevosteau.chess.pieces.Piece;
import fr.louisprevosteau.chess.utils.BoardUtils;

import java.util.ArrayList;
import java.util.List;

public class KingMovementStrategy implements MovementStrategy {

    private static final int[][] DIRECTIONS = {
            {0, 1},
            {0,  -1},
            { -1, 0},
            { 1,  0},
            {-1, -1},
            {-1,  1},
            { 1, -1},
            { 1,  1}
    };

    @Override
    public List<Move> getLegalMoves(Position position, Board board) {
        List<Move> legalMoves = new ArrayList<>();

        if (position == null || board == null || !position.isValid()) {
            return legalMoves;
        }

        Piece piece = board.getPiece(position);

        if (!(piece instanceof King king)) {
            return legalMoves;
        }

        for (int[] direction : DIRECTIONS) {
            int targetRow = position.getRow() + direction[0];
            int targetColumn = position.getColumn() + direction[1];

            if (!BoardUtils.isInsideBoard(targetRow, targetColumn)) {
                continue;
            }

            Position targetPosition = new Position(targetRow, targetColumn);
            Piece targetPiece = board.getPiece(targetPosition);

            if (targetPiece == null
                    || targetPiece.getColor() != king.getColor()) {
                legalMoves.add(new Move(position, targetPosition));
            }
        }

        return legalMoves;
    }
}
