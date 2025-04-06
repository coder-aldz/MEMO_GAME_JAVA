package memoapp.memo_final;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Card extends StackPane {

    //Declared the variables needed for a card
    private int cardID;
    private String imgPath = "Not Found!";
    private boolean isFlipped;
    private boolean isMatched;

    // Constructor
    public Card(int id, String imgPath) {
        this.cardID = id;
        this.imgPath = imgPath;
        this.isMatched = false; // Cards are initially not matched
        this.isFlipped = false; // Cards are initially not flipped
    }

    // Getter for ID
    public int getCardID() {
        return cardID;
    }

    // Getter for image path
    public String getImagePath() {
        return imgPath;
    }

    // Getter and setter for isFlipped
    public boolean isFlipped() {
        return isFlipped;
    }

    // Getter and setter for isMatched
    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + cardID +
                ", imagePath='" + imgPath + '\'' +
                ", isFlipped=" + isFlipped +
                ", isMatched=" + isMatched +
                '}';
    }

    public void flip() {
        isFlipped = !isFlipped; // Toggle the card state
    }
}
