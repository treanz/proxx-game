package amusurivskiy.test.proxxgame.model;

public class CellPresentation {

    private final Cell cell;

    public CellPresentation(Cell cell) {
        this.cell = cell;
    }

    public String getGameState() {
        if (!cell.isOpened()) {
            return ":";
        }
        int bombsNear = cell.getBombsNearCount();
        return bombsNear > 0 ? String.valueOf(bombsNear) : " ";
    }

    public String getRevealedState() {
        if (cell.isBomb && cell.isOpened) {
            return "X";
        }
        if (cell.isBomb) {
            return "x";
        }
        return String.valueOf(cell.getBombsNearCount());
    }


}
