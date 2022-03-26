
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tile {
    private final Color tileColor = Color.rgb( 0, 204, 0 );
    int row;
    int column;
    int xCoordinate;
    int yCoordinate;
    int tileSize;
    int tileNumber;

    public Tile( int tileNumber, int row, int column, int tileSize ) {
        this.tileNumber = tileNumber;
        this.row = row;
        this.column = column;
        this.tileSize = tileSize;
        this.xCoordinate = column*tileSize;
        this.yCoordinate = row*tileSize;
    }

    public Tile( int row, int column, int tileSize ) {
        this.tileSize = tileSize;
        this.row = row;
        this.column = column;
        this.xCoordinate = row*tileSize;
        this.yCoordinate = column*tileSize;
    }

    public void draw( GraphicsContext drawingArea ) {
        drawInnerRectangle( drawingArea );
        drawOutLine( drawingArea );
    }

    private void drawInnerRectangle( GraphicsContext drawingArea ) {
        drawingArea.setFill( tileColor );
        drawingArea.fillRect( xCoordinate, yCoordinate, tileSize, tileSize );
        drawingArea.setStroke( Color.BLACK );
        drawingArea.strokeText( String.valueOf( tileNumber ), xCoordinate+30, yCoordinate+45 );
    }

    private void drawOutLine( GraphicsContext drawingArea ) {
        drawingArea.setStroke( Color.BLACK );
        drawingArea.strokeRect( xCoordinate, yCoordinate, tileSize, tileSize );
    }

    public int getNumber() {
        return this.tileNumber;
    }

    public boolean isBlank() {
        return false;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setXCoordinate( int xCoordinate ) {
        this.xCoordinate = xCoordinate;
        this.column = xCoordinate / tileSize;
    }

    public void setYCoordinate( int yCoordinate ) {
        this.yCoordinate = yCoordinate;
        this.row = yCoordinate / tileSize;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow( int row ) {
        this.row = row;
        setYCoordinate( row*tileSize );
    }

    public void setColumn( int column ) {
        this.column = column;
        setXCoordinate( column*tileSize );

    }
}
