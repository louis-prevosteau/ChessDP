package fr.louisprevosteau.chess.domain;

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
        return false;
    }

    public boolean isSame(Position position) {
        return false;
    }

    public Position offset(int rowOffset, int columnOffset) {
        return null;
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
