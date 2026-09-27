package fr.louisprevosteau.chess.domain;

import java.util.Objects;

public class Position {

    private final int row, column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isValid() {
        return (row >= 0 && row <= 7) && (column >= 0 && column <= 7);
    }

    public boolean isSame(Position position) {
        return position != null && position.row == row && position.column == column;
    }

    public Position offset(int rowOffset, int columnOffset) {
        return new Position(
                row + rowOffset,
                column + columnOffset
        );
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Position position)) {
            return false;
        }

        return row == position.row
                && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
