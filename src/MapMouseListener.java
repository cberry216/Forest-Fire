import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class allows the trees to be placed on the {@link MapCanvas}
 * manually.
 */
public class MapMouseListener extends MouseAdapter {

    private MapCanvas mapCanvas;

    /**
     * Constructor for the {@link MapMouseListener} object.
     * @param mapCanvas  the {@link MapCanvas} to listen to.
     */
    MapMouseListener(MapCanvas mapCanvas) {
        super();
        this.mapCanvas = mapCanvas;
    }

    /**
     * Controls how to place living tree upon a mouse press.
     * @param me  object returned from clicking mouse.
     */
    @Override
    public void mousePressed(MouseEvent me) {
        int xVal = (me.getX() / this.mapCanvas.cellLength) * this.mapCanvas.cellLength;
        int yVal = (me.getY() / this.mapCanvas.cellLength) * this.mapCanvas.cellLength;
        Point toAdd = new Point(xVal, yVal);
        if(this.mapCanvas.livingTrees.contains(toAdd))
            this.mapCanvas.livingTrees.remove(toAdd);
        else
            this.mapCanvas.livingTrees.add(toAdd);
        this.mapCanvas.repaint();
    }
}
