import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BlankTile extends Tile {

    public BlankTile( int row, int column, int tileSize ) {
        super( row, column, tileSize );
    }

    public void draw( GraphicsContext drawingArea ) {
        // There's nothing to draw since this is a blank tile.
    }

    public boolean isBlank() {
        return true;
    }
}
