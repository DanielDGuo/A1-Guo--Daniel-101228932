package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("Check that the adventure deck is a permutation of 100 specified cards")
    void RESP_01_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        //assert the size
        assertEquals(100, game.getAdventureDeckSize());

        //assert that for every card in the adventure deck, it corresponds to one in the deck list
        ArrayList<Main.Card> deckList = game.AdventureDeckList;
        for (Main.Card c : game.getAdventureDeckCards()) {
            assertTrue(deckList.contains(c));
            deckList.remove(c);
        }
    }

    @Test
    @DisplayName("Check that the event deck is a permutation of 17 specified cards")
    void RESP_01_test_02() {
        Main game = new Main();
        game.initializeEventDeck();
        //assert the size
        assertEquals(17, game.getEventDeckSize());

        //assert that for every card in the event deck, it corresponds to one in the deck list
        ArrayList<Main.Card> deckList = game.EventDeckList;
        for (Main.Card c : game.getEventDeckCards()) {
            assertTrue(deckList.contains(c));
            deckList.remove(c);
        }
    }

    @Test
    @DisplayName("Check each player's hand for 12 cards at the start of the game")
    void RESP_02_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializePlayerHands();
        //make sure there are 4 players
        assertEquals(4, game.PlayerList.size());

        for (Main.Player p : game.PlayerList){
            assertEquals(12, p.getHand().size());
        }
        //also check for the correct number of removed cards
        assertEquals(52, game.getAdventureDeckSize());
    }

    @Test
    @DisplayName("Test winner detection")
    void RESP_03_test_01() {
        Main game = new Main();
        assertFalse(game.findWinners());

        //make a winner
        game.PlayerList.get(0).addShields(7);
        assertTrue(game.findWinners());
    }

    @Test
    @DisplayName("Test winner output")
    void RESP_04_test_01() {
        Main game = new Main();
        //test one winner
        game.PlayerList.get(0).addShields(7);
        assertEquals(game.printWinners(), "Player(s) P1 Won.");
        //test two winners
        game.PlayerList.get(1).addShields(7);
        assertEquals(game.printWinners(), "Player(s) P1, P2 Won.");
        //test four winners
        game.PlayerList.get(2).addShields(7);
        game.PlayerList.get(3).addShields(7);
        assertEquals(game.printWinners(), "Player(s) P1, P2, P3, P4 Won.");
    }
}