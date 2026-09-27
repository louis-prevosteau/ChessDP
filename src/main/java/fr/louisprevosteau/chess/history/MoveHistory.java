package fr.louisprevosteau.chess.history;

import fr.louisprevosteau.chess.domain.Move;

import java.util.ArrayList;
import java.util.List;

public class MoveHistory {

    private final List<Move> moves;

    public MoveHistory() {
        this.moves = new ArrayList<>();
    }

    public void add(Move move) {
        moves.add(move);
    }

    public void remove(Move move) {
        moves.remove(move);
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Move getLastMove() {
        if (moves.isEmpty())
            return null;
        return moves.get(moves.size() - 1);
    }
}
