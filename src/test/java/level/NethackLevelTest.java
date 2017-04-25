package level;

import locations.Coordinates;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class NethackLevelTest {

    @Test
    public void testLevelTakesOnTheDimensionsOfTheScreenBufferItIsCreatedWith() {
        char[][] screenBuffer = new char[24][80];
        NethackLevel level = new NethackLevel(screenBuffer);
        assertThat(level.getNumRows(), equalTo(screenBuffer.length));
        assertThat(level.getNumColumns(), equalTo(screenBuffer[0].length));
    }

    @Test
    public void testLevelKnowsTheCoordinatesForTheHeroLocation(){
        NethackLevel level = new NethackLevel(new char[24][80]);
        Coordinates heroLocation = new Coordinates(1, 3);
        level.setHeroLocation(heroLocation);

        assertThat(level.getHeroLocation(), equalTo(heroLocation));
    }
}