package agh.cs.miner.mapelements;

import agh.cs.miner.basics.Vector2d;
import javafx.scene.image.Image;

public class Miner extends AbstractMapElement {
    private boolean torchON;
    private boolean isInWormhole;

    public Miner(Vector2d position, double visibility, double fuel){
        this.position = position;
        this.fuel = fuel;
        this.visibility =visibility;
        this.points =0;
        this.isMovable = true;
        this.image = new Image(imagePath + "miner.png");
        this.canGoThrough = true;
        this.torchON = true;
        this.isInWormhole = false;
    }

    public double getVisibility() {
        if(torchON) return visibility;
        else return 0;
    }

    public void setVisibility(double maxFuelLevel, double maxVisibility){
        visibility = (maxVisibility * fuel)/maxFuelLevel;
    }

    public void torchONOFF(){
        torchON = !torchON;
    }

    public boolean isTorchON() {
        return torchON;
    }

    public void inWormhole() {
        isInWormhole = !isInWormhole;
    }

    public boolean isInWormhole() {
        return isInWormhole;
    }
}
