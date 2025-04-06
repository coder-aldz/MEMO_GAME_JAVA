package memoapp.memo_final;

import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGrid extends GridPane {
    private List<Card> cards;
    private GameController controller;

    public CardGrid(int gridSize) {
        this.cards = new ArrayList<>();

        this.setHgap(5);
        this.setVgap(5);
        this.setPadding(new Insets(50, 50, 50, 50));
        this.setAlignment(Pos.CENTER);

        this.initializeGrid(gridSize);
        this.initializeCards();

        controller = new GameController();
    }

    public void initializeGrid(int gridSize) {
        for (int i = 0; i < gridSize; i++) {
            cards.add(new Card(i / 2, "img_"+(i/2)+".png")); // Add card pairs
        }
        Collections.shuffle(cards);
    }

    private void initializeCards() {
        int uniqueIndex = 0;
        for (Card card : this.getCards()) {
            createCardComponents(card); // Method for adding card visuals and animation logic
            add(card, uniqueIndex % 4, uniqueIndex / 4); // Add card to grid
            uniqueIndex++;
        }
    }

    private void createCardComponents(Card card) {
        Image img = new Image(getClass().getResource(card.getImagePath()).toExternalForm());
        ImageView frontSide = new ImageView(img);
        Rectangle backSide = new Rectangle(80, 100, Color.ORANGE);

        //Setting the cards to be facing down
        frontSide.setVisible(false);
        backSide.setVisible(true);

        card.getChildren().addAll(backSide, frontSide);
        card.getStyleClass().add("cards");

        // Set up animations
        ScaleTransition scaleTransition = createScaleTransition(card, frontSide, backSide);
        card.setOnMouseClicked(clickEvent -> {
            if (controller.getFlippedCards() < 2 && !card.isFlipped()) {
                controller.handleCardClick(card); // Add card to flipped cards in the controller
                scaleTransition.play();
                card.flip();
                System.out.println(card.getCardID());
                if (controller.getFlippedCards() == 2) {
                    System.out.println(controller.getResult());
                    checkAndFlipBack(); // Check the two flipped cards and flip back if needed
                }
            }
        });
    }

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

    private void checkAndFlipBack() {
        // Delay to allow the user to see the cards before flipping back
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            if (!controller.getResult()) {
                // Flip both cards back if they don't match
                for (Card flippedCard : controller.getCardsFlipped()) {
                    toggleCardVisibility((ImageView) flippedCard.getChildren().get(1),
                            (Rectangle) flippedCard.getChildren().get(0));
                    ScaleTransition reverseTransition = new ScaleTransition(Duration.seconds(0.2), flippedCard);
                    reverseTransition.setFromX(0);
                    reverseTransition.setToX(1);
                    reverseTransition.play();
                }
            }
            controller.clearFlippedCards(); // Clear the flipped cards list in the controller
            controller.resetResult();
        });
        pause.play();
    }

    private void toggleCardVisibility(ImageView frontSide, Rectangle backSide) {
        if (backSide.isVisible()) {
            backSide.setVisible(false);
            frontSide.setVisible(true);
        } else {
            frontSide.setVisible(false);
            backSide.setVisible(true);
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
