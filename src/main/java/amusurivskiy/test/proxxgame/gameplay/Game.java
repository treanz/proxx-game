package amusurivskiy.test.proxxgame.gameplay;

import amusurivskiy.test.proxxgame.io.InputReader;
import amusurivskiy.test.proxxgame.io.OutputRenderer;
import amusurivskiy.test.proxxgame.model.Board;
import amusurivskiy.test.proxxgame.model.BoardPresentation;
import amusurivskiy.test.proxxgame.model.Position;

public final class Game {

    private final InputReader reader;
    private final OutputRenderer renderer;
    private final Board board;

    public Game(Board board, InputReader reader, OutputRenderer renderer) {
        this.board = board;
        this.reader = reader;
        this.renderer = renderer;
    }

    public void play() {
        GameFinisher gameFinisher = new GameFinisher(board.needToOpen());
        BoardPresentation boardPresentation = new BoardPresentation(board);
        while (!gameFinisher.isFinished()) {
            renderer.draw(boardPresentation);
            Position positionToOpen = reader.readPosition();
            int opened = board.open(positionToOpen);
            if (board.hasBomb(positionToOpen)) {
                gameFinisher.explode();
                System.err.println("You have found the bomb!");
                renderer.drawRevealed(boardPresentation);
                break;
            }
            gameFinisher.cellsOpened(opened);
        }
        if (!gameFinisher.isLoosed()) {
            renderer.draw(boardPresentation);
            System.err.println("You win!");
        }

    }
}
