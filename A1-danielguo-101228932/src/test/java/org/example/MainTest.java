package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

        for (Main.Player p : game.PlayerList) {
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
        assertEquals("Player(s) P1 Won.", game.printWinners());
        //test two winners
        game.PlayerList.get(1).addShields(7);
        assertEquals("Player(s) P1, P2 Won.", game.printWinners());
        //test four winners
        game.PlayerList.get(2).addShields(7);
        game.PlayerList.get(3).addShields(7);
        assertEquals("Player(s) P1, P2, P3, P4 Won.", game.printWinners());
    }

    @Test
    @DisplayName("Test hand output")
    void RESP_05_test_01() {
        Main game = new Main();
        game.curPlayer = game.PlayerList.get(0);
        //test hand
        game.curPlayer.addCard(game.new Card("F15", "Foe", 15));
        game.curPlayer.addCard(game.new Card("F5", "Foe", 5));
        game.curPlayer.addCard(game.new Card("F40", "Foe", 40));
        game.curPlayer.addCard(game.new Card("H10", "Weapon", 10));
        game.curPlayer.addCard(game.new Card("S10", "Weapon", 10));
        game.curPlayer.addCard(game.new Card("S10", "Weapon", 10));
        game.curPlayer.addCard(game.new Card("L20", "Weapon", 20));

        //check hand is sorted
        assertEquals("P1 Hand: F5, F15, F40, S10, S10, H10, L20", game.PlayerList.get(0).printHand());
    }

    @Test
    @DisplayName("Test Event Card Draw")
    void RESP_06_test_01() {
        Main game = new Main();
        //init a dummy event deck
        game.EvDeck = new ArrayList<>();
        Main.Card a = game.new Card("Plague", "Event", 0);
        game.EvDeck.add(a);
        Main.Card b = game.new Card("Q5", "Quest", 5);
        game.EvDeck.add(b);
        Main.Card c = game.new Card("Queen's Favour", "Event", 0);
        game.EvDeck.add(c);

        //check drawn card is correct
        assertEquals(a, game.drawEventCard());
        //check drawn card is in the discard
        assertTrue(game.EvDiscard.contains(a));
    }

    @Test
    @DisplayName("Test Plague Card Effects")
    void RESP_07_test_01() {
        Main game = new Main();
        game.curPlayer = game.PlayerList.get(0);
        //init a dummy event deck
        game.EvDeck = new ArrayList<>();
        //make an EvDeck of plagues for testing. Since only plagues are in the deck, no need to check event type
        game.EvDeck.add(game.new Card("Plague", "Event", 0));
        game.EvDeck.add(game.new Card("Plague", "Event", 0));
        game.EvDeck.add(game.new Card("Plague", "Event", 0));

        game.drawEventCard();
        game.plagueEffect();
        //check Plague works on a player with no shields
        assertEquals(0, game.curPlayer.shields);

        game.curPlayer.addShields(1);
        game.drawEventCard();
        game.plagueEffect();
        //check Plague works on a player with 1 shield
        assertEquals(0, game.curPlayer.shields);

        game.curPlayer.addShields(3);
        game.drawEventCard();
        game.plagueEffect();
        //check Plague works on a player with 3 shields
        assertEquals(1, game.curPlayer.shields);
    }

    @Test
    @DisplayName("Test Queen's Favour Card Effects")
    void RESP_08_test_01() {
        Main game = new Main();
        game.curPlayer = game.PlayerList.get(0);
        //init a dummy event deck
        game.EvDeck = new ArrayList<>();
        //make an EvDeck of plagues for testing. Since only plagues are in the deck, no need to check event type
        game.EvDeck.add(game.new Card("Queen's Favour", "Event", 0));
        game.EvDeck.add(game.new Card("Queen's Favour", "Event", 0));
        game.EvDeck.add(game.new Card("Queen's Favour", "Event", 0));

        game.drawEventCard();
        //logic will then check what event card it is
        game.queenEffect();
        //check Queen's Favour works on a player with no shields
        assertEquals(2, game.curPlayer.shields);

        game.curPlayer.addShields(1);
        //3 shields now
        game.drawEventCard();
        game.queenEffect();
        //check Queen's Favour works on a player with 3 shield
        assertEquals(5, game.curPlayer.shields);
    }
}