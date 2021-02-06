package agh.cs.miner.mapelements;

import agh.cs.miner.basics.Direction;
import agh.cs.miner.basics.Vector2d;
import javafx.scene.image.Image;

public interface IMapElement {

    double getFuel();
    double getVisibility();
    int getPoints();
    Vector2d getPosition();
    Image getImage();

    void addPoints(int points);
    void addFuel(double fuel);
    void move(Direction direction);

    boolean canGoThrough();
    boolean isLightSource();
    boolean isMovable();

    void addObserver(IPositionChangeObserver iPositionChangeObserver);
    void removeObserver(IPositionChangeObserver iPositionChangeObserver);
}
