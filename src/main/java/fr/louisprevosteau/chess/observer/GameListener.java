package fr.louisprevosteau.chess.observer;

import fr.louisprevosteau.chess.domain.GameResult;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.enums.Color;

public interface GameListener {

    void onGameStarted();

    void onMovePlayed(Move move);

    void onCheck(Color color);

    void onGameEnded(GameResult result);
}
