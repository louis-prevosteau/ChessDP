package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.King;
import fr.louisprevosteau.chess.pieces.Queen;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class StalemateDetectorTest {

    private StalemateDetector stalemateDetector;
    private Board board;

    @BeforeEach
    void setUp() {
        stalemateDetector = new StalemateDetector();
        board = new Board();
    }

    @Nested
    @DisplayName("isStalemate()")
    class IsStalemateTests {

        @Test
        @DisplayName("Doit retourner true en cas de pat")
        void testStalemate() {
            board.setPiece(new Position(7, 0), new King(Color.BLACK));

            board.setPiece(new Position(5, 2), new King(Color.WHITE));
            board.setPiece(new Position(5, 1), new Queen(Color.WHITE));

            assertTrue(stalemateDetector.isStalemate(board, Color.BLACK));
        }

        @Test
        @DisplayName("Doit retourner false si le roi est en échec")
        void testKingInCheck() {
            board.setPiece(new Position(7, 0), new King(Color.BLACK));
            board.setPiece(new Position(5, 2), new King(Color.WHITE));
            board.setPiece(new Position(6, 1), new Queen(Color.WHITE));

            assertFalse(stalemateDetector.isStalemate(board, Color.BLACK));
        }

        @Test
        @DisplayName("Doit retourner false si le joueur possède des coups légaux")
        void testPlayerHasLegalMoves() {
            board.setPiece(new Position(0, 0), new King(Color.WHITE));
            board.setPiece(new Position(7, 7), new King(Color.BLACK));

            assertFalse(stalemateDetector.isStalemate(board, Color.WHITE));
        }

        @Test
        @DisplayName("Doit retourner false si le plateau est null")
        void testNullBoard() {
            assertFalse(stalemateDetector.isStalemate(null, Color.WHITE));
        }

        @Test
        @DisplayName("Doit retourner false si la couleur est null")
        void testNullColor() {
            assertFalse(stalemateDetector.isStalemate(board, null));
        }
    }
}
