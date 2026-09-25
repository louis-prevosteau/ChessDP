package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.enums.MoveType;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @Test
    @DisplayName("Le constructeur simple doit initialiser les positions")
    void testConstructorWithPositions() {

        Position from = new Position(1, 4);
        Position to = new Position(3, 4);

        Move move = new Move(from, to);

        assertEquals(from, move.getFrom());
        assertEquals(to, move.getTo());

        assertNull(move.getType());
        assertNull(move.getMovedPiece());
        assertNull(move.getCapturedPiece());
        assertNull(move.getPromotionType());
    }

    @Test
    @DisplayName("Le constructeur avec type doit initialiser le type")
    void testConstructorWithMoveType() {

        Position from = new Position(0, 4);
        Position to = new Position(0, 6);

        Move move = new Move(
                from,
                to,
                MoveType.CASTLE_KINGSIDE
        );

        assertEquals(from, move.getFrom());
        assertEquals(to, move.getTo());
        assertEquals(
                MoveType.CASTLE_KINGSIDE,
                move.getType()
        );
    }

    @Test
    @DisplayName("isCastle() doit retourner true pour un roque")
    void testIsCastleReturnsTrueForCastleMove() {

        Move move = new Move(
                new Position(0, 4),
                new Position(0, 6),
                MoveType.CASTLE_KINGSIDE
        );

        assertTrue(move.isCastle());
    }

    @Test
    @DisplayName("isCastle() doit retourner false pour un coup normal")
    void testIsCastleReturnsFalseForNormalMove() {

        Move move = new Move(
                new Position(1, 4),
                new Position(3, 4),
                MoveType.NORMAL
        );

        assertFalse(move.isCastle());
    }

    @Test
    @DisplayName("isPromotion() doit retourner true pour une promotion")
    void testIsPromotionReturnsTrueForPromotionMove() {

        Move move = new Move(
                new Position(6, 0),
                new Position(7, 0),
                MoveType.PROMOTION
        );

        assertTrue(move.isPromotion());
    }

    @Test
    @DisplayName("isPromotion() doit retourner false pour un coup normal")
    void testIsPromotionReturnsFalseForNormalMove() {

        Move move = new Move(
                new Position(1, 0),
                new Position(2, 0),
                MoveType.NORMAL
        );

        assertFalse(move.isPromotion());
    }

    @Test
    @DisplayName("isEnPassant() doit retourner true pour une prise en passant")
    void testIsEnPassantReturnsTrueForEnPassantMove() {

        Move move = new Move(
                new Position(4, 4),
                new Position(5, 5),
                MoveType.EN_PASSANT
        );

        assertTrue(move.isEnPassant());
    }

    @Test
    @DisplayName("isEnPassant() doit retourner false pour un coup normal")
    void testIsEnPassantReturnsFalseForNormalMove() {

        Move move = new Move(
                new Position(1, 4),
                new Position(2, 4),
                MoveType.NORMAL
        );

        assertFalse(move.isEnPassant());
    }
}
