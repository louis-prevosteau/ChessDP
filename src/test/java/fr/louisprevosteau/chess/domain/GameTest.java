package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.clock.TimeControl;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.GameStatus;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;
    private Player white;
    private Player black;

    @BeforeEach
    void setup() {

        white = new Player(
                "Kasparov",
                Color.WHITE
        );

        black = new Player(
                "Karpov",
                Color.BLACK
        );

        TimeControl timeControl =
                new TimeControl(
                        Duration.ofMinutes(10)
                );

        game = new Game(
                black,
                white,
                timeControl
        );
    }

    @Test
    @DisplayName("Une partie doit être correctement initialisée")
    void testConstructor() {

        assertNotNull(game.getBoard());
        assertNotNull(game.getClock());
        assertNotNull(game.getMoveHistory());

        assertEquals(
                GameStatus.NOT_STARTED,
                game.getStatus()
        );
    }

    @Test
    @DisplayName("Le joueur courant doit être blanc au démarrage")
    void testCurrentPlayerIsWhite() {

        assertEquals(
                white,
                game.getCurrent()
        );
    }

    @Test
    @DisplayName("getOpponent() doit retourner le joueur adverse")
    void testGetOpponentWhenWhitePlays() {

        assertEquals(
                black,
                game.getOpponent()
        );
    }

    @Test
    @DisplayName("switchPlayer() doit passer au joueur adverse")
    void testSwitchPlayer() {

        game.switchOPlayer();

        assertEquals(
                black,
                game.getCurrent()
        );

        assertEquals(
                white,
                game.getOpponent()
        );
    }

    @Test
    @DisplayName("switchPlayer() appelé deux fois doit revenir au joueur initial")
    void testSwitchPlayerTwice() {

        game.switchOPlayer();
        game.switchOPlayer();

        assertEquals(
                white,
                game.getCurrent()
        );
    }

    @Test
    @DisplayName("Une partie nouvellement créée n'est pas terminée")
    void testGameIsNotOverAtInitialization() {

        assertFalse(
                game.isGameOver()
        );
    }

    @Test
    @DisplayName("Le statut doit être accessible")
    void testGetStatus() {

        assertNotNull(
                game.getStatus()
        );
    }

    @Test
    @DisplayName("La pendule doit être créée")
    void testClockInitialization() {

        assertNotNull(
                game.getClock()
        );
    }

    @Test
    @DisplayName("L'historique des coups doit être créé")
    void testMoveHistoryInitialization() {

        assertNotNull(
                game.getMoveHistory()
        );
    }
}
