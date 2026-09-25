package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Pawn;
import fr.louisprevosteau.chess.pieces.Piece;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setup() {
        player = new Player("Kasparov", Color.WHITE);
    }

    @Test
    @DisplayName("Un joueur doit avoir un nom, une couleur et aucune pièces capturée")
    void testInitPlayer() {
        assertNotNull(player.getName());
        assertInstanceOf(Color.class, player.getColor());
        assertTrue(player.getCapturedPieces().isEmpty());
    }

    @Test
    @DisplayName("capture() doit ajouter une pièce adverse aux pièces capturées")
    void testCaptureOpponentPiece() {
        Piece pawn = new Pawn(Color.BLACK);
        player.capture(pawn);
        assertTrue(
                player.getCapturedPieces()
                        .contains(pawn)
        );
    }

    @Test
    @DisplayName("Un joueur ne peut pas capturer ses propres pièces (exception)")
    void testCaptureOwnPieceThrowsException() {
        Piece pawn = new Pawn(Color.WHITE);
        assertThrows(IllegalStateException.class, () -> player.capture(pawn));
    }
}
