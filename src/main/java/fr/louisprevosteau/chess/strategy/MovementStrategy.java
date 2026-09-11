package fr.louisprevosteau.chess.strategy;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;

import java.util.List;

public interface MovementStrategy {

    List<Move> getLegalMoves(Position position, Board board);
}
