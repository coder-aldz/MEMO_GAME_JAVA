import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main view of the application.
 * This view serves as the starting screen, offering options to start the game or exit the application.
 */
public class MainView extends VBox {
    // The scene associated with the main view
    private final Scene scene;

    /**
     * Constructs the MainView, setting up buttons and layout for the main screen.
     *
     * @param primaryStage The primary stage of the application, used for navigation and scene control.
     */
    public MainView(Stage primaryStage) {
        // Create buttons for Start and Exit actions
        Button startBtn = new Button("START");
        Button exitBtn = new Button("EXIT");

        // Set button dimensions
        startBtn.setPrefHeight(50);
        startBtn.setPrefWidth(150);
        exitBtn.setPrefHeight(50);
        exitBtn.setPrefWidth(150);

        // Configure Exit button action to close the application
        exitBtn.setOnAction(event -> Platform.exit());
        exitBtn.getStyleClass().add("exit-button"); // Apply custom style for the Exit button

        // Configure Start button action to navigate to the game view
        startBtn.setOnAction(actionEvent -> primaryStage.setScene(new GameView(primaryStage).getScene()));

        // Set layout properties for the VBox container
        this.setAlignment(Pos.BOTTOM_CENTER); // Align components at the bottom center
        this.setPadding(new Insets(50, 50, 200, 50)); // Apply padding around the VBox
        this.setSpacing(20); // Set spacing between components
        this.getChildren().addAll(startBtn, exitBtn); // Add buttons to the VBox

        // Initialize the scene with the VBox and load associated stylesheets
        scene = new Scene(this, 1024, 780);
        scene.getStylesheets().add(MainView.class.getResource("scene-1.css").toExternalForm());
    }
}
