import locations.Coordinates;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class NethackLevelTest {

    @Test
    public void testLevelTakesOnTheDimensionsOfTheScreenBufferItIsCreatedWith_lessTheIgnoredTextRowsForNow() {
        char[][] screenBuffer = new char[24][80];
        NethackLevel level = new NethackLevel(screenBuffer);
        assertThat(level.getNumRows(), equalTo(screenBuffer.length - NethackLevel.NUM_BOTTOM_SCREEN_TEXT_ROWS - NethackLevel.NUM_TOP_SCREEN_TEXT_ROWS));
        assertThat(level.getNumColumns(), equalTo(screenBuffer[0].length));
    }

    @Test
    public void testLevelKnowsTheCoordinatesForTheHeroLocation(){
        NethackLevel level = new NethackLevel(new char[24][80]);
        Coordinates heroLocation = new Coordinates(1, 3);
        level.setHeroLocation(heroLocation);

        assertThat(level.getHeroLocation(), equalTo(heroLocation));
    }

    @Test
    public void testInterpretedLevelDoesNotTheFirstTwoAndLastThreeRowsOfTheScreen_forNowAsTheyAreNotAboutTheMap() {
        NethackLevel level = new NethackLevel((new char[24][80]));

        assertThat(level.getNumRows(), equalTo(24 - 2 - 3));
    }
}