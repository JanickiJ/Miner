package agh.cs.miner.engine;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class OptionsParser {

    private int width;
    private int height;
    private int numOfCarnisters;
    private int numOfDiamonds;
    private int numOfTorches;
    private int numOfTraps;
    private int numOfRocks;
    private int numOfZombies;
    private int numOfWormholes;
    private int carnisterCapacity;
    private double minerStartFuel;
    private double minerVisibility;
    private int diamondsPoints;
    private int torchVisibility;


    public static OptionsParser loadPropFromFile() throws FileNotFoundException,IllegalArgumentException {
        Gson gson = new Gson();
        OptionsParser parameters = gson.fromJson(new FileReader("src\\agh\\cs\\miner\\parameters.json"), OptionsParser.class);
        parameters.validate();
        return parameters;
    }

    public void validate() throws IllegalArgumentException{
        if(this.width <= 0){ throw new IllegalArgumentException("Invalid map width");}
        if(this.height <= 0){ throw new IllegalArgumentException("Invalid map height");}
        if(this.numOfCarnisters <= 0){ throw new IllegalArgumentException("Invalid number of carnisters");}
        if(this.numOfDiamonds <= 0){ throw new IllegalArgumentException("Invalid number of diamonds");}
        if(this.numOfTorches <= 0){ throw new IllegalArgumentException("Invalid number of torches");}
        if(this.numOfRocks <= 0){ throw new IllegalArgumentException("Invalid number of rocks");}
        if(this.numOfZombies <= 0){ throw new IllegalArgumentException("Invalid number of zombies");}
        if(this.numOfWormholes <= 0){ throw new IllegalArgumentException("Invalid number of wormholes");}
        if(this.carnisterCapacity <= 0){ throw new IllegalArgumentException("Invalid carnister capacity");}
        if(this.minerStartFuel <= 0){ throw new IllegalArgumentException("Invalid miner start fuel");}
        if(this.minerVisibility <= 0){ throw new IllegalArgumentException("Invalid miner visibility");}
        if(this.diamondsPoints <= 0){ throw new IllegalArgumentException("Invalid diamond points");}
        if(this.torchVisibility <= 0){ throw new IllegalArgumentException("Invalid torch visibility");}
    }

    public int getCarnisterCapacity() {
        return carnisterCapacity;
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

    public int getNumOfCarnisters() {
        return numOfCarnisters;
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