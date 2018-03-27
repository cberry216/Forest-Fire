import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameDriverTest {

    private GameDriver gameDriver = new GameDriver(10,10);

    @BeforeEach
    void before() {
        gameDriver = new GameDriver(10, 10);
    }

    @Test
    void testGetTreesToPlant() {
        for(int i = 0; i < 100; i++) {

            ArrayList<Point> treesToPlant = gameDriver.getTreesToPlant();

            assertTrue(treesToPlant.size() <= 100);
            for (Point tree : treesToPlant) {
                assertTrue(tree.x >= 0 && tree.x < 10);
                assertTrue(tree.y >= 0 && tree.y < 10);
                assertEquals(tree.getClass(), (new Point(0, 0)).getClass());
            }
        }
    }

    @Test
    void testGetTreesToIgnite() {
        for(int i = 0; i < 100; i++) {

            ArrayList<Point> treesToIgnite = gameDriver.getTreesToIgnite();

            assertTrue(treesToIgnite.size() <= 100);
            for (Point tree : treesToIgnite) {
                assertTrue(tree.x >= 0 && tree.x < 10);
                assertTrue(tree.y >= 0 && tree.y < 10);
                assertEquals(tree.getClass(), (new Point(0, 0)).getClass());
            }
        }
    }

    @Test
    void testBurnTrees() {
        assertFalse(gameDriver.burnTrees());

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                gameDriver.map.plantTree(i,j);
            }
        }
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(gameDriver.map.getTreeState(i,j), State.LIVING);
            }
        }

        gameDriver.map.igniteTree(4,4);

        assertTrue(gameDriver.burnTrees());

        ArrayList<Point> testBurningTrees = new ArrayList<>();
        testBurningTrees.add(new Point(3,3));
        testBurningTrees.add(new Point(4,3));
        testBurningTrees.add(new Point(5,3));
        testBurningTrees.add(new Point(3,4));
        testBurningTrees.add(new Point(5,4));
        testBurningTrees.add(new Point(3,5));
        testBurningTrees.add(new Point(4,5));
        testBurningTrees.add(new Point(5,5));

        for(Point burningTree : gameDriver.map.getBurningTrees()) {
            assertTrue(testBurningTrees.contains(burningTree));
        }
        assertEquals(gameDriver.map.getBurningTrees().size(), testBurningTrees.size());

        assertEquals(gameDriver.map.getTreeState(4,4), State.EMPTY);
    }

    @Test
    void testPlantTrees() {
        gameDriver.plantTrees(new ArrayList<>());
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(gameDriver.map.getTreeState(i,j), State.EMPTY);
            }
        }

        ArrayList<Point> treesToPlant = new ArrayList<>();

        treesToPlant.add(new Point(4,4));
        treesToPlant.add(new Point(5,4));
        treesToPlant.add(new Point(6,4));
        treesToPlant.add(new Point(4,5));
        treesToPlant.add(new Point(5,5));
        treesToPlant.add(new Point(6,5));

        gameDriver.plantTrees(treesToPlant);

        for(Point tree : gameDriver.map.getLivingTrees()) {
            assertTrue(treesToPlant.contains(tree));
        }
        assertEquals(gameDriver.map.getLivingTrees().size(), treesToPlant.size());
    }

    @Test
    void testIgniteTrees() {
        gameDriver.igniteTrees(new ArrayList<>());
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(gameDriver.map.getTreeState(i,j), State.EMPTY);
            }
        }

        gameDriver.map.plantTree(new Point(4,4));
        gameDriver.map.plantTree(new Point(5,4));
        gameDriver.map.plantTree(new Point(6,4));
        gameDriver.map.plantTree(new Point(4,5));
        gameDriver.map.plantTree(new Point(5,5));
        gameDriver.map.plantTree(new Point(6,5));


        ArrayList<Point> treesToIgnite = new ArrayList<>();

        treesToIgnite.add(new Point(4,4));
        treesToIgnite.add(new Point(5,4));
        treesToIgnite.add(new Point(6,4));
        treesToIgnite.add(new Point(4,5));
        treesToIgnite.add(new Point(5,5));
        treesToIgnite.add(new Point(6,5));

        gameDriver.igniteTrees(treesToIgnite);
        for(Point tree : gameDriver.map.getBurningTrees()) {
            assertTrue(treesToIgnite.contains(tree));
        }
        assertEquals(gameDriver.map.getBurningTrees().size(), treesToIgnite.size());
    }
}