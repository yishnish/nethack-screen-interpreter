import locations.Coordinates;
import mapitems.DungeonThing;
import org.junit.Test;

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
    public void testAddsAMarkerForVacantSpacesOnTheLevelMap(){
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'.'}});

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(DungeonThing.VACANT));
    }

    @Test
    public void testMarksHeroLocationAsUnknownIfItCannotBeFound(){
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'.'}});

        assertThat(level.getHeroLocation(), equalTo(Coordinates.UNKNOWN));
    }

    @Test
    public void testAddsAMarkerForTheHeroOnTheLevelMap(){
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'@', '.'}});

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(DungeonThing.HERO));
        assertThat(level.thingAt(new Coordinates(0, 1)), not(equalTo(DungeonThing.HERO)));
    }

}
