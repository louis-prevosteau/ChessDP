package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.*;

public class CastleValidator {

    private static final int WHITE_ROW = 0;
    private static final int BLACK_ROW = 7;
    private static final int KING_COLUMN = 4;
    private static final int KINGSIDE_ROOK_COLUMN = 7;
    private static final int QUEENSIDE_ROOK_COLUMN = 0;

    public boolean canCastleKingside(Board board, Color color) {
        return canCastle(board, color, true);
    }

    public boolean canCastleQueenside(Board board, Color color) {
        return canCastle(board, color, false);
    }

    private boolean canCastle(Board board, Color color, boolean kingside) {
        if (board == null || color == null) {
            return false;
        }

        int row = getBackRank(color);
        Position kingPosition = new Position(row, KING_COLUMN);
        Position rookPosition = new Position(
                row,
                kingside ? KINGSIDE_ROOK_COLUMN : QUEENSIDE_ROOK_COLUMN
        );

        Piece king = board.getPiece(kingPosition);
        Piece rook = board.getPiece(rookPosition);

        if (!(king instanceof King) || king.getColor() != color) {
            return false;
        }

        if (!(rook instanceof Rook) || rook.getColor() != color) {
            return false;
        }

        return isPathClear(board, color, kingside)
                && isCastleSafe(board, color, kingside);
    }

    public boolean isPathClear(Board board, Color color) {
        if (board == null || color == null) {
            return false;
        }

        return isPathClear(board, color, true)
                && isPathClear(board, color, false);
    }

    private boolean isPathClear(Board board, Color color, boolean kingside) {
        int row = getBackRank(color);
        int startColumn = kingside ? 5 : 1;
        int endColumn = kingside ? 6 : 3;

        for (int column = startColumn; column <= endColumn; column++) {
            if (board.getPiece(new Position(row, column)) != null) {
                return false;
            }
        }

        return true;
    }

    public boolean isSafe(Board board, Color color) {
        if (board == null || color == null) {
            return false;
        }

        int row = getBackRank(color);

        // Le roi ne peut pas roquer s'il est actuellement en échec.
        if (isSquareAttacked(board, row, KING_COLUMN, opposite(color))) {
            return false;
        }

        // Au moins un côté doit avoir un trajet sûr.
        return isCastleSafe(board, color, true)
                || isCastleSafe(board, color, false);
    }

    private boolean isCastleSafe(Board board, Color color, boolean kingside) {
        int row = getBackRank(color);
        int direction = kingside ? 1 : -1;
        Color opponent = opposite(color);

        // Le roi traverse une case et termine sur la suivante.
        for (int step = 1; step <= 2; step++) {
            int column = KING_COLUMN + direction * step;

            if (isSquareAttacked(board, row, column, opponent)) {
                return false;
            }
        }

        return true;
    }

    private boolean isSquareAttacked(
            Board board, int targetRow, int targetColumn, Color attackerColor) {

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Piece piece = board.getPiece(new Position(row, column));

                if (piece == null || piece.getColor() != attackerColor) {
                    continue;
                }

                int rowDelta = targetRow - row;
                int columnDelta = targetColumn - column;

                if (piece instanceof Pawn) {
                    int pawnDirection = attackerColor == Color.WHITE ? 1 : -1;

                    if (rowDelta == pawnDirection
                            && Math.abs(columnDelta) == 1) {
                        return true;
                    }
                } else if (piece instanceof Knight) {
                    if (Math.abs(rowDelta) == 2 && Math.abs(columnDelta) == 1
                            || Math.abs(rowDelta) == 1 && Math.abs(columnDelta) == 2) {
                        return true;
                    }
                } else if (piece instanceof King) {
                    if (Math.max(Math.abs(rowDelta), Math.abs(columnDelta)) == 1) {
                        return true;
                    }
                } else if (piece instanceof Bishop) {
                    if (Math.abs(rowDelta) == Math.abs(columnDelta)
                            && rowDelta != 0
                            && isLineClear(board, row, column,
                            targetRow, targetColumn)) {
                        return true;
                    }
                } else if (piece instanceof Rook) {
                    if ((rowDelta == 0 || columnDelta == 0)
                            && (rowDelta != 0 || columnDelta != 0)
                            && isLineClear(board, row, column,
                            targetRow, targetColumn)) {
                        return true;
                    }
                } else if (piece instanceof Queen) {
                    boolean diagonal = Math.abs(rowDelta) == Math.abs(columnDelta)
                            && rowDelta != 0;
                    boolean straight = (rowDelta == 0) != (columnDelta == 0);

                    if ((diagonal || straight)
                            && isLineClear(board, row, column,
                            targetRow, targetColumn)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isLineClear(
            Board board, int fromRow, int fromColumn, int toRow, int toColumn) {

        int rowStep = Integer.compare(toRow, fromRow);
        int columnStep = Integer.compare(toColumn, fromColumn);

        int row = fromRow + rowStep;
        int column = fromColumn + columnStep;

        while (row != toRow || column != toColumn) {
            if (board.getPiece(new Position(row, column)) != null) {
                return false;
            }

            row += rowStep;
            column += columnStep;
        }

        return true;
    }

    private int getBackRank(Color color) {
        return color == Color.WHITE ? WHITE_ROW : BLACK_ROW;
    }

    private Color opposite(Color color) {
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }
}
