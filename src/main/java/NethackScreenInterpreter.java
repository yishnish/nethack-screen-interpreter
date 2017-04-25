import locations.Coordinates;
import mapitems.DungeonThing;

import java.util.Arrays;

public class NethackScreenInterpreter {

    public static final int NUM_TOP_SCREEN_TEXT_ROWS = 2;
    public static final int NUM_BOTTOM_SCREEN_TEXT_ROWS = 3;

    public NethackLevel interpret(char[][] screenBuffer) {
        char[][] trimmedScreenBuffer = stripTextRows(screenBuffer);
        NethackLevel level = new NethackLevel(trimmedScreenBuffer);
        Coordinates heroLocation = getHeroLocation(trimmedScreenBuffer);
        for (int i = 0; i < trimmedScreenBuffer.length; i++) {
            char[] row = trimmedScreenBuffer[i];
            for (int j = 0; j < row.length; j++) {
                level.setThingAt(new Coordinates(i, j), DungeonThing.forCharacter(row[j]));
            }
        }
        level.setHeroLocation(heroLocation);
        return level;
    }

    private Coordinates getHeroLocation(char[][] screenBuffer) {
        for (int i = 0; i < screenBuffer.length; i++) {
            char[] row = screenBuffer[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == '@') {
                    return new Coordinates(i, j);
                }
            }
        }
        return Coordinates.UNKNOWN;
    }

    private char[][] stripTextRows(char[][] screenBuffer) {
        int rowCount = screenBuffer.length - NUM_TOP_SCREEN_TEXT_ROWS - NUM_BOTTOM_SCREEN_TEXT_ROWS;
        int columnCount = screenBuffer[0].length;
        char[][] newRow = new char[rowCount][columnCount];
        for (int i = NUM_TOP_SCREEN_TEXT_ROWS; i < screenBuffer.length - NUM_BOTTOM_SCREEN_TEXT_ROWS; i++) {
            newRow[i - NUM_TOP_SCREEN_TEXT_ROWS] = Arrays.copyOf(screenBuffer[i], screenBuffer[i].length);
        }
        return newRow;
    }

}
