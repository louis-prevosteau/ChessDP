package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Piece;
import fr.louisprevosteau.chess.strategy.MovementStrategy;

import java.util.ArrayList;
import java.util.List;

public class MoveValidator {

    private final CheckDetector checkDetector = new CheckDetector();

    private Color opposite(Color color) {
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public boolean isLegal(Move move, Board board) {
        if (move == null || board == null) {
            return false;
        }

        Position from = move.getFrom();
        Position to = move.getTo();

        if (from == null || to == null
                || !from.isValid() || !to.isValid()
                || from.equals(to)) {
            return false;
        }

        Piece piece = board.getPiece(from);

        if (piece == null) {
            return false;
        }

        Piece target = board.getPiece(to);

        if (target != null && target.getColor() == piece.getColor()) {
            return false;
        }

        List<Move> possibleMoves = piece.getLegalMoves(from, board);

        if (possibleMoves == null) {
            return false;
        }

        return possibleMoves.stream().anyMatch(possibleMove ->
                possibleMove != null
                        && possibleMove.getFrom().equals(from)
                        && possibleMove.getTo().equals(to)
        );
    }

    public void validate(Move move, Board board) {
        if (move == null) {
            throw new IllegalArgumentException(
                    "Le déplacement ne peut pas être null."
            );
        }

        if (board == null) {
            throw new IllegalArgumentException(
                    "L'échiquier ne peut pas être null."
            );
        }

        if (!isLegal(move, board)) {
            throw new IllegalArgumentException(
                    "Déplacement illégal : "
                            + move.getFrom() + " -> " + move.getTo()
            );
        }

        if (leavesKingInCheck(move, board)) {
            throw new IllegalArgumentException(
                    "Ce déplacement laisserait le roi en échec."
            );
        }
    }

    public boolean leavesKingInCheck(Move move, Board board) {
        if (move == null || board == null) {
            throw new IllegalArgumentException(
                    "Le déplacement et l'échiquier ne peuvent pas être null."
            );
        }

        Position from = move.getFrom();
        Position to = move.getTo();

        if (from == null || to == null
                || !from.isValid() || !to.isValid()) {
            throw new IllegalArgumentException(
                    "Les positions du déplacement sont invalides."
            );
        }

        Piece piece = board.getPiece(from);

        if (piece == null) {
            throw new IllegalArgumentException(
                    "Aucune pièce sur la case de départ."
            );
        }

        Color color = piece.getColor();

        Board simulatedBoard = board.copy();
        simulatedBoard.movePiece(move);

        Position kingPosition = simulatedBoard.findKing(color);

        if (kingPosition == null) {
            return false;
        }

        return checkDetector.isSquareAttacked(
                simulatedBoard,
                kingPosition,
                opposite(color)
        );
    }

    public List<Move> getLegalMoves(Piece piece, Position position, Board board) {
        List<Move> legalMoves = new ArrayList<>();

        if (piece == null || position == null || board == null
                || !position.isValid()) {
            return legalMoves;
        }

        if (board.getPiece(position) != piece) {
            return legalMoves;
        }

        List<Move> possibleMoves = piece.getLegalMoves(position, board);

        if (possibleMoves == null) {
            return legalMoves;
        }

        for (Move move : possibleMoves) {
            if (move != null && isLegal(move, board)) {
                legalMoves.add(move);
            }
        }

        return legalMoves;
    }
}
