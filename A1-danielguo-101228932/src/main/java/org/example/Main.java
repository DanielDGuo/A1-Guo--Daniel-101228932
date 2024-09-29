package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public ArrayList<Card> AdDeck;
    public ArrayList<Card> EvDeck;
    public final ArrayList<Card> AdventureDeckList = new ArrayList<Card>();
    public final ArrayList<Card> EventDeckList = new ArrayList<Card>();

    public static void main(String[] args) {
        System.out.print("Hello and welcome!");
    }

    class Card {
    }


    public void initializeAdventureDeck() {
    }

    public ArrayList<Card> getAdventureDeck() {
        return AdDeck;
    }

    public int getAdventureDeckSize() {
        return 0;
    }

    public ArrayList<Card> getAdventureDeckCards() {
        return new ArrayList<Card>();
    }

    public void initializeEventDeck() {
    }

    public ArrayList<Card> getEventDeck() {
        return EvDeck;
    }

    public ArrayList<Card> getEventDeckCards() {
        return new ArrayList<Card>();
    }

    public int getEventDeckSize() {
        return 0;
    }
}