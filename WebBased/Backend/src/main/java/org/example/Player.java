package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    @JsonProperty("id")
    private int id;
    @JsonProperty("shields")
    private int shields;
    @JsonProperty("hand")
    private ArrayList<Card> hand;
    @JsonProperty("eligible")
    private boolean eligible;

    public Player(int id) {
        this.id = id;
        hand = new ArrayList<>();
    }

    public void addShields(int i) {
        shields += i;
    }

    public void addCard(Card c) {
        hand.add(c);
        Collections.sort(hand);
    }

    public void printHand() {
        StringBuilder outString = new StringBuilder("P" + id + " Hand: ");
        Collections.sort(hand);
        for (Card c : hand) {
            outString.append(c).append(", ");
        }
        System.out.print(outString.substring(0, outString.length() - 2));
    }

    @Override
    public String toString() {
        return "P" + id;
    }

    public int getId() {
        return id;
    }

    public int getShields() {
        return shields;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public boolean isEligible() {
        return eligible;
    }

}
