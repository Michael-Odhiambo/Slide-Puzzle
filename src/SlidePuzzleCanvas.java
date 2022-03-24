
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

class SlidePuzzleCanvas extends Canvas {
    private final Color BACKGROUND_COLOR = Color.rgb( 0, 204, 0 );
    private final int NUMBER_OF_ROWS = 4;
    private final int NUMBER_OF_COLUMNS = 4;
    private GraphicsContext drawingArea = getGraphicsContext2D();
    private int tileSize;

    public SlidePuzzleCanvas( int canvasWidth, int canvasHeight, int tileSize ) {
        super( canvasWidth, canvasHeight );
        this.tileSize = tileSize;
        draw();
    }

    public void draw() {
        drawBackground();
        drawRows();
        drawColumns();
        drawOutline();
    }

    private void drawOutline() {
        drawingArea.setStroke( Color.BLACK );
        drawingArea.strokeRect( 0, 0, getWidth(), getHeight() );
    }

    private void drawBackground() {
        drawingArea.setFill( BACKGROUND_COLOR );
        drawingArea.fillRect( 0, 0, getWidth(), getHeight() );
    }

    private void drawRows() {
        drawingArea.setStroke( Color.BLACK );
        for ( int row = 0; row < NUMBER_OF_ROWS; row++ )
            drawingArea.strokeLine( 0, row*tileSize, getWidth(), row*tileSize );
    }

    private void drawColumns() {
        for ( int column = 0; column < NUMBER_OF_COLUMNS; column++ )
            drawingArea.strokeLine( column*tileSize, 0, column*tileSize, getHeight() );
    }

}
