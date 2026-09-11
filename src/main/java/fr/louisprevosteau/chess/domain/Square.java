package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.pieces.Piece;

public class Square {

    private final Position position;
    private Piece piece;

    public Square(Position position) {
        this.position = position;

    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isOccupied() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }
}
