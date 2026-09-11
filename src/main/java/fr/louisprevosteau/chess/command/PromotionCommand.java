package fr.louisprevosteau.chess.command;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.enums.PromotionType;

public class PromotionCommand implements Command {

    private Board board;
    private Move move;
    private PromotionType promotionType;

    public PromotionCommand(Board board, Move move, PromotionType promotionType) {
        this.board = board;
        this.move = move;
        this.promotionType = promotionType;
    }

    @Override
    public void execute() {

    }
}
