package fr.louisprevosteau.chess.strategy;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.pieces.Piece;
import fr.louisprevosteau.chess.pieces.Queen;
import fr.louisprevosteau.chess.pieces.Rook;
import fr.louisprevosteau.chess.utils.BoardUtils;

import java.util.ArrayList;
import java.util.List;

public class QueenMovementStrategy implements MovementStrategy {

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

        if (!(piece instanceof Queen queen)) {
            return legalMoves;
        }

        for (int[] direction : DIRECTIONS) {
            int row = position.getRow() + direction[0];
            int column = position.getColumn() + direction[1];

            while (BoardUtils.isInsideBoard(row, column)) {
                Position targetPosition = new Position(row, column);
                Piece targetPiece = board.getPiece(targetPosition);

                if (targetPiece == null) {
                    legalMoves.add(new Move(position, targetPosition));
                } else {
                    if (targetPiece.getColor() != queen.getColor()) {
                        legalMoves.add(new Move(position, targetPosition));
                    }

                    break;
                }

                row += direction[0];
                column += direction[1];
            }
        }

        return legalMoves;
    }
}
