import locations.Coordinates;

public class NethackScreenInterpreter {

    public NethackLevel interpret(char[][] chars) {
        NethackLevel level = new NethackLevel(chars);
        level.setHeroLocation(getHeroLocation(chars));
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
