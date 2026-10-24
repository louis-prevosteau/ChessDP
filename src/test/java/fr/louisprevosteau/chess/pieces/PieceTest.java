package fr.louisprevosteau.chess.pieces;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.PieceType;
import fr.louisprevosteau.chess.strategy.MovementStrategy;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    private Board board;
    private Piece whitePiece;
    private Piece blackPiece;

    private static class TestPiece extends Piece {

        TestPiece(Color color) {
            super(color, PieceType.PAWN);
        }

        TestPiece(Color color, MovementStrategy strategy) {
            super(color, PieceType.PAWN);
            this.movementStrategy = strategy;
        }
    }

    @BeforeEach
    void setUp() {
        board = new Board();
        whitePiece = new TestPiece(Color.WHITE);
        blackPiece = new TestPiece(Color.BLACK);
    }

    @Nested
    @DisplayName("Constructeur et accesseurs")
    class ConstructorTests {

        @Test
        @DisplayName("Le constructeur doit initialiser la couleur")
        void testGetColor() {
            assertEquals(Color.WHITE, whitePiece.getColor());
            assertEquals(Color.BLACK, blackPiece.getColor());
        }

        @Test
        @DisplayName("Le constructeur doit initialiser le type")
        void testGetType() {
            assertEquals(PieceType.PAWN, whitePiece.getType());
        }
    }

    @Nested
    @DisplayName("getLegalMoves()")
    class GetLegalMovesTests {

        @Test
        @DisplayName("Doit retourner une liste vide si la position est null")
        void testNullPosition() {
            assertTrue(whitePiece.getLegalMoves(null, board).isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une liste vide si le plateau est null")
        void testNullBoard() {
            assertTrue(whitePiece.getLegalMoves(
                    new Position(1, 4), null
            ).isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une liste vide si la position est invalide")
        void testInvalidPosition() {
            assertTrue(whitePiece.getLegalMoves(
                    new Position(8, 4), board
            ).isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une liste vide sans stratégie")
        void testMissingStrategy() {
            assertTrue(whitePiece.getLegalMoves(
                    new Position(1, 4), board
            ).isEmpty());
        }

        @Test
        @DisplayName("Doit déléguer à la stratégie de déplacement")
        void testDelegatesToMovementStrategy() {
            MovementStrategy strategy = (position, currentBoard) ->
                    List.of(new Move(
                            new Position(1, 4),
                            new Position(2, 4)
                    ));

            Piece piece = new TestPiece(Color.WHITE, strategy);

            List<Move> moves = piece.getLegalMoves(
                    new Position(1, 4), board
            );

            assertEquals(1, moves.size());
            assertEquals(
                    new Move(new Position(1, 4), new Position(2, 4)),
                    moves.get(0)
            );
        }
    }

    @Nested
    @DisplayName("isEnemy()")
    class IsEnemyTests {

        @Test
        @DisplayName("Le comportement actuel retourne false")
        void testIsEnemyDefault() {
            assertFalse(whitePiece.isEnemy(whitePiece));
        }
    }

    @Nested
    @DisplayName("isSameColor()")
    class IsSameColorTests {

        @Test
        @DisplayName("Le comportement actuel retourne false")
        void testIsSameColorDefault() {
            assertFalse(whitePiece.isSameColor(blackPiece));
        }
    }

    @Nested
    @DisplayName("canMoveTo()")
    class CanMoveToTests {

        @Test
        @DisplayName("Doit retourner true si le déplacement est autorisé")
        void testCanMoveToLegalPosition() {
            Position from = new Position(1, 4);
            Position to = new Position(2, 4);

            whitePiece.movementStrategy = (position, currentBoard) ->
                    List.of(new Move(from, to));

            board.setPiece(from, whitePiece);

            assertTrue(whitePiece.canMoveTo(from, to, board));
        }

        @Test
        @DisplayName("Doit retourner false si la position de départ est null")
        void testCanMoveToNullFrom() {
            assertFalse(whitePiece.canMoveTo(null, new Position(2, 4), board));
        }

        @Test
        @DisplayName("Doit retourner false si la destination est null")
        void testCanMoveToNullTo() {
            assertFalse(whitePiece.canMoveTo(
                    new Position(1, 4), null, board
            ));
        }

        @Test
        @DisplayName("Doit retourner false si le plateau est null")
        void testCanMoveToNullBoard() {
            assertFalse(whitePiece.canMoveTo(
                    new Position(1, 4), new Position(2, 4), null
            ));
        }

        @Test
        @DisplayName("Doit retourner false si la pièce n'est pas sur la case de départ")
        void testCanMoveToPieceNotAtFrom() {
            Position from = new Position(1, 4);
            Position to = new Position(2, 4);

            assertFalse(whitePiece.canMoveTo(from, to, board));
        }
    }
}
