package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.King;
import fr.louisprevosteau.chess.pieces.Pawn;
import fr.louisprevosteau.chess.pieces.Piece;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("Une case vide ne doit contenir aucune pièce")
    void testEmptyBoard() {

        Position position = new Position(3, 3);

        assertNull(board.getPiece(position));
    }

    @Test
    @DisplayName("setPiece() doit placer une pièce")
    void testSetPiece() {

        Position position = new Position(3, 3);
        Piece pawn = new Pawn(Color.WHITE);

        board.setPiece(position, pawn);

        assertEquals(
                pawn,
                board.getPiece(position)
        );
    }

    @Test
    @DisplayName("removePiece() doit retirer la pièce")
    void testRemovePiece() {

        Position position = new Position(3, 3);
        Piece pawn = new Pawn(Color.WHITE);

        board.setPiece(position, pawn);

        Piece removed =
                board.removePiece(position);

        assertEquals(pawn, removed);
        assertNull(board.getPiece(position));
    }

    @Test
    @DisplayName("isOccupied() doit retourner true lorsqu'une pièce est présente")
    void testIsOccupied() {

        Position position = new Position(4, 4);

        board.setPiece(
                position,
                new Pawn(Color.BLACK)
        );

        assertTrue(
                board.isOccupied(position)
        );
    }

    @Test
    @DisplayName("isEmpty() doit retourner true lorsqu'aucune pièce n'est présente")
    void testIsEmpty() {

        Position position = new Position(4, 4);

        assertTrue(
                board.isEmpty(position)
        );
    }

    @Test
    @DisplayName("movePiece() doit déplacer la pièce")
    void testMovePiece() {

        Position from =
                new Position(1, 0);

        Position to =
                new Position(2, 0);

        Piece pawn =
                new Pawn(Color.WHITE);

        board.setPiece(from, pawn);

        Move move =
                new Move(from, to);

        board.movePiece(move);

        assertNull(
                board.getPiece(from)
        );

        assertEquals(
                pawn,
                board.getPiece(to)
        );
    }

    @Test
    @DisplayName("findKing() doit retrouver le roi blanc")
    void testFindWhiteKing() {

        Position kingPosition =
                new Position(0, 4);

        board.setPiece(
                kingPosition,
                new King(Color.WHITE)
        );

        assertEquals(
                kingPosition,
                board.findKing(Color.WHITE)
        );
    }

    @Test
    @DisplayName("getPieces() doit retourner toutes les pièces d'une couleur")
    void testGetPiecesByColor() {

        board.setPiece(
                new Position(0, 0),
                new Pawn(Color.WHITE)
        );

        board.setPiece(
                new Position(0, 1),
                new Pawn(Color.WHITE)
        );

        board.setPiece(
                new Position(7, 7),
                new Pawn(Color.BLACK)
        );

        assertEquals(
                2,
                board.getPieces(Color.WHITE).size()
        );

        assertEquals(
                1,
                board.getPieces(Color.BLACK).size()
        );
    }

    @Test
    @DisplayName("Un déplacement doit être ajouté à l'historique")
    void testMoveHistory() {

        Position from =
                new Position(1, 0);

        Position to =
                new Position(2, 0);

        Piece pawn =
                new Pawn(Color.WHITE);

        board.setPiece(from, pawn);

        Move move =
                new Move(from, to);

        board.movePiece(move);

        assertEquals(
                move,
                board.getLastMove()
        );
    }

    @Test
    @DisplayName("copy() doit créer un nouveau plateau indépendant")
    void testCopyBoard() {

        board.setPiece(
                new Position(0, 0),
                new Pawn(Color.WHITE)
        );

        Board copy = board.copy();

        copy.removePiece(
                new Position(0, 0)
        );

        assertNotNull(
                board.getPiece(
                        new Position(0, 0)
                )
        );
    }
}
