package fr.louisprevosteau.chess.ai;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.rules.MoveValidator;

public class AlphaBetaEngine implements ChessEngine {

    private int depth;
    private PositionEvaluator positionEvaluator;
    private MoveValidator moveValidator;

    public AlphaBetaEngine(int depth) {
        this.depth = depth;
    }

    @Override
    public Move findBestMove(Board board, Color color) {
        return null;
    }

    public int alphabeta(Board board, int depth, int alpha, int beta, boolean maximising) {
        return 0;
    }
}
