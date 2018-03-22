import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapButtonListener implements ActionListener {

    GUIDriver guiDriver;
    byte button;

    MapButtonListener(GUIDriver guiDriver, byte button) {
        this.guiDriver = guiDriver;
        this.button = button;
    }

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
