package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream outContentOriginal = System.out;

    @BeforeEach
    public void setUpStreams() {
        //any prints will be put into the outContent instead
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        //reset the prints back to the original I/O
        System.setOut(outContentOriginal);
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
        if (curCard.id.equals("Plague")) {
            game.plagueEffect();
        }
        //check Plague works on a player with no shields
        assertEquals(0, game.curPlayer.shields);

        game.curPlayer.addShields(1);
        curCard = game.drawEventCard();
        if (curCard.id.equals("Plague")) {
            game.plagueEffect();
        }
        //check Plague works on a player with 1 shield
        assertEquals(0, game.curPlayer.shields);

        game.curPlayer.addShields(3);
        curCard = game.drawEventCard();
        if (curCard.id.equals("Plague")) {
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
        if (curCard.id.equals("Queen's Favour")) {
            game.queenEffectNoDiscard();
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
        if (curCard.id.equals("Queen's Favour")) {
            game.queenEffectNoDiscard();
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
        if (curCard.id.equals("Prosperity")) {
            game.prosperityEffectNoDiscard();
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
        if (curCard.id.equals("Prosperity")) {
            game.prosperityEffectNoDiscard();
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
        if (curCard.type.equals("Quest")) {
            game.questEffect(curCard);
        }
        assertEquals("Beginning the effects of a Quest card with " + 2 + " stages.\n", outContent.toString());
        outContent.reset();

        curCard = game.drawEventCard();
        outContent.reset();
        if (curCard.type.equals("Quest")) {
            game.questEffect(curCard);
        }
        assertEquals("Beginning the effects of a Quest card with " + 3 + " stages.\n", outContent.toString());
        outContent.reset();

        curCard = game.drawEventCard();
        outContent.reset();
        if (curCard.type.equals("Quest")) {
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
        game.initializeAdventureDeck();
        game.initializePlayerHands();

        //have P1 go up to 13 cards
        game.drawAdCardNoDiscard(game.curPlayer, 1);
        assertEquals("\n\n\n\n\n\n\n\n\n\nP1 is over the max hand size by 1. Please give controls to P1, and press enter.\n",
                outContent.toString());
        outContent.reset();

        //have P3 go up to 15 cards
        game.drawAdCardNoDiscard(game.PlayerList.get(2), 3);
        assertEquals("\n\n\n\n\n\n\n\n\n\nP3 is over the max hand size by 3. Please give controls to P3, and press enter.\n",
                outContent.toString());
        outContent.reset();

        //remove some cards from P2
        game.PlayerList.get(1).hand.removeFirst();
        game.PlayerList.get(1).hand.removeFirst();
        game.PlayerList.get(1).hand.removeFirst();
        //check that the discard doesn't activate
        game.drawAdCardNoDiscard(game.PlayerList.get(1), 1);
        assertEquals("", outContent.toString());
        outContent.reset();
    }

    @Test
    @DisplayName("Test discard prompts and verifies with the player")
    void RESP_13_test_01() {
        Main game = new Main();
        game.curPlayer = game.PlayerList.get(0);

        //create a dummy hand of 15 cards
        game.curPlayer.addCard(game.new Card("F5", "Foe", 5));
        game.curPlayer.addCard(game.new Card("F5", "Foe", 5));
        game.curPlayer.addCard(game.new Card("F5", "Foe", 5));
        game.curPlayer.addCard(game.new Card("F10", "Foe", 10));
        game.curPlayer.addCard(game.new Card("F10", "Foe", 10));
        game.curPlayer.addCard(game.new Card("F15", "Foe", 15));
        game.curPlayer.addCard(game.new Card("F20", "Foe", 20));
        game.curPlayer.addCard(game.new Card("F20", "Foe", 20));
        game.curPlayer.addCard(game.new Card("F20", "Foe", 20));
        game.curPlayer.addCard(game.new Card("F70", "Foe", 70));
        game.curPlayer.addCard(game.new Card("D5", "Weapon", 5));
        game.curPlayer.addCard(game.new Card("D5", "Weapon", 5));
        game.curPlayer.addCard(game.new Card("S10", "Weapon", 10));
        game.curPlayer.addCard(game.new Card("H10", "Weapon", 10));
        game.curPlayer.addCard(game.new Card("L20", "Weapon", 20));

        //test one invalid then 3 valid. Index starts at 1
        game.discardAdCard(game.curPlayer, 3, new Scanner("\n16\n2\n4\n11"));
        assertEquals("""
                        
                        Are you P1?
                        You have to discard 3 more card(s)
                        P1, This is your hand:
                        P1 Hand: F5, F5, F5, F10, F10, F15, F20, F20, F20, F70, D5, D5, S10, H10, L20
                        Please select a card to discard by index(1 - 15)
                        Invalid index. Try again.
                        You have to discard 3 more card(s)
                        P1, This is your hand:
                        P1 Hand: F5, F5, F5, F10, F10, F15, F20, F20, F20, F70, D5, D5, S10, H10, L20
                        Please select a card to discard by index(1 - 15)
                        You have to discard 2 more card(s)
                        P1, This is your hand:
                        P1 Hand: F5, F5, F10, F10, F15, F20, F20, F20, F70, D5, D5, S10, H10, L20
                        Please select a card to discard by index(1 - 14)
                        You have to discard 1 more card(s)
                        P1, This is your hand:
                        P1 Hand: F5, F5, F10, F15, F20, F20, F20, F70, D5, D5, S10, H10, L20
                        Please select a card to discard by index(1 - 13)
                        Discarding Complete. This is your new hand:
                        P1 Hand: F5, F5, F10, F15, F20, F20, F20, F70, D5, D5, H10, L20
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        """,
                outContent.toString());
        outContent.reset();
        //one more test with an invalid input at the very beginning
        game.curPlayer.addCard(game.new Card("F5", "Foe", 5));
        game.discardAdCard(game.curPlayer, 1, new Scanner("asdf\n\n7"));
        assertEquals("""
                        
                        Are you P1?
                        Invalid input. Please press Enter.
                        You have to discard 1 more card(s)
                        P1, This is your hand:
                        P1 Hand: F5, F5, F5, F10, F15, F20, F20, F20, F70, D5, D5, H10, L20
                        Please select a card to discard by index(1 - 13)
                        Discarding Complete. This is your new hand:
                        P1 Hand: F5, F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        """,
                outContent.toString());
        outContent.reset();
    }

    @Test
    @DisplayName("Test sponsor seeking")
    void RESP_14_test_01() {
        Main game = new Main();
        game.curPlayer = game.PlayerList.get(0);

        //ask p1, p2, p3, p4 if they'd like to sponsor.
        //test p1, p2 decline, p3 accept
        Main.Player p = game.seekSponsor(new Scanner("K\nN\nN\nY"));
        assertEquals("""
                        P1, would you like to sponsor this quest? (Y/N)
                        Invalid input.
                        P2, would you like to sponsor this quest? (Y/N)
                        P3, would you like to sponsor this quest? (Y/N)
                        P3 has accepted to sponsor this quest.
                        """
                , outContent.toString());
        outContent.reset();
        assertEquals(game.PlayerList.get(2), p);

        //ask p1, p2, p3, p4 if they'd like to sponsor.
        //test all decline
        p = game.seekSponsor(new Scanner("N\nN\nN\nN"));
        assertEquals("""
                        P1, would you like to sponsor this quest? (Y/N)
                        P2, would you like to sponsor this quest? (Y/N)
                        P3, would you like to sponsor this quest? (Y/N)
                        P4, would you like to sponsor this quest? (Y/N)
                        Everybody turned down the sponsor.
                        """
                , outContent.toString());
        outContent.reset();
        assertNull(p);

        //start on P3 instead
        game.curPlayer = game.PlayerList.get(2);
        p = game.seekSponsor(new Scanner("N\nY"));
        assertEquals("""
                        P3, would you like to sponsor this quest? (Y/N)
                        P4, would you like to sponsor this quest? (Y/N)
                        P4 has accepted to sponsor this quest.
                        """
                , outContent.toString());
        outContent.reset();
        assertEquals(game.PlayerList.get(3), p);
    }

    @Test
    @DisplayName("Test Quest Building start")
    void RESP_15_test_01() {
        Main game = new Main();

        //state that the build phase is beginning and upon confirmation that the sponsor is in control, display their hand
        //leave the rest of the build phase later
        Main.Player sponsor = game.PlayerList.get(0);

        //create a dummy sponsor hand
        sponsor.addCard(game.new Card("F5", "Foe", 5));
        sponsor.addCard(game.new Card("F5", "Foe", 5));
        sponsor.addCard(game.new Card("F5", "Foe", 5));
        sponsor.addCard(game.new Card("F10", "Foe", 10));
        sponsor.addCard(game.new Card("F15", "Foe", 15));
        sponsor.addCard(game.new Card("F20", "Foe", 20));
        sponsor.addCard(game.new Card("F20", "Foe", 20));
        sponsor.addCard(game.new Card("F70", "Foe", 70));
        sponsor.addCard(game.new Card("D5", "Weapon", 5));
        sponsor.addCard(game.new Card("D5", "Weapon", 5));
        sponsor.addCard(game.new Card("H10", "Weapon", 10));
        sponsor.addCard(game.new Card("L20", "Weapon", 20));

        game.beginQuestBuilding(sponsor, new Scanner("\n"));
        assertEquals("""
                        P1, you are the sponsor. Please confirm you are in control.
                        P1, this is your hand:
                        P1 Hand: F5, F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        """
                , outContent.toString());
        outContent.reset();

        //try the second player
        sponsor = game.PlayerList.get(1);

        //create a dummy sponsor hand
        sponsor.addCard(game.new Card("F5", "Foe", 5));
        sponsor.addCard(game.new Card("F5", "Foe", 5));
        sponsor.addCard(game.new Card("D5", "Weapon", 5));
        sponsor.addCard(game.new Card("D5", "Weapon", 5));

        game.beginQuestBuilding(sponsor, new Scanner("K\n\n"));
        assertEquals("""
                        P2, you are the sponsor. Please confirm you are in control.
                        Invalid input.
                        P2, this is your hand:
                        P2 Hand: F5, F5, D5, D5
                        """
                , outContent.toString());
        outContent.reset();
    }

    @Test
    @DisplayName("Test Quest Building start")
    void RESP_16_test_01() {
        Main game = new Main();

        //state that the build phase is beginning and upon confirmation that the sponsor is in control, display their hand
        //leave the rest of the build phase later
        Main.Player sponsor = game.PlayerList.get(0);

        //create a dummy sponsor hand
        sponsor.addCard(game.new Card("F5", "Foe", 5));
        sponsor.addCard(game.new Card("F5", "Foe", 5));
        sponsor.addCard(game.new Card("F5", "Foe", 5));
        sponsor.addCard(game.new Card("F10", "Foe", 10));
        sponsor.addCard(game.new Card("F15", "Foe", 15));
        sponsor.addCard(game.new Card("F20", "Foe", 20));
        sponsor.addCard(game.new Card("F20", "Foe", 20));
        sponsor.addCard(game.new Card("F70", "Foe", 70));
        sponsor.addCard(game.new Card("D5", "Weapon", 5));
        sponsor.addCard(game.new Card("D5", "Weapon", 5));
        sponsor.addCard(game.new Card("H10", "Weapon", 10));
        sponsor.addCard(game.new Card("L20", "Weapon", 20));

        //start building 4 stages
        ArrayList<ArrayList<Main.Card>> stages = game.beginStageBuilding(sponsor, 4, new Scanner("1\n\n3\n\n3\n8\n\n5\n\n\n"));
        assertEquals("""
                        You must build 4 stages.
                        Stage 1:\s
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        Stage 1: F5
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        Stage 2:\s
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        Stage 2: F10
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F15, F20, F20, F70, D5, D5, H10, L20
                        Stage 3:\s
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F15, F20, F20, F70, D5, D5, H10, L20
                        Stage 3: F15
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F20, F20, F70, D5, D5, H10, L20
                        Stage 3: F15, H10
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F20, F20, F70, D5, D5, L20
                        Stage 4:\s
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F20, F20, F70, D5, D5, L20
                        Stage 4: F70
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F20, F20, D5, D5, L20
                        """
                , outContent.toString());
        outContent.reset();
        //check stage contents
        assertEquals("F5", stages.get(0).get(0).toString());
        assertEquals("F10", stages.get(1).get(0).toString());
        assertEquals("F15", stages.get(2).get(0).toString());
        assertEquals("H10", stages.get(2).get(1).toString());
        assertEquals("F70", stages.get(3).get(0).toString());
    }

    @Test
    @DisplayName("Test Quest Building start w/ invalid inputs")
    void RESP_17_test_01() {
        Main game = new Main();

        //state that the build phase is beginning and upon confirmation that the sponsor is in control, display their hand
        //leave the rest of the build phase later
        Main.Player sponsor = game.PlayerList.get(0);

        //create a dummy sponsor hand
        sponsor.addCard(game.new Card("F5", "Foe", 5));
        sponsor.addCard(game.new Card("F5", "Foe", 5));
        sponsor.addCard(game.new Card("F5", "Foe", 5));
        sponsor.addCard(game.new Card("F10", "Foe", 10));
        sponsor.addCard(game.new Card("F15", "Foe", 15));
        sponsor.addCard(game.new Card("F20", "Foe", 20));
        sponsor.addCard(game.new Card("F20", "Foe", 20));
        sponsor.addCard(game.new Card("F70", "Foe", 70));
        sponsor.addCard(game.new Card("D5", "Weapon", 5));
        sponsor.addCard(game.new Card("D5", "Weapon", 5));
        sponsor.addCard(game.new Card("H10", "Weapon", 10));
        sponsor.addCard(game.new Card("L20", "Weapon", 20));

        //start building 4 stages
        ArrayList<ArrayList<Main.Card>> stages = game.beginStageBuilding(sponsor, 4, new Scanner("\n27\n1\n1\n\n3\n\n3\n8\n\n5\n5\n5\n\n\n"));
        assertEquals("""
                        You must build 4 stages.
                        Stage 1:\s
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        Stage cannot be empty. Please insert at least one foe.
                        Stage 1:\s
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        Invalid input. Please provide a valid index or press enter.
                        Stage 1:\s
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        Stage 1: F5
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        Invalid input. Cannot put two foes in one stage.
                        Stage 1: F5
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        Stage 2:\s
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        Stage 2: F10
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F15, F20, F20, F70, D5, D5, H10, L20
                        Stage 3:\s
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F15, F20, F20, F70, D5, D5, H10, L20
                        Stage 3: F15
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F20, F20, F70, D5, D5, H10, L20
                        Stage 3: F15, H10
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F20, F20, F70, D5, D5, L20
                        Stage 4:\s
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F20, F20, F70, D5, D5, L20
                        Stage 4: F70
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F20, F20, D5, D5, L20
                        Stage 4: F70, D5
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F20, F20, D5, L20
                        Invalid input. Duplicate weapon.
                        Stage 4: F70, D5
                        Please select an index of a card you wish to add to the stage, or press enter to finish.
                        P1 Hand: F5, F5, F20, F20, D5, L20
                        """
                , outContent.toString());
        outContent.reset();
        //check stage contents
        assertEquals("F5", stages.get(0).get(0).toString());
        assertEquals("F10", stages.get(1).get(0).toString());
        assertEquals("F15", stages.get(2).get(0).toString());
        assertEquals("H10", stages.get(2).get(1).toString());
        assertEquals("F70", stages.get(3).get(0).toString());
        assertEquals("D5", stages.get(3).get(1).toString());
    }

    @Test
    @DisplayName("Test Quest Build End")
    void RESP_18_test_01() {
        Main game = new Main();
        Main.Player sponsor = game.PlayerList.get(0);

        //display the completed stages to the sponsor, then clear the screen
        ArrayList<ArrayList<Main.Card>> stages = new ArrayList<>();

        stages.add(new ArrayList<>());
        stages.get(0).add(game.new Card("F5", "Foe", 5));
        stages.get(0).add(game.new Card("D5", "Weapon", 5));
        stages.add(new ArrayList<>());
        stages.get(1).add(game.new Card("F15", "Foe", 15));
        stages.add(new ArrayList<>());
        stages.get(2).add(game.new Card("F15", "Foe", 15));
        stages.get(2).add(game.new Card("D5", "Weapon", 5));
        stages.add(new ArrayList<>());
        stages.get(3).add(game.new Card("F70", "Foe", 70));

        //print the stages
        game.endStageBuilding(sponsor, stages, new Scanner("\n"));
        assertEquals("""
                        P1, here are your stages:
                        Stage 1: F5, D5
                        Stage 2: F15
                        Stage 3: F15, D5
                        Stage 4: F70
                        Press enter to confirm.
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        """
                , outContent.toString());
        outContent.reset();
    }

    @Test
    @DisplayName("Test participant seeking")
    void RESP_19_test_01() {
        Main game = new Main();
        Main.Player sponsor = game.PlayerList.get(0);
        //being the sponsor makes you ineligible
        //declining to participate makes you ineligible
        //failing a stage makes you ineligible

        //ask p2, p3, p4 if they'd like to participate. p1 ineligible due to being the sponsor
        ArrayList<Main.Player> participants = game.seekParticipants(sponsor, true, new Scanner("K\nN\nY\nY\n"));
        assertEquals("""
                        P2, would you like to participate in this stage? (Y/N)
                        Invalid input.
                        P3, would you like to participate in this stage? (Y/N)
                        P4, would you like to participate in this stage? (Y/N)
                        P3, P4 will participate in this stage.
                        """
                , outContent.toString());
        outContent.reset();
        //double-check the player list
        assertFalse(participants.contains(game.PlayerList.get(0)));
        assertFalse(participants.contains(game.PlayerList.get(1)));
        assertTrue(participants.contains(game.PlayerList.get(2)));
        assertTrue(participants.contains(game.PlayerList.get(3)));
        //double-check eligibility
        assertFalse(game.PlayerList.get(0).eligible);
        assertFalse(game.PlayerList.get(1).eligible);
        assertTrue(game.PlayerList.get(2).eligible);
        assertTrue(game.PlayerList.get(3).eligible);

        //second stage test. Have P4 "fail" stage 1
        game.PlayerList.get(3).eligible = false;
        participants = game.seekParticipants(sponsor, false, new Scanner("Y\n"));
        assertEquals("""
                        P3, would you like to participate in this stage? (Y/N)
                        P3 will participate in this stage.
                        """
                , outContent.toString());
        outContent.reset();
        //double-check the player list
        assertFalse(participants.contains(game.PlayerList.get(0)));
        assertFalse(participants.contains(game.PlayerList.get(1)));
        assertTrue(participants.contains(game.PlayerList.get(2)));
        assertFalse(participants.contains(game.PlayerList.get(3)));
        //double-check eligibility
        assertFalse(game.PlayerList.get(0).eligible);
        assertFalse(game.PlayerList.get(1).eligible);
        assertTrue(game.PlayerList.get(2).eligible);
        assertFalse(game.PlayerList.get(3).eligible);
    }

    @Test
    @DisplayName("Test Quest Attack Team Building")
    void RESP_20_test_01() {
        Main game = new Main();

        ArrayList<Main.Player> stageParticipants = new ArrayList<>();
        stageParticipants.add(game.PlayerList.get(1));
        stageParticipants.add(game.PlayerList.get(2));

        //create two dummy hands
        stageParticipants.get(0).addCard(game.new Card("F5", "Foe", 5));
        stageParticipants.get(0).addCard(game.new Card("F5", "Foe", 5));
        stageParticipants.get(0).addCard(game.new Card("F5", "Foe", 5));
        stageParticipants.get(0).addCard(game.new Card("F10", "Foe", 10));
        stageParticipants.get(0).addCard(game.new Card("F15", "Foe", 15));
        stageParticipants.get(0).addCard(game.new Card("F20", "Foe", 20));
        stageParticipants.get(0).addCard(game.new Card("F20", "Foe", 20));
        stageParticipants.get(0).addCard(game.new Card("F70", "Foe", 70));
        stageParticipants.get(0).addCard(game.new Card("D5", "Weapon", 5));
        stageParticipants.get(0).addCard(game.new Card("D5", "Weapon", 5));
        stageParticipants.get(0).addCard(game.new Card("H10", "Weapon", 10));
        stageParticipants.get(0).addCard(game.new Card("L20", "Weapon", 20));

        stageParticipants.get(1).addCard(game.new Card("F5", "Foe", 5));
        stageParticipants.get(1).addCard(game.new Card("F5", "Foe", 5));
        stageParticipants.get(1).addCard(game.new Card("F10", "Foe", 10));
        stageParticipants.get(1).addCard(game.new Card("F15", "Foe", 15));
        stageParticipants.get(1).addCard(game.new Card("F20", "Foe", 20));
        stageParticipants.get(1).addCard(game.new Card("D5", "Weapon", 5));
        stageParticipants.get(1).addCard(game.new Card("D5", "Weapon", 5));
        stageParticipants.get(1).addCard(game.new Card("H10", "Weapon", 10));
        stageParticipants.get(1).addCard(game.new Card("S10", "Weapon", 10));
        stageParticipants.get(1).addCard(game.new Card("S10", "Weapon", 10));
        stageParticipants.get(1).addCard(game.new Card("B15", "Weapon", 10));
        stageParticipants.get(1).addCard(game.new Card("L20", "Weapon", 20));

        //start building 4 stages
        ArrayList<ArrayList<Main.Card>> attackTeams = game.createAttackTeams(stageParticipants, new Scanner("\n9\n\n\n\n6\n7\n8\n\n\n"));
        assertEquals("""
                        Please confirm you are P2
                        Current Attack Team:\s
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P2 Hand: F5, F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        Current Attack Team: D5
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P2 Hand: F5, F5, F5, F10, F15, F20, F20, F70, D5, H10, L20
                        P2, Here is your attack team:
                        Current Attack Team: D5
                        Press enter to confirm.
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        Please confirm you are P3
                        Current Attack Team:\s
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P3 Hand: F5, F5, F10, F15, F20, D5, D5, S10, S10, H10, B15, L20
                        Current Attack Team: D5
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P3 Hand: F5, F5, F10, F15, F20, D5, S10, S10, H10, B15, L20
                        Current Attack Team: D5, S10
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P3 Hand: F5, F5, F10, F15, F20, D5, S10, H10, B15, L20
                        Current Attack Team: D5, S10, H10
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P3 Hand: F5, F5, F10, F15, F20, D5, S10, B15, L20
                        P3, Here is your attack team:
                        Current Attack Team: D5, S10, H10
                        Press enter to confirm.
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        """
                , outContent.toString());
        outContent.reset();
        //check attack team contents
        assertEquals("D5", attackTeams.get(0).get(0).toString());
        assertEquals("D5", attackTeams.get(1).get(0).toString());
        assertEquals("S10", attackTeams.get(1).get(1).toString());
        assertEquals("H10", attackTeams.get(1).get(2).toString());
        //no need to check hands; above function will use printHand(), which will cause an assert fail on string if incorrect
    }
    @Test
    @DisplayName("Test Quest Attack Team Building w/ invalid selections")
    void RESP_21_test_01() {
        Main game = new Main();

        ArrayList<Main.Player> stageParticipants = new ArrayList<>();
        stageParticipants.add(game.PlayerList.get(1));
        stageParticipants.add(game.PlayerList.get(2));

        //create two dummy hands
        stageParticipants.get(0).addCard(game.new Card("F5", "Foe", 5));
        stageParticipants.get(0).addCard(game.new Card("F5", "Foe", 5));
        stageParticipants.get(0).addCard(game.new Card("F5", "Foe", 5));
        stageParticipants.get(0).addCard(game.new Card("F10", "Foe", 10));
        stageParticipants.get(0).addCard(game.new Card("F15", "Foe", 15));
        stageParticipants.get(0).addCard(game.new Card("F20", "Foe", 20));
        stageParticipants.get(0).addCard(game.new Card("F20", "Foe", 20));
        stageParticipants.get(0).addCard(game.new Card("F70", "Foe", 70));
        stageParticipants.get(0).addCard(game.new Card("D5", "Weapon", 5));
        stageParticipants.get(0).addCard(game.new Card("D5", "Weapon", 5));
        stageParticipants.get(0).addCard(game.new Card("H10", "Weapon", 10));
        stageParticipants.get(0).addCard(game.new Card("L20", "Weapon", 20));

        stageParticipants.get(1).addCard(game.new Card("F5", "Foe", 5));
        stageParticipants.get(1).addCard(game.new Card("F5", "Foe", 5));
        stageParticipants.get(1).addCard(game.new Card("F10", "Foe", 10));
        stageParticipants.get(1).addCard(game.new Card("F15", "Foe", 15));
        stageParticipants.get(1).addCard(game.new Card("F20", "Foe", 20));
        stageParticipants.get(1).addCard(game.new Card("D5", "Weapon", 5));
        stageParticipants.get(1).addCard(game.new Card("D5", "Weapon", 5));
        stageParticipants.get(1).addCard(game.new Card("H10", "Weapon", 10));
        stageParticipants.get(1).addCard(game.new Card("S10", "Weapon", 10));
        stageParticipants.get(1).addCard(game.new Card("S10", "Weapon", 10));
        stageParticipants.get(1).addCard(game.new Card("B15", "Weapon", 10));
        stageParticipants.get(1).addCard(game.new Card("L20", "Weapon", 20));

        //start building 4 stages
        //this time, select invalid indexes and cards as well. Otherwise, it's the same expected in/out.
        //foes are invalid options, as well as duplicate weapons
        ArrayList<ArrayList<Main.Card>> attackTeams = game.createAttackTeams(stageParticipants, new Scanner("\n15\n9\n9\n\n\n\n6\n7\n8\n1\n\n\n"));
        assertEquals("""
                        Please confirm you are P2
                        Current Attack Team:\s
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P2 Hand: F5, F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        Invalid input. Please provide a valid index or press enter.
                        Current Attack Team:\s
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P2 Hand: F5, F5, F5, F10, F15, F20, F20, F70, D5, D5, H10, L20
                        Current Attack Team: D5
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P2 Hand: F5, F5, F5, F10, F15, F20, F20, F70, D5, H10, L20
                        Invalid input. Duplicate weapon.
                        Current Attack Team: D5
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P2 Hand: F5, F5, F5, F10, F15, F20, F20, F70, D5, H10, L20
                        P2, Here is your attack team:
                        Current Attack Team: D5
                        Press enter to confirm.
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        Please confirm you are P3
                        Current Attack Team:\s
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P3 Hand: F5, F5, F10, F15, F20, D5, D5, S10, S10, H10, B15, L20
                        Current Attack Team: D5
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P3 Hand: F5, F5, F10, F15, F20, D5, S10, S10, H10, B15, L20
                        Current Attack Team: D5, S10
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P3 Hand: F5, F5, F10, F15, F20, D5, S10, H10, B15, L20
                        Current Attack Team: D5, S10, H10
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P3 Hand: F5, F5, F10, F15, F20, D5, S10, B15, L20
                        Invalid input. Cannot add foes to attack team.
                        Current Attack Team: D5, S10, H10
                        Please select an index of a card you wish to add to the attack, or press enter to finish.
                        P3 Hand: F5, F5, F10, F15, F20, D5, S10, B15, L20
                        P3, Here is your attack team:
                        Current Attack Team: D5, S10, H10
                        Press enter to confirm.
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        """
                , outContent.toString());
        outContent.reset();
        //check attack team contents
        assertEquals("D5", attackTeams.get(0).get(0).toString());
        assertEquals("D5", attackTeams.get(1).get(0).toString());
        assertEquals("S10", attackTeams.get(1).get(1).toString());
        assertEquals("H10", attackTeams.get(1).get(2).toString());
        //no need to check hands; above function will use printHand(), which will cause an assert fail on string if incorrect
    }


}