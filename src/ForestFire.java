/**
 * The main class that runs the game of Forest Fire.
 */
public class ForestFire {

    public static void main(String[] args) {
        GUIDriver guiDriver = new GUIDriver();
        guiDriver.setVisible(true);
        while(true) {
            if(guiDriver.getPlay()) {
                if(!guiDriver.getStarted()) {
                    guiDriver.startSimulation();
                }
                else
                    guiDriver.continueSimulation();
            }
            try {
                Thread.sleep(100);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
