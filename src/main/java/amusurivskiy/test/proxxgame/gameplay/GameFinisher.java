package amusurivskiy.test.proxxgame.gameplay;

public class GameFinisher {
    private int cellsToOpen;
    private boolean exploded;

    public GameFinisher(int cellsToOpen) {
        this.cellsToOpen = cellsToOpen;
        this.exploded = false;
    }

    public void cellsOpened(int opened) {
        this.cellsToOpen -= opened;
    }

    public void explode() {
        this.exploded = true;
    }

    public boolean isFinished() {
        return isLoosed() || allCellsOpened();
    }

    public boolean isLoosed() {
        return exploded;
    }

    private boolean allCellsOpened() {
        return cellsToOpen < 1;
    }
}
