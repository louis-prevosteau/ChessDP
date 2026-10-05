package fr.louisprevosteau.chess.state;

import fr.louisprevosteau.chess.domain.Game;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Player;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Pawn;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class PlayingStateTest {

    private PlayingState state;
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
                        new fr.louisprevosteau.chess.clock.TimeControl(
                                Duration.ofMinutes(10)
                        )
                );

        state = new PlayingState();
    }

    @Test
    @DisplayName("canPlay() doit retourner true")
    void testCanPlay() {

        assertTrue(
                state.canPlay()
        );
    }

    @Test
    @DisplayName("playMove() doit déplacer une pièce sur le plateau")
    void testPlayMove() {

        Position from =
                new Position(1, 0);

        Position to =
                new Position(2, 0);

        Pawn pawn =
                new Pawn(Color.WHITE);

        game.getBoard().setPiece(
                from,
                pawn
        );

        Move move =
                new Move(
                        from,
                        to
                );

        state.playMove(
                game,
                move
        );

        assertNull(
                game.getBoard().getPiece(from)
        );

        assertEquals(
                pawn,
                game.getBoard().getPiece(to)
        );
    }
}
