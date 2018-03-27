import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class controls what happens when each button from the
 * {@link GUIDriver} menu is pressed. Each listener has a number identifier
 * to determine which button as pressed.
 */
public class MenuButtonListener implements ActionListener {

    private GUIDriver guiDriver;
    private byte menuItem;

    /**
     * The constructor for the {@link MenuButtonListener} object.
     * @param guiDriver  the {@link GUIDriver} that contains the menu
     * @param menuItem   the type of menu item to assign.
     */
    MenuButtonListener(GUIDriver guiDriver, byte menuItem) {
        this.guiDriver = guiDriver;
        this.menuItem = menuItem;
    }

    /**
     * Controls what happens when each menu item is pressed.
     * @param e  the object returned from pressing a menu item.
     */
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
