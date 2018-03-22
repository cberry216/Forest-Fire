import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpeedChangeListener implements ChangeListener {

    private GUIDriver guiDriver;

    SpeedChangeListener(GUIDriver guiDriver) {
        this.guiDriver = guiDriver;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        guiDriver.setSpeed(guiDriver.getSpeed());
    }
}
