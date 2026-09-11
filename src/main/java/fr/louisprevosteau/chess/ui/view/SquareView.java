package fr.louisprevosteau.chess.ui.view;

import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.pieces.Piece;

public class SquareView {

    private Position position;
    private PieceView pieceView;

    public SquareView(Position position) {
        this.position = position;
    }

    public void setPiece(Piece piece) {}

    public void highlight() {}

    public void clearHighlight() {}
}
