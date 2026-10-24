package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.King;
import fr.louisprevosteau.chess.pieces.Queen;
import fr.louisprevosteau.chess.pieces.Rook;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CheckmateDetectorTest {

    private MoveValidator moveValidator;
    private CheckDetector checkDetector;
    private CheckmateDetector checkmateDetector;
    private Board board;

    @BeforeEach
    void setUp() {
        moveValidator = new MoveValidator();
        checkDetector = new CheckDetector();
        checkmateDetector = new CheckmateDetector(
                moveValidator, checkDetector);
        board = new Board();
    }

    @Nested
    @DisplayName("isCheckmate()")
    class IsCheckmateTests {

        @Test
        @DisplayName("Doit retourner false si le roi n'est pas en échec")
        void testKingNotInCheck() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));
            board.setPiece(new Position(7, 4), new King(Color.BLACK));

            assertFalse(checkmateDetector.isCheckmate(board, Color.WHITE));
        }

        @Test
        @DisplayName("Doit détecter un échec et mat")
        void testCheckmate() {
            board.setPiece(new Position(0, 0), new King(Color.WHITE));

            board.setPiece(new Position(2, 2), new King(Color.BLACK));
            board.setPiece(new Position(1, 1), new Queen(Color.BLACK));

            assertTrue(checkmateDetector.isCheckmate(board, Color.WHITE));
        }

        @Test
        @DisplayName("Doit retourner false si le plateau est null")
        void testNullBoard() {
            assertFalse(checkmateDetector.isCheckmate(null, Color.WHITE));
        }

        @Test
        @DisplayName("Doit retourner false si la couleur est null")
        void testNullColor() {
            assertFalse(checkmateDetector.isCheckmate(board, null));
        }
    }

    @Nested
    @DisplayName("hasLegalMoves()")
    class HasLegalMovesTests {

        @Test
        @DisplayName("Doit retourner true si le roi peut se déplacer")
        void testKingHasLegalMoves() {
            board.setPiece(new Position(0, 0), new King(Color.WHITE));
            board.setPiece(new Position(7, 7), new King(Color.BLACK));

            assertTrue(checkmateDetector.hasLegalMoves(board, Color.WHITE));
        }

        @Test
        @DisplayName("Doit retourner false si le joueur n'a aucune pièce")
        void testNoPieces() {
            assertFalse(checkmateDetector.hasLegalMoves(board, Color.WHITE));
        }

        @Test
        @DisplayName("Doit retourner false si le plateau est null")
        void testNullBoard() {
            assertFalse(checkmateDetector.hasLegalMoves(null, Color.WHITE));
        }

        @Test
        @DisplayName("Doit retourner false si la couleur est null")
        void testNullColor() {
            assertFalse(checkmateDetector.hasLegalMoves(board, null));
        }

        @Test
        @DisplayName("Doit détecter les déplacements légaux d'une tour")
        void testRookHasLegalMoves() {
            board.setPiece(new Position(3, 3), new Rook(Color.WHITE));
            board.setPiece(new Position(7, 7), new King(Color.BLACK));

            assertTrue(checkmateDetector.hasLegalMoves(board, Color.WHITE));
        }
    }
}
