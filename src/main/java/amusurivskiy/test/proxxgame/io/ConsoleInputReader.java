package amusurivskiy.test.proxxgame.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.NumberUtils;

import amusurivskiy.test.proxxgame.model.BoardSeed;
import amusurivskiy.test.proxxgame.model.Position;

public class ConsoleInputReader implements InputReader {

    private static final Pattern DIGITS_PATTERN = Pattern.compile("\\d{1,3}");
    private final BufferedReader reader;

    public ConsoleInputReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public Position readPosition() {
        int colNumber = readPositiveNumber(reader, "Enter column number to open :(press Q for quit)");
        int rowNumber = readPositiveNumber(reader, "Enter row number to open :(press Q for quit)");
        // we remove offset from provided coordinates to align with array starting index
        return new Position(colNumber - 1, rowNumber - 1);
    }

    @Override
    public BoardSeed readBoardSeed() {
        System.out.println("I want to play a game ...");
        System.out.println("It's called ProXX");
        int width = readPositiveNumber(reader, "Enter a positive width of a board:(press Q for quit)");
        int height = readPositiveNumber(reader, "Enter a positive height of a board:(press Q for quit)");
        int holesCount = readPositiveNumber(reader, "Enter a positive holes count:(press Q for quit)");
        return new BoardSeed(width, height, holesCount);
    }

    private int readPositiveNumber(BufferedReader reader, String message) {
        while (true) {
            System.out.println(message);
            String input;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                input = "Q";
            }
            quitIfNeeded(input);
            Matcher matcher = DIGITS_PATTERN.matcher(input);
            if (matcher.matches()) {
                return NumberUtils.parseNumber(input, Integer.class);
            }
        }
    }

    private void quitIfNeeded(String input) {
        if ("Q".equals(input) || "q".equals(input)) {
            System.exit(0);
        }
    }
}
