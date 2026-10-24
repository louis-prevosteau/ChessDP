package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CastleValidatorTest {

    private Board board;
    private CastleValidator validator;

    @BeforeEach
    void setUp() {
        board = new Board();
        validator = new CastleValidator();
    }

    private void placeWhiteKingAndRooks() {
        board.setPiece(new Position(0, 4), new King(Color.WHITE));
        board.setPiece(new Position(0, 0), new Rook(Color.WHITE));
        board.setPiece(new Position(0, 7), new Rook(Color.WHITE));
    }

    private void placeBlackKingAndRooks() {
        board.setPiece(new Position(7, 4), new King(Color.BLACK));
        board.setPiece(new Position(7, 0), new Rook(Color.BLACK));
        board.setPiece(new Position(7, 7), new Rook(Color.BLACK));
    }

    @Nested
    @DisplayName("Petit roque")
    class KingsideCastlingTests {

        @Test
        @DisplayName("Le petit roque blanc est possible lorsque le chemin est libre et sûr")
        void testWhiteKingsideCastlePossible() {
            placeWhiteKingAndRooks();

            assertTrue(validator.canCastleKingside(board, Color.WHITE));
        }

        @Test
        @DisplayName("Le petit roque noir est possible lorsque le chemin est libre et sûr")
        void testBlackKingsideCastlePossible() {
            placeBlackKingAndRooks();

            assertTrue(validator.canCastleKingside(board, Color.BLACK));
        }

        @Test
        @DisplayName("Le petit roque est impossible si le roi est absent")
        void testKingsideCastleImpossibleWithoutKing() {
            board.setPiece(new Position(0, 7), new Rook(Color.WHITE));

            assertFalse(validator.canCastleKingside(board, Color.WHITE));
        }

        @Test
        @DisplayName("Le petit roque est impossible si la tour est absente")
        void testKingsideCastleImpossibleWithoutRook() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));

            assertFalse(validator.canCastleKingside(board, Color.WHITE));
        }

        @Test
        @DisplayName("Le petit roque est impossible si une pièce bloque le chemin")
        void testKingsideCastleImpossibleWhenPathBlocked() {
            placeWhiteKingAndRooks();
            board.setPiece(new Position(0, 5), new Bishop(Color.WHITE));

            assertFalse(validator.canCastleKingside(board, Color.WHITE));
        }
    }

    @Nested
    @DisplayName("Grand roque")
    class QueensideCastlingTests {

        @Test
        @DisplayName("Le grand roque blanc est possible lorsque le chemin est libre et sûr")
        void testWhiteQueensideCastlePossible() {
            placeWhiteKingAndRooks();

            assertTrue(validator.canCastleQueenside(board, Color.WHITE));
        }

        @Test
        @DisplayName("Le grand roque noir est possible lorsque le chemin est libre et sûr")
        void testBlackQueensideCastlePossible() {
            placeBlackKingAndRooks();

            assertTrue(validator.canCastleQueenside(board, Color.BLACK));
        }

        @Test
        @DisplayName("Le grand roque est impossible si la tour est absente")
        void testQueensideCastleImpossibleWithoutRook() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));

            assertFalse(validator.canCastleQueenside(board, Color.WHITE));
        }

        @Test
        @DisplayName("Le grand roque est impossible si une pièce bloque le chemin")
        void testQueensideCastleImpossibleWhenPathBlocked() {
            placeWhiteKingAndRooks();
            board.setPiece(new Position(0, 2), new Knight(Color.WHITE));

            assertFalse(validator.canCastleQueenside(board, Color.WHITE));
        }
    }

    @Nested
    @DisplayName("Vérification du chemin")
    class PathClearTests {

        @Test
        @DisplayName("Le chemin est libre si aucune pièce ne bloque le roi et la tour")
        void testPathIsClear() {
            placeWhiteKingAndRooks();

            assertTrue(validator.isPathClear(board, Color.WHITE));
        }

        @Test
        @DisplayName("Le chemin est bloqué par une pièce entre le roi et la tour")
        void testPathBlockedByPiece() {
            placeWhiteKingAndRooks();
            board.setPiece(new Position(0, 5), new Bishop(Color.WHITE));

            assertFalse(validator.isPathClear(board, Color.WHITE));
        }

        @Test
        @DisplayName("Le chemin du grand roque est bloqué si la case b1 est occupée")
        void testQueensidePathBlockedByPiece() {
            placeWhiteKingAndRooks();
            board.setPiece(new Position(0, 1), new Knight(Color.WHITE));

            assertFalse(validator.isPathClear(board, Color.WHITE));
        }
    }

    @Nested
    @DisplayName("Sécurité du roque")
    class SafetyTests {

        @Test
        @DisplayName("Le roque est sûr si les cases traversées ne sont pas attaquées")
        void testCastleIsSafe() {
            placeWhiteKingAndRooks();

            assertTrue(validator.isSafe(board, Color.WHITE));
        }

        @Test
        @DisplayName("Le roque est dangereux si une pièce adverse attaque le roi")
        void testCastleIsUnsafeWhenKingIsInCheck() {
            placeWhiteKingAndRooks();
            board.setPiece(new Position(7, 4), new Rook(Color.BLACK));

            assertFalse(validator.isSafe(board, Color.WHITE));
        }

        @Test
        @DisplayName("Un plateau nul ne permet pas de roquer")
        void testNullBoard() {
            assertFalse(validator.canCastleKingside(null, Color.WHITE));
            assertFalse(validator.canCastleQueenside(null, Color.WHITE));
            assertFalse(validator.isPathClear(null, Color.WHITE));
            assertFalse(validator.isSafe(null, Color.WHITE));
        }

        @Test
        @DisplayName("Une couleur nulle ne permet pas de roquer")
        void testNullColor() {
            assertFalse(validator.canCastleKingside(board, null));
            assertFalse(validator.canCastleQueenside(board, null));
            assertFalse(validator.isPathClear(board, null));
            assertFalse(validator.isSafe(board, null));
        }
    }
}
