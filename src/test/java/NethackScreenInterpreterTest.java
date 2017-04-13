import locations.Coordinates;
import mapitems.DungeonThing;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class NethackScreenInterpreterTest {

    @Test
    public void testInterpretingANethackScreenBufferCreatesANethackDungeonLevel() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel interpretedLevel = interpreter.interpret(new char[][]{{'@'}});

        assertThat(interpretedLevel, not(nullValue()));
    }

    @Test
    public void testSetsTheHeroLocationExplicitlyOnTheLevel() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'@'}});

        assertThat(level.getHeroLocation(), equalTo(new Coordinates(0, 0)));
    }

    @Test
    public void testAddsAMarkerForTheHeroOnTheLevelMap(){
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'@', Character.MIN_VALUE}});

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(DungeonThing.HERO));
        assertThat(level.thingAt(new Coordinates(0, 1)), not(equalTo(DungeonThing.HERO)));
    }

    @Test
    public void testAddsAMarkerForEmptySpacesOnTheLevelMap(){
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'@', Character.MIN_VALUE}});

        assertThat(level.thingAt(new Coordinates(0, 0)), equalTo(DungeonThing.HERO));
        assertThat(level.thingAt(new Coordinates(0, 1)), equalTo(DungeonThing.EMPTY));
    }
}
