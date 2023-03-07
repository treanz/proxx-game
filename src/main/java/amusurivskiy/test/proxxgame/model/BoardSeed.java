package amusurivskiy.test.proxxgame.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class BoardSeed {
    final int width;
    final int height;
    final int holesCount;

    private final Random random = new Random();
    private final Supplier<Position> bombGenerator;

    public BoardSeed(int width, int height, int holesCount) {
        this.width = width;
        this.height = height;
        this.holesCount = holesCount;
        this.bombGenerator = () -> new Position(random.nextInt(width), random.nextInt(height));
    }

    /**
     * Generate random bomb positions
     *
     * @return {@link Set} of unique bomb positions
     */
    public Set<Position> generateBombsPositions() {
        Set<Position> bombs = new HashSet<>(holesCount + 1, 1F); // avoid set resizing
        while (bombs.size() < holesCount) {
            bombs.add(bombGenerator.get());
        }
        return bombs;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
