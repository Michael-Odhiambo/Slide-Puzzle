
public class SlidePuzzleBoard {
    private Tile[][] slidePuzzleTiles;
    private int numberOfRows, numberOfColumns;
    private int tileSize;

    public SlidePuzzleBoard( int tileSize, int numberOfRows, int numberOfColumns ) {
        initializeTileSize( tileSize );
        initializeRowsAndColumns( numberOfRows, numberOfColumns );
        setupTiles();
    }

    private void initializeTileSize( int tileSize ) {
        this.tileSize = tileSize;
    }

    private void initializeRowsAndColumns( int numberOfRows, int numberOfColumns ) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    private void setupTiles() {
        slidePuzzleTiles = new Tile[numberOfRows][numberOfColumns];
        int counter = 1;
        for ( int row = 0; row < numberOfRows; row++ ) {
            for ( int column = 0; column < numberOfColumns; column++ ) {
                if ( row == numberOfRows-1 && column == numberOfColumns-1 )
                    slidePuzzleTiles[row][column] = new BlankTile( row, column, tileSize );
                else
                    slidePuzzleTiles[row][column] = new Tile( counter, row, column, tileSize );
                counter++;
            }
        }
    }

    public void moveTile( int fromRow, int fromColumn, int toRow, int toColumn ) {
        Tile blankTile = getBlankTile();
        slidePuzzleTiles[toRow][toColumn] = slidePuzzleTiles[fromRow][fromColumn];
        slidePuzzleTiles[fromRow][fromColumn] = blankTile;
    }

    public Integer getRowPositionOfBlankTile() {
        for ( int row = 0; row < numberOfRows; row++ )
            for ( int column = 0; column < numberOfColumns; column++ )
                if ( slidePuzzleTiles[row][column].isBlank() )
                    return row;
        return null;
    }

    public Integer getColumnPositionOfBlankTile() {
        for ( int row = 0; row < numberOfRows; row++ )
            for ( int column = 0; column < numberOfColumns; column++ )
                if ( slidePuzzleTiles[row][column].isBlank() )
                    return column;
        return null;
    }

    public Tile getTile( int row, int column ) {
        return slidePuzzleTiles[row][column];
    }

    public Tile getBlankTile() {
        return slidePuzzleTiles[getRowPositionOfBlankTile()][getColumnPositionOfBlankTile()];
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getTileSize() {
        return tileSize;
    }
}
