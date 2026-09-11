package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.enums.MoveType;
import fr.louisprevosteau.chess.enums.PromotionType;
import fr.louisprevosteau.chess.pieces.Piece;

public class Move {

    private final Position from, to;
    private Piece movedPiece, capturedPiece;
    private MoveType type;
    private PromotionType promotionType;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Move(Position from, Position to, MoveType type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    public MoveType getType() {
        return type;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public boolean isCastle() {
        return false;
    }

    public boolean isPromotion() {
        return false;
    }

    public boolean isEnPassant() {
        return false;
    }
}
