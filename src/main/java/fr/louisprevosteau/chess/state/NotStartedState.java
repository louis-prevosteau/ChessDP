package fr.louisprevosteau.chess.state;

import fr.louisprevosteau.chess.domain.Game;
import fr.louisprevosteau.chess.domain.Move;

public class NotStartedState implements GameState {

    @Override
    public void playMove(Game game, Move move) {

    }

    @Override
    public boolean canPlay() {
        return false;
    }
}
