
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

public class SlidePuzzle extends Application {

    private final int CANVAS_WIDTH = 320;
    private final int CANVAS_HEIGHT = 320;
    private final int TILE_SIZE = 80;

    private Canvas mainCanvas;
    private Button resetButton;
    private Button newGameButton;
    private Button solveButton;

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
        return scene;
    }

    private Pane setupPane() {
        Pane componentHolder = new Pane( setupCanvas(), setupResetButton(), setupNewGameButton(), setupSolveButton() );
        componentHolder.setStyle( "-fx-background-color:rgb( 3, 54, 73 );" );
        positionPaneComponents( componentHolder );
        return componentHolder;
    }

    private void positionPaneComponents( Pane pane ) {
        pane.setPrefSize( 500, 500 );
        mainCanvas.relocate(  85, 85 );
        newGameButton.relocate( 100, 450 );
        resetButton.relocate( 230, 450 );
        solveButton.relocate( 330, 450 );
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

    private Canvas setupCanvas() {
        mainCanvas = new SlidePuzzleCanvas( CANVAS_WIDTH, CANVAS_HEIGHT, TILE_SIZE );
        return mainCanvas;
    }

    private void showMainWindow( Stage stage ) {
        stage.show();
    }
}
