public class NethackLevel {
    private Integer numRows;
    private Integer numColumns;

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
}
