package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Piece;

import java.util.List;

public class CheckmateDetector {

    private final MoveValidator moveValidator;
    private final CheckDetector checkDetector;

    public CheckmateDetector(MoveValidator moveValidator, CheckDetector checkDetector) {
        this.moveValidator = moveValidator;
        this.checkDetector = checkDetector;
    }

    public boolean isCheckmate(Board board, Color color) {
        if (board == null || color == null) return false;
        return checkDetector.isInCheck(board, color) && !hasLegalMoves(board, color);
    }

    public boolean hasLegalMoves(Board board, Color color) {
        if (board == null || color == null) return false;

        for (int row = 0 ; row < 8 ; row++) {
            for (int column = 0 ; column < 8 ; column++) {
                Position from = new Position(row, column);
                Piece piece = board.getPiece(from);
                if (piece == null || piece.getColor() != color) {
                    continue;
                }

                List<Move> possibleMoves =
                        piece.getLegalMoves(from, board);

                if (possibleMoves == null) {
                    continue;
                }

                for (Move move : possibleMoves) {
                    if (move == null) {
                        continue;
                    }

                    if (moveValidator.isLegal(move, board)
                            && !moveValidator.leavesKingInCheck(move, board)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
