import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CellSizeChangeListener implements ChangeListener{

    GUIDriver guiDriver;

    CellSizeChangeListener(GUIDriver guiDriver) {
        this.guiDriver = guiDriver;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.guiDriver.getMapCanvas().setCellLength(this.guiDriver.getCellLength());
        this.guiDriver.getMapCanvas().resetNumCols();
        this.guiDriver.getMapCanvas().resetNumRows();
        this.guiDriver.getMapCanvas().repaint();
    }
}
