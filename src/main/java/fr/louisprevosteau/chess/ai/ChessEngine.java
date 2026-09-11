package fr.louisprevosteau.chess.ai;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.enums.Color;

public interface ChessEngine {

    Move findBestMove(Board board, Color color);
}
