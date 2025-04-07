import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Represents the main game view in the memory game.
 * The view displays the grid of cards and a button to return to the main screen.
 */
public class GameView extends VBox {
    // Scene associated with this game view
    private final Scene scene;

    // Card grid containing the cards for the game
    private final CardGrid cardGrid;

    /**
     * Constructs the GameView, setting up the card grid and back button.
     *
     * @param primaryStage The primary stage of the application, used for navigation.
     */
    public GameView(Stage primaryStage) {
        // Initialize the card grid with 16 cards
        cardGrid = new CardGrid(16);

        // Create a button to navigate back to the main screen
        Button backBtn = new Button("Back");
        backBtn.setOnAction(actionEvent -> primaryStage.setScene(new MainView(primaryStage).getScene()));

        // Add the card grid and back button to the VBox layout
        this.getChildren().addAll(cardGrid, backBtn);
        this.setSpacing(50); // Set spacing between elements
        this.setAlignment(Pos.CENTER); // Center elements in the VBox

        // Create the scene and load associated CSS styles
        scene = new Scene(this, 1024, 780);
        scene.getStylesheets().add(Objects.requireNonNull(MainView.class.getResource("/resource/scene-2.css")).toExternalForm());
    }
}
