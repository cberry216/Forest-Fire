import java.awt.*;
import java.util.ArrayList;

public class Map {

    State[][] map;
    ArrayList<Point> livingTrees, burningTrees, emptyTrees;
    int width, height;

    Map(int width, int height) {
        this.width = width;
        this.height = height;

        this.map = new State[this.width][this.height];
        this.livingTrees = new ArrayList<>();
        this.burningTrees = new ArrayList<>();
        this.emptyTrees = new ArrayList<>();

        populateMap();
    }

    private void populateMap() {
        for(int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                this.map[i][j] = State.EMPTY;
                this.emptyTrees.add(new Point(i,j));
            }
        }
    }

    public State getTreeState(int xPos, int yPos) {
        return this.map[xPos][yPos];
    }

    public State getTreeState(Point point) {
        return this.map[(int)point.getX()][(int)point.getY()];
    }

    public void setTreeState(int xPos, int yPos, State state) {
        this.map[xPos][yPos] = state;
    }

    public void setTreeState(Point point, State state) {
        this.map[(int)point.getX()][(int)point.getY()] = state;
    }

    public void igniteTree(int xPos, int yPos) {
        if(getTreeState(xPos, yPos) == State.LIVING) {
//            System.out.printf("Igniting tree @ (%d, %d)\n", xPos, yPos);
            setTreeState(xPos, yPos, State.BURNING);
            Point treeToIgnite = new Point(xPos, yPos);
            this.burningTrees.add(treeToIgnite);
            this.livingTrees.remove(treeToIgnite);
        }
    }

    public void igniteTree(Point tree) {
        igniteTree((int)tree.getX(), (int)tree.getY());
    }

    public void plantTree(int xPos, int yPos) {
        if(getTreeState(xPos, yPos) == State.EMPTY) {
//            System.out.printf("Planting tree @ (%d, %d)\n", xPos, yPos);
            setTreeState(xPos, yPos, State.LIVING);
            Point treeToPlant = new Point(xPos, yPos);
            this.livingTrees.add(treeToPlant);
            this.emptyTrees.remove(treeToPlant);
        }
    }

    public void plantTree(Point tree) {
        plantTree((int)tree.getX(), (int)tree.getY());
    }

    public void burn() {
        if(this.livingTrees.size() > 0) {
//            System.out.println("Burning Trees...");
            ArrayList<Point> allLivingNeighbors = new ArrayList<>();
            ArrayList<Point> burningTreesToRemove = new ArrayList<>();
//            System.out.println("                A: Empty   Trees: " + this.emptyTrees);
//            System.out.println("                A: Living  Trees: " + this.livingTrees);
//            System.out.println("                A: Burning Trees: " + this.burningTrees);
            for(Point burningTree : this.burningTrees) {
//                System.out.printf("                                   Burning Tree @ (%d, %d)\n", (int)burningTree.getX(), (int)burningTree.getY());
//                System.out.println("                                   " + getLivingNeighbors((int)burningTree.getX(), (int)burningTree.getY()));
                allLivingNeighbors.addAll(getLivingNeighbors((int)burningTree.getX(), (int)burningTree.getY()));
                setTreeState(burningTree, State.EMPTY);
                burningTreesToRemove.add(burningTree);
                this.emptyTrees.add(burningTree);
            }
            removeBurningTrees(burningTreesToRemove);
//            System.out.println("                B: Empty   Trees: " + this.emptyTrees);
//            System.out.println("                B: Living  Trees: " + this.livingTrees);
//            System.out.println("                B: Burning Trees: " + this.burningTrees);
            for(Point newBurningTree : allLivingNeighbors) {
                setTreeState(newBurningTree, State.BURNING);
                if(!this.burningTrees.contains(newBurningTree))
                    this.burningTrees.add(newBurningTree);
                this.livingTrees.remove(newBurningTree);
            }
//            System.out.println("                C: Empty   Trees: " + this.emptyTrees);
//            System.out.println("                C: Living  Trees: " + this.livingTrees);
//            System.out.println("                C: Burning Trees: " + this.burningTrees);
        }
    }

    public void removeBurningTrees(ArrayList<Point> burningTreesToRemove) {
        for(Point burningTree : burningTreesToRemove) {
            this.burningTrees.remove(burningTree);
        }
    }

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

//    public ArrayList<Tree> getNeighbors(Tree tree) {
//        getNeighbors(tree.getXPos(), tree.getYPos());
//    }

    public ArrayList<Point> getLivingNeighbors(int xPos, int yPos) {
        ArrayList<Point> neighbors = getNeighbors(xPos, yPos);
        ArrayList<Point> livingNeighbors = new ArrayList<>();
//        System.out.printf("                                   Neighbors of (%d, %d): ", xPos, yPos);
//        System.out.println(neighbors);
        for(Point tree : neighbors) {
            try {
                if (getTreeState(tree) == State.LIVING && tree != null)
                    livingNeighbors.add(tree);
            }
            catch (ArrayIndexOutOfBoundsException e) {
//                System.out.printf("Tree @ (%d, %d) out of bounds\n", (int)tree.getX(), (int)tree.getY());
            }
        }
//        System.out.printf("                                   Living Neighbors of (%d, %d): ", xPos, yPos);
//        System.out.println(livingNeighbors);
        return livingNeighbors;
    }

//    public void transferMap(Map newMap) {
//        newMap.map.putAll(this.map);
//        this.map = newMap.map;
//    }

    // Getters
    public ArrayList<Point> getLivingTrees() {
        return livingTrees;
    }

    public ArrayList<Point> getBurningTrees() {
        return burningTrees;
    }

    public ArrayList<Point> getEmptyTrees() {
        return emptyTrees;
    }

    // Setters
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
    public boolean hasBurningTrees() {
        return (this.burningTrees.size() > 0);
    }
}
