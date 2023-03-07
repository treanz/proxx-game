package amusurivskiy.test.proxxgame.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Board {

    public final int width;
    public final int height;
    private final Set<Position> bombsPositions;
    private final Cell[][] grid;

    public Board(BoardSeed seed) {
        this.width = seed.getWidth();
        this.height = seed.getHeight();
        this.bombsPositions = seed.generateBombsPositions();
        this.grid = fillBoardCells(this.bombsPositions, seed.width, seed.height);
        fillGridInfo(this.grid);
    }

    public boolean hasBomb(Position position) {
        return bombsPositions.contains(position);
    }

    public int open(Position position) {
        return getCell(position).open();
    }

    Cell getCell(Position position) {
        if (!isValidPosition(position)) {
            throw new IllegalStateException("fail fast");
        }
        return grid[position.x()][position.y()];
    }

    void fillGridInfo(Cell[][] cells) {
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Position currentPosition = new Position(row, col);
                Cell currentCell = getCell(currentPosition);
                getNeighboursPositionsFor(currentPosition)
                    .stream()
                    .filter(this::isValidPosition)
                    .map(this::getCell)
                    .forEach(currentCell::addNeighbour);
            }
        }
    }

    private Collection<Position> getNeighboursPositionsFor(Position position) {
        Collection<Position> neighbours = new HashSet<>();
        for (int x = position.x() - 1; x <= position.x() + 1; x++) {
            for (int y = position.y() - 1; y <= position.y() + 1; y++) {
                if (x == position.x() && y == position.y()) {
                    continue;
                }
                neighbours.add(new Position(x, y));
            }
        }
        return neighbours;
    }

    /**
     * Creates cells array with empty boxes(cells) except specified bombs
     *
     * @param bombsPositions - provided collection of bomb positions
     * @param width          - defines a width of array
     * @param height         - defines a height of array
     * @return - cells filled either empty box or bomb
     */
    private Cell[][] fillBoardCells(Collection<Position> bombsPositions, int width, int height) {
        Cell[][] cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Position position = new Position(x, y);
                cells[x][y] = bombsPositions.contains(position) ? Cell.bomb() : Cell.empty();
            }
        }
        return cells;
    }


    public int needToOpen() {
        return height * width - bombsPositions.size();
    }

    private boolean isValidPosition(Position position) {
        return position.x() >= 0 && position.x() < width
            && position.y() >= 0 && position.y() < height;
    }
}
