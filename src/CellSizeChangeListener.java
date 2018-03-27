import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class listens to any change in the cell size
 * {@link javax.swing.JSpinner} in the given {@link GUIDriver}
 */
public class CellSizeChangeListener implements ChangeListener{

    private GUIDriver guiDriver;

    /**
     * Constructor for the {@link CellSizeChangeListener} object.
     * @param guiDriver  the {@link GUIDriver} that controls the cell size
     */
    CellSizeChangeListener(GUIDriver guiDriver) {
        this.guiDriver = guiDriver;
    }

    /**
     * Controls what happens upon a change in cell size.
     * @param e the object returned form a change in cell size.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        this.guiDriver.getMapCanvas().setCellLength(this.guiDriver.getCellLength());
        this.guiDriver.getMapCanvas().resetNumCols();
        this.guiDriver.getMapCanvas().resetNumRows();
        this.guiDriver.getMapCanvas().repaint();
    }
}
