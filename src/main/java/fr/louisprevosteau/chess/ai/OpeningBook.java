package fr.louisprevosteau.chess.ai;

import fr.louisprevosteau.chess.domain.Move;

import java.util.List;
import java.util.Map;

public class OpeningBook {

    private Map<String, List<Move>> openings;

    public OpeningBook() {
    }

    public Move getMove(String fen) {
        return null;
    }

    public boolean contains(String fen) {
        return false;
    }

    public void add(String fen, Move move) {}
}
