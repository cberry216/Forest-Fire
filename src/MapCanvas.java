import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class acts as the canvas which the grid and trees is drawn on. Is
 * in charge of setting up map, resetting map, generating random map, and
 * painting the map upon a change.
 */
public class MapCanvas extends JPanel {

    private int width, height, numCols, numRows;
    int cellLength;
    private GUIDriver guiDriver;
    ArrayList<Point> livingTrees, burningTrees;
    GameDriver gameDriver;

    /**
     * Constructor for the {@link MapCanvas} object.
     * @param width       the width of the {@link MapCanvas} in pixels.
     * @param height      the height of the {@link MapCanvas} in pixels.
     * @param cellLength  the length of each cell in pixels.
     * @param guiDriver   the {@link GUIDriver} the {@link MapCanvas} is in.
     */
    MapCanvas(int width, int height, int cellLength, GUIDriver guiDriver) {
        this.width = width;
        this.height = height;
        this.cellLength = cellLength;
        this.guiDriver = guiDriver;

        this.livingTrees = new ArrayList<>();
        this.burningTrees = new ArrayList<>();

        this.numCols = this.width / this.cellLength;
        this.numRows = this.height / this.cellLength;

        this.gameDriver = new GameDriver(this.numCols, this.numRows);

        setPreferredSize(new Dimension(this.width, this.height));
        addMouseListener(new MapMouseListener(this));
    }

    /**
     * Converts all living cells on the {@link MapCanvas} to living trees in
     * the {@link Map}.
     */
    public void setupMap() {
        this.guiDriver.setGameDriver(new GameDriver(this.numCols, this.numRows));
        for(Point livingTree : this.livingTrees) {
            this.guiDriver.getGameDriver().map.plantTree((int)livingTree.getX() / this.cellLength, (int)livingTree.getY() / this.cellLength);
        }
    }

    /**
     * Resets the {@link MapCanvas} and {@link GameDriver}.
     */
    public void resetMapCanvas() {
        this.livingTrees.clear();
        this.burningTrees.clear();
        this.gameDriver = new GameDriver(this.numCols, this.numRows);
    }

    /**
     * Randomly generates series of living trees.
     */
    public void generateRandomMap() {
        this.livingTrees.clear();
        for(int i = this.numCols / 10; i < this.numCols * 9 / 10; i++) {
            for(int j = this.numRows / 10; j < this.numRows * 9 / 10; j++) {
                if(Math.random() > 0.7) {
                    int xVal = i * this.cellLength;
                    int yVal = j * this.cellLength;
                    this.livingTrees.add(new Point(xVal, yVal));
                }
            }
        }
        this.repaint();
    }

    /**
     * Paints the grid, living trees and burning trees.
     * @param g  Not used explicitly in implementation.
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.width, this.height);
        g.setColor(Color.BLACK);

        // Draw columns
        int currentDrawWidth = 0;
        while (currentDrawWidth < this.width) {
            g.drawLine(currentDrawWidth, 0, currentDrawWidth, this.height);
            currentDrawWidth += this.cellLength;
        }

        // Draw rows
        int currentDrawHeight = 0;
        while (currentDrawHeight < this.height) {
            g.drawLine(0, currentDrawHeight, this.width, currentDrawHeight);
            currentDrawHeight += this.cellLength;
        }

        // Draw living trees
        g.setColor(new Color(14, 104, 0));
        for(Point livingTree : this.livingTrees) {
            g.fillRect((int)livingTree.getX(), (int)livingTree.getY(), this.cellLength, this.cellLength);
        }

        // Draw burning trees
        g.setColor(new Color(231, 86, 49));
        for(Point burningTree : this.burningTrees) {
            g.fillRect((int)burningTree.getX(), (int)burningTree.getY(), this.cellLength, this.cellLength);
        }
    }

    // Setters

    /**
     * Sets the cell length of game to the given length.
     * @param cellLength  the length to set cellLength to in pixels.
     */
    public void setCellLength(int cellLength) {
        this.cellLength = cellLength;
    }

    // Resetters

    /**
     * Reset the number of columns on a cell length change.
     */
    public void resetNumCols() {
        this.numCols = this.width / this.cellLength;
    }

    /**
     * Reset the number of rows on a cell length change.
     */
    public void resetNumRows() {
        this.numRows = this.height / this.cellLength;
    }
}
