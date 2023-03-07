package amusurivskiy.test.proxxgame.io;

import amusurivskiy.test.proxxgame.model.BoardPresentation;

public interface OutputRenderer {
    void draw(BoardPresentation cellViews);

    void drawRevealed(BoardPresentation cellViews);
}
