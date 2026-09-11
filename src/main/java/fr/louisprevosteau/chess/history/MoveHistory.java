package fr.louisprevosteau.chess.history;

import fr.louisprevosteau.chess.domain.Move;

import java.util.List;

public class MoveHistory {

    private List<Move> moves;

    public MoveHistory() {
    }

    public void add(Move move) {}

    public void remove(Move move) {}

    public List<Move> getMoves() {
        return moves;
    }

    public Move getLastMove() {
        return null;
    }
}
