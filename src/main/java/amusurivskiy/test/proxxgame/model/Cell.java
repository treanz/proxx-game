package amusurivskiy.test.proxxgame.model;

import java.util.HashSet;
import java.util.Set;

public class Cell {

    final Set<Cell> neighbours = new HashSet<>();
    final boolean isBomb;
    protected boolean isOpened = false;
    private int bombsNear = 0;

    private Cell(boolean isBomb) {
        this.isBomb = isBomb;
    }

    public static Cell empty() {
        return new Cell(false);
    }

    public static Cell bomb() {
        return new Cell(true);
    }

    public int open() {
        int opened = 0;
        if (isOpened) {
            return opened;
        }
        this.isOpened = true;
        if (isBomb) {
            return opened;
        }
        opened++;
        if (getBombsNearCount() == 0) {
            for (Cell c : neighbours) {
                opened = opened + c.open();
            }
        }
        return opened;
    }

    public final boolean isOpened() {
        return isOpened;
    }

    public void addNeighbour(Cell neighbour) {
        this.neighbours.add(neighbour);
        if (neighbour.isBomb) {
            bombsNear++;
        }
    }

    public int getBombsNearCount() {
        return bombsNear;
    }
}
