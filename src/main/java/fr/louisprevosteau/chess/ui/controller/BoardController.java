package fr.louisprevosteau.chess.ui.controller;

import fr.louisprevosteau.chess.domain.Game;
import fr.louisprevosteau.chess.domain.Position;

public class BoardController {

    private Game game;
    private Position selectedPosition;

    public BoardController(Game game) {
        this.game = game;
    }

    public void onSquareClicked(Position position) {}

    public void selectPiece(Position position) {}

    public void movePiece(Position position) {}

    public void cancelSelection() {}
}
