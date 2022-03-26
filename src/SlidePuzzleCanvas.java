
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

class SlidePuzzleCanvas extends Canvas {
    private SlidePuzzleBoard slidePuzzleBoard;
    private Color backgroundColor;
    private GraphicsContext drawingArea = getGraphicsContext2D();

    public SlidePuzzleCanvas( SlidePuzzleBoard slidePuzzleBoard, Color backgroundColor,
                              int canvasWidth, int canvasHeight ) {
        super( canvasWidth, canvasHeight );
        initializeSlidePuzzleBoard( slidePuzzleBoard );
        initializeBackgroundColor( backgroundColor );
        draw();
    }

    private void initializeSlidePuzzleBoard( SlidePuzzleBoard board ) {
        this.slidePuzzleBoard = board;
    }

    private void initializeBackgroundColor( Color backgroundColor ) {
        this.backgroundColor = backgroundColor;
    }

    public void draw() {
        drawBackground();
        drawTiles();
        drawOutline();
    }

    private void drawBackground() {
        drawingArea.setFill( backgroundColor );
        drawingArea.fillRect( 0, 0, getWidth(), getHeight() );
    }

    private void drawTiles() {
        for ( int row = 0; row < slidePuzzleBoard.getNumberOfRows(); row++ )
            for ( int col = 0; col < slidePuzzleBoard.getNumberOfColumns(); col++ )
                slidePuzzleBoard.getTile( row, col ).draw( drawingArea );
    }

    private void drawOutline() {
        drawingArea.setStroke( Color.BLACK );
        drawingArea.strokeRect( 0, 0, getWidth(), getHeight() );
    }

    public void drawTile( Tile tile ) {
        tile.draw( drawingArea );
    }

}
