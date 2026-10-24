package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.PieceType;
import fr.louisprevosteau.chess.history.MoveHistory;
import fr.louisprevosteau.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class DrawDetector {

    public boolean isDrawByInsufficientMaterial(Board board) {
        List<Piece> pieces = new ArrayList<>();

        if (board == null) {
            return false;
        }

        for (int row = 0 ; row < 8 ; row++) {
            for (int column = 0 ; column < 8 ; column++) {
                Piece piece = board.getPiece(new Position(row, column));
                if (piece != null)
                    pieces.add(piece);
            }
        }

        long kingCount = pieces.stream()
                .filter(piece -> piece.getType() == PieceType.KING)
                .count();

        if (kingCount != 2) {
            return false;
        }

        List<Piece> remainingPieces = pieces.stream()
                .filter(piece -> piece.getType() != PieceType.KING)
                .toList();

        if (remainingPieces.isEmpty()) {
            return true;
        }

        if (remainingPieces.size() == 1) {
            PieceType type = remainingPieces.get(0).getType();

            return type == PieceType.BISHOP
                    || type == PieceType.KNIGHT;
        }

        if (remainingPieces.size() == 2
                && remainingPieces.stream()
                .allMatch(piece -> piece.getType() == PieceType.BISHOP)) {

            List<Position> bishopPositions = new ArrayList<>();

            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    Piece piece = board.getPiece(new Position(row, column));

                    if (piece != null && piece.getType() == PieceType.BISHOP) {
                        bishopPositions.add(new Position(row, column));
                    }
                }
            }

            if (bishopPositions.size() == 2) {
                Position first = bishopPositions.get(0);
                Position second = bishopPositions.get(1);

                boolean firstSquareColor =
                        (first.getRow() + first.getColumn()) % 2 == 0;
                boolean secondSquareColor =
                        (second.getRow() + second.getColumn()) % 2 == 0;

                return firstSquareColor == secondSquareColor;
            }
        }

        return false;
    }

    public boolean isDrawByFiftyMoves(Board board) {
        if (board == null) return false;
        return board.getMoveHistory().size() == 100;
    }

    public boolean isThreefoldRepetition(MoveHistory moveHistory, String currentPositionKey) {
        if (moveHistory == null) return false;
        return moveHistory.countPositionOccurrences(currentPositionKey) >= 3;
    }

    public boolean isDraw(Board board, MoveHistory moveHistory, String currentPositionKey) {
        if (board == null || moveHistory == null || currentPositionKey == null) return false;
        return isDrawByInsufficientMaterial(board) || isThreefoldRepetition(moveHistory, currentPositionKey) || isDrawByFiftyMoves(board);
    }
}
