package fr.louisprevosteau.chess.strategy;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Bishop;
import fr.louisprevosteau.chess.pieces.Pawn;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BishopMovementStrategyTest {

    private Board board;
    private BishopMovementStrategy strategy;

    @BeforeEach
    void setUp() {
        board = new Board();
        strategy = new BishopMovementStrategy();
    }

    @Test
    @DisplayName("Un fou au centre peut se déplacer sur 13 cases")
    void testBishopAtCenterHasThirteenMoves() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Bishop(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertEquals(13, moves.size());
        assertTrue(containsMove(moves, 3, 3, 4, 4));
        assertTrue(containsMove(moves, 3, 3, 5, 5));
        assertTrue(containsMove(moves, 3, 3, 2, 4));
        assertTrue(containsMove(moves, 3, 3, 0, 0));
    }

    @Test
    @DisplayName("Un fou peut se déplacer jusqu'au bord")
    void testBishopCanReachBoardEdge() {
        Position position = new Position(0, 0);
        board.setPiece(position, new Bishop(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertEquals(7, moves.size());
        assertTrue(containsMove(moves, 0, 0, 7, 7));
    }

    @Test
    @DisplayName("Un fou peut capturer une pièce adverse")
    void testBishopCanCaptureEnemyPiece() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Bishop(Color.WHITE));
        board.setPiece(new Position(5, 5), new Pawn(Color.BLACK));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertTrue(containsMove(moves, 3, 3, 5, 5));
        assertFalse(containsMove(moves, 3, 3, 6, 6));
    }

    @Test
    @DisplayName("Un fou ne peut pas capturer une pièce alliée")
    void testBishopCannotCaptureFriendlyPiece() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Bishop(Color.WHITE));
        board.setPiece(new Position(5, 5), new Pawn(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertFalse(containsMove(moves, 3, 3, 5, 5));
        assertFalse(containsMove(moves, 3, 3, 6, 6));
    }

    @Test
    @DisplayName("Un fou ne peut pas traverser une pièce")
    void testBishopCannotJumpOverPiece() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Bishop(Color.WHITE));
        board.setPiece(new Position(4, 4), new Pawn(Color.BLACK));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertTrue(containsMove(moves, 3, 3, 4, 4));
        assertFalse(containsMove(moves, 3, 3, 5, 5));
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
