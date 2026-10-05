package fr.louisprevosteau.chess.state;

import fr.louisprevosteau.chess.clock.TimeControl;
import fr.louisprevosteau.chess.domain.Game;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Player;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class NotStartedStateTest {

    private NotStartedState state;
    private Game game;

    @BeforeEach
    void setup() {
        Player white = new Player("Kasparov", Color.WHITE);
        Player black = new Player("Karpov", Color.BLACK);

        game = new Game(
                black,
                white,
                new TimeControl(Duration.ofMinutes(10))
        );

        state = new NotStartedState();
    }

    @Test
    @DisplayName("canPlay() doit retourner false")
    void testCanPlayReturnsFalse() {
        assertFalse(state.canPlay());
    }

    @Test
    @DisplayName("playMove() doit lever une exception si la partie n'a pas commencé")
    void testPlayMoveThrowsException() {
        Move move = new Move(
                new Position(1, 0),
                new Position(2, 0)
        );

        assertThrows(
                IllegalStateException.class,
                () -> state.playMove(game, move)
        );
    }

    @Test
    @DisplayName("playMove() ne doit pas modifier le plateau")
    void testPlayMoveDoesNotModifyBoard() {
        Position from = new Position(1, 0);
        Position to = new Position(2, 0);

        Object pieceBefore = game.getBoard().getPiece(from);

        Move move = new Move(from, to);

        assertThrows(
                IllegalStateException.class,
                () -> state.playMove(game, move)
        );

        assertEquals(
                pieceBefore,
                game.getBoard().getPiece(from)
        );

        assertNull(
                game.getBoard().getPiece(to)
        );
    }
}
