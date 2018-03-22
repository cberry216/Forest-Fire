import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapMouseListener extends MouseAdapter {

    private MapCanvas mapCanvas;

    MapMouseListener(MapCanvas mapCanvas) {
        super();
        this.mapCanvas = mapCanvas;
    }

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
