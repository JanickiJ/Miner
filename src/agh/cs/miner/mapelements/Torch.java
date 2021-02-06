package agh.cs.miner.mapelements;

import agh.cs.miner.basics.Vector2d;
import javafx.scene.image.Image;

public class Torch extends AbstractMapElement {
    public Torch(Vector2d position, int visibility){
        this.position = position;
        this.fuel = 0;
        this.visibility =visibility;
        this.points =0;
        this.isMovable = false;
        this.image = new Image(imagePath + "torch.png");
        this.canGoThrough = false;
    }
}
