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

public class ResignedStateTest {

    private ResignedState state;
    private Game game;

    @BeforeEach
    void setup() {

        Player white =
                new Player(
                        "Kasparov",
                        Color.WHITE
                );

        Player black =
                new Player(
                        "Karpov",
                        Color.BLACK
                );

        game =
                new Game(
                        black,
                        white,
                        new TimeControl(
                                Duration.ofMinutes(10)
                        )
                );

        state = new ResignedState();
    }

    @Test
    @DisplayName("canPlay() doit retourner false")
    void testCanPlayReturnsFalse() {
        assertFalse(
                state.canPlay()
        );
    }

    @Test
    @DisplayName("playMove() doit lever une exception")
    void testPlayMoveThrowsException() {

        Move move =
                new Move(
                        new Position(1, 0),
                        new Position(2, 0)
                );

        assertThrows(
                IllegalStateException.class,
                () -> state.playMove(game, move)
        );
    }
}
