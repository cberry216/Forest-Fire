import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameDriver {

    Map map;

    GameDriver(int width, int height) {
        this.map = new Map(width, height);
    }

    public ArrayList<Point> getTreesToPlant() {
        ArrayList<Point> treesToPlant = new ArrayList<>();
        for(Point emptyTree : this.map.getEmptyTrees()) {
            if(Math.random() < .05) {
                treesToPlant.add(emptyTree);
            }
        }
        return treesToPlant;
    }

    public ArrayList<Point> getTreesToIgnite() {
        ArrayList<Point> treesToIgnite = new ArrayList<>();
        for(Point livingTree : this.map.getLivingTrees()) {
            if(Math.random() < 0.01) {
                treesToIgnite.add(livingTree);
            }
        }
        return treesToIgnite;
    }

    public boolean burnTrees() {
        if(this.map.hasBurningTrees()) {
            this.map.burn();
            return true;
        }
        return false;
    }

    public void plantTrees(ArrayList<Point> treesToPlant) {
        for(Point tree : treesToPlant) {
            this.map.plantTree(tree);
        }
    }

    public void igniteTrees(ArrayList<Point> treesToIgnite) {
        for(Point tree : treesToIgnite) {
            this.map.igniteTree(tree);
        }
    }

    public void step() {
        ArrayList<Point> treesToPlant = getTreesToPlant();
        ArrayList<Point> treesToIgnite = getTreesToIgnite();
        burnTrees();
        plantTrees(treesToPlant);
        igniteTrees(treesToIgnite);
    }

//    public static void main(String[] args) {
//        GameDriver gameDriver = new GameDriver(10, 10);
//        Scanner scan = new Scanner(System.in);
//        while(true) {
//            System.out.println(gameDriver.map.getEmptyTrees());
//            System.out.println(gameDriver.map.getLivingTrees());
//            System.out.println(gameDriver.map.getBurningTrees());
//            scan.nextLine();
//            gameDriver.step();
//        }
//    }
}
