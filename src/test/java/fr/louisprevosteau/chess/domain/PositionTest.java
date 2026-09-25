package fr.louisprevosteau.chess.domain;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    @DisplayName("Le constructeur doit initialiser ligne et colonne")
    void testConstructor() {
        Position position = new Position(3, 5);

        assertEquals(3, position.getRow());
        assertEquals(5, position.getColumn());
    }

    @Test
    @DisplayName("Une position comprise entre 0 et 7 est valide")
    void testIsValidReturnsTrueForValidPosition() {
        Position position = new Position(4, 6);

        assertTrue(position.isValid());
    }

    @Test
    @DisplayName("Une ligne négative rend la position invalide")
    void testIsValidReturnsFalseForNegativeRow() {
        Position position = new Position(-1, 4);

        assertFalse(position.isValid());
    }

    @Test
    @DisplayName("Une colonne négative rend la position invalide")
    void testIsValidReturnsFalseForNegativeColumn() {
        Position position = new Position(4, -1);

        assertFalse(position.isValid());
    }

    @Test
    @DisplayName("Une ligne supérieure à 7 rend la position invalide")
    void testIsValidReturnsFalseForRowGreaterThanSeven() {
        Position position = new Position(8, 4);

        assertFalse(position.isValid());
    }

    @Test
    @DisplayName("Une colonne supérieure à 7 rend la position invalide")
    void testIsValidReturnsFalseForColumnGreaterThanSeven() {
        Position position = new Position(4, 8);

        assertFalse(position.isValid());
    }

    @Test
    @DisplayName("isSame() doit retourner true pour deux positions identiques")
    void testIsSameReturnsTrueForSameCoordinates() {
        Position first = new Position(2, 3);
        Position second = new Position(2, 3);

        assertTrue(first.isSame(second));
    }

    @Test
    @DisplayName("isSame() doit retourner false pour deux positions différentes")
    void testIsSameReturnsFalseForDifferentCoordinates() {
        Position first = new Position(2, 3);
        Position second = new Position(2, 4);

        assertFalse(first.isSame(second));
    }

    @Test
    @DisplayName("offset() doit retourner une nouvelle position décalée")
    void testOffsetReturnsShiftedPosition() {
        Position position = new Position(4, 4);

        Position offsetPosition = position.offset(-1, 2);

        assertEquals(3, offsetPosition.getRow());
        assertEquals(6, offsetPosition.getColumn());
    }

    @Test
    @DisplayName("offset() ne doit pas modifier la position d'origine")
    void testOffsetDoesNotModifyOriginalPosition() {
        Position position = new Position(4, 4);

        position.offset(1, 1);

        assertEquals(4, position.getRow());
        assertEquals(4, position.getColumn());
    }

    @Test
    @DisplayName("toString() doit contenir la ligne et la colonne")
    void testToString() {
        Position position = new Position(3, 5);

        String result = position.toString();

        assertTrue(result.contains("row=3"));
        assertTrue(result.contains("column=5"));
    }
}
