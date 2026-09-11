package fr.louisprevosteau.chess.ui.view;

import fr.louisprevosteau.chess.pieces.Piece;

public class PieceView {

    private Piece piece;

    public PieceView(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void render() {}
}
