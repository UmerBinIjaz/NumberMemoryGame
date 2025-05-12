package application;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.paint.CycleMethod;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    private static final long DURATION_SECONDS = 6;
  
    
    private ScheduledExecutorService timerThread = Executors.newSingleThreadScheduledExecutor();

    private VBox root;
    private Pane tilePane;
    private List<TileView> tileSequence = new ArrayList<>();
    
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        timerThread.shutdownNow();
    }

    private Parent createContent() {
        root = new VBox();
        root.setPrefSize(1280, 720 + 100);

        var button = new Button("Start");
        button.setOnAction(e -> startGame());

        button.setStyle(
        		"-fx-font-family: \"-apple-system\", \"system-ui\", \"Segoe UI\", \"Roboto\", \"Helvetica Neue\", \"Fira Sans\", \"Ubuntu\", \"Oxygen\", \"Droid Sans\", \"Helvetica\", \"Arial\", sans-serif; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: 600; " +
                "-fx-padding: 10 20; " +
                "-fx-background-color: #0A66C2; " +
                "-fx-border-radius: 100px; " +
                "-fx-padding-left: 20px; " +
                "-fx-padding-right: 20px; " +
                "-fx-text-fill: white; " +
                "-fx-border-radius: 5; " +
                "-fx-cursor: hand;"
        );
        
        root.getChildren().addAll(new Pane(), button);

        return root;
    }

    private void startGame() {
        tilePane = populateGrid();

       
        root.getChildren().set(0, tilePane);
        
        timerThread.schedule(() -> {
            tilePane.getChildren()
                    .stream()
                    .map(n -> (TileView) n)
                    .forEach(TileView::hide);

        }, DURATION_SECONDS, TimeUnit.SECONDS);
    }

    private void handleTileClick(TileView tile) {
        if (tileSequence.isEmpty()) {
            System.out.println("Game is already over");
            return;
        }

        var correctTile = tileSequence.remove(0);

        if (tile == correctTile) {
            tile.show();
            if (tileSequence.isEmpty()) {
                System.out.println("Congratulations! You've won!");
                startGame(); // Start a new game
            }
        } else {
            // Handle the wrong tile, reset the state, but keep the game going
            System.out.println("Wrong tile: game continues");
        }
    }

    private Pane populateGrid() {
        var pane = new Pane();
        pane.setPrefSize(1280, 720);

        Random random = new Random();

        List<Point2D> usedPoints = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            int randomX = random.nextInt(1280 / 80);
            int randomY = random.nextInt(720 / 80);

            Point2D p = new Point2D(randomX, randomY);

            while (usedPoints.contains(p)) {
                randomX = random.nextInt(1280 / 80);
                randomY = random.nextInt(720 / 80);

                p = new Point2D(randomX, randomY);
            }

            usedPoints.add(p);

            var tile = new TileView(Integer.toString(i));
         // Create a LinearGradient for the background fill
            LinearGradient gradient = new LinearGradient(
                    0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.web("#0A66C2")),
                    new Stop(1, Color.web("#16437E"))
            );

            // Set the background fill of the tile using the gradient
            tile.setStyle(
                    "-fx-font-family: \"-apple-system\", \"system-ui\", \"Segoe UI\", \"Roboto\", \"Helvetica Neue\", \"Fira Sans\", \"Ubuntu\", \"Oxygen\", \"Droid Sans\", \"Helvetica\", \"Arial\", sans-serif; " +
                    "-fx-font-size: 24px; " +
                    "-fx-font-weight: 800; " +
                    "-fx-padding-left: 20px; " +
                    "-fx-padding-right: 20px; " +
                    "-fx-cursor: hand; "
                    //"-fx-color: linear-gradient(to right, #12c2e9, #c471ed, #f64f59);"+
            );


            tile.setTranslateX(randomX * 80);
            tile.setTranslateY(randomY * 80);
            tile.setOnMouseClicked(e -> handleTileClick(tile));

            pane.getChildren().add(tile);
            tileSequence.add(tile);
        }

        return pane;
    }

    private static class TileView extends StackPane {

        private Text text;

        TileView(String content) {
            var border = new Rectangle(80, 80, null);
//            border.setStroke(Color.linear-gradient(to right, #0A0E14, #010609, #07090B));
            border.setStyle(
            	    "-fx-stroke: linear-gradient(to right, #23c4e9, #c270ed, #f24f35);"
            	);

            border.setStrokeWidth(4);
            border.setStrokeType(StrokeType.INSIDE);

            text = new Text(content);
            text.setFont(Font.font(64));

            text.setStyle(
            	    "-fx-fill: linear-gradient(to right, #23c4e9, #c270ed, #f24f35);"
            	);

            
            getChildren().addAll(border, text);

            setPickOnBounds(true);
        }

        void hide() {
            text.setVisible(false);
        }

        void show() {
            text.setVisible(true);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}