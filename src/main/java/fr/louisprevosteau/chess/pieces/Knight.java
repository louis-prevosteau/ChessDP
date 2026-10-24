package fr.louisprevosteau.chess.pieces;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.PieceType;
import fr.louisprevosteau.chess.strategy.KnightMovementStrategy;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
        this.movementStrategy = new KnightMovementStrategy();
    }
}
