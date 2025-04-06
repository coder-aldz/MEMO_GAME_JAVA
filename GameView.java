package memoapp.memo_final;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameView extends VBox{
    private Scene scene;
    private CardGrid cardGrid;

    public GameView(Stage primaryStage){

        //This initiate the cards to be dealt during the game. 16 is the number of cards.
        cardGrid = new CardGrid(16);

        //Button to go back to the main screen
        Button backBtn = new Button("Back");
        backBtn.setOnAction(actionEvent -> primaryStage.setScene(new MainView(primaryStage).getScene()));

        //This adds the both the exit button and the cards on the main layout which is VBox.
        this.getChildren().addAll(cardGrid, backBtn);
        this.setSpacing(50);
        this.setAlignment(Pos.CENTER);

        //Add the VBox to the scene so that it will show on the screen.
        scene = new Scene(this, 1024, 780);
        scene.getStylesheets().add(MainView.class.getResource("scene-2.css").toExternalForm()); //Loading the CSS file.
    }
}
