package fr.louisprevosteau.chess.strategy;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Pawn;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PawnMovementStrategyTest {

    private PawnMovementStrategy strategy;
    private Board board;

    @BeforeEach
    void setUp() {
        strategy = new PawnMovementStrategy();
        board = new Board();
    }

    @Test
    @DisplayName("Un pion blanc peut avancer d'une case")
    void testWhitePawnMovesOneSquareForward() {
        Position position = new Position(1, 4);
        board.setPiece(position, new Pawn(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);
        assertTrue(containsMove(moves, 1, 4, 2, 4));
    }

    @Test
    @DisplayName("Un pion noir peut avancer d'une case")
    void testBlackPawnMovesOneSquareForward() {
        Position position = new Position(6, 4);
        board.setPiece(position, new Pawn(Color.BLACK));

        List<Move> moves = strategy.getLegalMoves(position, board);
        assertTrue(containsMove(moves, 6, 4, 5, 4));
    }

    @Test
    @DisplayName("Un pion peut avancer de deux cases depuis sa position initiale")
    void testPawnMovesTwoSquaresFromStartingPosition() {
        Position whitePosition = new Position(1, 4);
        board.setPiece(whitePosition, new Pawn(Color.WHITE));

        List<Move> moves =
                strategy.getLegalMoves(whitePosition, board);

        assertTrue(containsMove(moves, 1, 4, 3, 4));
    }

    @Test
    @DisplayName("Un pion ne peut pas avancer si la case devant lui est occupée")
    void testPawnCannotMoveForwardWhenBlocked() {
        Position position = new Position(1, 4);
        board.setPiece(position, new Pawn(Color.WHITE));
        board.setPiece(
                new Position(2, 4),
                new Pawn(Color.BLACK)
        );

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertTrue(moves.isEmpty());
    }

    @Test
    @DisplayName("Un pion ne peut pas avancer de deux cases si la case intermédiaire est occupée")
    void testPawnCannotMoveTwoSquaresWhenIntermediateSquareIsBlocked() {
        Position position = new Position(1, 4);
        board.setPiece(position, new Pawn(Color.WHITE));
        board.setPiece(
                new Position(2, 4),
                new Pawn(Color.BLACK)
        );

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertFalse(containsMove(moves, 1, 4, 3, 4));
    }

    @Test
    @DisplayName("Un pion peut capturer une pièce adverse en diagonale")
    void testPawnCanCaptureOpponentDiagonally() {
        Position position = new Position(1, 4);
        board.setPiece(position, new Pawn(Color.WHITE));
        board.setPiece(
                new Position(2, 3),
                new Pawn(Color.BLACK)
        );

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertTrue(containsMove(moves, 1, 4, 2, 3));
    }

    @Test
    @DisplayName("Un pion ne peut pas capturer une pièce de sa couleur")
    void testPawnCannotCaptureSameColorPiece() {
        Position position = new Position(1, 4);
        board.setPiece(position, new Pawn(Color.WHITE));
        board.setPiece(
                new Position(2, 3),
                new Pawn(Color.WHITE)
        );

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertFalse(containsMove(moves, 1, 4, 2, 3));
    }

    @Test
    @DisplayName("Un pion ne peut pas avancer hors du plateau")
    void testPawnCannotMoveOutsideBoard() {
        Position position = new Position(7, 4);
        board.setPiece(position, new Pawn(Color.WHITE));

        List<Move> moves = strategy.getLegalMoves(position, board);

        assertTrue(moves.isEmpty());
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
