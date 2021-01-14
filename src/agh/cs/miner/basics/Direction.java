package agh.cs.miner.basics;

import java.util.Random;

public enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN;

    public Vector2d toUnitVector() {
        return switch (this) {
            case LEFT -> new Vector2d(-1,0);
            case RIGHT -> new Vector2d(1,0);
            case UP -> new Vector2d(0,-1);
            case DOWN -> new Vector2d(0,1);
        };
    }

    public Direction randomDirection(){
        Random random = new Random();
        return switch (random.nextInt(4)) {
            case 0 -> LEFT;
            case 1 -> RIGHT;
            case 2 -> UP;
            case 3 -> DOWN;
            default -> throw new IllegalStateException("Unexpected value: " + random.nextInt(3));
        };
    }


}
