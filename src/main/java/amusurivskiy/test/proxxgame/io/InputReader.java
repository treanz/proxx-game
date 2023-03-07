package amusurivskiy.test.proxxgame.io;

import amusurivskiy.test.proxxgame.model.BoardSeed;
import amusurivskiy.test.proxxgame.model.Position;

public interface InputReader {

    Position readPosition();

    BoardSeed readBoardSeed();
}
