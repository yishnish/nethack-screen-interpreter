import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class NethackLevelTest {

    @Test
    public void testLevelTakesOnTheDimensionsOfTheScreenBufferItIsCreatedWith() {
        char[][] screenBuffer = new char[26][80];
        NethackLevel level = new NethackLevel(screenBuffer);
        assertThat(level.getNumRows(), equalTo(screenBuffer.length));
        assertThat(level.getNumColumns(), equalTo(screenBuffer[0].length));
    }
}