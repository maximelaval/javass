package ch.epfl.javass.gui;

import ch.epfl.javass.jass.Card;
import ch.epfl.javass.jass.CardSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.util.Collections;
import java.util.HashSet;

import static ch.epfl.javass.jass.Jass.HAND_SIZE;

/**
 * Represents a Java Bean that contains the current hand of the graphical player.
 *
 * @author Lucas Meier (283726)
 * @author Maxime Laval (287323)
 */
public class HandBean {

    private final ObservableList<Card> hand = FXCollections.observableArrayList(Collections.nCopies(HAND_SIZE, null));
    private final ObservableSet<Card> playableCards = FXCollections.observableSet(new HashSet<>());


    /**
     * Returns the bean property hand.
     *
     * @return the property hand.
     */
    public ObservableList<Card> handProperty() {
        return FXCollections.unmodifiableObservableList(hand);
    }

    /**
     * Sets the current hand with the given new hand.
     *
     * @param newHand the given new hand.
     */
    public void setHand(CardSet newHand) {
        if (newHand.size() != HAND_SIZE) {

            for (int i = 0; i < HAND_SIZE; i++) {
                if (hand.get(i) != null && !newHand.contains(hand.get(i))) {
                    hand.set(i, null);
                }
            }
        } else {

            for (int i = 0; i < HAND_SIZE; i++) {
                hand.set(i, newHand.get(i));
            }
        }
    }

    /**
     * Returns the bean property of the playable cards.
     *
     * @return the bean property of the playable cards.
     */
    public ObservableSet<Card> playableCardsProperty() {
        return FXCollections.unmodifiableObservableSet(playableCards);
    }

    /**
     * Sets the current playable cards with the given new playable cards.
     *
     * @param newPlayableCards the given playable cards.
     */
    public void setPlayableCards(CardSet newPlayableCards) {
        playableCards.clear();
        for (int i = 0; i < newPlayableCards.size(); i++) {
            playableCards.add(newPlayableCards.get(i));
        }
    }
}
