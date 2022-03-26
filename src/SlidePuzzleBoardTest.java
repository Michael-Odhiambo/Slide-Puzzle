

public class SlidePuzzleBoardTest {

    public static void main( String[] args ) {
        SlidePuzzleBoard board = new SlidePuzzleBoard( 80, 4, 4 );
        System.out.println( "Row of blank tile: " + board.getRowPositionOfBlankTile() );
        System.out.println( "Column of blank tile: " + board.getColumnPositionOfBlankTile() );
        System.out.println( "First tile: " + board.getTile( 0, 0 ).getNumber() );
    }
}
