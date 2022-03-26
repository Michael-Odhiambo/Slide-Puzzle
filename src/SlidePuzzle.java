
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

public class SlidePuzzle extends Application {

    private final int CANVAS_WIDTH = 320;
    private final int CANVAS_HEIGHT = 320;
    private final int TILE_SIZE = 80;
    private final int NUMBER_OF_ROWS = 4;
    private final int NUMBER_OF_COLUMNS = 4;
    private final Color CANVAS_BACKGROUND_COLOR = Color.rgb( 3, 54, 73 );

    private enum DIRECTION { UP, DOWN, LEFT, RIGHT };
    private SlidePuzzleBoard slidePuzzleBoard;
    private SlidePuzzleCanvas mainCanvas;
    private Button resetButton;
    private Button newGameButton;
    private Button solveButton;
    private DIRECTION direction;
    private boolean slideInProgress;

    public void start( Stage stage ) {
        setupMainWindow( stage );
        showMainWindow( stage );
    }

    private void setupMainWindow( Stage stage ) {
        stage.setScene( setupScene() );
        stage.setResizable( false );
        stage.setTitle( "Slide Puzzle" );
    }

    private Scene setupScene() {
        Scene scene = new Scene( setupPane() );
        scene.setOnKeyPressed( event -> keyPressed( event ) );
        return scene;
    }

    private void keyPressed( KeyEvent event ) {
        if ( slideInProgress )
            return;
        KeyCode keyPressed = event.getCode();
        direction = setDirection( keyPressed );
        if ( userDidNotPressOneOfTheArrowKeys() )
            return;
        slideInProgress = true;
        Tile blankTile = slidePuzzleBoard.getBlankTile();
        Tile slidingTile = getSlidingTileBasedOn( blankTile );
        new SlideAnimationThread( slidingTile, blankTile ).start();
    }

    private DIRECTION setDirection( KeyCode keyPressed ) {
        if ( keyPressed == KeyCode.UP )
            return DIRECTION.UP;
        else if ( keyPressed == KeyCode.DOWN )
            return DIRECTION.DOWN;
        else if ( keyPressed == KeyCode.LEFT )
            return DIRECTION.LEFT;
        else if ( keyPressed == KeyCode.RIGHT )
            return DIRECTION.RIGHT;
        return null;
    }


    private boolean userDidNotPressOneOfTheArrowKeys() {
        return direction == null;
    }

    private Tile getSlidingTileBasedOn( Tile blankTile ) {
        if ( direction == DIRECTION.UP )
            return slidePuzzleBoard.getTile( blankTile.getRow()+1, blankTile.getColumn() );
        else if ( direction == DIRECTION.DOWN )
            return slidePuzzleBoard.getTile( blankTile.getRow()-1, blankTile.getColumn() );
        else if ( direction == DIRECTION.LEFT )
            return slidePuzzleBoard.getTile( blankTile.getRow(), blankTile.getColumn()+1 );
        else if ( direction == DIRECTION.RIGHT )
            return slidePuzzleBoard.getTile( blankTile.getRow(), blankTile.getColumn()-1 );
        return null;
    }

    private Pane setupPane() {
        Pane componentHolder = new Pane( setupCanvas(), setupResetButton(), setupNewGameButton(), setupSolveButton() );
        componentHolder.setStyle( "-fx-background-color:rgb( 3, 54, 73 );" );
        positionPaneComponents( componentHolder );
        return componentHolder;
    }

    private Canvas setupCanvas() {
        setupSlidePuzzleBoard();
        mainCanvas = new SlidePuzzleCanvas( slidePuzzleBoard, CANVAS_BACKGROUND_COLOR, CANVAS_WIDTH, CANVAS_HEIGHT );
        return mainCanvas;
    }

    private void setupSlidePuzzleBoard() {
        slidePuzzleBoard = new SlidePuzzleBoard( TILE_SIZE, NUMBER_OF_ROWS, NUMBER_OF_COLUMNS );
    }

    private Button setupResetButton() {
        resetButton = new Button( "Reset" );
        return resetButton;
    }

    private Button setupNewGameButton() {
        newGameButton = new Button( "New Game" );
        return newGameButton;
    }

    private Button setupSolveButton() {
        solveButton = new Button( "Solve" );
        return solveButton;
    }

    private void positionPaneComponents( Pane pane ) {
        pane.setPrefSize( 500, 500 );
        mainCanvas.relocate(  85, 85 );
        newGameButton.relocate( 100, 450 );
        resetButton.relocate( 230, 450 );
        solveButton.relocate( 330, 450 );
    }

    private void showMainWindow( Stage stage ) {
        stage.show();
    }


    private class SlideAnimationThread extends Thread {
        Tile slidingTile;
        Tile blankTile;

        SlideAnimationThread( Tile slidingTile, Tile blankTile ) {
            setDaemon( true );
            this.slidingTile = slidingTile;
            this.blankTile = blankTile;
        }

        public void run() {
            int slidingTileRow = slidingTile.getRow();
            int slidingTileColumn = slidingTile.getColumn();
            slidePuzzleBoard.moveTile( slidingTileRow, slidingTileColumn,
                    blankTile.getRow(), blankTile.getColumn() );
            while ( slidingTile.getXCoordinate() != blankTile.getXCoordinate() ||
                    slidingTile.getYCoordinate() != blankTile.getYCoordinate() ) {
                if ( direction == DIRECTION.UP )
                    slideTileUp();
                else if ( direction == DIRECTION.DOWN )
                    slideTileDown();
                else if ( direction == DIRECTION.LEFT )
                    slideTileLeft();
                else
                    slideTileRight();
                Platform.runLater( () -> mainCanvas.draw() );
                try {
                    Thread.sleep( 10 );
                }
                catch ( InterruptedException e ) {}
            }
            blankTile.setRow( slidingTileRow );
            blankTile.setColumn( slidingTileColumn );
            slideInProgress = false;
        }

        void slideTileUp() {
            int yCoordinate = slidingTile.getYCoordinate();
            yCoordinate--;
            slidingTile.setYCoordinate( yCoordinate );
        }

        void slideTileDown() {
            int yCoordinate = slidingTile.getYCoordinate();
            yCoordinate++;
            slidingTile.setYCoordinate( yCoordinate );
        }

        void slideTileLeft() {
            int xCoordinate = slidingTile.getXCoordinate();
            xCoordinate--;
            slidingTile.setXCoordinate( xCoordinate );
        }

        void slideTileRight() {
            int xCoordinate = slidingTile.getXCoordinate();
            xCoordinate++;
            slidingTile.setXCoordinate( xCoordinate );
        }
    }

}
