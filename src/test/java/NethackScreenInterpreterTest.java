import locations.Coordinates;
import mapitems.DungeonThing;
import org.junit.Test;

import static java.lang.Character.MIN_VALUE;
import static mapitems.DungeonThing.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NethackScreenInterpreterTest {

    private static final char[] BLANK_ROW =  {MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE, MIN_VALUE};

    private static char[][] aMinimalLevelWithOneCharacterRowOf(char oneCharacter) {
        return new char[][]{{'a'}, {'a'}, {oneCharacter}, {'a'}, {'a'}, {'a'}};
    }

    @Test
    public void testSetsTheHeroLocationExplicitlyOnTheLevel() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(aMinimalLevelWithOneCharacterRowOf('@'));

        assertThat(level.getHeroLocation(), equalTo(new Coordinates(0, 0)));
    }

    @Test
    public void testAddsAMarkerForVacantSpacesOnTheLevelMap() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(aMinimalLevelWithOneCharacterRowOf('.'));

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(VACANT));
    }

    @Test
    public void testMarksHeroLocationAsUnknownIfItCannotBeFound() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(aMinimalLevelWithOneCharacterRowOf('.'));

        assertThat(level.getHeroLocation(), equalTo(Coordinates.UNKNOWN));
    }

    @Test
    public void testAddsAMarkerForTheHeroOnTheLevelMap() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(aMinimalLevelWithOneCharacterRowOf('@'));

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(HERO));
    }

    @Test
    public void testAddsAMarkerForHorizontalWallsOnTheLevelMap() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(aMinimalLevelWithOneCharacterRowOf('-'));

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(DungeonThing.HORIZONTAL_WALL));
    }

    @Test
    public void testAddsAMarkerForVerticalWallsOnTheLevelMap() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(aMinimalLevelWithOneCharacterRowOf('|'));

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(VERTICAL_WALL));
    }

    @Test
    public void testAddsAMarkerForStairwayUpOnTheLevelMap() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(aMinimalLevelWithOneCharacterRowOf('<'));

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(STAIRWAY_UP));
    }

    @Test
    public void testAddsAMarkerForClosedDoorUpOnTheLevelMap() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(aMinimalLevelWithOneCharacterRowOf('+'));

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(CLOSED_DOOR));
    }

    @Test
    public void testInterpretingADungeonRoom(){

        char[][] dungeonRoomBuffer = new char[][]{
                BLANK_ROW,
                BLANK_ROW,
                {'-', '-', '-', '-', '-', '-', '-'},
                {'|', '.', '.', '.', '.', '.', '|'},
                {'|', '.', '.', '.', '.', '<', '+'},
                {'|', '.', '@', '.', '.', '.', '|'},
                {'-', '-', '-', '-', '-', '-', '-'},
                BLANK_ROW,
                BLANK_ROW,
                BLANK_ROW,
        };

        DungeonThing[][] expectedInterpretedRoom = new DungeonThing[][]{
                {HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL},
                {VERTICAL_WALL, VACANT, VACANT, VACANT, VACANT, VACANT, VERTICAL_WALL},
                {VERTICAL_WALL, VACANT, VACANT, VACANT, VACANT, STAIRWAY_UP, CLOSED_DOOR},
                {VERTICAL_WALL, VACANT, HERO, VACANT, VACANT, VACANT, VERTICAL_WALL},
                {HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL},
        };

        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(dungeonRoomBuffer);
        assertThat(level.getDungeonMap(), equalTo(expectedInterpretedRoom));
    }
    
    @Test
    public void testIgnoresTheTextInTheTopTwoAndBottomThreeRowsOfTheScreen_forNow() {

        char[] textRow1 = "1234567".toCharArray();
        char[] textRow2 = "1234567".toCharArray();
        char[] textRow3 = "1234567".toCharArray();
        char[] textRow4 = "1234567".toCharArray();
        char[] textRow5 = "1234567".toCharArray();

        char[][] dungeonRoomBuffer = new char[][]{
                textRow1,
                textRow2,
                {'-', '-', '-', '-', '-', '-', '-'},
                {'|', '.', '.', '.', '.', '.', '|'},
                {'|', '.', '.', '.', '.', '<', '+'},
                {'|', '.', '@', '.', '.', '.', '|'},
                {'-', '-', '-', '-', '-', '-', '-'},
                textRow3,
                textRow4,
                textRow5,

        };

        DungeonThing[][] expectedInterpretedRoom = new DungeonThing[][]{
                {HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL},
                {VERTICAL_WALL, VACANT, VACANT, VACANT, VACANT, VACANT, VERTICAL_WALL},
                {VERTICAL_WALL, VACANT, VACANT, VACANT, VACANT, STAIRWAY_UP, CLOSED_DOOR},
                {VERTICAL_WALL, VACANT, HERO, VACANT, VACANT, VACANT, VERTICAL_WALL},
                {HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL, HORIZONTAL_WALL},
        };

        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(dungeonRoomBuffer);
        assertThat(level.getDungeonMap(), equalTo(expectedInterpretedRoom));
    }

}
