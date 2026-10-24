package fr.louisprevosteau.chess.pieces;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.PieceType;
import fr.louisprevosteau.chess.strategy.PawnMovementStrategy;

public class Pawn extends Piece {

    private boolean hasMoved;

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
        this.movementStrategy = new PawnMovementStrategy();
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
