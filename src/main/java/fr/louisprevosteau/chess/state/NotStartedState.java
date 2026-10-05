package fr.louisprevosteau.chess.state;

import fr.louisprevosteau.chess.domain.Game;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.enums.GameStatus;

public class NotStartedState implements GameState {

    @Override
    public void playMove(Game game, Move move) {
        throw new IllegalStateException("La partie n'a pas encore commencé");
    }

    @Override
    public boolean canPlay() {
        return false;
    }
}
