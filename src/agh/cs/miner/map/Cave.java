package agh.cs.miner.map;

import agh.cs.miner.basics.Direction;
import agh.cs.miner.basics.Vector2d;
import agh.cs.miner.engine.OptionsParser;
import agh.cs.miner.mapelements.IMapElement;
import agh.cs.miner.mapelements.IPositionChangeObserver;
import agh.cs.miner.mapelements.Miner;
import agh.cs.miner.mapelements.Zombie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Cave implements IPositionChangeObserver {
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final Map<Vector2d, IMapElement> map;
    private Miner miner;
    private final OptionsParser optionsParser;
    private final LinkedList<IMapElement> lightSources;
    private final LinkedList<IMapElement> movableIMapElements;

    public Cave(OptionsParser optionsParser){
        this.map = new HashMap<>();
        this.lowerLeft = new Vector2d(0,0);
        this.optionsParser = optionsParser;
        this.upperRight = new Vector2d(optionsParser.getWidth()-1, optionsParser.getHeight()-1);
        this.lightSources = new LinkedList<>();
        this.movableIMapElements = new LinkedList<>();
        CaveGenerator generator = new CaveGenerator(this,optionsParser);
        generator.generateMap();
    }


    public boolean canMoveTo(Vector2d position){
        if(!inBorders(position)) return false;
        else if(objectAt(position)!=null) return objectAt(position).canGoThrough();
        else return true;
    }

    public IMapElement objectAt(Vector2d position){
        if(isObjectAt(position)) return map.get(position);
        else return null;
    }

    public boolean isObjectAt(Vector2d position){
        return map.containsKey(position);
    }

    public void removeObject(IMapElement iMapElement){
        map.remove(iMapElement.getPosition());
        iMapElement.removeObserver(this);
    }

    public void addObject(IMapElement iMapElement){
        if(iMapElement.isLightSource()) lightSources.add(iMapElement);
        if(iMapElement.isMovable()) movableIMapElements.add(iMapElement);
        map.put(iMapElement.getPosition(),iMapElement);
        iMapElement.addObserver(this);
    }

    public void moveZombie(IMapElement iMapElement){
        Direction direction = Direction.DOWN.randomDirection();
        Vector2d newPosition = iMapElement.getPosition().add(direction.toUnitVector());

        if(canMoveTo(newPosition)){
            if(!isObjectAt(newPosition)){
                iMapElement.move(direction);
            }
            else if(objectAt(newPosition) == miner) {
                miner.addFuel(iMapElement.getFuel());
            }
        }
    }

    public void moveMap(Direction direction){
        moveMiner(direction);
        for(IMapElement iMapElement: movableIMapElements){
            if(iMapElement instanceof Zombie){
                moveZombie(iMapElement);
            }
        }
    }

    public void moveMiner(Direction direction) {
        Vector2d newPosition = miner.getPosition().add(direction.toUnitVector());
        spendFuel();
        if (canMoveTo(newPosition)){
            if(isObjectAt(newPosition)) {
                IMapElement objectAt = objectAt(newPosition);
                miner.addFuel(objectAt.getFuel());
                miner.addPoints(objectAt.getPoints());
                removeObject(objectAt);
            }
            miner.move(direction);
        }
        miner.setVisibility(optionsParser.getMinerStartFuel(), optionsParser.getMinerVisibility());
    }

    public boolean inBorders(Vector2d position){
        return (position.precedes(upperRight) && position.follows(lowerLeft));
    }
    public boolean isVisible(Vector2d vector2d){
        for(IMapElement light: lightSources){
            if (light.getPosition().distance(vector2d) <= light.getVisibility()) return true;
        }
        return false;
    }

    public void torchONOFF(){
        miner.torchONOFF();
    }
    public void spendFuel(){
        if(miner.isTorchON()){
            miner.addFuel(-1);
        }
    }
    public boolean win(){
        return optionsParser.getWinScore() == getScore();
    }

    public void setMiner(Miner miner){
        this.miner = miner;
        addObject(miner);
    }

    public int getScore(){
        return miner.getPoints();
    }
    public double getFuel(){
        return miner.getFuel();
    }
    public boolean minerHasFuel(){
        return miner.getFuel()>0;
    }

    @Override
    public void positionChanged(Vector2d newPosition, IMapElement iMapElement) {
        map.put(newPosition, iMapElement);
    }

    @Override
    public void positionWillChange(Vector2d oldPosition, IMapElement iMapElement){
        map.remove(oldPosition,iMapElement);
    }
}
