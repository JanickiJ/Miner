package agh.cs.miner.mapelements;

import agh.cs.miner.basics.Vector2d;

public interface IPositionChangeObserver {
    void positionChanged(Vector2d newPosition, IMapElement iMapElement);
    void positionWillChange(Vector2d oldPosition, IMapElement iMapElement);
}
