package agh.cs.miner.mapelements;

import agh.cs.miner.basics.Direction;
import agh.cs.miner.basics.Vector2d;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractMapElement implements IMapElement {
    protected Vector2d position;
    protected double fuel;
    protected double visibility;
    protected Image image;
    protected int points;
    protected boolean isMovable;
    protected boolean canGoThrough;
    private final List<IPositionChangeObserver> observers = new LinkedList<>();



    @Override
    public double getFuel() {
        return fuel;
    }

    @Override
    public void addFuel(double fuel) {
        this.fuel += fuel;
    }

    @Override
    public double getVisibility() {
        return visibility;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean canGoThrough() {
        return canGoThrough;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public int getPoints(){
        return points;
    }

    @Override
    public void addPoints(int points){
        this.points += points;
    }

    @Override
    public boolean isLightSource(){
        return visibility>0;
    }

    @Override
    public boolean isMovable(){
        return isMovable;
    }

    @Override
    public void move(Direction direction){
        positionWillChanged();
        position = position.add(direction.toUnitVector());
        positionChanged();
    }

    public void positionChanged(){
        for(IPositionChangeObserver o : observers){
            o.positionChanged(position,this);
        }
    }

    public void positionWillChanged(){
        for(IPositionChangeObserver o : observers){
            o.positionWillChange(position,this);
        }
    }

    public void addObserver(IPositionChangeObserver observer){
        if (!observers.contains(observer)){
            observers.add(observer);
        }
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
}
