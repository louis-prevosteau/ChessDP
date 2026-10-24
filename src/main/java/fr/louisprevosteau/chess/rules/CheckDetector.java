package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.PieceType;
import fr.louisprevosteau.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class CheckDetector {

    public boolean isInCheck(Board board, Color color) {
        if (board == null || color == null) {
            return false;
        }

        Position kingPosition = board.findKing(color);

        if (kingPosition ==  null) return false;

        return isSquareAttacked(board, kingPosition, opposite(color));
    }

    public boolean isSquareAttacked(Board board, Position position, Color byColor) {
        return !getAttackers(board, position, byColor).isEmpty();
    }

    public List<Piece> getAttackers(Board board, Position position, Color color) {
        List<Piece> attackers = new ArrayList<>();

        if (board == null || position == null
                || !position.isValid() || color == null) {
            return attackers;
        }

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {

                Position from = new Position(row, column);
                Piece piece = board.getPiece(from);

                if (piece == null || piece.getColor() != color) {
                    continue;
                }

                if (attacksSquare(piece, from, position, board)) {
                    attackers.add(piece);
                }
            }
        }

        return attackers;
    }

    private boolean attacksSquare(Piece piece, Position from, Position to, Board board) {
        int rowDifference = to.getRow() - from.getRow();
        int columnDifference = to.getColumn() - from.getColumn();

        int absRow = Math.abs(rowDifference);
        int absColumn = Math.abs(columnDifference);

        PieceType type = piece.getType();

        if (type == null) {
            return false;
        }

        switch (type) {
            case PAWN:
                int direction = piece.getColor() == Color.WHITE ? 1 : -1;

                return rowDifference == direction
                        && absColumn == 1;

            case KNIGHT:
                return (absRow == 2 && absColumn == 1)
                        || (absRow == 1 && absColumn == 2);

            case KING:
                return absRow <= 1
                        && absColumn <= 1
                        && (absRow + absColumn > 0);

            case BISHOP:
                if (absRow != absColumn) {
                    return false;
                }
                return isPathClear(from, to, board);

            case ROOK:
                if (rowDifference != 0 && columnDifference != 0) {
                    return false;
                }
                return isPathClear(from, to, board);

            case QUEEN:
                boolean diagonal = absRow == absColumn;
                boolean straight = rowDifference == 0
                        || columnDifference == 0;

                if (!diagonal && !straight) {
                    return false;
                }
                return isPathClear(from, to, board);

            default:
                return false;
        }
    }

    private boolean isPathClear(Position from, Position to, Board board) {
        int rowStep = Integer.compare(
                to.getRow(), from.getRow());

        int columnStep = Integer.compare(
                to.getColumn(), from.getColumn());

        int row = from.getRow() + rowStep;
        int column = from.getColumn() + columnStep;

        while (row != to.getRow()
                || column != to.getColumn()) {

            if (board.getPiece(new Position(row, column)) != null) {
                return false;
            }

            row += rowStep;
            column += columnStep;
        }

        return true;
    }

    private Color opposite(Color color) {
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }
}
