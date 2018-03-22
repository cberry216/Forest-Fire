import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButtonListener implements ActionListener {

    GUIDriver guiDriver;
    byte menuItem;

    MenuButtonListener(GUIDriver guiDriver, byte menuItem) {
        this.guiDriver = guiDriver;
        this.menuItem = menuItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(this.menuItem){
            case(0):
                System.exit(0);
                break;
            case(1):
                this.guiDriver.getMapCanvas().generateRandomMap();
                break;
        }
    }
}
