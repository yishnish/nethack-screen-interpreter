import locations.Coordinates;

public class NethackLevel {
    private Integer numRows;
    private Integer numColumns;
    private Coordinates heroLocation;

    public NethackLevel(char[][] screenBuffer) {
        this.numRows = screenBuffer.length;
        this.numColumns = screenBuffer[0].length;
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
}
