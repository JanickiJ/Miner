package agh.cs.miner.mapelements;

import agh.cs.miner.basics.Vector2d;
import javafx.scene.image.Image;

public class Zombie extends AbstractMapElement {

        public Zombie(Vector2d position){
            this.position = position;
            this.fuel = Integer.MIN_VALUE;
            this.visibility =0;
            this.isMovable = true;
            this.points =0;
            this.image = new Image(imagePath + "zombie.png");
            this.canGoThrough = true;
        }

}
