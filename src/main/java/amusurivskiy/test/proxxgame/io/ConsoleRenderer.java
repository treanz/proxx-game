package amusurivskiy.test.proxxgame.io;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import amusurivskiy.test.proxxgame.model.BoardPresentation;
import amusurivskiy.test.proxxgame.model.CellPresentation;

public class ConsoleRenderer implements OutputRenderer {

    @Override
    public void draw(BoardPresentation cellViews) {
        System.out.println("\n\n--- Current positions ---");
        drawPresentations(cellViews.getCellsPresentations(), CellPresentation::getGameState);
    }

    @Override
    public void drawRevealed(BoardPresentation cellViews) {
        System.out.println("\n\n--- Final positions ---");
        drawPresentations(cellViews.getCellsPresentations(), CellPresentation::getRevealedState);

    }

    private void drawPresentations(CellPresentation[][] cellPresentations, Function<CellPresentation, String> how) {
        System.out.printf("%4s ", "col→");
        int width = cellPresentations.length;
        int height = cellPresentations[0].length;
        for (int colIndex = 0; colIndex < width; colIndex++) {
            System.out.printf("%4s ", colIndex + 1);
        }
        System.out.println();
        System.out.print(IntStream.iterate(0, i -> i < width, i -> i + 1)
            .mapToObj(i -> String.format("%4s ", "-"))
            .collect(Collectors.joining("", String.format("%5s", "row↓"), "")));

        for (int y = 0; y < height; y++) {
            System.out.printf("\n%4s|", y + 1);
            for (int x = 0; x < width; x++) {
                CellPresentation cellPresentation = cellPresentations[x][y];
                System.out.printf("%4s ", how.apply(cellPresentation));
            }
        }
        System.out.println();

    }

}
