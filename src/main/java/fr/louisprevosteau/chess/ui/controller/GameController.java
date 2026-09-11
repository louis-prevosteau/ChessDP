package fr.louisprevosteau.chess.ui.controller;

import fr.louisprevosteau.chess.domain.Game;

public class GameController {

    private Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public void startGame() {}

    public void resign() {}

    public void offerDraw() {}

    public void save() {}

    public void load() {}
}
