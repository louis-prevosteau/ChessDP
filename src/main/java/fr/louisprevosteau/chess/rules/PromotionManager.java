package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.PromotionType;
import fr.louisprevosteau.chess.pieces.Pawn;
import fr.louisprevosteau.chess.pieces.Piece;

import java.util.List;

public class PromotionManager {

    public boolean canPromote(Pawn pawn, Position position) {
        return false;
    }

    public Piece promote(Pawn pawn, PromotionType type) {
        return null;
    }

    public List<PromotionType> getPromotionChoices() {
        return null;
    }
}
