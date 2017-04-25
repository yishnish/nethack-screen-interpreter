import locations.Coordinates;
import mapitems.DungeonThing;

public class NethackLevel {
    private Integer numRows;
    private Integer numColumns;
    private Coordinates heroLocation;
    private DungeonThing[][] dungeonMap;

    public NethackLevel(char[][] screenBuffer) {
        this.numRows = screenBuffer.length;
        this.numColumns = screenBuffer[0].length;
        dungeonMap = new DungeonThing[numRows][numColumns];
    }

    public Integer getNumRows() {
        return numRows;
    }

    public Integer getNumColumns() {
        return numColumns;
    }

    public void setHeroLocation(Coordinates heroLocation) {
        this.heroLocation = heroLocation;
    }

    public Coordinates getHeroLocation() {
        return heroLocation;
    }

    public DungeonThing thingAt(Coordinates location) {
        return this.dungeonMap[location.getRow()][location.getColumn()];
    }

    public void setThingAt(Coordinates location, DungeonThing thing) {
        this.dungeonMap[location.getRow()][location.getColumn()] = thing;
    }

    public DungeonThing[][] getDungeonMap() {
        return dungeonMap;
    }
}
