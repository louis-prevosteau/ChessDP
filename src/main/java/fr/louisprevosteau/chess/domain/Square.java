package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.pieces.Piece;

public class Square {

    private final Position position;
    private Piece piece;

    public Square(Position position) {
        this.position = position;
        this.piece = null;

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
        return piece != null;
    }

    public boolean isEmpty() {
        return piece == null;
    }
}
