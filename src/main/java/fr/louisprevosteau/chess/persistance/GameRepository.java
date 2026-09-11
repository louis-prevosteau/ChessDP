package fr.louisprevosteau.chess.persistance;

import fr.louisprevosteau.chess.domain.Game;

public interface GameRepository {

    void save(Game game);

    Game load(String ind);

    void delete(String id);

    boolean exists(String id);
}
