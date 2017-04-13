package locations;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class CoordinatesTest {

    @Test
    public void testCoordinatesKnowRowAndColumn() {
        Coordinates coordinates = new Coordinates(1, 2);
        assertThat(coordinates.getRow(), equalTo(1));
        assertThat(coordinates.getColumn(), equalTo(2));
    }

    @Test
    public void testCoordinateEqualityIsBasedOnRowAndColumn() {
        Coordinates coordinates1 = new Coordinates(1, 1);
        Coordinates coordinates2 = new Coordinates(1, 2);
        Coordinates coordinates3 = new Coordinates(1, 1);

        assertThat(coordinates1, equalTo(coordinates1));
        assertThat(coordinates1, not(equalTo(coordinates2)));
        assertThat(coordinates1, equalTo(coordinates3));

        assertThat(coordinates3, equalTo(coordinates1));
    }
}
