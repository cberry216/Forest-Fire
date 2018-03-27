import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class listens to the speed {@link javax.swing.JSlider} and adjusts
 * the {@link GUIDriver} accordingly.
 */
public class SpeedChangeListener implements ChangeListener {

    private GUIDriver guiDriver;

    /**
     * Constructor for the {@link SpeedChangeListener}.
     * @param guiDriver  the {@link GUIDriver} that contains the speed slider.
     */
    SpeedChangeListener(GUIDriver guiDriver) {
        this.guiDriver = guiDriver;
    }

    /**
     * Upon a speed change, change the speed of the simulation.
     * @param e  the object returned from a speed change.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        guiDriver.setSpeed(guiDriver.getSpeed());
    }
}
