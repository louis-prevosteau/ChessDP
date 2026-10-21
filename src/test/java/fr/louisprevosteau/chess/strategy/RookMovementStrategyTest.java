package fr.louisprevosteau.chess.strategy;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Pawn;
import fr.louisprevosteau.chess.pieces.Rook;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RookMovementStrategyTest {

    private Board board;
    private RookMovementStrategy strategy;

    @BeforeEach
    void setUp() {
        board = new Board();
        strategy = new RookMovementStrategy();
    }

    @Test
    @DisplayName("Une tour au centre peut effectuer 14 déplacements")
    void testRookAtCenterHasFourteenMoves() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Rook(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertEquals(14, moves.size());
        assertTrue(containsMove(moves, 3, 3, 3, 0));
        assertTrue(containsMove(moves, 3, 3, 3, 7));
        assertTrue(containsMove(moves, 3, 3, 0, 3));
        assertTrue(containsMove(moves, 3, 3, 7, 3));
    }

    @Test
    @DisplayName("Une tour peut capturer une pièce adverse")
    void testRookCanCaptureEnemyPiece() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Rook(Color.WHITE));
        board.setPiece(new Position(3, 5), new Pawn(Color.BLACK));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertTrue(containsMove(moves, 3, 3, 3, 5));
        assertFalse(containsMove(moves, 3, 3, 3, 6));
    }

    @Test
    @DisplayName("Une tour ne peut pas capturer une pièce alliée")
    void testRookCannotCaptureFriendlyPiece() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Rook(Color.WHITE));
        board.setPiece(new Position(3, 5), new Pawn(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertFalse(containsMove(moves, 3, 3, 3, 5));
        assertFalse(containsMove(moves, 3, 3, 3, 6));
    }

    @Test
    @DisplayName("Une tour ne peut pas traverser une pièce")
    void testRookCannotJumpOverPiece() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Rook(Color.WHITE));
        board.setPiece(new Position(3, 4), new Pawn(Color.BLACK));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertTrue(containsMove(moves, 3, 3, 3, 4));
        assertFalse(containsMove(moves, 3, 3, 3, 5));
    }

    @Test
    @DisplayName("Une tour ne se déplace pas en diagonale")
    void testRookCannotMoveDiagonally() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Rook(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertFalse(containsMove(moves, 3, 3, 4, 4));
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
