package agh.cs.miner.map;
import agh.cs.miner.basics.Vector2d;
import agh.cs.miner.engine.OptionsParser;
import agh.cs.miner.mapelements.*;

import java.util.Random;


public class CaveGenerator {
    private final Cave map;
    private final OptionsParser data;

    public CaveGenerator(Cave map, OptionsParser data) {
        this.map = map;
        this.data = data;
    }

    public void generateMap(){
        addCanister();
        addDiamonds();
        addRocks();
        addTorches();
        addTraps();
        addZombies();
        addWormholes();
        addMiner();
    }


    public Vector2d getRandomVector() {
        Vector2d vector;
        Random rand = new Random();
        do {
            int x = rand.nextInt(data.getWidth());
            int y = rand.nextInt(data.getHeight());
            vector = new Vector2d(x, y);
        } while (map.isObjectAt(vector));
        return vector;
    }

    public void addCanister() {
        int i = data.getNumOfCanisters();
        while(i >0){
            Canister canister = new Canister(getRandomVector(), data.getCanisterCapacity());
            map.addObject(canister);
            i--;
        }
    }

    public void addDiamonds() {
        int i = data.getNumOfDiamonds();
        while(i >0){
            Diamond diamond = new Diamond(getRandomVector(), data.getDiamondsPoints());
            map.addObject(diamond);
            i--;
        }
    }

    public void addRocks() {
        int i = data.getNumOfRocks();
        while(i >0){
            Rock rock = new Rock(getRandomVector());
            map.addObject(rock);
            i--;
        }
    }

    public void addTorches() {
        int i = data.getNumOfTorches();
        while(i >0){
            Torch torch = new Torch(getRandomVector(), data.getTorchVisibility());
            map.addObject(torch);
            i--;
        }
    }

    public void addTraps() {
        int i = data.getNumOfTraps();
        while(i >0){
            Trap trap = new Trap(getRandomVector());
            map.addObject(trap);
            i--;
        }
    }

    public void addZombies(){
        int i = data.getNumOfZombies();
        while (i>0){
            Zombie zombie = new Zombie(getRandomVector());
            map.addObject(zombie);
            i--;
        }
    }

    private void addWormholes(){
        int i = data.getNumOfWormholes();
        while (i>0){
            Wormhole wormhole = new Wormhole(getRandomVector());
            map.addObject(wormhole);
            i--;
        }
    }

    public void addMiner(){
        Miner miner = new Miner(getRandomVector(), data.getMinerVisibility(), data.getMinerStartFuel());
        map.setMiner(miner);
    }
}
