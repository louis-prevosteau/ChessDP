package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final Color color;
    private final List<Piece> capturedPieces;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.capturedPieces = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public List<Piece> getCapturedPieces() {
        return capturedPieces;
    }

    public void capture(Piece piece) {}
}
