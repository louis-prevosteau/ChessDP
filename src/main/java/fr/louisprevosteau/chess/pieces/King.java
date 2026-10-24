package fr.louisprevosteau.chess.pieces;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.PieceType;
import fr.louisprevosteau.chess.strategy.KingMovementStrategy;

public class King extends Piece {

    private boolean hasMoved;

    public King(Color color) {
        super(color, PieceType.KING);
        this.movementStrategy = new KingMovementStrategy();
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
