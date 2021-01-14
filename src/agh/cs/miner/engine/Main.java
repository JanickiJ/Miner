package agh.cs.miner.engine;

import agh.cs.miner.viev.Visualisation;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import static agh.cs.miner.engine.OptionsParser.loadPropFromFile;

public class Main extends Application {

    private OptionsParser parameters;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            parameters = loadPropFromFile();
        } catch (IllegalArgumentException | FileNotFoundException ex) {
            System.out.println(ex);
        }

        Visualisation visualisation = new Visualisation(parameters);
        primaryStage.setTitle("MINER GAME");
        primaryStage.setScene(visualisation.getScene());
        primaryStage.show();

    }
}
