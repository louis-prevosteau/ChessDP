package fr.louisprevosteau.chess.strategy;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Knight;
import fr.louisprevosteau.chess.pieces.Pawn;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KnightMovementStrategyTest {

    private Board board;
    private KnightMovementStrategy strategy;

    @BeforeEach
    void setUp() {
        board = new Board();
        strategy = new KnightMovementStrategy();
    }

    @Nested
    @DisplayName("Déplacements du cavalier")
    class KnightMovesTests {

        @Test
        @DisplayName("Un cavalier au centre peut effectuer huit déplacements")
        void testKnightCanMoveInEightDirections() {
            Position position = new Position(3, 3);
            board.setPiece(position, new Knight(Color.WHITE));

            List<Move> moves = strategy.getLegalMoves(position, board);

            assertEquals(8, moves.size());

            assertTrue(containsMove(moves, 3, 3, 1, 2));
            assertTrue(containsMove(moves, 3, 3, 1, 4));
            assertTrue(containsMove(moves, 3, 3, 2, 1));
            assertTrue(containsMove(moves, 3, 3, 2, 5));
            assertTrue(containsMove(moves, 3, 3, 4, 1));
            assertTrue(containsMove(moves, 3, 3, 4, 5));
            assertTrue(containsMove(moves, 3, 3, 5, 2));
            assertTrue(containsMove(moves, 3, 3, 5, 4));
        }

        @Test
        @DisplayName("Un cavalier dans un coin ne peut effectuer que deux déplacements")
        void testKnightInCornerHasTwoMoves() {
            Position position = new Position(0, 0);
            board.setPiece(position, new Knight(Color.WHITE));

            List<Move> moves = strategy.getLegalMoves(position, board);

            assertEquals(2, moves.size());

            assertTrue(containsMove(moves, 0, 0, 1, 2));
            assertTrue(containsMove(moves, 0, 0, 2, 1));
        }

        @Test
        @DisplayName("Un cavalier près du bord ne sort pas de l'échiquier")
        void testKnightDoesNotMoveOutsideBoard() {
            Position position = new Position(0, 4);
            board.setPiece(position, new Knight(Color.WHITE));

            List<Move> moves = strategy.getLegalMoves(position, board);

            assertEquals(4, moves.size());

            for (Move move : moves) {
                assertTrue(move.getTo().isValid());
            }
        }
    }

    @Nested
    @DisplayName("Cases occupées et captures")
    class OccupiedSquaresTests {

        @Test
        @DisplayName("Un cavalier peut capturer une pièce adverse")
        void testKnightCanCaptureEnemyPiece() {
            Position position = new Position(3, 3);
            board.setPiece(position, new Knight(Color.WHITE));
            board.setPiece(new Position(5, 4), new Pawn(Color.BLACK));

            List<Move> moves = strategy.getLegalMoves(position, board);

            assertTrue(containsMove(moves, 3, 3, 5, 4));
        }

        @Test
        @DisplayName("Un cavalier ne peut pas capturer une pièce de sa couleur")
        void testKnightCannotCaptureFriendlyPiece() {
            Position position = new Position(3, 3);
            board.setPiece(position, new Knight(Color.WHITE));
            board.setPiece(new Position(5, 4), new Pawn(Color.WHITE));

            List<Move> moves = strategy.getLegalMoves(position, board);

            assertFalse(containsMove(moves, 3, 3, 5, 4));
        }

        @Test
        @DisplayName("Un cavalier peut se déplacer vers une case vide")
        void testKnightCanMoveToEmptySquare() {
            Position position = new Position(3, 3);
            board.setPiece(position, new Knight(Color.WHITE));

            List<Move> moves = strategy.getLegalMoves(position, board);

            assertTrue(containsMove(moves, 3, 3, 5, 4));
        }
    }

    @Nested
    @DisplayName("Positions invalides")
    class InvalidPositionTests {

        @Test
        @DisplayName("Une position nulle ne produit aucun déplacement")
        void testNullPositionReturnsNoMoves() {
            List<Move> moves = strategy.getLegalMoves(null, board);

            assertNotNull(moves);
            assertTrue(moves.isEmpty());
        }

        @Test
        @DisplayName("Un plateau nul ne produit aucun déplacement")
        void testNullBoardReturnsNoMoves() {
            List<Move> moves = strategy.getLegalMoves(
                    new Position(3, 3),
                    null
            );

            assertNotNull(moves);
            assertTrue(moves.isEmpty());
        }

        @Test
        @DisplayName("Une case vide ne produit aucun déplacement")
        void testEmptySquareReturnsNoMoves() {
            Position position = new Position(3, 3);

            List<Move> moves = strategy.getLegalMoves(position, board);

            assertNotNull(moves);
            assertTrue(moves.isEmpty());
        }
    }

    private boolean containsMove(
            List<Move> moves,
            int fromRow,
            int fromColumn,
            int toRow,
            int toColumn
    ) {
        return moves.stream().anyMatch(move ->
                move.getFrom().getRow() == fromRow
                        && move.getFrom().getColumn() == fromColumn
                        && move.getTo().getRow() == toRow
                        && move.getTo().getColumn() == toColumn
        );
    }
}
