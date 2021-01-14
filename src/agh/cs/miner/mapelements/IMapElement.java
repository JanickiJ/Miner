package agh.cs.miner.mapelements;

import agh.cs.miner.basics.Direction;
import agh.cs.miner.basics.Vector2d;
import javafx.scene.image.Image;

public interface IMapElement {
    double getFuel();
    double getVisibility();
    int getPoints();
    void addPoints(int points);
    void addFuel(double fuel);
    Image getImage();
    boolean canGoThrough();
    Vector2d getPosition();
    boolean isLightSource();
    void move(Direction direction);
    boolean isMovable();

    void addObserver(IPositionChangeObserver iPositionChangeObserver);
    void removeObserver(IPositionChangeObserver iPositionChangeObserver);
}
