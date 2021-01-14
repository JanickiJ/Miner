package test.agh.cs.miner;


import agh.cs.miner.basics.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector2dTest {

    @Test
    public void distanceTest(){
        Vector2d v1 = new Vector2d(4,4);
        Vector2d u1 = new Vector2d(4,4);
        assertEquals(0,v1.distance(u1));


        Vector2d v2 = new Vector2d(5,6);
        Vector2d u2 = new Vector2d(5,0);
        assertEquals(6,v2.distance(u2));

        Vector2d v3 = new Vector2d(6,6);
        Vector2d u3 = new Vector2d(0,0);
        assertEquals(8,v3.distance(u3));

        Vector2d v4 = new Vector2d(0,0);
        Vector2d u4 = new Vector2d(2,1);
        assertEquals(2,v4.distance(u4));
    }

}