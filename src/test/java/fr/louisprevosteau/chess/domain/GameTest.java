package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.clock.TimeControl;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.GameStatus;
import fr.louisprevosteau.chess.observer.GameListener;
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

        game.switchPlayer();

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

        game.switchPlayer();
        game.switchPlayer();

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

    @Test
    @DisplayName("start() doit démarrer la partie")
    void testStartGame() {

        game.start();

        assertEquals(
                GameStatus.PLAYING,
                game.getStatus()
        );

        assertTrue(
                game.getClock().isRunning()
        );
    }

    @Test
    @DisplayName("playMove() doit jouer le coup et changer le joueur")
    void testPlayMove() {

        Position from = new Position(1, 0);
        Position to = new Position(2, 0);

        Move move = new Move(from, to);

        game.getBoard().initialize();

        game.playMove(move);

        assertEquals(
                move,
                game.getMoveHistory().getLastMove()
        );

        assertEquals(
                black,
                game.getCurrent()
        );
    }

    @Test
    @DisplayName("resign() doit terminer la partie")
    void testResign() {

        game.resign();

        assertTrue(
                game.isGameOver()
        );

        assertEquals(
                GameStatus.RESIGNED,
                game.getStatus()
        );
    }

    @Test
    @DisplayName("offerDraw() doit enregistrer une proposition de nulle")
    void testOfferDraw() {

        game.offerDraw();

        assertEquals(
                GameStatus.DRAW_OFFERED,
                game.getStatus()
        );
    }

    @Test
    @DisplayName("acceptDraw() doit terminer la partie par une nulle")
    void testAcceptDraw() {

        game.offerDraw();

        game.acceptDraw();

        assertTrue(
                game.isGameOver()
        );

        assertEquals(
                GameStatus.DRAW,
                game.getStatus()
        );
    }

    /***
     @Test
     @DisplayName("addListener() doit ajouter un listener")
     void testAddListener() {

     GameListener listener;

     game.addListener(listener);

     assertTrue(
     game.getListeners()
     .contains(listener)
     );
     }

     @Test
     @DisplayName("removeListener() doit retirer un listener")
     void testRemoveListener() {

     GameListener listener;

     game.addListener(listener);

     game.removeListener(listener);

     assertFalse(
     game.getListeners()
     .contains(listener)
     );
     }
     */

    @Test
    @DisplayName("getResult() doit retourner le résultat après abandon")
    void testGetResultAfterResignation() {

        game.resign();

        GameResult result =
                game.getResult();

        assertNotNull(result);

        assertTrue(
                result.hasWinner()
        );
    }

    @Test
    @DisplayName("getResult() doit retourner une nulle")
    void testGetResultAfterDraw() {

        game.offerDraw();
        game.acceptDraw();

        GameResult result =
                game.getResult();

        assertTrue(
                result.isDraw()
        );

        assertFalse(
                result.hasWinner()
        );
    }
}
