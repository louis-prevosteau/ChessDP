package fr.louisprevosteau.chess.ui.view;

import fr.louisprevosteau.chess.domain.Board;
import fr.louisprevosteau.chess.domain.Position;

public class BoardView {

    private SquareView[][] squares;

    public BoardView() {
    }

    public void highlight(Position position) {}

    public void clearHighlight() {}

    public void render(Board board) {}

    public void select(Position position) {}

    public void refresh() {}
}
