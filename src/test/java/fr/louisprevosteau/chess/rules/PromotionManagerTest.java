package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.PromotionType;
import fr.louisprevosteau.chess.pieces.Bishop;
import fr.louisprevosteau.chess.pieces.Knight;
import fr.louisprevosteau.chess.pieces.Pawn;
import fr.louisprevosteau.chess.pieces.Piece;
import fr.louisprevosteau.chess.pieces.Queen;
import fr.louisprevosteau.chess.pieces.Rook;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PromotionManagerTest {

    private PromotionManager promotionManager;

    @BeforeEach
    void setUp() {
        promotionManager = new PromotionManager();
    }

    @Nested
    @DisplayName("canPromote()")
    class CanPromoteTests {

        @Test
        @DisplayName("Doit retourner true pour un pion blanc sur la dernière rangée")
        void testWhitePawnOnLastRank() {
            Pawn pawn = new Pawn(Color.WHITE);
            Position position = new Position(7, 0);

            assertTrue(promotionManager.canPromote(pawn, position));
        }

        @Test
        @DisplayName("Doit retourner true pour un pion noir sur la dernière rangée")
        void testBlackPawnOnLastRank() {
            Pawn pawn = new Pawn(Color.BLACK);
            Position position = new Position(0, 7);

            assertTrue(promotionManager.canPromote(pawn, position));
        }

        @Test
        @DisplayName("Doit retourner false pour un pion blanc avant la dernière rangée")
        void testWhitePawnBeforeLastRank() {
            Pawn pawn = new Pawn(Color.WHITE);
            Position position = new Position(6, 0);

            assertFalse(promotionManager.canPromote(pawn, position));
        }

        @Test
        @DisplayName("Doit retourner false pour un pion noir avant la dernière rangée")
        void testBlackPawnBeforeLastRank() {
            Pawn pawn = new Pawn(Color.BLACK);
            Position position = new Position(1, 7);

            assertFalse(promotionManager.canPromote(pawn, position));
        }

        @Test
        @DisplayName("Doit retourner false si le pion est null")
        void testNullPawn() {
            assertFalse(
                    promotionManager.canPromote(null, new Position(7, 0))
            );
        }

        @Test
        @DisplayName("Doit retourner false si la position est null")
        void testNullPosition() {
            assertFalse(
                    promotionManager.canPromote(
                            new Pawn(Color.WHITE), null
                    )
            );
        }

        @Test
        @DisplayName("Doit retourner false si la position est invalide")
        void testInvalidPosition() {
            assertFalse(
                    promotionManager.canPromote(
                            new Pawn(Color.WHITE), new Position(8, 0)
                    )
            );
        }
    }

    @Nested
    @DisplayName("promote()")
    class PromoteTests {

        @Test
        @DisplayName("Doit promouvoir un pion blanc en dame")
        void testPromoteToQueen() {
            Piece piece = promotionManager.promote(
                    new Pawn(Color.WHITE), PromotionType.QUEEN
            );

            assertInstanceOf(Queen.class, piece);
            assertEquals(Color.WHITE, piece.getColor());
        }

        @Test
        @DisplayName("Doit promouvoir un pion noir en tour")
        void testPromoteToRook() {
            Piece piece = promotionManager.promote(
                    new Pawn(Color.BLACK), PromotionType.ROOK
            );

            assertInstanceOf(Rook.class, piece);
            assertEquals(Color.BLACK, piece.getColor());
        }

        @Test
        @DisplayName("Doit promouvoir un pion blanc en fou")
        void testPromoteToBishop() {
            Piece piece = promotionManager.promote(
                    new Pawn(Color.WHITE), PromotionType.BISHOP
            );

            assertInstanceOf(Bishop.class, piece);
            assertEquals(Color.WHITE, piece.getColor());
        }

        @Test
        @DisplayName("Doit promouvoir un pion noir en cavalier")
        void testPromoteToKnight() {
            Piece piece = promotionManager.promote(
                    new Pawn(Color.BLACK), PromotionType.KNIGHT
            );

            assertInstanceOf(Knight.class, piece);
            assertEquals(Color.BLACK, piece.getColor());
        }

        @Test
        @DisplayName("Doit retourner null si le pion est null")
        void testPromoteNullPawn() {
            assertNull(
                    promotionManager.promote(null, PromotionType.QUEEN)
            );
        }

        @Test
        @DisplayName("Doit retourner null si le type de promotion est null")
        void testPromoteNullType() {
            assertNull(
                    promotionManager.promote(
                            new Pawn(Color.WHITE), null
                    )
            );
        }
    }

    @Nested
    @DisplayName("getPromotionChoices()")
    class GetPromotionChoicesTests {

        @Test
        @DisplayName("Doit retourner les quatre choix de promotion")
        void testGetPromotionChoices() {
            List<PromotionType> choices =
                    promotionManager.getPromotionChoices();

            assertNotNull(choices);
            assertEquals(4, choices.size());
            assertTrue(choices.contains(PromotionType.QUEEN));
            assertTrue(choices.contains(PromotionType.ROOK));
            assertTrue(choices.contains(PromotionType.BISHOP));
            assertTrue(choices.contains(PromotionType.KNIGHT));
        }
    }
}