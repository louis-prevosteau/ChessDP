package fr.louisprevosteau.chess.history;

import fr.louisprevosteau.chess.domain.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveHistory {

    private final List<Move> moves;
    private final Map<String, Integer> positionOccurrences;

    public MoveHistory() {
        this.moves = new ArrayList<>();
        this.positionOccurrences = new HashMap<>();
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

    public void recordPosition(String positionKey) {
        if (positionKey != null) {
            positionOccurrences.merge(positionKey, 1, Integer::sum);
        }
    }

    public int countPositionOccurrences(String positionKey) {
        if (positionKey == null) {
            return 0;
        }

        return positionOccurrences.getOrDefault(positionKey, 0);
    }
}
