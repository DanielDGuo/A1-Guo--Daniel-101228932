package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent;
    private final PrintStream outContentOriginal = System.out;
    private final InputStream inContentOriginal = System.in;
    @BeforeEach
    public void setUpStreams() {
        //any prints will be put into the outContent instead
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        //reset the prints back to the original I/O
        System.setOut(outContentOriginal);
        System.setIn(inContentOriginal);
    }

    //helper that converts the data into ByteArrayInputStream readable format
    void provideInput(String data) {
        inContent = new ByteArrayInputStream(data.getBytes());
        System.setIn(inContent);
    }

    @Test
    @DisplayName("Check that the adventure deck is a permutation of 100 specified cards")
    void RESP_01_test_01() {
        Main game = new Main();
        game.initializeAdventureDeck();
        //assert the size
        assertEquals(100, game.AdDeck.size());

        //assert that for every card in the adventure deck, it corresponds to one in the deck list
        ArrayList<Main.Card> deckList = game.AdventureDeckList;
        for (Main.Card c : game.AdDeck) {
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
        assertEquals(17, game.EvDeck.size());

        //assert that for every card in the event deck, it corresponds to one in the deck list
        ArrayList<Main.Card> deckList = game.EventDeckList;
        for (Main.Card c : game.EvDeck) {
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
            assertEquals(12, p.hand.size());
        }
        //also check for the correct number of removed cards
        assertEquals(52, game.AdDeck.size());
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
        game.printWinners();
        assertEquals("Player(s) P1 Won.", outContent.toString());
        outContent.reset();
        //test two winners
        game.PlayerList.get(3).addShields(7);
        game.printWinners();
        assertEquals("Player(s) P1, P4 Won.", outContent.toString());
        outContent.reset();
        //test four winners
        game.PlayerList.get(2).addShields(7);
        game.PlayerList.get(1).addShields(7);
        game.printWinners();
        assertEquals("Player(s) P1, P2, P3, P4 Won.", outContent.toString());
        outContent.reset();
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
        game.PlayerList.get(0).printHand();
        assertEquals("P1 Hand: F5, F15, F40, S10, S10, H10, L20", outContent.toString());
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
        assertEquals("\nThe current event is: Plague\n", outContent.toString());
        outContent.reset();

        //check drawn card is correct
        assertEquals(b, game.drawEventCard());
        //check drawn card is in the discard
        assertTrue(game.EvDiscard.contains(b));
        assertEquals("\nThe current event is: Q5\n", outContent.toString());
        outContent.reset();
    }

    @Test
    @DisplayName("Test Plague Card Effects")
    void RESP_07_test_01() {
        Main game = new Main();
        game.curPlayer = game.PlayerList.get(0);
        //init a dummy event deck
        game.EvDeck = new ArrayList<>();
        //make an EvDeck of plagues for testing.
        game.EvDeck.add(game.new Card("Plague", "Event", 0));
        game.EvDeck.add(game.new Card("Plague", "Event", 0));
        game.EvDeck.add(game.new Card("Plague", "Event", 0));

        Main.Card curCard = game.drawEventCard();
        if (curCard.id.equals("Plague")){
            game.plagueEffect();
        }
        //check Plague works on a player with no shields
        assertEquals(0, game.curPlayer.shields);

        game.curPlayer.addShields(1);
        curCard = game.drawEventCard();
        if (curCard.id.equals("Plague")){
            game.plagueEffect();
        }
        //check Plague works on a player with 1 shield
        assertEquals(0, game.curPlayer.shields);

        game.curPlayer.addShields(3);
        curCard = game.drawEventCard();
        if (curCard.id.equals("Plague")){
            game.plagueEffect();
        }
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
        //make an EvDeck of queen's favours for testing.
        game.EvDeck.add(game.new Card("Queen's Favour", "Event", 0));
        game.EvDeck.add(game.new Card("Queen's Favour", "Event", 0));
        game.EvDeck.add(game.new Card("Queen's Favour", "Event", 0));

        game.initializeAdventureDeck();
        game.initializePlayerHands();
        assertEquals(52, game.AdDeck.size());

        Main.Card curCard = game.drawEventCard();
        if (curCard.id.equals("Queen's Favour")){
            game.queenEffect();
        }
        //check Queen's Favour works on hand with 12 cards
        assertEquals(14, game.curPlayer.hand.size());
        assertEquals(50, game.AdDeck.size());

        //remove some cards and test again
        game.curPlayer.hand.removeFirst();
        game.curPlayer.hand.removeFirst();
        game.curPlayer.hand.removeFirst();
        assertEquals(11, game.curPlayer.hand.size());
        curCard = game.drawEventCard();
        if (curCard.id.equals("Queen's Favour")){
            game.queenEffect();
        }
        assertEquals(13, game.curPlayer.hand.size());
        assertEquals(48, game.AdDeck.size());
    }

    @Test
    @DisplayName("Test Prosperity Card Effects")
    void RESP_09_test_01() {
        Main game = new Main();
        game.curPlayer = game.PlayerList.get(0);
        //init a dummy event deck
        game.EvDeck = new ArrayList<>();
        //make an EvDeck of prosperity for testing.
        game.EvDeck.add(game.new Card("Prosperity", "Event", 0));
        game.EvDeck.add(game.new Card("Prosperity", "Event", 0));
        game.EvDeck.add(game.new Card("Prosperity", "Event", 0));

        //each hand now has 12 cards
        game.initializeAdventureDeck();
        game.initializePlayerHands();
        assertEquals(52, game.AdDeck.size());

        Main.Card curCard = game.drawEventCard();
        if (curCard.id.equals("Prosperity")){
            game.prosperityEffect();
        }
        //make sure everyone's drawn 2 cards. Discards only happen at the end of each turn
        assertEquals(14, game.PlayerList.get(0).hand.size());
        assertEquals(14, game.PlayerList.get(1).hand.size());
        assertEquals(14, game.PlayerList.get(2).hand.size());
        assertEquals(14, game.PlayerList.get(3).hand.size());
        assertEquals(44, game.AdDeck.size());

        //remove some cards
        game.PlayerList.get(3).hand.removeFirst();
        game.PlayerList.get(3).hand.removeFirst();
        game.PlayerList.get(3).hand.removeFirst();
        game.PlayerList.get(2).hand.removeFirst();
        game.PlayerList.get(2).hand.removeFirst();
        game.PlayerList.get(1).hand.removeFirst();

        curCard = game.drawEventCard();
        if (curCard.id.equals("Prosperity")){
            game.prosperityEffect();
        }
        //make sure everyone's drawn 2 cards. Discards only happen at the end of each turn
        assertEquals(16, game.PlayerList.get(0).hand.size());
        assertEquals(15, game.PlayerList.get(1).hand.size());
        assertEquals(14, game.PlayerList.get(2).hand.size());
        assertEquals(13, game.PlayerList.get(3).hand.size());
        assertEquals(36, game.AdDeck.size());
    }

    @Test
    @DisplayName("Test Quest Card Effects")
    void RESP_10_test_01() {
        Main game = new Main();
        game.curPlayer = game.PlayerList.get(0);
        //init a dummy event deck
        game.EvDeck = new ArrayList<>();
        //make an EvDeck of quests for testing.
        game.EvDeck.add(game.new Card("Q2", "Quest", 2));
        game.EvDeck.add(game.new Card("Q3", "Quest", 3));
        game.EvDeck.add(game.new Card("Q5", "Quest", 5));

        //quest should just output a declaration string for now; no logic for this responsibility

        Main.Card curCard = game.drawEventCard();
        outContent.reset();
        if (curCard.type.equals("Quest")){
            game.questEffect(curCard);
        }
        assertEquals("Beginning the effects of a Quest card with " + 2 + " stages.\n", outContent.toString());
        outContent.reset();

        curCard = game.drawEventCard();
        outContent.reset();
        if (curCard.type.equals("Quest")){
            game.questEffect(curCard);
        }
        assertEquals("Beginning the effects of a Quest card with " + 3 + " stages.\n", outContent.toString());
        outContent.reset();

        curCard = game.drawEventCard();
        outContent.reset();
        if (curCard.type.equals("Quest")){
            game.questEffect(curCard);
        }
        assertEquals("Beginning the effects of a Quest card with " + 5 + " stages.\n", outContent.toString());
        outContent.reset();
    }

    @Test
    @DisplayName("Test End of Turn output")
    void RESP_11_test_01() {
        Main game = new Main();
        game.curPlayer = game.PlayerList.get(0);
        //init a dummy event deck
        game.EvDeck = new ArrayList<>();
        game.initializeAdventureDeck();
        game.initializePlayerHands();

        //test for end of turn message
        //test for one invalid input then one valid input
        game.endTurn(new Scanner("foo\n"));
        assertEquals("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nP1's turn has ended. Please give controls to P2, and press enter.\nInvalid input.\n", outContent.toString());
        outContent.reset();

        //test for three invalid input then one valid input
        game.endTurn(new Scanner("foo\nfoo\nfoo\n"));
        assertEquals("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nP1's turn has ended. Please give controls to P2, and press enter.\nInvalid input.\nInvalid input.\nInvalid input.\n", outContent.toString());
        outContent.reset();

        //test a single enter press; 1 valid input
        game.endTurn(new Scanner("\n"));
        assertEquals("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nP1's turn has ended. Please give controls to P2, and press enter.\n",
                outContent.toString());
        outContent.reset();
    }

    @Test
    @DisplayName("Test Draw leads to Discard if needed")
    void RESP_12_test_01() {
        Main game = new Main();
        game.curPlayer = game.PlayerList.get(0);
        //init a dummy event deck
        game.EvDeck = new ArrayList<>();
        game.initializeAdventureDeck();
        game.initializePlayerHands();

        //have P1 go up to 13 cards
        game.drawAdCard(game.curPlayer, 1);
        assertEquals("P1 is over the max hand size by 1. Please give controls to P1, and press enter.\n",
                outContent.toString());
        outContent.reset();

        //have P3 go up to 15 cards
        game.drawAdCard(game.PlayerList.get(2), 3);
        assertEquals("P3 is over the max hand size by 3. Please give controls to P3, and press enter.\n",
                outContent.toString());
        outContent.reset();

        //remove some cards from P2
        game.PlayerList.get(1).hand.removeFirst();
        game.PlayerList.get(1).hand.removeFirst();
        game.PlayerList.get(1).hand.removeFirst();
        //check that the discard doesn't activate
        game.drawAdCard(game.PlayerList.get(1), 1);
        assertEquals("", outContent.toString());
        outContent.reset();
    }
}