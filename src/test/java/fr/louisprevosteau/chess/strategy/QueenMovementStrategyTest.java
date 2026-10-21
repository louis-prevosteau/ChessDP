package fr.louisprevosteau.chess.strategy;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Pawn;
import fr.louisprevosteau.chess.pieces.Queen;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueenMovementStrategyTest {

    private Board board;
    private QueenMovementStrategy strategy;

    @BeforeEach
    void setUp() {
        board = new Board();
        strategy = new QueenMovementStrategy();
    }

    @Test
    @DisplayName("Une dame au centre peut effectuer 27 déplacements")
    void testQueenAtCenterHasTwentySevenMoves() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Queen(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertEquals(27, moves.size());

        // Déplacements horizontaux
        assertTrue(containsMove(moves, 3, 3, 3, 0));
        assertTrue(containsMove(moves, 3, 3, 3, 7));

        // Déplacements verticaux
        assertTrue(containsMove(moves, 3, 3, 0, 3));
        assertTrue(containsMove(moves, 3, 3, 7, 3));

        // Déplacements diagonaux
        assertTrue(containsMove(moves, 3, 3, 0, 0));
        assertTrue(containsMove(moves, 3, 3, 6, 6));
    }

    @Test
    @DisplayName("Une dame peut capturer une pièce adverse")
    void testQueenCanCaptureEnemyPiece() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Queen(Color.WHITE));
        board.setPiece(new Position(3, 5), new Pawn(Color.BLACK));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertTrue(containsMove(moves, 3, 3, 3, 5));
        assertFalse(containsMove(moves, 3, 3, 3, 6));
    }

    @Test
    @DisplayName("Une dame ne peut pas capturer une pièce alliée")
    void testQueenCannotCaptureFriendlyPiece() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Queen(Color.WHITE));
        board.setPiece(new Position(5, 5), new Pawn(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertFalse(containsMove(moves, 3, 3, 5, 5));
        assertFalse(containsMove(moves, 3, 3, 6, 6));
    }

    @Test
    @DisplayName("Une dame ne peut pas traverser une pièce")
    void testQueenCannotJumpOverPiece() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Queen(Color.WHITE));
        board.setPiece(new Position(4, 4), new Pawn(Color.BLACK));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertTrue(containsMove(moves, 3, 3, 4, 4));
        assertFalse(containsMove(moves, 3, 3, 5, 5));
    }

    @Test
    @DisplayName("Une dame peut se déplacer horizontalement, verticalement et en diagonale")
    void testQueenMovesInAllDirections() {
        Position position = new Position(3, 3);
        board.setPiece(position, new Queen(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertTrue(containsMove(moves, 3, 3, 3, 4));
        assertTrue(containsMove(moves, 3, 3, 4, 3));
        assertTrue(containsMove(moves, 3, 3, 4, 4));
        assertTrue(containsMove(moves, 3, 3, 2, 2));
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
