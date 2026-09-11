package fr.louisprevosteau.chess.persistance;

import fr.louisprevosteau.chess.domain.Game;

public class SaveManager {

    private GameRepository repository;

    public SaveManager(GameRepository repository) {
        this.repository = repository;
    }

    public void save(Game game) {}

    public Game load(String id) {
        return null;
    }
}
