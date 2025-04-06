import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;

/**
 * Represents a Card in the memory game.
 * Each card has an ID, an image path, and states for whether it is flipped or matched.
 */
public class Card extends StackPane {

    //Added this Static and Constant String for better Default Handling
    private static final String DEFAULT_IMAGE_PATH = "Not Found!";

    // Unique identifier for the card
    private final int cardID;

    // Path to the image associated with this card
    private String imgPath = DEFAULT_IMAGE_PATH;

    // Indicates whether the card is flipped
    private boolean isFlipped;

    // Indicates whether the card has been matched with its pair
    private boolean isMatched;

    /**
     * Constructs a new Card with a specified ID and image path.
     *
     * @param id      Unique identifier for the card.
     * @param imgPath Path to the image associated with the card.
     */
    public Card(int id, String imgPath) {

        // Added this check to make sure that the Image Path is never null and also the Card ID is never a negative.
        try {
            if (id < 0) {
                throw new IllegalArgumentException("Card ID must be a positive integer.");
            }
            if (imgPath == null || imgPath.trim().isEmpty()) {
                throw new IllegalArgumentException("Image path cannot be null or empty.");
            }
        } catch (IllegalArgumentException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error Message");
            error.setContentText(e.getMessage());
            error.show();
        }

        this.cardID = id;
        this.imgPath = imgPath;
        this.isMatched = false; // Cards are initially unmatched
        this.isFlipped = false; // Cards are initially not flipped
    }

    /**
     * Gets the unique identifier of the card.
     *
     * @return The card ID.
     */
    public int getCardID() {
        return cardID;
    }

    /**
     * Gets the image path associated with the card.
     *
     * @return The image path as a string.
     */
    public String getImagePath() {
        return imgPath;
    }

    /**
     * Checks if the card is flipped.
     *
     * @return True if the card is flipped, false otherwise.
     */
    public boolean isFlipped() {
        return isFlipped;
    }

    /**
     * Checks if the card is matched with another card.
     *
     * @return True if the card is matched, false otherwise.
     */
    public boolean isMatched() {
        return isMatched;
    }

    /**
     * Sets the matched state of the card.
     *
     * @param matched True to mark the card as matched, false to mark it as unmatched.
     */
    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    /**
     * Toggles the flipped state of the card.
     * If it is flipped, it will become unflipped, and vice versa.
     */
    public void flip() {
        //Added the IF Logic to avoid flipping a card that is already matched.
        if(!isMatched){isFlipped = !isFlipped;}
    }

    /**
     * Returns a string representation of the card, including its ID, image path,
     * flipped state, and matched state.
     *
     * @return A string describing the card.
     */
    @Override
    public String toString() {
        return "Card{" +
                "id=" + cardID +
                ", imagePath='" + imgPath + '\'' +
                ", isFlipped=" + isFlipped +
                ", isMatched=" + isMatched +
                '}';
    }
}
