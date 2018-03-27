import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class controls what happens when each button in the
 * {@link GUIDriver} is pressed. Each listener has a number identifier to
 * which button was pressed.
 */
public class MapButtonListener implements ActionListener {

    private GUIDriver guiDriver;
    private byte button;

    /**
     * Constructor for the {@link MapButtonListener} object.
     * @param guiDriver  the {@link GUIDriver} that contains the buttons.
     * @param button     the type of button to assign.
     */
    MapButtonListener(GUIDriver guiDriver, byte button) {
        this.guiDriver = guiDriver;
        this.button = button;
    }

    /**
     * Determines what to do when each button is pressed.
     * @param e  the object returned from pressing a button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (button) {
            case 0:
                this.guiDriver.setPlay(true);
                break;
            case 1:
                this.guiDriver.setPlay(false);
                break;
            case 2:
                this.guiDriver.resetGUI();
                break;
        }
    }
}
