package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.pieces.Pawn;

public class EnPassantValidator {

    public boolean canCapture(Move move, Board board) {
        return false;
    }

    public Pawn getCapturedPawn(Move move, Board board) {
        return null;
    }
}
