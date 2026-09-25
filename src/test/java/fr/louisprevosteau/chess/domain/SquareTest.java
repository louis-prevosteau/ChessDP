package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Pawn;
import fr.louisprevosteau.chess.pieces.Piece;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {

    @Test
    @DisplayName("Le constructeur doit initialiser la position")
    void testConstructorInitializesPosition() {
        Position position = new Position(3, 4);

        Square square = new Square(position);

        assertEquals(position, square.getPosition());
    }

    @Test
    @DisplayName("Une case nouvellement créée ne doit contenir aucune pièce")
    void testConstructorInitializesWithoutPiece() {
        Square square = new Square(new Position(3, 4));

        assertNull(square.getPiece());
    }

    @Test
    @DisplayName("setPiece() doit placer une pièce sur la case")
    void testSetPiece() {
        Square square = new Square(new Position(3, 4));
        Piece pawn = new Pawn(Color.WHITE);

        square.setPiece(pawn);

        assertEquals(pawn, square.getPiece());
    }

    @Test
    @DisplayName("Une case contenant une pièce est occupée")
    void testIsOccupiedReturnsTrueWhenPiecePresent() {
        Square square = new Square(new Position(3, 4));
        square.setPiece(new Pawn(Color.WHITE));

        assertTrue(square.isOccupied());
    }

    @Test
    @DisplayName("Une case sans pièce n'est pas occupée")
    void testIsOccupiedReturnsFalseWhenNoPiecePresent() {
        Square square = new Square(new Position(3, 4));

        assertFalse(square.isOccupied());
    }

    @Test
    @DisplayName("Une case sans pièce est vide")
    void testIsEmptyReturnsTrueWhenNoPiecePresent() {
        Square square = new Square(new Position(3, 4));

        assertTrue(square.isEmpty());
    }

    @Test
    @DisplayName("Une case contenant une pièce n'est pas vide")
    void testIsEmptyReturnsFalseWhenPiecePresent() {
        Square square = new Square(new Position(3, 4));
        square.setPiece(new Pawn(Color.BLACK));

        assertFalse(square.isEmpty());
    }

    @Test
    @DisplayName("Une case occupée ne doit pas être vide")
    void testOccupiedAndEmptyAreConsistent() {
        Square square = new Square(new Position(3, 4));
        square.setPiece(new Pawn(Color.WHITE));

        assertTrue(square.isOccupied());
        assertFalse(square.isEmpty());
    }
}
