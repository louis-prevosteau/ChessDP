package fr.louisprevosteau.chess.pieces;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.PieceType;
import fr.louisprevosteau.chess.strategy.QueenMovementStrategy;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, PieceType.QUEEN);
        this.movementStrategy = new QueenMovementStrategy();
    }
}
