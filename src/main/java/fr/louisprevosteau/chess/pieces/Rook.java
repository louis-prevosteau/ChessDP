package fr.louisprevosteau.chess.pieces;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.PieceType;
import fr.louisprevosteau.chess.strategy.RookMovementStrategy;

public class Rook extends Piece {

    private boolean hasMoved;

    public Rook(Color color) {
        super(color, PieceType.ROOK);
        this.movementStrategy = new RookMovementStrategy();
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
