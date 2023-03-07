package amusurivskiy.test.proxxgame.model;

public class BoardPresentation {

    private final CellPresentation[][] cellPresentations;

    public BoardPresentation(Board board) {
        this.cellPresentations = new CellPresentation[board.width][board.height];
        for (int x = 0; x < board.width; x++) {
            for (int y = 0; y < board.height; y++) {
                Cell cell = board.getCell(new Position(x, y));
                cellPresentations[x][y] = new CellPresentation(cell);
            }
        }
    }

    public CellPresentation[][] getCellsPresentations() {
        return cellPresentations;
    }
}
