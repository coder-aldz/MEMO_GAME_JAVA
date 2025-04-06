package memoapp.memo_final;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends VBox {
    private Scene scene;
    public MainView(Stage primaryStage){

        //Button Declaration for both Start and Exit
        Button startBtn = new Button("START");
        Button exitBtn = new Button("EXIT");

        //Setting the Size of the Button
        startBtn.setPrefHeight(50);
        startBtn.setPrefWidth(150);
        exitBtn.setPrefHeight(50);
        exitBtn.setPrefWidth(150);

        //Exit Action for the Exit Button
        exitBtn.setOnAction(event -> Platform.exit());
        exitBtn.getStyleClass().add("exit-button");
        startBtn.setOnAction(actionEvent -> primaryStage.setScene(new GameView(primaryStage).getScene()));

        //Setting the VBox Layout
        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setPadding(new Insets(50,50,200,50));
        this.setSpacing(20);
        this.getChildren().addAll(startBtn,exitBtn); //Add both buttons

        //Add the VBox to the scene so that it will show on the screen.
        scene = new Scene(this, 1024, 780);
        scene.getStylesheets().add(MainView.class.getResource("scene-1.css").toExternalForm()); //Loading the CSS file.
    }
}
