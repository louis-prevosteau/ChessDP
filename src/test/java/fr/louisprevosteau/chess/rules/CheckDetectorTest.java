package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CheckDetectorTest {

    private CheckDetector checkDetector;
    private Board board;

    @BeforeEach
    void setUp() {
        checkDetector = new CheckDetector();
        board = new Board();
    }

    @Nested
    @DisplayName("isInCheck()")
    class IsInCheckTests {

        @Test
        @DisplayName("Doit retourner false si le roi n'est pas en échec")
        void testKingNotInCheck() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));
            board.setPiece(new Position(7, 4), new King(Color.BLACK));

            assertFalse(checkDetector.isInCheck(board, Color.WHITE));
        }

        @Test
        @DisplayName("Doit retourner true si le roi blanc est attaqué par une tour noire")
        void testWhiteKingInCheckByBlackRook() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));
            board.setPiece(new Position(7, 4), new King(Color.BLACK));
            board.setPiece(new Position(0, 0), new Rook(Color.BLACK));

            assertTrue(checkDetector.isInCheck(board, Color.WHITE));
        }

        @Test
        @DisplayName("Doit retourner true si le roi noir est attaqué par une tour blanche")
        void testBlackKingInCheckByWhiteRook() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));
            board.setPiece(new Position(7, 4), new King(Color.BLACK));
            board.setPiece(new Position(7, 0), new Rook(Color.WHITE));

            assertTrue(checkDetector.isInCheck(board, Color.BLACK));
        }

        @Test
        @DisplayName("Doit retourner false si le roi est protégé par une pièce interposée")
        void testKingProtectedByBlockingPiece() {
            board.setPiece(new Position(0, 4), new King(Color.WHITE));
            board.setPiece(new Position(7, 4), new King(Color.BLACK));
            board.setPiece(new Position(0, 0), new Rook(Color.BLACK));
            board.setPiece(new Position(0, 2), new Bishop(Color.WHITE));

            assertFalse(checkDetector.isInCheck(board, Color.WHITE));
        }

        @Test
        @DisplayName("Doit retourner false si le roi n'est pas présent")
        void testKingNotFound() {
            assertFalse(checkDetector.isInCheck(board, Color.WHITE));
        }

        @Test
        @DisplayName("Doit gérer un plateau null")
        void testNullBoard() {
            assertFalse(checkDetector.isInCheck(null, Color.WHITE));
        }

        @Test
        @DisplayName("Doit gérer une couleur null")
        void testNullColor() {
            assertFalse(checkDetector.isInCheck(board, null));
        }
    }

    @Nested
    @DisplayName("isSquareAttacked()")
    class IsSquareAttackedTests {

        @Test
        @DisplayName("Doit retourner true si une tour attaque une case sur sa ligne")
        void testSquareAttackedByRook() {
            board.setPiece(new Position(0, 0), new Rook(Color.WHITE));

            assertTrue(checkDetector.isSquareAttacked(
                    board, new Position(0, 7), Color.WHITE
            ));
        }

        @Test
        @DisplayName("Doit retourner true si un cavalier attaque une case")
        void testSquareAttackedByKnight() {
            board.setPiece(new Position(0, 1), new Knight(Color.WHITE));

            assertTrue(checkDetector.isSquareAttacked(
                    board, new Position(2, 2), Color.WHITE
            ));
        }

        @Test
        @DisplayName("Doit retourner true si un pion blanc attaque en diagonale")
        void testSquareAttackedByWhitePawn() {
            board.setPiece(new Position(1, 3), new Pawn(Color.WHITE));

            assertTrue(checkDetector.isSquareAttacked(
                    board, new Position(2, 2), Color.WHITE
            ));
        }

        @Test
        @DisplayName("Doit retourner true si un pion noir attaque en diagonale")
        void testSquareAttackedByBlackPawn() {
            board.setPiece(new Position(6, 3), new Pawn(Color.BLACK));

            assertTrue(checkDetector.isSquareAttacked(
                    board, new Position(5, 2), Color.BLACK
            ));
        }

        @Test
        @DisplayName("Doit retourner false si aucune pièce n'attaque la case")
        void testSquareNotAttacked() {
            board.setPiece(new Position(0, 0), new Rook(Color.WHITE));

            assertFalse(checkDetector.isSquareAttacked(
                    board, new Position(2, 2), Color.WHITE
            ));
        }

        @Test
        @DisplayName("Doit retourner false si la couleur attaquante est null")
        void testNullAttackingColor() {
            assertFalse(checkDetector.isSquareAttacked(
                    board, new Position(3, 3), null
            ));
        }

        @Test
        @DisplayName("Doit retourner false si la position est null")
        void testNullPosition() {
            assertFalse(checkDetector.isSquareAttacked(
                    board, null, Color.WHITE
            ));
        }

        @Test
        @DisplayName("Doit retourner false si la position est invalide")
        void testInvalidPosition() {
            assertFalse(checkDetector.isSquareAttacked(
                    board, new Position(8, 8), Color.WHITE
            ));
        }

        @Test
        @DisplayName("Doit gérer un plateau null")
        void testNullBoard() {
            assertFalse(checkDetector.isSquareAttacked(
                    null, new Position(3, 3), Color.WHITE
            ));
        }
    }

    @Nested
    @DisplayName("getAttackers()")
    class GetAttackersTests {

        @Test
        @DisplayName("Doit retourner la tour qui attaque la case")
        void testGetAttackersByRook() {
            Rook rook = new Rook(Color.WHITE);
            board.setPiece(new Position(0, 0), rook);

            List<Piece> attackers =
                    checkDetector.getAttackers(
                            board, new Position(0, 7), Color.WHITE
                    );

            assertNotNull(attackers);
            assertEquals(1, attackers.size());
            assertSame(rook, attackers.get(0));
        }

        @Test
        @DisplayName("Doit retourner plusieurs pièces attaquantes")
        void testGetMultipleAttackers() {
            Rook rook = new Rook(Color.WHITE);
            Knight knight = new Knight(Color.WHITE);

            board.setPiece(new Position(2, 0), rook);
            board.setPiece(new Position(4, 3), knight);

            List<Piece> attackers = checkDetector.getAttackers(
                    board,
                    new Position(2, 2),
                    Color.WHITE
            );

            assertNotNull(attackers);
            assertEquals(2, attackers.size());
            assertTrue(attackers.contains(rook));
            assertTrue(attackers.contains(knight));
        }

        @Test
        @DisplayName("Doit retourner une liste vide si aucune pièce n'attaque la case")
        void testNoAttackers() {
            List<Piece> attackers =
                    checkDetector.getAttackers(
                            board, new Position(3, 3), Color.WHITE
                    );

            assertNotNull(attackers);
            assertTrue(attackers.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une liste vide si le plateau est null")
        void testGetAttackersNullBoard() {
            List<Piece> attackers =
                    checkDetector.getAttackers(
                            null, new Position(3, 3), Color.WHITE
                    );

            assertNotNull(attackers);
            assertTrue(attackers.isEmpty());
        }

        @Test
        @DisplayName("Doit retourner une liste vide si la position est null")
        void testGetAttackersNullPosition() {
            List<Piece> attackers =
                    checkDetector.getAttackers(
                            board, null, Color.WHITE
                    );

            assertNotNull(attackers);
            assertTrue(attackers.isEmpty());
        }

        @Test
        @DisplayName("Ne doit retourner que les attaquants de la couleur demandée")
        void testGetAttackersFiltersByColor() {
            Rook whiteRook = new Rook(Color.WHITE);
            Rook blackRook = new Rook(Color.BLACK);

            board.setPiece(new Position(0, 0), whiteRook);
            board.setPiece(new Position(7, 7), blackRook);

            List<Piece> attackers =
                    checkDetector.getAttackers(
                            board, new Position(0, 7), Color.WHITE
                    );

            assertNotNull(attackers);
            assertTrue(attackers.contains(whiteRook));
            assertFalse(attackers.contains(blackRook));
        }
    }
}
