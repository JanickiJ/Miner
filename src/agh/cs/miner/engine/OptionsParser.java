package agh.cs.miner.engine;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class OptionsParser {

    private int width;
    private int height;
    private int numOfCanisters;
    private int numOfDiamonds;
    private int numOfTorches;
    private int numOfTraps;
    private int numOfRocks;
    private int numOfZombies;
    private int numOfWormholes;
    private int canisterCapacity;
    private double minerStartFuel;
    private double minerVisibility;
    private int diamondsPoints;
    private int torchVisibility;


    public static OptionsParser loadPropFromFile(String path) throws FileNotFoundException,IllegalArgumentException {
        Gson gson = new Gson();
        OptionsParser parameters = gson.fromJson(new FileReader(path), OptionsParser.class);
        parameters.testData();
        return parameters;
    }

    public void testData() throws IllegalArgumentException{
        String incorrect = "Incorrect data: ";
        if(width <= 0){ throw new IllegalArgumentException(incorrect + "map width");}
        if(height <= 0){ throw new IllegalArgumentException(incorrect + "map height");}
        if(numOfCanisters <= 0){ throw new IllegalArgumentException(incorrect + "number of canisters");}
        if(numOfDiamonds <= 0){ throw new IllegalArgumentException(incorrect + "number of diamonds");}
        if(numOfTorches <= 0){ throw new IllegalArgumentException(incorrect + "number of torches");}
        if(numOfRocks <= 0){ throw new IllegalArgumentException(incorrect + "number of rocks");}
        if(numOfZombies <= 0){ throw new IllegalArgumentException(incorrect + "number of zombies");}
        if(numOfWormholes <= 0){ throw new IllegalArgumentException(incorrect + "number of wormholes");}
        if(canisterCapacity <= 0){ throw new IllegalArgumentException(incorrect + "canister capacity");}
        if(minerStartFuel <= 0){ throw new IllegalArgumentException(incorrect + "miner start fuel");}
        if(minerVisibility <= 0){ throw new IllegalArgumentException(incorrect + "miner visibility");}
        if(diamondsPoints <= 0){ throw new IllegalArgumentException(incorrect + "diamond points");}
        if(torchVisibility <= 0){ throw new IllegalArgumentException(incorrect + "torch visibility");}
        int mapObjects = numOfCanisters + numOfDiamonds + numOfTorches + numOfRocks + numOfZombies + numOfWormholes + 1;
        if(mapObjects > width * height){ throw new IllegalArgumentException("To many objects");}
    }

    public int getCanisterCapacity() {
        return canisterCapacity;
    }

    public int getDiamondsPoints() {
        return diamondsPoints;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double getMinerStartFuel() {
        return minerStartFuel;
    }

    public int getNumOfCanisters() {
        return numOfCanisters;
    }

    public int getNumOfDiamonds() {
        return numOfDiamonds;
    }

    public int getNumOfRocks() {
        return numOfRocks;
    }

    public int getNumOfZombies() {
        return numOfZombies;
    }

    public int getNumOfTorches() {
        return numOfTorches;
    }

    public int getNumOfTraps() {
        return numOfTraps;
    }

    public int getTorchVisibility() {
        return torchVisibility;
    }

    public double getMinerVisibility() {
        return minerVisibility;
    }

    public int getWinScore(){
        return diamondsPoints*numOfDiamonds;
    }

    public int getNumOfWormholes() {
        return numOfWormholes;
    }
}