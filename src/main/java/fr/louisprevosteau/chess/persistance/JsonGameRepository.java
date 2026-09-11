package fr.louisprevosteau.chess.persistance;

import fr.louisprevosteau.chess.domain.Game;

public class JsonGameRepository implements GameRepository {

    private String filePath;

    public JsonGameRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(Game game) {

    }

    @Override
    public Game load(String ind) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public boolean exists(String id) {
        return false;
    }
}
