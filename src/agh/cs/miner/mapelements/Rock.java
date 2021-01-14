package agh.cs.miner.mapelements;

import agh.cs.miner.basics.Vector2d;
import javafx.scene.image.Image;

public class Rock extends AbstractMapElement {
    public Rock(Vector2d position){
        this.position = position;
        this.fuel = 0;
        this.visibility =0;
        this.points =0;
        this.isMovable = false;
        this.image = new Image( "resources/agh/cs/miner/rock.png");
        this.canGoThrough = false;
    }
}
