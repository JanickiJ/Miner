package agh.cs.miner.mapelements;
import agh.cs.miner.basics.Vector2d;
import javafx.scene.image.Image;

public class Canister extends AbstractMapElement {
    public Canister(Vector2d position, double fuel){
        this.position = position;
        this.fuel = fuel;
        this.visibility =0;
        this.points =0;
        this.isMovable = false;
        this.image = new Image(imagePath + "canister.png");
        this.canGoThrough = true;
    }
}
