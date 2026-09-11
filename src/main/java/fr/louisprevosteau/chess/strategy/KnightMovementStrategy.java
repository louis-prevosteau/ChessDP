package fr.louisprevosteau.chess.strategy;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;

import java.util.List;

public class KnightMovementStrategy implements MovementStrategy {

    @Override
    public List<Move> getLegalMoves(Position position, Board board) {
        return List.of();
    }
}
