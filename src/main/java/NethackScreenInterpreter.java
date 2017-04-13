import locations.Coordinates;
import mapitems.DungeonThing;

public class NethackScreenInterpreter {

    public NethackLevel interpret(char[][] chars) {
        NethackLevel level = new NethackLevel(chars);
        Coordinates heroLocation = getHeroLocation(chars);
        for (int i = 0; i < chars.length; i++) {
            char[] row = chars[i];
            for (int j = 0; j < row.length; j++) {
                level.setThingAt(new Coordinates(i, j), DungeonThing.forCharacter(row[j]));
            }
        }
        level.setHeroLocation(heroLocation);
        return level;
    }

    private Coordinates getHeroLocation(char[][] screen) {
        for (int i = 0; i < screen.length; i++) {
            char[] row = screen[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == '@') {
                    return new Coordinates(i, j);
                }
            }
        }
        return null;
    }

}
