package fr.louisprevosteau.chess.rules;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Move;
import fr.louisprevosteau.chess.domain.Position;
import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.history.MoveHistory;
import fr.louisprevosteau.chess.pieces.Pawn;
import fr.louisprevosteau.chess.pieces.Piece;

public class EnPassantValidator {

    private final MoveHistory moveHistory;

    public EnPassantValidator(MoveHistory moveHistory) {
        this.moveHistory = new MoveHistory();
    }

    public boolean canCapture(Move move, Board board) {
        if (move == null || board == null) {
            return false;
        }

        Position from = move.getFrom();
        Position to = move.getTo();

        if (from == null || to == null
                || !from.isValid() || !to.isValid()) {
            return false;
        }

        Piece piece = board.getPiece(from);

        if (!(piece instanceof Pawn movingPawn)) {
            return false;
        }

        // La destination doit être vide.
        if (board.getPiece(to) != null) {
            return false;
        }

        // Le pion doit avancer d'une case en diagonale.
        int direction = movingPawn.getColor() == Color.WHITE ? 1 : -1;

        if (to.getRow() - from.getRow() != direction
                || Math.abs(to.getColumn() - from.getColumn()) != 1) {
            return false;
        }

        Pawn capturedPawn = getCapturedPawn(move, board);

        if (capturedPawn == null
                || capturedPawn.getColor() == movingPawn.getColor()) {
            return false;
        }

        // Le dernier coup doit être le déplacement de deux cases
        // du pion adverse, qui se trouve encore sur la même colonne.
        Move lastMove = moveHistory.getLastMove();

        if (lastMove == null
                || lastMove.getFrom() == null
                || lastMove.getTo() == null) {
            return false;
        }

        Piece lastMovedPiece = board.getPiece(lastMove.getTo());

        if (!(lastMovedPiece instanceof Pawn)
                || lastMovedPiece.getColor() == movingPawn.getColor()) {
            return false;
        }

        int lastMoveDistance = Math.abs(
                lastMove.getTo().getRow() - lastMove.getFrom().getRow()
        );

        return lastMoveDistance == 2
                && lastMove.getTo().equals(
                new Position(from.getRow(), to.getColumn())
        )
                && capturedPawn == lastMovedPiece;
    }

    public Pawn getCapturedPawn(Move move, Board board) {
        if (move == null || board == null
                || move.getFrom() == null || move.getTo() == null
                || !move.getFrom().isValid() || !move.getTo().isValid()) {
            return null;
        }

        Position from = move.getFrom();
        Position to = move.getTo();

        if (Math.abs(to.getColumn() - from.getColumn()) != 1) {
            return null;
        }

        // Le pion capturé se trouve sur la ligne de départ
        // du pion qui effectue la prise et la colonne d'arrivée.
        Piece piece = board.getPiece(
                new Position(from.getRow(), to.getColumn())
        );

        return piece instanceof Pawn pawn ? pawn : null;
    }
}
