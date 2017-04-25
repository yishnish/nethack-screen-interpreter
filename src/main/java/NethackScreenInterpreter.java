import locations.Coordinates;
import mapitems.DungeonThing;

public class NethackScreenInterpreter {

    public NethackLevel interpret(char[][] screenBuffer) {
        NethackLevel level = new NethackLevel(screenBuffer);
        Coordinates heroLocation = getHeroLocation(screenBuffer);
        for (int i = NethackLevel.NUM_TOP_SCREEN_TEXT_ROWS ; i < screenBuffer.length - NethackLevel.NUM_BOTTOM_SCREEN_TEXT_ROWS ; i++) {
            char[] row = screenBuffer[i];
            for (int j = 0; j < row.length; j++) {
                level.setThingAt(new Coordinates(i - NethackLevel.NUM_TOP_SCREEN_TEXT_ROWS, j), DungeonThing.forCharacter(row[j]));
            }
        }
        level.setHeroLocation(heroLocation);
        return level;
    }

    private Coordinates getHeroLocation(char[][] screenBuffer) {
        for (int i = NethackLevel.NUM_TOP_SCREEN_TEXT_ROWS; i < screenBuffer.length - NethackLevel.NUM_BOTTOM_SCREEN_TEXT_ROWS; i++) {
            char[] row = screenBuffer[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == '@') {
                    return new Coordinates(i - NethackLevel.NUM_TOP_SCREEN_TEXT_ROWS, j);
                }
            }
        }
        return Coordinates.UNKNOWN;
    }

}
