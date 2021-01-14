package agh.cs.miner.mapelements;
import agh.cs.miner.basics.Vector2d;
import javafx.scene.image.Image;

public class Carnister extends AbstractMapElement {
    public Carnister(Vector2d position, double fuel){
        this.position = position;
        this.fuel = fuel;
        this.visibility =0;
        this.points =0;
        this.isMovable = false;
        this.image = new Image("resources/agh/cs/miner/carnister.png");
        this.canGoThrough = true;
    }
}
