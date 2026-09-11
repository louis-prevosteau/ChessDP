package fr.louisprevosteau.chess.state;

import fr.louisprevosteau.chess.domain.Game;
import fr.louisprevosteau.chess.domain.Move;

public interface GameState {

    void playMove(Game game, Move move);

    boolean canPlay();
}
