package agh.cs.miner.map;

import agh.cs.miner.basics.Direction;
import agh.cs.miner.basics.Vector2d;
import agh.cs.miner.engine.OptionsParser;
import agh.cs.miner.mapelements.IMapElement;
import agh.cs.miner.mapelements.Miner;
import agh.cs.miner.mapelements.Wormhole;
import agh.cs.miner.mapelements.Zombie;

public class CaveSimulation {
    private final Cave map;
    private final Miner miner;
    private final OptionsParser parameters;

    public CaveSimulation(Cave cave) {
        this.map = cave;
        this.parameters = map.getOptionsParser();
        this.miner = map.getMiner();

    }

    public void moveMap(Direction direction){
        moveMiner(direction);
        for(IMapElement iMapElement: map.getMovableIMapElements()){
            if(iMapElement instanceof Zombie){
                moveZombie(iMapElement);
            }
        }
    }



    private void moveZombie(IMapElement iMapElement){
        Direction direction = Direction.DOWN.randomDirection();
        Vector2d newPosition = iMapElement.getPosition().add(direction.toUnitVector());

        if(map.canMoveTo(newPosition)){
            if(!map.isObjectAt(newPosition)){
                iMapElement.move(direction);
            }
            else if(map.objectAt(newPosition) == miner) {
                miner.addFuel(iMapElement.getFuel());
            }
        }
    }

    private void moveMiner(Direction direction) {
        Vector2d newPosition = miner.getPosition().add(direction.toUnitVector());
        spendFuel();
        if (map.canMoveTo(newPosition)){
            if(map.isObjectAt(newPosition)) {

                if (map.objectAt(newPosition) instanceof Wormhole) { miner.inWormhole();}

                else {
                    IMapElement objectAt = map.objectAt(newPosition);
                    miner.addFuel(objectAt.getFuel());
                    miner.addPoints(objectAt.getPoints());
                    map.removeObject(objectAt);
                }

            }
            miner.move(direction);
        }
        miner.setVisibility(parameters.getMinerStartFuel(), parameters.getMinerVisibility());
    }

    public void spendFuel(){
        if(miner.isTorchON()){
            miner.addFuel(-1);
        }
    }
}
