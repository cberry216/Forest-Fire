import java.awt.*;
import java.util.ArrayList;

/**
 * This class is used to run the game and handle most of the game logic,
 * including planting random trees, burning random trees, and stepping
 * through the simulation.
 */
public class GameDriver {

    Map map;

    /**
     * Constructor for the {@link GameDriver} object.
     * @param width   the width of the {@link Map} used.
     * @param height  the height of the {@link Map} used.
     */
    GameDriver(int width, int height) {
        this.map = new Map(width, height);
    }

    /**
     * Randomly decides the location of trees to plant.
     * @return  array of locations to plant trees.
     */
    public ArrayList<Point> getTreesToPlant() {
        ArrayList<Point> treesToPlant = new ArrayList<>();
        for(Point emptyTree : this.map.getEmptyTrees()) {
            if(Math.random() < .01) {
                treesToPlant.add(emptyTree);
            }
        }
        return treesToPlant;
    }

    /**
     * Randomly decides which trees should be ignited.
     * @return  array of tree locations to ignite.
     */
    public ArrayList<Point> getTreesToIgnite() {
        ArrayList<Point> treesToIgnite = new ArrayList<>();
        for(Point livingTree : this.map.getLivingTrees()) {
            if(Math.random() < 0.0001) {
                treesToIgnite.add(livingTree);
            }
        }
        return treesToIgnite;
    }

    /**
     * Burn all BURNING trees.
     * @return true if burning occurred, false otherwise.
     */
    public boolean burnTrees() {
        if(this.map.hasBurningTrees()) {
            this.map.burn();
            return true;
        }
        return false;
    }

    /**
     * Plants the trees from the given array in the {@link Map}
     * @param treesToPlant  array of tree locations to plant.
     */
    public void plantTrees(ArrayList<Point> treesToPlant) {
        for(Point tree : treesToPlant) {
            this.map.plantTree(tree);
        }
    }

    /**
     * Ignites the trees from the given array in the {@link Map}
     * @param treesToIgnite  array of trees to ignite.
     */
    public void igniteTrees(ArrayList<Point> treesToIgnite) {
        for(Point tree : treesToIgnite) {
            this.map.igniteTree(tree);
        }
    }

    /**
     * Takes one step through the simulation; the trees to plant and ignite are
     * created, burning occurs, and the new trees to plant and ignite are
     * planted and ignited.
     */
    public void step() {
        ArrayList<Point> treesToPlant = getTreesToPlant();
        ArrayList<Point> treesToIgnite = getTreesToIgnite();
        burnTrees();
        plantTrees(treesToPlant);
        igniteTrees(treesToIgnite);
    }
}
