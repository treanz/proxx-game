package amusurivskiy.test.proxxgame;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import amusurivskiy.test.proxxgame.gameplay.Game;
import amusurivskiy.test.proxxgame.io.ConsoleInputReader;
import amusurivskiy.test.proxxgame.io.ConsoleRenderer;
import amusurivskiy.test.proxxgame.io.InputReader;
import amusurivskiy.test.proxxgame.io.OutputRenderer;
import amusurivskiy.test.proxxgame.model.Board;
import amusurivskiy.test.proxxgame.model.BoardSeed;

@Component
@ConditionalOnProperty(prefix = "console", value = "enabled", havingValue = "true")
public class ConsoleGame implements CommandLineRunner {

    @Override
    public void run(String... args) {
        InputReader reader = new ConsoleInputReader();
        BoardSeed seed = reader.readBoardSeed();
        Board board = new Board(seed);
        OutputRenderer renderer = new ConsoleRenderer();
        new Game(board, reader, renderer)
            .play();
    }

}
