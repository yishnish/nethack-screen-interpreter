import locations.Coordinates;
import mapitems.DungeonThing;
import org.junit.Test;

import static mapitems.DungeonThing.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class NethackScreenInterpreterTest {

    @Test
    public void testSetsTheHeroLocationExplicitlyOnTheLevel() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'@'}});

        assertThat(level.getHeroLocation(), equalTo(new Coordinates(0, 0)));
    }

    @Test
    public void testAddsAMarkerForVacantSpacesOnTheLevelMap() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'.'}});

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(VACANT));
    }

    @Test
    public void testMarksHeroLocationAsUnknownIfItCannotBeFound() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'.'}});

        assertThat(level.getHeroLocation(), equalTo(Coordinates.UNKNOWN));
    }

    @Test
    public void testAddsAMarkerForTheHeroOnTheLevelMap() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'@', '.'}});

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(HERO));
        assertThat(level.thingAt(new Coordinates(0, 1)), not(equalTo(HERO)));
    }

    @Test
    public void testAddsAMarkerForHorizontalWallsOnTheLevelMap() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'-'}});

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(DungeonThing.HORIZONTAL_WALL));
    }

    @Test
    public void testAddsAMarkerForVerticalWallsOnTheLevelMap() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'|'}});

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(VERTICAL_WALL));
    }

    @Test
    public void testAddsAMarkerForStairwayUpOnTheLevelMap() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'<'}});

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(STAIRWAY_UP));
    }

    @Test
    public void testAddsAMarkerForClosedDoorUpOnTheLevelMap() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'+'}});

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(CLOSED_DOOR));
    }

    @Test
    public void testInterpretingADungeonRoom(){

        char[][] dungeonRoomBuffer = new char[][]{
                {'-', '-', '-', '-', '-', '-', '-'},
                {'|', '.', '.', '.', '.', '.', '|'},
                {'|', '.', '.', '.', '.', '<', '+'},
                {'|', '.', '@', '.', '.', '.', '|'},
                {'-', '-', '-', '-', '-', '-', '-'},

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
        assertThat(level.getMap(), equalTo(expectedInterpretedRoom));
    }

}
