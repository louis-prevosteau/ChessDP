package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.PromotionType;
import fr.louisprevosteau.chess.pieces.*;

import java.util.List;

public class PromotionManager {

    private final List<PromotionType> PROMOTION_CHOICES = List.of(
            PromotionType.QUEEN,
            PromotionType.ROOK,
            PromotionType.BISHOP,
            PromotionType.KNIGHT
    );

    public boolean canPromote(Pawn pawn, Position position) {
        if (pawn == null || position == null || !position.isValid()) return false;
        return (pawn.getColor().equals(Color.WHITE) && position.getRow() == 7) || (pawn.getColor().equals(Color.BLACK) && position.getRow() == 0);
    }

    public Piece promote(Pawn pawn, PromotionType type) {
        if (pawn == null || type == null || !PROMOTION_CHOICES.contains(type)) return null;
        Color color = pawn.getColor();
        return switch (type) {
            case QUEEN -> new Queen(color);
            case ROOK -> new Rook(color);
            case BISHOP -> new Bishop(color);
            case KNIGHT -> new Knight(color);
        };
    }

    public List<PromotionType> getPromotionChoices() {
        return PROMOTION_CHOICES;
    }
}
