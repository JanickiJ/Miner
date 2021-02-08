package agh.cs.miner.viev;

import agh.cs.miner.basics.Direction;
import agh.cs.miner.basics.Vector2d;
import agh.cs.miner.engine.OptionsParser;
import agh.cs.miner.map.Cave;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Visualisation {

    private final int rows;
    private final int columns;
    private final int squareSize;
    private final OptionsParser parameters;
    private Cave map;
    private final GridPane gridPane;
    private final BorderPane bPane;
    private final HBox statisticPanel;
    private final Scene scene;
    private final String path = "resources/agh/cs/miner/";

    public Visualisation(OptionsParser parameters) {

        this.map = new Cave(parameters);
        this.rows = parameters.getWidth();
        this.columns = parameters.getHeight();
        this.squareSize = 23;
        this.parameters = parameters;
        this.gridPane = new GridPane();
        this.bPane = new BorderPane();
        statisticPanel = new HBox();
        drawBorderPane();
        this.scene = new Scene(bPane,bPane.getMaxWidth(), bPane.getMaxHeight());
        getKey();

    }

    private void newGame(boolean isContinuation){
        int earnedPoints = map.getMiner().getPoints();
        double earnedFuel = map.getMiner().getFuel();
        this.map =  new Cave(parameters);
        if(isContinuation) {
            this.map.getMiner().addPoints(earnedPoints);
            this.map.getMiner().addFuel(earnedFuel - parameters.getMinerStartFuel());
        }

    }

    private void drawBorderPane() {
        if(map.getMiner().isInWormhole()){
            newGame(true);
        }

        if(map.minerHasFuel() && !map.win()){
            drawBackground();
            drawMap();
            drawStatisticPanel();
            gridPane.scaleXProperty();
        }
        else{
            drawGameOver();
        }
    }

    private void drawBackground() {
        Image mapImage = new Image(path + "map.png");
        gridPane.getChildren().clear();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Rectangle rectangle = new Rectangle(i * squareSize, j * squareSize, squareSize, squareSize);
                rectangle.setFill(new ImagePattern(mapImage));
                gridPane.add(rectangle, i * squareSize, j * squareSize);
            }
        }

    }

    private void drawMap() {
        Image blackImage = new Image(path + "black.png");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Vector2d vector2d = new Vector2d(i, j);
                Rectangle rectangle = new Rectangle(i * squareSize, j * squareSize, squareSize, squareSize);

                if (map.isVisible(vector2d)) {
                    if (map.isObjectAt(vector2d)) {
                        Image objectImage = map.objectAt(vector2d).getImage();
                        rectangle.setFill(new ImagePattern(objectImage));
                        gridPane.add(rectangle, i * squareSize, j * squareSize);
                    }
                }
                else {
                    rectangle.setFill(new ImagePattern(blackImage));
                    gridPane.add(rectangle, i * squareSize, j * squareSize);
                }
            }
        }
        bPane.setCenter(gridPane);
    }

    private void drawStatisticPanel(){
        statisticPanel.getChildren().clear();
        Text score = new Text("Score " + map.getScore());
        score.setFont(Font.font("Cooper Black", 15));
        score.setFill(Color.BLACK);
        Text fuel = new Text("Fuel " + map.getFuel());
        fuel.setFont(Font.font("Cooper Black", 15));
        fuel.setFill(Color.BLACK);
        statisticPanel.setAlignment(Pos.CENTER);
        statisticPanel.setSpacing(50);
        statisticPanel.getChildren().addAll(fuel,createTorchONOFF(),score);
        statisticPanel.setMaxWidth(gridPane.getMaxWidth());
        statisticPanel.setBackground(new Background(new BackgroundFill(Color.rgb(142,140,132), CornerRadii.EMPTY, Insets.EMPTY)));
        bPane.setTop(statisticPanel);

    }

    private void drawGameOver(){
        bPane.getChildren().clear();
        Image result;
        ImageView imageView;

        if (map.win()){
            result = new Image(path + "win.png");
            imageView = new ImageView(result);
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);

        }
        else {
            result = new Image(path + "GameOver.png");
            imageView = new ImageView(result);
            imageView.setFitHeight(60);
            imageView.setFitWidth(200);
        }

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        Text title = new Text("Score " + map.getScore());
        title.setFont(Font.font("Cooper Black", 20));
        title.setFill(Color.WHITE);
        vBox.setSpacing(50);
        vBox.getChildren().addAll(imageView, title);
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(246, 151, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        createNextGameButton(vBox);
        bPane.setCenter(vBox);
    }

    private Button createTorchONOFF(){
        Button button = new Button();
        button.setAlignment(Pos.CENTER_RIGHT);
        button.setOnAction(e -> map.torchONOFF());
        Image image = new Image(path + "torch.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
        button.setPrefSize(25, 25);
        return button;
    }

    private void createNextGameButton(VBox vBox){
        Button button = new Button();
        button.setAlignment(Pos.CENTER);
        button.setOnAction(e -> {
            newGame(false);
            drawBorderPane();
        });

        Image image = new Image(path + "replay.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
        button.setBackground(new Background(new BackgroundFill(Color.rgb(246, 151, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().add(button);
    }

    private void getKey() {
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.RIGHT || code == KeyCode.D) {
                map.moveMap(Direction.RIGHT);
            } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                map.moveMap(Direction.LEFT);
            } else if (code == KeyCode.UP || code == KeyCode.W) {
                map.moveMap(Direction.UP);
            } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                map.moveMap(Direction.DOWN);
            }
            drawBorderPane();
        });
    }

    public Scene getScene() {
        return scene;
    }

}
