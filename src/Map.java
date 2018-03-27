import java.awt.*;
import java.util.ArrayList;

/**
 * This class is used to keep track of all trees in the space. Most of the
 * game logic takes place here including populating the map, igniting
 * trees, planting trees, and burning all ignited trees.
 */
public class Map {

    State[][] map;
    ArrayList<Point> livingTrees, burningTrees, emptyTrees;
    int width, height;

    /**
     * Constructor for the {@link Map} object
     * @param width   the width of the {@link Map}
     * @param height  the height of the {@link Map}
     */
    Map(int width, int height) {
        this.width = width;
        this.height = height;

        this.map = new State[this.width][this.height];
        this.livingTrees = new ArrayList<>();
        this.burningTrees = new ArrayList<>();
        this.emptyTrees = new ArrayList<>();

        populateMap();
    }

    /**
     * Populate the map with empty trees.
     */
    private void populateMap() {
        for(int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                this.map[i][j] = State.EMPTY;
                this.emptyTrees.add(new Point(i,j));
            }
        }
    }

    /**
     * Get the {@link State} of the tree at the given coordinates.
     * @param xPos  the x-position of the tree to get the {@link State} of.
     * @param yPos  the y-position of the tree to get the {@link State} of.
     * @return      the {@link State} of the tree.
     */
    public State getTreeState(int xPos, int yPos) {
        return this.map[xPos][yPos];
    }

    /**
     * Get the {@link State} of the tree at the given {@link Point}.
     * @param point  the {@link Point} of the tree to get the state of.
     * @return       the {@link State} of the tree.
     */
    public State getTreeState(Point point) {
        return this.map[(int)point.getX()][(int)point.getY()];
    }

    /**
     * Set the {@link State} of the tree at the given coordinates.
     * @param xPos   the x-position of the tree to set the {@link State} of.
     * @param yPos   the y-position of the tree to set the {@link State} of.
     * @param state  the {@link State} to set the tree to.
     */
    public void setTreeState(int xPos, int yPos, State state) {
        this.map[xPos][yPos] = state;
    }

    /**
     * Set the {@link State} of the tree at the given {@link Point}.
     * @param point  the {@link Point} of the tree to set the {@link State} of.
     * @param state  the {@link State} to set the tree to.
     */
    public void setTreeState(Point point, State state) {
        this.map[(int)point.getX()][(int)point.getY()] = state;
    }

    /**
     * Sets the {@link State} of the given tree to BURNING.
     * @param xPos  the x-position of the tree to set to BURNING.
     * @param yPos  the y-position of the tree to set to BURNING.
     */
    public void igniteTree(int xPos, int yPos) {
        if(getTreeState(xPos, yPos) == State.LIVING) {
            setTreeState(xPos, yPos, State.BURNING);
            Point treeToIgnite = new Point(xPos, yPos);
            this.burningTrees.add(treeToIgnite);
            this.livingTrees.remove(treeToIgnite);
        }
    }

    /**
     * Sets the {@link State} of the given tree to BURNING.
     * @param tree  the {@link Point} of the tree to set to BURNING.
     */
    public void igniteTree(Point tree) {
        igniteTree((int)tree.getX(), (int)tree.getY());
    }

    /**
     * Sets the {@link State} of the given tree to LIVING.
     * @param xPos  the x-position of the tree to set to LIVING.
     * @param yPos  the y-position of the tree to set to LIVING.
     */
    public void plantTree(int xPos, int yPos) {
        if(getTreeState(xPos, yPos) == State.EMPTY) {
            setTreeState(xPos, yPos, State.LIVING);
            Point treeToPlant = new Point(xPos, yPos);
            this.livingTrees.add(treeToPlant);
            this.emptyTrees.remove(treeToPlant);
        }
    }

    /**
     * Sets the {@link State} of the given tree to LIVING.
     * @param tree  the {@link Point} of the tree to set to LIVING.
     */
    public void plantTree(Point tree) {
        plantTree((int)tree.getX(), (int)tree.getY());
    }

    /**
     * Sets the living neighbors of all burning trees to BURNING, thus
     * simulating the fire spreading.
     */
    public void burn() {
        if(this.livingTrees.size() > 0) {
            ArrayList<Point> allLivingNeighbors = new ArrayList<>();
            ArrayList<Point> burningTreesToRemove = new ArrayList<>();
            for(Point burningTree : this.burningTrees) {
                allLivingNeighbors.addAll(getLivingNeighbors((int)burningTree.getX(), (int)burningTree.getY()));
                setTreeState(burningTree, State.EMPTY);
                burningTreesToRemove.add(burningTree);
                this.emptyTrees.add(burningTree);
            }
            removeBurningTrees(burningTreesToRemove);
            for(Point newBurningTree : allLivingNeighbors) {
                setTreeState(newBurningTree, State.BURNING);
                if(!this.burningTrees.contains(newBurningTree))
                    this.burningTrees.add(newBurningTree);
                this.livingTrees.remove(newBurningTree);
            }
        }
    }

    /**
     * Removes the array of trees from the burningTrees array; this is to allow
     * the converting of BURNING trees to EMPTY trees without causing a
     * ConcurrentModificationException.
     * @param burningTreesToRemove  array of trees to remove from burningTrees.
     */
    public void removeBurningTrees(ArrayList<Point> burningTreesToRemove) {
        for(Point burningTree : burningTreesToRemove) {
            this.burningTrees.remove(burningTree);
        }
    }

    /**
     * Gets all neighbors of a given tree.
     * @param xPos  the x-position of the tree to get the neighbors of.
     * @param yPos  the y-position of the tree to get the neighbors of.
     * @return      an array containing {@link Point}s that are the coordinates
     *              of the neighbors of the given tree.
     */
    public ArrayList<Point> getNeighbors(int xPos, int yPos) {
        ArrayList<Point> neighbors = new ArrayList<>();
        neighbors.add(new Point(xPos - 1, yPos - 1));
        neighbors.add(new Point(xPos, yPos - 1));
        neighbors.add(new Point(xPos + 1, yPos - 1));
        neighbors.add(new Point(xPos - 1, yPos));
        neighbors.add(new Point(xPos + 1, yPos));
        neighbors.add(new Point(xPos - 1, yPos + 1));
        neighbors.add(new Point(xPos, yPos + 1));
        neighbors.add(new Point(xPos + 1, yPos + 1));
        return neighbors;
    }

    /**
     * Gets all the living neighbors of a given tree.
     * @param xPos  the x-position of the tree to get the living neighbors of.
     * @param yPos  the y-position of the tree to get the living neighbors of.
     * @return      an array containing {@link Point}s that are the coordinates
     *              of the living neighbors of the given tree.
     */
    public ArrayList<Point> getLivingNeighbors(int xPos, int yPos) {
        ArrayList<Point> neighbors = getNeighbors(xPos, yPos);
        ArrayList<Point> livingNeighbors = new ArrayList<>();
        for(Point tree : neighbors) {
            try {
                if (getTreeState(tree) == State.LIVING && tree != null)
                    livingNeighbors.add(tree);
            }
            catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return livingNeighbors;
    }

    /**
     * Gets the livingTrees array.
     * @return  the livingTrees array.
     */
    // Getters
    public ArrayList<Point> getLivingTrees() {
        return livingTrees;
    }

    /**
     * Gets the burningTrees array.
     * @return  the burningTrees array.
     */
    public ArrayList<Point> getBurningTrees() {
        return burningTrees;
    }

    /**
     * Gets the emptyTrees array.
     * @return  the emptyTrees array.
     */
    public ArrayList<Point> getEmptyTrees() {
        return emptyTrees;
    }

    // Setters

    /**
     * Sets the size of the {@link Map} to the given width and height.
     * @param width   the width to set the {@link Map} to.
     * @param height  the height to set the {@link Map} to.
     */
    public void setMapSize(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new State[width][height];
        populateMap();

        ArrayList<Point> treesToPlant = new ArrayList<>(getLivingTrees());
        ArrayList<Point> treesToIgnite = new ArrayList<>(getBurningTrees());

        for(Point livingTree : treesToPlant) {
            plantTree((int)livingTree.getX(), (int)livingTree.getY());
        }

        for(Point burningTree : treesToIgnite) {
            igniteTree((int)burningTree.getX(), (int)burningTree.getY());
        }
    }

    // Inspect

    /**
     * Returns true if the {@link Map} has at least 1 burning tree.
     * @return  true if {@link Map} contains at least 1 burning tree, false
     *          otherwise.
     */
    public boolean hasBurningTrees() {
        return (this.burningTrees.size() > 0);
    }
}
