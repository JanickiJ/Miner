package agh.cs.miner.mapelements;

import agh.cs.miner.basics.Vector2d;
import javafx.scene.image.Image;

public class Diamond extends AbstractMapElement  {

  public Diamond(Vector2d position, int points){
    this.position = position;
    this.fuel = 0;
    this.visibility =0;
    this.points =points;
    this.isMovable = false;
    this.image = new Image(imagePath + "diamond.png");
    this.canGoThrough = true;
  }
}
