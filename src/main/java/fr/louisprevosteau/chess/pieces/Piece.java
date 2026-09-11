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
        return null;
    }

    public boolean isEnemy() {
        return false;
    }

    public boolean isSameColor() {
        return false;
    }

    public boolean canMoveTo(Position position, Board board) {
        return false;
    }
}
