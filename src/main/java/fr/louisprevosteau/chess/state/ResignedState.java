package fr.louisprevosteau.chess.state;

import fr.louisprevosteau.chess.domain.Game;
import fr.louisprevosteau.chess.domain.Move;

public class ResignedState implements GameState {

    @Override
    public void playMove(Game game, Move move) {
        throw new IllegalStateException("La partie est terminée par abandon.");
    }

    @Override
    public boolean canPlay() {
        return false;
    }
}
