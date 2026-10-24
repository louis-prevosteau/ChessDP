package fr.louisprevosteau.chess.pieces;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.PieceType;
import fr.louisprevosteau.chess.strategy.MovementStrategy;

import java.util.List;

public abstract class Piece {

    protected Color color;
    protected PieceType type;
    protected MovementStrategy movementStrategy;

    public Piece(Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public List<Move> getLegalMoves(Position position, Board board) {
        if (position == null || board == null || !position.isValid()) {
            return List.of();
        }

        if (movementStrategy == null) {
            return List.of();
        }

        return movementStrategy.getLegalMoves(position, board);
    }

    public boolean isEnemy(Piece other) {
        return other != null && color != other.getColor();
    }

    public boolean isSameColor(Piece other) {
        return other != null && color == other.getColor();
    }

    public boolean canMoveTo(Position from, Position to , Board board) {
        if (from == null || to == null || board == null
                || !from.isValid() || !to.isValid()) {
            return false;
        }

        return getLegalMoves(from, board).stream()
                .anyMatch(move -> move.getTo().equals(to));
    }
}
