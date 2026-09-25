package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.enums.GameStatus;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameResultTest {

    @Test
    @DisplayName("Le constructeur avec statut doit initialiser le statut")
    void testConstructorWithStatus() {

        GameResult result =
                new GameResult(GameStatus.DRAW);

        assertEquals(
                GameStatus.DRAW,
                result.getStatus()
        );

        assertNull(result.getWinner());
        assertNull(result.getReason());
    }

    @Test
    @DisplayName("Le constructeur avec gagnant doit initialiser le statut et le gagnant")
    void testConstructorWithWinner() {

        GameResult result =
                new GameResult(
                        GameStatus.CHECKMATE,
                        Color.WHITE
                );

        assertEquals(
                GameStatus.CHECKMATE,
                result.getStatus()
        );

        assertEquals(
                Color.WHITE,
                result.getWinner()
        );
    }

    @Test
    @DisplayName("isDraw() doit retourner true lorsque la partie est nulle")
    void testIsDrawReturnsTrueForDraw() {

        GameResult result =
                new GameResult(GameStatus.DRAW);

        assertTrue(result.isDraw());
    }

    @Test
    @DisplayName("isDraw() doit retourner false lorsqu'il y a un gagnant")
    void testIsDrawReturnsFalseForWin() {

        GameResult result =
                new GameResult(
                        GameStatus.CHECKMATE,
                        Color.BLACK
                );

        assertFalse(result.isDraw());
    }

    @Test
    @DisplayName("hasWinner() doit retourner true lorsqu'un gagnant existe")
    void testHasWinnerReturnsTrue() {

        GameResult result =
                new GameResult(
                        GameStatus.CHECKMATE,
                        Color.WHITE
                );

        assertTrue(result.hasWinner());
    }

    @Test
    @DisplayName("hasWinner() doit retourner false lorsqu'il n'y a pas de gagnant")
    void testHasWinnerReturnsFalse() {

        GameResult result =
                new GameResult(GameStatus.DRAW);

        assertFalse(result.hasWinner());
    }

    @Test
    @DisplayName("Une partie nulle ne doit pas avoir de gagnant")
    void testDrawHasNoWinner() {

        GameResult result =
                new GameResult(GameStatus.DRAW);

        assertTrue(result.isDraw());
        assertFalse(result.hasWinner());
        assertNull(result.getWinner());
    }
}
