package fr.louisprevosteau.chess.strategy;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.King;
import fr.louisprevosteau.chess.pieces.Pawn;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KingMovementStrategyTest {

    private Board board;
    private KingMovementStrategy strategy;

    @BeforeEach
    void setUp() {
        board = new Board();
        strategy = new KingMovementStrategy();
    }

    @Test
    @DisplayName("Un roi au centre peut effectuer huit déplacements")
    void testKingAtCenterHasEightMoves() {
        Position position = new Position(3, 3);
        board.setPiece(position, new King(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertEquals(8, moves.size());
        assertTrue(containsMove(moves, 3, 3, 2, 2));
        assertTrue(containsMove(moves, 3, 3, 2, 3));
        assertTrue(containsMove(moves, 3, 3, 2, 4));
        assertTrue(containsMove(moves, 3, 3, 3, 2));
        assertTrue(containsMove(moves, 3, 3, 3, 4));
        assertTrue(containsMove(moves, 3, 3, 4, 2));
        assertTrue(containsMove(moves, 3, 3, 4, 3));
        assertTrue(containsMove(moves, 3, 3, 4, 4));
    }

    @Test
    @DisplayName("Un roi dans un coin ne peut effectuer que trois déplacements")
    void testKingInCornerHasThreeMoves() {
        Position position = new Position(0, 0);
        board.setPiece(position, new King(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertEquals(3, moves.size());
        assertTrue(containsMove(moves, 0, 0, 0, 1));
        assertTrue(containsMove(moves, 0, 0, 1, 0));
        assertTrue(containsMove(moves, 0, 0, 1, 1));
    }

    @Test
    @DisplayName("Un roi peut capturer une pièce adverse")
    void testKingCanCaptureEnemyPiece() {
        Position position = new Position(3, 3);
        board.setPiece(position, new King(Color.WHITE));
        board.setPiece(new Position(4, 4), new Pawn(Color.BLACK));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertTrue(containsMove(moves, 3, 3, 4, 4));
    }

    @Test
    @DisplayName("Un roi ne peut pas capturer une pièce alliée")
    void testKingCannotCaptureFriendlyPiece() {
        Position position = new Position(3, 3);
        board.setPiece(position, new King(Color.WHITE));
        board.setPiece(new Position(4, 4), new Pawn(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertFalse(containsMove(moves, 3, 3, 4, 4));
    }

    @Test
    @DisplayName("Un roi ne peut pas se déplacer de deux cases")
    void testKingCannotMoveTwoSquares() {
        Position position = new Position(3, 3);
        board.setPiece(position, new King(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertFalse(containsMove(moves, 3, 3, 3, 5));
        assertFalse(containsMove(moves, 3, 3, 5, 3));
    }

    @Test
    @DisplayName("Une position nulle ne produit aucun déplacement")
    void testNullPositionReturnsNoMoves() {
        assertTrue(strategy.getLegalMoves(null, board).isEmpty());
    }

    @Test
    @DisplayName("Un plateau nul ne produit aucun déplacement")
    void testNullBoardReturnsNoMoves() {
        assertTrue(strategy.getLegalMoves(new Position(3, 3), null).isEmpty());
    }

    @Test
    @DisplayName("Une case vide ne produit aucun déplacement")
    void testEmptySquareReturnsNoMoves() {
        assertTrue(strategy.getLegalMoves(new Position(3, 3), board).isEmpty());
    }

    private boolean containsMove(List<Move> moves,
                                 int fromRow, int fromColumn,
                                 int toRow, int toColumn) {
        return moves.stream().anyMatch(move ->
                move.getFrom().getRow() == fromRow
                        && move.getFrom().getColumn() == fromColumn
                        && move.getTo().getRow() == toRow
                        && move.getTo().getColumn() == toColumn);
    }
}
