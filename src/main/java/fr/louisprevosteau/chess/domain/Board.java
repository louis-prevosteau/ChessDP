package fr.louisprevosteau.chess.domain;

import fr.louisprevosteau.chess.enums.Color;
import fr.louisprevosteau.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final Square[][] squares;
    private final List<Move> movesHistory;

    public Board() {
        squares = new Square[8][8];

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                squares[row][column] =
                        new Square(
                                new Position(
                                        row,
                                        column
                                )
                        );
            }
        }
        movesHistory = new ArrayList<>();
    }

    public void initialize() {
        setPiece(new Position(0, 0), new Rook(Color.WHITE));
        setPiece(new Position(0, 1), new Knight(Color.WHITE));
        setPiece(new Position(0, 2), new Bishop(Color.WHITE));
        setPiece(new Position(0, 3), new Queen(Color.WHITE));
        setPiece(new Position(0, 4), new King(Color.WHITE));
        setPiece(new Position(0, 5), new Bishop(Color.WHITE));
        setPiece(new Position(0, 6), new Knight(Color.WHITE));
        setPiece(new Position(0, 7), new Rook(Color.WHITE));

        for (int column = 0; column < 8; column++) {
            setPiece(
                    new Position(1, column),
                    new Pawn(Color.WHITE)
            );
        }

        setPiece(new Position(7, 0), new Rook(Color.BLACK));
        setPiece(new Position(7, 1), new Knight(Color.BLACK));
        setPiece(new Position(7, 2), new Bishop(Color.BLACK));
        setPiece(new Position(7, 3), new Queen(Color.BLACK));
        setPiece(new Position(7, 4), new King(Color.BLACK));
        setPiece(new Position(7, 5), new Bishop(Color.BLACK));
        setPiece(new Position(7, 6), new Knight(Color.BLACK));
        setPiece(new Position(7, 7), new Rook(Color.BLACK));

        for (int column = 0; column < 8; column++) {
            setPiece(
                    new Position(6, column),
                    new Pawn(Color.BLACK)
            );
        }
    }

    public Square getSquare(Position position) {
        return squares[position.getRow()][position.getColumn()];
    }

    public Piece getPiece(Position position) {
        if (position == null) {
            throw new IllegalArgumentException(
                    "Position cannot be null"
            );
        }

        if (!position.isValid()) {
            throw new IllegalArgumentException(
                    "Invalid position: " + position
            );
        }
        return squares[position.getRow()][position.getColumn()].getPiece();
    }

    public void setPiece(Position position, Piece piece) {
        squares[position.getRow()][position.getColumn()].setPiece(piece);
    }

    public Piece removePiece(Position position) {
        Piece piece = getPiece(position);
        setPiece(position, null);
        return piece;
    }

    public void movePiece(Move move) {
        Piece piece = removePiece(move.getFrom());
        setPiece(
                move.getTo(),
                piece
        );
        movesHistory.add(move);
    }

    public boolean isOccupied(Position position) {
        return squares[position.getRow()][position.getColumn()].isOccupied();
    }

    public boolean isEmpty(Position position) {
        return squares[position.getRow()][position.getColumn()].isEmpty();
    }

    public List<Piece> getPieces(Color color) {
        List<Piece> pieces = new ArrayList<>();
        for (Square[] row : squares) {
            for (Square square : row) {
                if (square.isOccupied() && square.getPiece().getColor().equals(color))
                    pieces.add(square.getPiece());
            }
        }
        return pieces;
    }

    public Position findKing(Color color) {
        for (Square[] row : squares) {
            for (Square square : row) {
                if (square.isOccupied()
                        && square.getPiece() instanceof King
                        && square.getPiece().getColor() == color) {
                    return square.getPosition();
                }
            }
        }
        return null;
    }

    public Move getLastMove() {
        return movesHistory.getLast();
    }

    public List<Move> getMoveHistory() {
        return movesHistory;
    }

    public Board copy() {
        Board copy = new Board();

        for (Square[] row : squares) {
            for (Square square : row) {
                if (square.isOccupied()) {
                    copy.setPiece(
                            square.getPosition(),
                            square.getPiece()
                    );
                }
            }
        }
        return copy;
    }

    public void reset() {
        for (Square[] row : squares) {
            for (Square square : row) {
                square.setPiece(null);
            }
        }
        movesHistory.clear();
        initialize();
    }
}
