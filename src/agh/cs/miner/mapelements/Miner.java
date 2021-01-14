package agh.cs.miner.mapelements;

import agh.cs.miner.basics.Vector2d;
import javafx.scene.image.Image;

public class Miner extends AbstractMapElement {
    private boolean torchON;

    public Miner(Vector2d position, double visibility, double fuel){
        this.position = position;
        this.fuel = fuel;
        this.visibility =visibility;
        this.points =0;
        this.isMovable = true;
        this.image = new Image("resources/agh/cs/miner/miner.png");
        this.canGoThrough = true;
        this.torchON = true;
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
}
