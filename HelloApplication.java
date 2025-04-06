/*
 * CCS Project - Memory Game Version 1.0
 *
 * This is a memory game application written in Java using the JavaFX library.
 *
 * Author: Paul Ryan Alducente
 * Date: April 1, 2025
 */

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * HelloApplication serves as the entry point of the Memory Game.
 * It initializes the main stage and displays the welcome screen.
 */
public class HelloApplication extends Application {

    /**
     * The start method is called after the application is launched.
     * It sets up the initial stage and displays the welcome screen.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Create and initialize the layout for the main welcome screen
        MainView mainScreenLayout = new MainView(stage);

        // Configure the stage properties
        stage.setTitle("ALDZ MEMORY GAME");   // Set the title of the game window
        stage.setResizable(false);           // Disable window resizing
        stage.setScene(mainScreenLayout.getScene()); // Set the scene for the main view
        stage.show();                        // Display the stage
    }

    /**
     * The main method serves as the entry point for the application.
     * It launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch(); // Launches the JavaFX application
    }
}
