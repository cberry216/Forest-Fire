import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class acts as the frame that the user actually sees. It controls
 * the repainting of the {@link MapCanvas}, running the simulation, and
 * adding all the listeners.
 */
public class GUIDriver extends JFrame {

    private Dimension screenSize;

    private MapCanvas mapCanvas;
    private JButton start;
    private JButton stop;
    private JButton reset;
    private JLabel speedLabel;
    private JSlider speed;
    private JLabel cellSizeLabel;
    private JSpinner cellSize;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu gameMenu;
    private JMenuItem exitGame;
    private JMenuItem randomGame;

    private int playSpeed;
    private boolean play;
    private boolean started;

    /**
     * Constructor for the {@link GameDriver} object.
     */
    GUIDriver() {
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(this.screenSize);
        setSize(this.screenSize);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        this.mapCanvas = new MapCanvas(this.screenSize.width - 219, this.screenSize.height - 219, 10, this);

        this.start = new JButton("Start");
        this.stop = new JButton("Stop");
        this.reset = new JButton("Restart");
        this.speedLabel = new JLabel("Speed", SwingConstants.CENTER);
        this.speed = new JSlider(0, 10, 1);
        this.cellSizeLabel = new JLabel("Cell Size", SwingConstants.CENTER);
        SpinnerNumberModel snm = new SpinnerNumberModel(10, 2, 100, 1);
        this.cellSize = new JSpinner(snm);

        this.menuBar = new JMenuBar();
        this.fileMenu = new JMenu("File");
        this.gameMenu = new JMenu("Game");
        this.exitGame = new JMenuItem("Exit");
        this.randomGame = new JMenuItem("Random Game");

        this.play = false;
        this.playSpeed = 1;

        // True if the game has already been started, reset upon resetting
        this.started = false;

        addListeners();
        initJFrame();
    }

    /**
     * Adds the listeners to each corresponding GUI object.
     */
    private void addListeners() {
        this.start.addActionListener(new MapButtonListener(this, (byte)0));
        this.stop.addActionListener(new MapButtonListener(this, (byte)1));
        this.reset.addActionListener(new MapButtonListener(this, (byte)2));

        this.speed.addChangeListener(new SpeedChangeListener(this));

        this.cellSize.addChangeListener(new CellSizeChangeListener(this));

        this.exitGame.addActionListener(new MenuButtonListener(this, (byte)0));
        this.randomGame.addActionListener(new MenuButtonListener(this, (byte)1));
    }

    /**
     * Sets up the {@link GUIDriver} in organized fashion.
     */
    private void initJFrame() {
        setLayout(new GridBagLayout());
        setPreferredSize(this.screenSize);

        Dimension buttonSize = new Dimension(200, 130);

        this.fileMenu.add(this.exitGame);

        this.gameMenu.add(this.randomGame);

        this.menuBar.add(this.fileMenu);
        this.menuBar.add(this.gameMenu);

        setJMenuBar(this.menuBar);

        this.start.setPreferredSize(buttonSize);
        this.stop.setPreferredSize(buttonSize);
        this.reset.setPreferredSize(buttonSize);

        this.speedLabel.setPreferredSize(new Dimension(200, 20));
        this.speed.setPreferredSize(new Dimension(200, 60));
        this.speed.setPaintTicks(true);
        this.speed.setPaintLabels(true);
        this.speed.setPaintTrack(true);
        this.speed.setSnapToTicks(true);
        this.speed.setMajorTickSpacing(1);

        this.cellSizeLabel.setPreferredSize(new Dimension(200, 20));
        this.cellSize.setPreferredSize(new Dimension(200, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(3,3,3,3);
        add(this.start, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(3,3,3,3);
        add(this.stop, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(3,3,3,3);
        add(this.reset, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.ipady = 25;
        gbc.insets = new Insets(3,3,0,3);
        add(this.speedLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.ipady = 25;
        gbc.insets = new Insets(0,3,3,3);
        add(this.speed, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(3,3,0,3);
        add(this.cellSizeLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,3,3,3);
        add(this.cellSize, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.gridheight = 7;
        add(this.mapCanvas, gbc);
    }

    /**
     * Takes one step through the simulation.
     */
    private void stepSimulation() {
        this.mapCanvas.gameDriver.step();
        ArrayList<Point> stepLivingTrees = this.mapCanvas.gameDriver.map.getLivingTrees();
        ArrayList<Point> stepBurningTrees = this.mapCanvas.gameDriver.map.getBurningTrees();
        this.mapCanvas.livingTrees.clear();
        this.mapCanvas.burningTrees.clear();
        for(Point livingTree : stepLivingTrees) {
            this.mapCanvas.livingTrees.add(new Point((int)livingTree.getX() * this.mapCanvas.cellLength, (int)livingTree.getY() * this.mapCanvas.cellLength));
        }
        for(Point burningTree : stepBurningTrees) {
            this.mapCanvas.burningTrees.add(new Point((int)burningTree.getX() * this.mapCanvas.cellLength, (int)burningTree.getY() * this.mapCanvas.cellLength));
        }
        this.mapCanvas.repaint();
        try {
            Thread.sleep(500 / this.playSpeed);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the {@link MapCanvas} up and runs the simulation.
     */
    public void startSimulation() {
        this.mapCanvas.setupMap();
        this.play = true;
        this.started = true;
        while(this.play) {
            stepSimulation();
        }
    }

    /**
     * If the game has already started, just run the simulation.
     */
    public void continueSimulation() {
        while(this.play) {
            stepSimulation();
        }
    }

    /**
     * Resets the {@link MapCanvas} and {@link GUIDriver}.
     */
    public void resetGUI() {
        this.mapCanvas.resetMapCanvas();
        this.started = false;
        this.mapCanvas.repaint();
        this.play = false;

    }

    // Getters

    /**
     * Gets the {@link MapCanvas} used in {@link GUIDriver}.
     * @return the {@link MapCanvas} used.
     */
    public MapCanvas getMapCanvas() {
        return this.mapCanvas;
    }

    /**
     * Returns whether the game is currently playing.
     * @return  the play member.
     */
    public boolean getPlay() {
        return this.play;
    }

    /**
     * Returns whether the game has already started.
     * @return  the started member
     */
    public boolean getStarted() {
        return this.started;
    }

    /**
     * Gets the speed value of the speed {@link JSlider}.
     * @return  the value of speed.
     */
    public int getSpeed() {
        return this.speed.getValue();
    }

    /**
     * Gets the cell size value of the cell size {@link JSpinner}.
     * @return  the value of cell size.
     */
    public int getCellLength() {
        return (int)this.cellSize.getValue();
    }

    /**
     * Gets the {@link GameDriver} used in the {@link MapCanvas}.
     * @return  the {@link GameDriver} in the {@link MapCanvas}.
     */
    public GameDriver getGameDriver() {
        return this.mapCanvas.gameDriver;
    }

    // Setters

    /**
     * Sets the value of play.
     * @param play  true if running, false otherwise.
     */
    public void setPlay(boolean play) {
        this.play = play;
    }

    /**
     * Set the speed of the simulation.
     * @param speed  the speed of the simulation.
     */
    public void setSpeed(int speed) {
        if(speed == 0) {
            this.play = false;
        }
        else {
            this.playSpeed = speed;
        }
    }

    /**
     * Sets the {@link GameDriver} of the {@link MapCanvas} to a different
     * {@link GameDriver}.
     * @param gameDriver  the {@link GameDriver} to change to.
     */
    public void setGameDriver(GameDriver gameDriver) {
        this.mapCanvas.gameDriver = gameDriver;
    }
}
