package fr.louisprevosteau.chess.history;

import fr.louisprevosteau.chess.domain.Game;
import fr.louisprevosteau.chess.state.GameState;

import java.util.List;

public class HistoryManager {

    public HistoryManager() {
    }

    private List<GameState> states;

    public void save(Game game) {}

    public void restore(Game game) {}

    public boolean canRestore() {
        return false;
    }

    public void clear() {}
}
