package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Pawn;
import fr.louisprevosteau.chess.pieces.Piece;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MoveValidatorTest {

    private MoveValidator validator;
    private Board board;

    @BeforeEach
    void setUp() {
        validator = new MoveValidator();
        board = new Board();
    }

    private Position position(int row, int column) {
        return new Position(row, column);
    }

    private void placeWhitePawn() {
        board.setPiece(position(1, 4), new Pawn(Color.WHITE));
    }

    private void placeBlackPawn() {
        board.setPiece(position(6, 4), new Pawn(Color.BLACK));
    }

    @Nested
    @DisplayName("isLegal()")
    class IsLegalTests {

        @Test
        @DisplayName("Un déplacement légal doit être accepté")
        void testLegalMove() {
            placeWhitePawn();

            Move move = new Move(position(1, 4), position(2, 4));

            assertTrue(validator.isLegal(move, board));
        }

        @Test
        @DisplayName("Un déplacement depuis une case vide doit être refusé")
        void testMoveFromEmptySquare() {
            Move move = new Move(position(1, 4), position(2, 4));

            assertFalse(validator.isLegal(move, board));
        }

        @Test
        @DisplayName("Un déplacement vers une case occupée par une pièce alliée doit être refusé")
        void testMoveToFriendlyPiece() {
            placeWhitePawn();
            board.setPiece(position(2, 4), new Pawn(Color.WHITE));

            Move move = new Move(position(1, 4), position(2, 4));

            assertFalse(validator.isLegal(move, board));
        }

        @Test
        @DisplayName("Un déplacement hors de l'échiquier doit être refusé")
        void testMoveOutsideBoard() {
            placeWhitePawn();

            Move move = new Move(position(1, 4), position(8, 4));

            assertFalse(validator.isLegal(move, board));
        }

        @Test
        @DisplayName("Un déplacement nul doit être refusé")
        void testNullMove() {
            assertFalse(validator.isLegal(null, board));
        }

        @Test
        @DisplayName("Un échiquier null doit être refusé")
        void testNullBoard() {
            Move move = new Move(position(1, 4), position(2, 4));

            assertFalse(validator.isLegal(move, null));
        }
    }

    @Nested
    @DisplayName("validate()")
    class ValidateTests {

        @Test
        @DisplayName("Un déplacement légal ne doit pas lever d'exception")
        void testValidateLegalMove() {
            placeWhitePawn();

            Move move = new Move(position(1, 4), position(2, 4));

            assertDoesNotThrow(() -> validator.validate(move, board));
        }

        @Test
        @DisplayName("Un déplacement illégal doit lever une exception")
        void testValidateIllegalMove() {
            Move move = new Move(position(1, 4), position(2, 4));

            assertThrows(
                    IllegalArgumentException.class,
                    () -> validator.validate(move, board)
            );
        }

        @Test
        @DisplayName("Un déplacement null doit être rejeté")
        void testValidateNullMove() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> validator.validate(null, board)
            );
        }
    }

    @Nested
    @DisplayName("leavesKingInCheck()")
    class LeavesKingInCheckTests {

        @Test
        @DisplayName("Un déplacement qui ne met pas le roi en échec doit retourner false")
        void testMoveDoesNotLeaveKingInCheck() {
            placeWhitePawn();

            Move move = new Move(position(1, 4), position(2, 4));

            assertFalse(validator.leavesKingInCheck(move, board));
        }
    }

    @Nested
    @DisplayName("getLegalMoves()")
    class GetLegalMovesTests {

        @Test
        @DisplayName("Un pion blanc doit disposer de son déplacement vers l'avant")
        void testWhitePawnLegalMoves() {
            placeWhitePawn();

            Piece pawn = board.getPiece(position(1, 4));

            List<Move> moves = validator.getLegalMoves(
                    pawn, position(1, 4), board
            );

            assertNotNull(pawn);

            List<Move> possibleMoves = pawn.getLegalMoves(
                    position(1, 4), board
            );

            assertNotNull(moves);
            assertTrue(moves.contains(
                    new Move(position(1, 4), position(2, 4))
            ));
        }

        @Test
        @DisplayName("Un pion noir doit pouvoir avancer vers les rangées inférieures")
        void testBlackPawnLegalMoves() {
            placeBlackPawn();

            Piece pawn = board.getPiece(position(6, 4));

            List<Move> moves = validator.getLegalMoves(
                    pawn, position(6, 4), board
            );

            assertNotNull(pawn);

            List<Move> possibleMoves = pawn.getLegalMoves(
                    position(1, 4), board
            );

            assertNotNull(moves);
            assertTrue(moves.contains(
                    new Move(position(6, 4), position(5, 4))
            ));
        }

        @Test
        @DisplayName("Une pièce absente doit produire une liste vide")
        void testLegalMovesForMissingPiece() {
            List<Move> moves = validator.getLegalMoves(
                    null, position(1, 4), board
            );

            assertNotNull(moves);
            assertTrue(moves.isEmpty());
        }

        @Test
        @DisplayName("Une position null doit être gérée")
        void testLegalMovesWithNullPosition() {
            List<Move> moves = validator.getLegalMoves(
                    new Pawn(Color.WHITE), null, board
            );

            assertNotNull(moves);
            assertTrue(moves.isEmpty());
        }

        @Test
        @DisplayName("Un échiquier null doit être géré")
        void testLegalMovesWithNullBoard() {
            List<Move> moves = validator.getLegalMoves(
                    new Pawn(Color.WHITE), position(1, 4), null
            );

            assertNotNull(moves);
            assertTrue(moves.isEmpty());
        }
    }
}
