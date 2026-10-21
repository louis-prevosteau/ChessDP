package fr.louisprevosteau.chess.strategy;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Pawn;
import fr.louisprevosteau.chess.pieces.Piece;
import fr.louisprevosteau.chess.utils.BoardUtils;

import java.util.ArrayList;
import java.util.List;

public class PawnMovementStrategy implements MovementStrategy {

    private static final int WHITE_START_ROW = 1;
    private static final int BLACK_START_ROW = 6;

    @Override
    public List<Move> getLegalMoves(Position position, Board board) {
        List<Move> legalMoves = new ArrayList<>();

        if (position == null || board == null || !position.isValid()) {
            return legalMoves;
        }

        Piece piece = board.getPiece(position);

        if (!(piece instanceof Pawn pawn)) {
            return legalMoves;
        }

        int direction = pawn.getColor() == Color.WHITE ? 1 : -1;
        int currentRow = position.getRow();
        int currentColumn = position.getColumn();

        Position oneStepForward = new Position(
                currentRow + direction,
                currentColumn
        );

        if (BoardUtils.isInsideBoard(oneStepForward.getRow(), oneStepForward.getColumn())
                && board.getPiece(oneStepForward) == null) {

            legalMoves.add(new Move(position, oneStepForward));

            int startingRow = pawn.getColor() == Color.WHITE
                    ? WHITE_START_ROW
                    : BLACK_START_ROW;

            Position twoStepsForward = new Position(
                    currentRow + 2 * direction,
                    currentColumn
            );

            if (currentRow == startingRow
                    && BoardUtils.isInsideBoard(twoStepsForward.getRow(), twoStepsForward.getColumn())
                    && board.getPiece(twoStepsForward) == null) {

                legalMoves.add(new Move(position, twoStepsForward));
            }
        }

        for (int columnOffset : new int[]{-1, 1}) {
            Position diagonalPosition = new Position(
                    currentRow + direction,
                    currentColumn + columnOffset
            );

            if (!BoardUtils.isInsideBoard(diagonalPosition.getRow(), diagonalPosition.getColumn())) {
                continue;
            }

            Piece target = board.getPiece(diagonalPosition);

            if (target != null && target.getColor() != pawn.getColor()) {
                legalMoves.add(new Move(position, diagonalPosition));
            }
        }

        return legalMoves;
    }
}
