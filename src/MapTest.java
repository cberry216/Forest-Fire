import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    private Map map = new Map(10,10);

    @BeforeEach
    void before() {
        map = new Map(10,10);
    }

    @Test
    void testInitPopulateMap() {
        assertEquals(map.height, 10);
        assertEquals(map.width, 10);
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.map[i][j], State.EMPTY);
            }
        }
        assertEquals(map.emptyTrees.size(), 100);
    }

    @Test
    void testGetTreeState() {
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i, j), State.EMPTY);
            }
        }
        map.map[5][5] = State.LIVING;
        assertEquals(map.getTreeState(5,5), State.LIVING);
    }

    @Test
    void testGetTreeStateAlt() {
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(new Point(i,j)), State.EMPTY);
            }
        }
        map.map[5][5] = State.LIVING;
        assertEquals(map.getTreeState(new Point(5,5)), State.LIVING);
    }

    @Test
    void testSetTreeState() {
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.setTreeState(i, j, State.BURNING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i, j), State.BURNING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.setTreeState(i, j, State.LIVING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i, j), State.LIVING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.setTreeState(i, j, State.EMPTY);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i, j), State.EMPTY);
            }
        }
    }

    @Test
    void testSetTreeStateAlt() {
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.setTreeState(new Point(i, j), State.BURNING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i, j), State.BURNING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.setTreeState(new Point(i, j), State.LIVING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i, j), State.LIVING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.setTreeState(new Point(i, j), State.EMPTY);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i, j), State.EMPTY);
            }
        }
    }

    @Test
    void testIgniteTree() {
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.igniteTree(i,j);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i,j), State.EMPTY);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.setTreeState(i, j, State.LIVING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.igniteTree(i,j);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i,j), State.BURNING);
            }
        }
        assertEquals(map.burningTrees.size(), 100);
    }

    @Test
    void testIgniteTreeAlt() {
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.igniteTree(new Point(i,j));
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i,j), State.EMPTY);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.setTreeState(i, j, State.LIVING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.igniteTree(new Point(i,j));
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i,j), State.BURNING);
            }
        }
        assertEquals(map.burningTrees.size(), 100);
    }

    @Test
    void testPlantTree() {
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.plantTree(i,j);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i,j), State.LIVING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.setTreeState(i, j, State.BURNING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.plantTree(i,j);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i,j), State.BURNING);
            }
        }
        assertEquals(map.livingTrees.size(), 100);
    }


    @Test
    void testPlantTreeAlt() {
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.plantTree(new Point(i,j));
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i,j), State.LIVING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.setTreeState(i, j, State.BURNING);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.plantTree(new Point(i,j));
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i,j), State.BURNING);
            }
        }
    }

    @Test
    void testBurn() {
        map.burn();
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i, j), State.EMPTY);
            }
        }
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                map.plantTree(i, j);
            }
        }
        map.burn();
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i, j), State.LIVING);
            }
        }
        map.igniteTree(5,5);
        assertEquals(map.burningTrees.size(), 1);
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                if(i == 5 && j == 5)
                    assertEquals(map.getTreeState(i, j), State.BURNING);
                else
                    assertEquals(map.getTreeState(i, j), State.LIVING);
            }
        }
        map.burn();

        assertEquals(map.burningTrees.size(), 8);
        assertEquals(map.getTreeState(4,4), State.BURNING);
        assertEquals(map.getTreeState(5,4), State.BURNING);
        assertEquals(map.getTreeState(6,4), State.BURNING);
        assertEquals(map.getTreeState(4,5), State.BURNING);
        assertEquals(map.getTreeState(6,5), State.BURNING);
        assertEquals(map.getTreeState(4,6), State.BURNING);
        assertEquals(map.getTreeState(5,6), State.BURNING);
        assertEquals(map.getTreeState(6,6), State.BURNING);

        assertEquals(map.getTreeState(5,5), State.EMPTY);

        map.burn();

        assertEquals(map.getTreeState(3, 3), State.BURNING);
        assertEquals(map.getTreeState(4, 3), State.BURNING);
        assertEquals(map.getTreeState(5, 3), State.BURNING);
        assertEquals(map.getTreeState(6, 3), State.BURNING);
        assertEquals(map.getTreeState(7, 3), State.BURNING);
        assertEquals(map.getTreeState(3, 4), State.BURNING);
        assertEquals(map.getTreeState(7, 4), State.BURNING);
        assertEquals(map.getTreeState(3, 5), State.BURNING);
        assertEquals(map.getTreeState(7, 5), State.BURNING);
        assertEquals(map.getTreeState(3, 6), State.BURNING);
        assertEquals(map.getTreeState(7, 6), State.BURNING);
        assertEquals(map.getTreeState(3, 7), State.BURNING);
        assertEquals(map.getTreeState(4, 7), State.BURNING);
        assertEquals(map.getTreeState(5, 7), State.BURNING);
        assertEquals(map.getTreeState(6, 7), State.BURNING);
        assertEquals(map.getTreeState(7, 7), State.BURNING);

        assertEquals(map.getTreeState(4, 4), State.EMPTY);
        assertEquals(map.getTreeState(5, 4), State.EMPTY);
        assertEquals(map.getTreeState(6, 4), State.EMPTY);
        assertEquals(map.getTreeState(4, 5), State.EMPTY);
        assertEquals(map.getTreeState(5, 5), State.EMPTY);
        assertEquals(map.getTreeState(6, 5), State.EMPTY);
        assertEquals(map.getTreeState(4, 6), State.EMPTY);
        assertEquals(map.getTreeState(5, 6), State.EMPTY);
        assertEquals(map.getTreeState(6, 6), State.EMPTY);
    }

    @Test
    void testRemoveBurningTrees() {
        map.burningTrees.add(new Point(1,1));
        map.burningTrees.add(new Point(1,2));
        map.burningTrees.add(new Point(1,3));
        map.burningTrees.add(new Point(1,4));

        ArrayList<Point> testPoint = new ArrayList<>();
        testPoint.add(new Point(1,2));
        testPoint.add(new Point(1,4));

        map.removeBurningTrees(testPoint);

        assertEquals(map.burningTrees.size(), 2);
        assertTrue(map.burningTrees.contains(new Point(1,1)));
        assertTrue(map.burningTrees.contains(new Point(1,3)));
        assertFalse(map.burningTrees.contains(new Point(1,2)));
        assertFalse(map.burningTrees.contains(new Point(1,4)));
    }

    @Test
    void testGetNeighbors() {
        ArrayList<Point> neighbors = map.getNeighbors(0,0);
        ArrayList<Point> testNeighbors = new ArrayList<>();
        testNeighbors.add(new Point(-1, -1));
        testNeighbors.add(new Point(0, -1));
        testNeighbors.add(new Point(1, -1));
        testNeighbors.add(new Point(-1, 0));
        testNeighbors.add(new Point(1, 0));
        testNeighbors.add(new Point(-1, 1));
        testNeighbors.add(new Point(0, 1));
        testNeighbors.add(new Point(1, 1));
        assertEquals(neighbors, testNeighbors);

        neighbors = map.getNeighbors(5,5);
        testNeighbors.clear();
        testNeighbors.add(new Point(4, 4));
        testNeighbors.add(new Point(5, 4));
        testNeighbors.add(new Point(6, 4));
        testNeighbors.add(new Point(4, 5));
        testNeighbors.add(new Point(6, 5));
        testNeighbors.add(new Point(4, 6));
        testNeighbors.add(new Point(5, 6));
        testNeighbors.add(new Point(6, 6));
        assertEquals(neighbors, testNeighbors);
    }

    @Test
    void testGetLivingNeighbors() {
        map.plantTree(1,0);
        map.plantTree(1,1);
        map.plantTree(0,1);

        ArrayList<Point> neighbors = map.getLivingNeighbors(0, 0);
        ArrayList<Point> testNeighbors = new ArrayList<>();

        testNeighbors.add(new Point(1, 0));
        testNeighbors.add(new Point(1, 1));
        testNeighbors.add(new Point(0, 1));

        for(Point livingTree : neighbors) {
            assertTrue(testNeighbors.contains(livingTree));
        }
        assertEquals(neighbors.size(), testNeighbors.size());

        neighbors = map.getLivingNeighbors(6,6);
        testNeighbors.clear();

        assertEquals(neighbors, testNeighbors);

        map.plantTree(5,5);
        map.plantTree(7,5);
        map.plantTree(5,6);
        map.plantTree(7,6);
        map.plantTree(5,7);
        map.plantTree(7,7);

        neighbors = map.getLivingNeighbors(6,6);
        testNeighbors.add(new Point(5,5));
        testNeighbors.add(new Point(7,5));
        testNeighbors.add(new Point(5,6));
        testNeighbors.add(new Point(7,6));
        testNeighbors.add(new Point(5,7));
        testNeighbors.add(new Point(7,7));

        for(Point livingTree : neighbors) {
            assertTrue(testNeighbors.contains(livingTree));
        }
        assertEquals(neighbors.size(), testNeighbors.size());
    }

    @Test
    void testGetLivingTrees() {
        assertEquals(new ArrayList<Point>(), map.getLivingTrees());

        map.plantTree(4,4);
        map.plantTree(4,5);
        map.plantTree(4,6);

        ArrayList<Point> testTrees = new ArrayList<>();
        testTrees.add(new Point(4, 4));
        testTrees.add(new Point(4, 5));
        testTrees.add(new Point(4, 6));

        for(Point livingTree : map.getLivingTrees()) {
            assertTrue(testTrees.contains(livingTree));
        }
        assertEquals(map.getLivingTrees().size(), testTrees.size());
    }

    @Test
    void testGetBurningTrees() {
        assertEquals(new ArrayList<Point>(), map.getBurningTrees());

        map.plantTree(4,4);
        map.plantTree(4,5);
        map.plantTree(4,6);

        map.igniteTree(4,4);
        map.igniteTree(4,5);
        map.igniteTree(4,6);

        ArrayList<Point> testTrees = new ArrayList<>();
        testTrees.add(new Point(4, 4));
        testTrees.add(new Point(4, 5));
        testTrees.add(new Point(4, 6));

        for(Point burningTree : map.getBurningTrees()) {
            assertTrue(testTrees.contains(burningTree));
        }
        assertEquals(map.getBurningTrees().size(), testTrees.size());
    }

    @Test
    void testGetEmptyTrees() {
        ArrayList<Point> testTrees = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                testTrees.add(new Point(i, j));
            }
        }

        for(Point emptyTree : map.getEmptyTrees()) {
            assertTrue(testTrees.contains(emptyTree));
        }
        assertEquals(map.getEmptyTrees().size(), testTrees.size());

        map.plantTree(0,0);
        map.plantTree(1,0);
        map.plantTree(2,0);
        map.plantTree(3,0);
        map.plantTree(4,0);
        map.plantTree(5,0);
        map.plantTree(6,0);
        map.plantTree(7,0);
        map.plantTree(8,0);
        map.plantTree(9,0);

        testTrees.clear();
        for(int i = 0; i < 10; i++) {
            for(int j = 1; j < 10; j++) {
                testTrees.add(new Point(i, j));
            }
        }

        for(Point emptyTree : map.getEmptyTrees()) {
            assertTrue(testTrees.contains(emptyTree));
        }
        assertEquals(map.getEmptyTrees().size(), testTrees.size());
    }

    @Test
    void testSetMapSize() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                assertEquals(map.getTreeState(i, j), State.EMPTY);
            }
        }

        map.setMapSize(12,12);
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 12; j++) {
                assertEquals(map.getTreeState(i, j), State.EMPTY);
            }
        }

        map.plantTree(4,4);
        map.plantTree(5,4);
        map.plantTree(6,4);
        map.plantTree(4,5);
        map.plantTree(5,5);
        map.plantTree(6,5);

        map.igniteTree(4,4);
        map.igniteTree(5,4);
        map.igniteTree(6,4);

        ArrayList<Point> previousLivingTrees = map.getLivingTrees();
        ArrayList<Point> previousBurningTrees = map.getBurningTrees();

        map.setMapSize(15,15);

        for(Point livingTree : map.getLivingTrees()) {
            assertTrue(previousLivingTrees.contains(livingTree));
        }
        for(Point burningTree : map.getBurningTrees()) {
            assertTrue(previousBurningTrees.contains(burningTree));
        }

        assertEquals(previousLivingTrees.size(), map.getLivingTrees().size());
        assertEquals(previousBurningTrees.size(), map.getBurningTrees().size());
    }

    @Test
    void testHasBurningTrees() {
        assertFalse(map.hasBurningTrees());

        map.plantTree(4,4);
        map.plantTree(5,4);
        map.plantTree(6,4);

        assertFalse(map.hasBurningTrees());

        map.igniteTree(4,4);
        map.igniteTree(5,4);
        map.igniteTree(6,4);

        assertTrue(map.hasBurningTrees());
    }

}