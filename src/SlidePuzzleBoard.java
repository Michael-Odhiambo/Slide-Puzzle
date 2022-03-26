
public class SlidePuzzleBoard {
    private Tile[][] slidePuzzleTiles;
    private Tile[][] solvedBoard;
    private int numberOfRows, numberOfColumns;
    private int tileSize;

    public SlidePuzzleBoard( int tileSize, int numberOfRows, int numberOfColumns ) {
        initializeTileSize( tileSize );
        initializeRowsAndColumns( numberOfRows, numberOfColumns );
        solvedBoard = setupTiles();
        slidePuzzleTiles = setupTiles();
        scrambleBoard();
    }

    private void initializeTileSize( int tileSize ) {
        this.tileSize = tileSize;
    }

    private void initializeRowsAndColumns( int numberOfRows, int numberOfColumns ) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    private Tile[][] setupTiles() {
        Tile[][] tiles = new Tile[numberOfRows][numberOfColumns];
        int counter = 1;
        for ( int row = 0; row < numberOfRows; row++ ) {
            for ( int column = 0; column < numberOfColumns; column++ ) {
                if ( row == numberOfRows-1 && column == numberOfColumns-1 )
                    tiles[row][column] = new BlankTile( row, column, tileSize );
                else
                    tiles[row][column] = new Tile( counter, row, column, tileSize );
                counter++;
            }
        }
        return tiles;
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

    public void scrambleBoard() {
        for ( int row = 0; row < numberOfRows; row++ ) {
            for ( int column = 0; column < numberOfColumns; column++ ) {
                int randomRow = (int)(Math.random()*numberOfRows);
                int randomColumn = (int)(Math.random()*numberOfColumns);
                Tile tile = getTile( row, column );
                tile.setRow( randomRow );
                tile.setColumn( randomColumn );
                Tile randomTile = getTile( randomRow, randomColumn );
                randomTile.setRow( row );
                randomTile.setColumn( column );
                slidePuzzleTiles[row][column] = randomTile;
                slidePuzzleTiles[randomRow][randomColumn] = tile;

            }
        }
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

    public boolean hasWon() {
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {
                if (slidePuzzleTiles[row][column].isBlank()) {
                    if (!solvedBoard[row][column].isBlank())
                        return false;
                }
                if (slidePuzzleTiles[row][column].getNumber() != solvedBoard[row][column].getNumber())
                    return false;
            }
        }
        return true;
    }
}
