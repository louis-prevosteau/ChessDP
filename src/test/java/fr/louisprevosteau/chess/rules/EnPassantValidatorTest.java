package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.history.MoveHistory;
import fr.louisprevosteau.chess.pieces.Pawn;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class EnPassantValidatorTest {

    private EnPassantValidator validator;
    private Board board;

    @BeforeEach
    void setUp() {
        validator = new EnPassantValidator(new MoveHistory());
        board = new Board();
    }

    @Nested
    @DisplayName("canCapture()")
    class CanCaptureTests {

        @Test
        @DisplayName("Doit retourner false si le coup est null")
        void testNullMove() {
            assertFalse(validator.canCapture(null, board));
        }

        @Test
        @DisplayName("Doit retourner false si le plateau est null")
        void testNullBoard() {
            Move move = new Move(
                    new Position(4, 4),
                    new Position(5, 5)
            );

            assertFalse(validator.canCapture(move, null));
        }

        @Test
        @DisplayName("Doit retourner false si la case de départ est vide")
        void testEmptyStartingSquare() {
            Move move = new Move(
                    new Position(4, 4),
                    new Position(5, 5)
            );

            assertFalse(validator.canCapture(move, board));
        }

        @Test
        @DisplayName("Doit retourner false si le déplacement n'est pas diagonal")
        void testNonDiagonalMove() {
            board.setPiece(
                    new Position(4, 4),
                    new Pawn(Color.WHITE)
            );

            Move move = new Move(
                    new Position(4, 4),
                    new Position(5, 4)
            );

            assertFalse(validator.canCapture(move, board));
        }

        @Test
        @DisplayName("Doit retourner false si la case d'arrivée est occupée")
        void testOccupiedDestination() {
            board.setPiece(
                    new Position(4, 4),
                    new Pawn(Color.WHITE)
            );
            board.setPiece(
                    new Position(5, 5),
                    new Pawn(Color.BLACK)
            );

            Move move = new Move(
                    new Position(4, 4),
                    new Position(5, 5)
            );

            assertFalse(validator.canCapture(move, board));
        }

        @Test
        @DisplayName("Doit retourner false si aucun pion adverse ne peut être capturé")
        void testNoCapturedPawn() {
            board.setPiece(
                    new Position(4, 4),
                    new Pawn(Color.WHITE)
            );

            Move move = new Move(
                    new Position(4, 4),
                    new Position(5, 5)
            );

            assertFalse(validator.canCapture(move, board));
        }
    }

    @Nested
    @DisplayName("getCapturedPawn()")
    class GetCapturedPawnTests {

        @Test
        @DisplayName("Doit retourner null si le coup est null")
        void testNullMove() {
            assertNull(validator.getCapturedPawn(null, board));
        }

        @Test
        @DisplayName("Doit retourner null si le plateau est null")
        void testNullBoard() {
            Move move = new Move(
                    new Position(4, 4),
                    new Position(5, 5)
            );

            assertNull(validator.getCapturedPawn(move, null));
        }

        @Test
        @DisplayName("Doit retourner null si aucun pion adverse n'est présent")
        void testNoCapturedPawn() {
            board.setPiece(
                    new Position(4, 4),
                    new Pawn(Color.WHITE)
            );

            Move move = new Move(
                    new Position(4, 4),
                    new Position(5, 5)
            );

            assertNull(validator.getCapturedPawn(move, board));
        }
    }
}