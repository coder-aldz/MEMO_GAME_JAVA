import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CardGrid represents the grid of cards in the memory game.
 * It initializes the grid layout, card components, and handles animations and game logic.
 */
public class CardGrid extends GridPane {
    // List of cards to be displayed in the grid
    private final List<Card> cards;

    // Controller to manage game logic and flipped cards
    private final GameController controller;

    /**
     * Constructs the CardGrid with the specified grid size.
     *
     * @param gridSize The number of cards to display in the grid.
     */
    public CardGrid(int gridSize) {
        this.cards = new ArrayList<>();
        this.controller = new GameController();

        // Grid layout settings
        this.setHgap(5);
        this.setVgap(5);
        this.setPadding(new Insets(50, 50, 50, 50));
        this.setAlignment(Pos.CENTER);

        // Initialize the grid and its components
        this.initializeGrid(gridSize);
        this.initializeCards();
    }

    /**
     * Initializes the grid by creating card pairs and shuffling them.
     *
     * @param gridSize The total number of cards in the grid.
     */
    public void initializeGrid(int gridSize) {
        for (int i = 0; i < gridSize; i++) {
            cards.add(new Card(i / 2, "img_" + (i / 2) + ".png")); // Add card pairs
        }
        Collections.shuffle(cards); // Randomize card order
    }

    /**
     * Adds card components to the grid and sets their positions.
     */
    private void initializeCards() {
        int uniqueIndex = 0;
        for (Card card : this.getCards()) {
            createCardComponents(card); // Add card visuals and logic
            add(card, uniqueIndex % 4, uniqueIndex / 4); // Arrange cards in a grid (4 columns)
            uniqueIndex++;
        }
    }

    /**
     * Creates components for a card, including its animations and click handling.
     *
     * @param card The card to be configured.
     */
    private void createCardComponents(Card card) {
        Image img = new Image(getClass().getResource(card.getImagePath()).toExternalForm());
        ImageView frontSide = new ImageView(img); // Front side of the card
        Rectangle backSide = new Rectangle(80, 100, Color.ORANGE); // Back side of the card

        // Set default visibility (cards facing down initially)
        frontSide.setVisible(false);
        backSide.setVisible(true);

        // Add visual elements to the card
        card.getChildren().addAll(backSide, frontSide);
        card.getStyleClass().add("cards"); // Apply CSS styles

        // Configure animations and click logic
        ScaleTransition scaleTransition = createScaleTransition(card, frontSide, backSide);
        card.setOnMouseClicked(clickEvent -> {
            if (this.controller.getFlippedCardCount() < 2 && !card.isFlipped()) {
                this.controller.handleCardClick(card); // Add card to flipped list
                scaleTransition.play();
                card.flip(); // Flip the card
                winMessage();
                if (this.controller.getFlippedCardCount() == 2) {
                    checkAndFlipBack(); // Check match and flip back if necessary
                }
            }
        });
    }

    /**
     * Creates a scale animation for flipping the card.
     *
     * @param card      The card to be animated.
     * @param frontSide The front side of the card.
     * @param backSide  The back side of the card.
     * @return The configured ScaleTransition object.
     */
    private ScaleTransition createScaleTransition(Card card, ImageView frontSide, Rectangle backSide) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), card);
        scaleTransition.setFromX(1);
        scaleTransition.setToX(0);

        scaleTransition.setOnFinished(event -> {
            toggleCardVisibility(frontSide, backSide);
            ScaleTransition reverseTransition = new ScaleTransition(Duration.seconds(0.2), card);
            reverseTransition.setFromX(0);
            reverseTransition.setToX(1);
            reverseTransition.play();
        });

        return scaleTransition;
    }

    /**
     * Checks the two flipped cards for a match and flips them back if they don't match.
     * Includes a delay to allow the user to see the cards before flipping back.
     */
    private void checkAndFlipBack() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1)); // Delay for visibility
        pause.setOnFinished(event -> {
            if (!this.controller.hasMatchResult()) {
                // Flip both cards back if they don't match
                for (Card flippedCard : this.controller.getFlippedCards()) {
                    toggleCardVisibility(
                            (ImageView) flippedCard.getChildren().get(1),
                            (Rectangle) flippedCard.getChildren().get(0)
                    );
                    ScaleTransition reverseTransition = new ScaleTransition(Duration.seconds(0.2), flippedCard);
                    reverseTransition.setFromX(0);
                    reverseTransition.setToX(1);
                    reverseTransition.play();
                }
            }

            // Clear the list of flipped cards
            this.controller.clearFlippedCards();
            // Reset the controller's previous result
            this.controller.resetMatchResult();
        });
        pause.play();
    }

    /**
     * Toggles the visibility of the card's front and back sides.
     *
     * @param frontSide The front side of the card (image).
     * @param backSide  The back side of the card (rectangle).
     */
    private void toggleCardVisibility(ImageView frontSide, Rectangle backSide) {
        if (backSide.isVisible()) {
            backSide.setVisible(false);
            frontSide.setVisible(true);
        } else {
            frontSide.setVisible(false);
            backSide.setVisible(true);
        }
    }

    /**
     * Gets the list of cards displayed in the grid.
     *
     * @return A list of Card objects.
     */
    public List<Card> getCards() {
        return cards;
    }


    /**
     * This is a method that prints a message when all the cards are matched.
     */
    public void winMessage(){
        int points = 0;
        for (Card status : this.getCards()){
            if(status.isMatched()){
                points++;
            }
        }
        if(points == 16){
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Congratulations");
            msg.setContentText("You have a brain like Einstein!!!");
            msg.show();
        }
    }
}
