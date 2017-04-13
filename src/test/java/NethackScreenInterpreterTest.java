import org.junit.Test;

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
}
