package fr.louisprevosteau.chess.strategy;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.pieces.Knight;
import fr.louisprevosteau.chess.pieces.Pawn;
import fr.louisprevosteau.chess.pieces.Piece;
import fr.louisprevosteau.chess.utils.BoardUtils;

import java.util.ArrayList;
import java.util.List;

public class KnightMovementStrategy implements MovementStrategy {

    private static final int[][] OFFSETS = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {-1, 2},
            {1, -2}, {1, 2},
            {2, -1}, {2, 1}
    };

    @Override
    public List<Move> getLegalMoves(Position position, Board board) {
        List<Move> legalMoves = new ArrayList<>();

        if (position == null || board == null || !position.isValid()) {
            return legalMoves;
        }

        Piece piece = board.getPiece(position);

        if (!(piece instanceof Knight knight)) {
            return legalMoves;
        }

        for (int[] offset : OFFSETS) {
            int targetRow = position.getRow() + offset[0];
            int targetColumn = position.getColumn() + offset[1];

            if (!BoardUtils.isInsideBoard(targetRow, targetColumn)) {
                continue;
            }

            Position targetPosition = new Position(targetRow, targetColumn);
            Piece targetPiece = board.getPiece(targetPosition);

            if (targetPiece == null
                    || targetPiece.getColor() != knight.getColor()) {
                legalMoves.add(new Move(position, targetPosition));
            }
        }

        return legalMoves;
    }
}
