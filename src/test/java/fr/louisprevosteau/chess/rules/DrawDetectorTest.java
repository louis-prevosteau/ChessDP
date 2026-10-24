package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.history.MoveHistory;
import fr.louisprevosteau.chess.pieces.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DrawDetectorTest {

    private DrawDetector drawDetector;
    private Board board;
    private MoveHistory moveHistory;

    @BeforeEach
    void setUp() {
        drawDetector = new DrawDetector();
        board = new Board();
        moveHistory = new MoveHistory();
    }

    @Nested
    @DisplayName("isDrawByInsufficientMaterial()")
    class InsufficientMaterialTests {

        @Test
        @DisplayName("Doit détecter une position avec seulement les deux rois")
        void testOnlyKings() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));
            board.setPiece(new Position(7, 4), new King(Color.BLACK));

            assertTrue(drawDetector.isDrawByInsufficientMaterial(board));
        }

        @Test
        @DisplayName("Doit détecter un roi contre un roi et un cavalier")
        void testKingAgainstKingAndKnight() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));
            board.setPiece(new Position(7, 4), new King(Color.BLACK));
            board.setPiece(new Position(6, 2), new Knight(Color.BLACK));

            assertTrue(drawDetector.isDrawByInsufficientMaterial(board));
        }

        @Test
        @DisplayName("Doit détecter un roi contre un roi et un fou")
        void testKingAgainstKingAndBishop() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));
            board.setPiece(new Position(7, 4), new King(Color.BLACK));
            board.setPiece(new Position(6, 2), new Bishop(Color.BLACK));

            assertTrue(drawDetector.isDrawByInsufficientMaterial(board));
        }

        @Test
        @DisplayName("Doit retourner false si une dame est présente")
        void testQueenOnBoard() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));
            board.setPiece(new Position(7, 4), new King(Color.BLACK));
            board.setPiece(new Position(3, 3), new Queen(Color.WHITE));

            assertFalse(drawDetector.isDrawByInsufficientMaterial(board));
        }

        @Test
        @DisplayName("Doit retourner false si un pion est présent")
        void testPawnOnBoard() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));
            board.setPiece(new Position(7, 4), new King(Color.BLACK));
            board.setPiece(new Position(1, 0), new Pawn(Color.WHITE));

            assertFalse(drawDetector.isDrawByInsufficientMaterial(board));
        }

        @Test
        @DisplayName("Doit gérer un plateau null")
        void testNullBoard() {
            assertFalse(drawDetector.isDrawByInsufficientMaterial(null));
        }
    }

    @Nested
    @DisplayName("isDrawByFiftyMoves()")
    class FiftyMovesTests {

        @Test
        @DisplayName("Doit retourner false si aucun demi-coup n'a été joué")
        void testNoMovesPlayed() {
            assertFalse(drawDetector.isDrawByFiftyMoves(board));
        }

        @Test
        @DisplayName("Doit gérer un plateau null")
        void testNullBoard() {
            assertFalse(drawDetector.isDrawByFiftyMoves(null));
        }

        /*
         * Ajoute ici les tests du seuil des 100 demi-coups
         * lorsque Board expose le compteur de demi-coups
         * ou permet de le configurer.
         */
    }

    @Nested
    @DisplayName("isThreefoldRepetition()")
    class ThreefoldRepetitionTests {

        @Test
        @DisplayName("Doit retourner false si l'historique est vide")
        void testEmptyHistory() {
            assertFalse(drawDetector.isThreefoldRepetition(moveHistory, "position"));
        }

        @Test
        @DisplayName("Doit gérer un historique null")
        void testNullHistory() {
            assertFalse(drawDetector.isThreefoldRepetition(null, "position"));
        }

        @Test
        @DisplayName("Doit retourner false si la clé de position est null")
        void testNullPositionKey() {
            assertFalse(
                    drawDetector.isThreefoldRepetition(moveHistory, null)
            );
        }

        /*
         * Ajoute ici un test positif lorsque MoveHistory permet
         * d'enregistrer et de comparer les positions successives.
         */
    }

    @Nested
    @DisplayName("isDraw()")
    class IsDrawTests {

        @Test
        @DisplayName("Doit détecter une nulle par matériel insuffisant")
        void testDrawByInsufficientMaterial() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));
            board.setPiece(new Position(7, 4), new King(Color.BLACK));

            assertTrue(drawDetector.isDraw(board, moveHistory, ""));
        }

        @Test
        @DisplayName("Doit retourner false si aucune condition de nulle n'est remplie")
        void testNoDraw() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));
            board.setPiece(new Position(7, 4), new King(Color.BLACK));
            board.setPiece(new Position(1, 0), new Pawn(Color.WHITE));

            assertFalse(drawDetector.isDraw(board, moveHistory, ""));
        }

        @Test
        @DisplayName("Doit gérer un plateau null")
        void testNullBoard() {
            assertFalse(drawDetector.isDraw(null, moveHistory, ""));
        }

        @Test
        @DisplayName("Doit gérer un historique null")
        void testNullHistory() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));
            board.setPiece(new Position(7, 4), new King(Color.BLACK));
            board.setPiece(new Position(1, 0), new Pawn(Color.WHITE));

            assertFalse(drawDetector.isDraw(board, null, ""));
        }
    }
}
