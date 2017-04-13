import locations.Coordinates;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class NethackScreenInterpreterTest {

    @Test
    public void testInterpretingANethackScreenBufferCreatesANethackDungeonLevel() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel interpretedLevel = interpreter.interpret(new char[][]{{},{}});

        assertThat(interpretedLevel, not(nullValue()));
    }

    @Test
    public void testInterpretsAnAmpersandToBeTheHero() {
        NethackScreenInterpreter interpreter = new NethackScreenInterpreter();
        NethackLevel level = interpreter.interpret(new char[][]{{'@'}});

        assertThat(level.getHeroLocation(), equalTo(new Coordinates(0, 0)));
    }
}
