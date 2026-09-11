package fr.louisprevosteau.chess.observer;

import fr.louisprevosteau.chess.domain.Board;

public interface BoardListener {

    void onBoardChanged(Board board);
}
