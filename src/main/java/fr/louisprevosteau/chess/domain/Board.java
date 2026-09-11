package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Piece;

import java.util.List;

public class Board {

    private Square[][] squares;
    private List<Move> movesHistory;

    public Board() {
    }

    public void initialize() {}

    public Square getSquare(Position position) {
        return null;
    }

    public Piece getPiece(Position position) {
        return null;
    }

    public void setPiece(Position position, Piece piece) {}

    public Piece removePiece(Position position) {
        return null;
    }

    public void movePiece(Move move) {}

    public boolean isOccupied(Position position) {
        return false;
    }

    public boolean isEmpty(Position position) {
        return false;
    }

    public List<Piece> getPieces(Color color) {
        return null;
    }

    public Position findKing(Color color) {
        return null;
    }

    public Move getLastMove() {
        return null;
    }

    public List<Move> getMoveHistory() {
        return null;
    }

    public Board copy() {
        return null;
    }

    public void reset() {}
}
