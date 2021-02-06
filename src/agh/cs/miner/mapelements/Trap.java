package agh.cs.miner.mapelements;

import agh.cs.miner.basics.Vector2d;
import javafx.scene.image.Image;

public class Trap extends AbstractMapElement {
    public Trap(Vector2d position){
        this.position = position;
        this.fuel = Integer.MIN_VALUE;
        this.visibility =0;
        this.points =0;
        this.isMovable = false;
        this.image = new Image(imagePath + "trap2.png");
        this.canGoThrough = true;
    }
}
