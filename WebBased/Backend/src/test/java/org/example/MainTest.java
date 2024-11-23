package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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

        //remove 2 cards from the hand
        game.curPlayer.hand.removeFirst();
        game.curPlayer.hand.removeFirst();

        Main.Card curCard = game.drawEventCard();
        if (curCard.id.equals("Queen's Favour")) {
            game.queenEffect();
        }
        //check Queen's Favour works on hand with 12 cards
        assertEquals(12, game.curPlayer.hand.size());
        assertEquals(50, game.AdDeck.size());

        //remove some cards and test again
        game.curPlayer.hand.removeFirst();
        game.curPlayer.hand.removeFirst();
        game.curPlayer.hand.removeFirst();
        game.curPlayer.hand.removeFirst();
        game.curPlayer.hand.removeFirst();
        assertEquals(7, game.curPlayer.hand.size());
        curCard = game.drawEventCard();
        if (curCard.id.equals("Queen's Favour")) {
            game.queenEffect();
        }
        assertEquals(9, game.curPlayer.hand.size());
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

        game.PlayerList.get(0).hand.removeFirst();
        game.PlayerList.get(0).hand.removeFirst();
        game.PlayerList.get(1).hand.removeFirst();
        game.PlayerList.get(1).hand.removeFirst();
        game.PlayerList.get(2).hand.removeFirst();
        game.PlayerList.get(2).hand.removeFirst();
        game.PlayerList.get(3).hand.removeFirst();
        game.PlayerList.get(3).hand.removeFirst();

        Main.Card curCard = game.drawEventCard();
        if (curCard.id.equals("Prosperity")) {
            game.prosperityEffect();
        }
        //make sure everyone's drawn 2 cards. Discards only happen at the end of each turn
        assertEquals(12, game.PlayerList.get(0).hand.size());
        assertEquals(12, game.PlayerList.get(1).hand.size());
        assertEquals(12, game.PlayerList.get(2).hand.size());
        assertEquals(12, game.PlayerList.get(3).hand.size());
        assertEquals(44, game.AdDeck.size());

        //remove some more cards
        game.PlayerList.get(0).hand.removeFirst();
        game.PlayerList.get(0).hand.removeFirst();
        game.PlayerList.get(1).hand.removeFirst();
        game.PlayerList.get(1).hand.removeFirst();
        game.PlayerList.get(2).hand.removeFirst();
        game.PlayerList.get(2).hand.removeFirst();
        game.PlayerList.get(3).hand.removeFirst();
        game.PlayerList.get(3).hand.removeFirst();

        game.PlayerList.get(3).hand.removeFirst();
        game.PlayerList.get(3).hand.removeFirst();
        game.PlayerList.get(3).hand.removeFirst();
        game.PlayerList.get(2).hand.removeFirst();
        game.PlayerList.get(2).hand.removeFirst();
        game.PlayerList.get(1).hand.removeFirst();

        curCard = game.drawEventCard();
        if (curCard.id.equals("Prosperity")) {
            game.prosperityEffect();
        }
        //make sure everyone's drawn 2 cards. Discards only happen at the end of each turn
        assertEquals(12, game.PlayerList.get(0).hand.size());
        assertEquals(11, game.PlayerList.get(1).hand.size());
        assertEquals(10, game.PlayerList.get(2).hand.size());
        assertEquals(9, game.PlayerList.get(3).hand.size());
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

        //have P1 go up to 13 cards, then discard the first
        game.drawAdCard(game.curPlayer, 1, new Scanner("\n\n\n\n\n\n\n1\n\n\n\n\n\n"));
        assertTrue(outContent.toString().contains("\n\n\n\n\n\n\n\n\n\nP1 is over the max hand size by 1. Please give controls to P1, and press enter.\n"));
        outContent.reset();

        //have P3 go up to 15 cards
        game.drawAdCard(game.PlayerList.get(2), 3, new Scanner("\n\n\n\n\n\n\n1\n1\n1\n\n\n\n"));
        assertTrue(outContent.toString().contains("\n\n\n\n\n\n\n\n\n\nP3 is over the max hand size by 3. Please give controls to P3, and press enter.\n"));
        outContent.reset();

        //remove some cards from P2
        game.PlayerList.get(1).hand.removeFirst();
        game.PlayerList.get(1).hand.removeFirst();
        game.PlayerList.get(1).hand.removeFirst();
        //check that the discard doesn't activate
        game.drawAdCard(game.PlayerList.get(1), 1, new Scanner(""));
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
    @DisplayName("Test sponsor seeking - P3 Accept")
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
    }

    @Test
    @DisplayName("Test sponsor seeking - All Decline")
    void RESP_14_test_02() {
        Main game = new Main();
        game.curPlayer = game.PlayerList.get(0);

        //ask p1, p2, p3, p4 if they'd like to sponsor.
        //test all decline
        Main.Player p = game.seekSponsor(new Scanner("N\nN\nN\nN"));
        assertEquals("""
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        P1, would you like to sponsor this quest? (Y/N)
                        P2, would you like to sponsor this quest? (Y/N)
                        P3, would you like to sponsor this quest? (Y/N)
                        P4, would you like to sponsor this quest? (Y/N)
                        Everybody turned down the sponsor opportunity.
                        """
                , outContent.toString());
        outContent.reset();
        assertNull(p);
    }

    @Test
    @DisplayName("Test sponsor seeking - P4 Accept, curPlayer P3")
    void RESP_14_test_03() {
        Main game = new Main();
        game.curPlayer = game.PlayerList.get(2);

        Main.Player p = game.seekSponsor(new Scanner("N\nY"));
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
    @DisplayName("Test Quest Building start for P1")
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
    }

    @Test
    @DisplayName("Test Quest Building start for non-P1")
    void RESP_15_test_02() {
        Main game = new Main();

        //state that the build phase is beginning and upon confirmation that the sponsor is in control, display their hand
        //leave the rest of the build phase later
        Main.Player sponsor = game.PlayerList.get(1);

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
    @DisplayName("Test participant seeking for first stage")
    void RESP_19_test_01() {
        Main game = new Main();
        Main.Player sponsor = game.PlayerList.get(0);
        //being the sponsor makes you ineligible
        //declining to participate makes you ineligible
        //failing a stage makes you ineligible

        //ask p2, p3, p4 if they'd like to participate. p1 ineligible due to being the sponsor
        //if it's the first stage, reset their eligibility first
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
    }


    @Test
    @DisplayName("Test participant seeking for second stage and beyond")
    void RESP_19_test_02() {
        Main game = new Main();
        Main.Player sponsor = game.PlayerList.get(0);

        game.PlayerList.get(2).eligible = true;
        game.PlayerList.get(3).eligible = true;

        //second stage test. Have P4 "fail" stage 1
        game.PlayerList.get(3).eligible = false;
        ArrayList<Main.Player> participants = game.seekParticipants(sponsor, false, new Scanner("Y\n"));
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

        //have each participant create attacks
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

    @Test
    @DisplayName("Test Card draw at beginning of each stage")
    void RESP_22_test_01() {
        Main game = new Main();

        ArrayList<Main.Player> stageParticipants = new ArrayList<>();
        stageParticipants.add(game.PlayerList.get(0));

        //don't need to draw specific cards from AdDeck; just need to test if number of cards in hand and deck become as expected
        game.initializeAdventureDeck();

        //shouldn't output anything
        game.participantsDrawCard(stageParticipants, new Scanner(""));
        assertEquals("Player(s) P1 will draw a card.\n", outContent.toString());
        outContent.reset();

        assertEquals(1, game.PlayerList.get(0).hand.size());
        assertEquals(0, game.PlayerList.get(1).hand.size());
        assertEquals(0, game.PlayerList.get(2).hand.size());
        assertEquals(0, game.PlayerList.get(3).hand.size());
        assertEquals(99, game.AdDeck.size());

        stageParticipants.add(game.PlayerList.get(1));
        //shouldn't output anything
        game.participantsDrawCard(stageParticipants, new Scanner(""));
        assertEquals("Player(s) P1, P2 will draw a card.\n", outContent.toString());
        outContent.reset();

        assertEquals(2, game.PlayerList.get(0).hand.size());
        assertEquals(1, game.PlayerList.get(1).hand.size());
        assertEquals(0, game.PlayerList.get(2).hand.size());
        assertEquals(0, game.PlayerList.get(3).hand.size());
        assertEquals(97, game.AdDeck.size());

        game.drawAdCard(game.PlayerList.get(0), 10, new Scanner(""));
        assertEquals(12, game.PlayerList.get(0).hand.size());
        assertEquals(87, game.AdDeck.size());
        //should force P1 to discard some cards now
        game.participantsDrawCard(stageParticipants, new Scanner("\n\n\n\n1\n\n\n\n"));
        outContent.reset();

        assertEquals(12, game.PlayerList.get(0).hand.size());
        assertEquals(2, game.PlayerList.get(1).hand.size());
        assertEquals(0, game.PlayerList.get(2).hand.size());
        assertEquals(0, game.PlayerList.get(3).hand.size());
        assertEquals(85, game.AdDeck.size());
    }

    @Test
    @DisplayName("Test stage resolution")
    void RESP_23_test_01() {
        Main game = new Main();
        //dummy stage and attack teams
        ArrayList<Main.Card> curStage = new ArrayList<>();
        curStage.add(game.new Card("F5", "Foe", 5));
        curStage.add(game.new Card("D5", "Weapon", 5));

        ArrayList<Main.Player> participants = new ArrayList<>();
        participants.add(game.PlayerList.get(0));
        participants.add(game.PlayerList.get(1));

        ArrayList<Main.Card> p1Attack = new ArrayList<>();
        p1Attack.add(game.new Card("D5", "Weapon", 5));

        ArrayList<Main.Card> p2Attack = new ArrayList<>();
        p2Attack.add(game.new Card("D5", "Weapon", 5));
        p2Attack.add(game.new Card("S10", "Weapon", 10));

        ArrayList<ArrayList<Main.Card>> attackTeams = new ArrayList<>();
        attackTeams.add(p1Attack);
        attackTeams.add(p2Attack);

        game.PlayerList.get(0).eligible = true;
        game.PlayerList.get(1).eligible = true;

        //init done. Test resolve function. Should return a list of who passed and who didn't, and set eligibility if required
        ArrayList<Boolean> attackOutcomes = game.resolveAttacks(curStage, attackTeams, participants);
        assertFalse(attackOutcomes.get(0));
        assertTrue(attackOutcomes.get(1));
        assertFalse(game.PlayerList.get(0).eligible);
        assertTrue(game.PlayerList.get(1).eligible);
        assertEquals("""
                The current stage was: F5, D5.
                P2 are the winner(s) and are eligible to continue.
                """, outContent.toString());
        outContent.reset();
    }

    @Test
    @DisplayName("Test continue attacks")
    void RESP_24_test_01() {
        Main game = new Main();
        //if outcome is all false, all attack teams failed. Return false
        ArrayList<Boolean> outcome = new ArrayList<>();
        outcome.add(false);
        outcome.add(false);
        boolean continueQuest = game.findStageSurvivors(outcome);
        assertFalse(continueQuest);
        assertEquals("No one passed the stage. Aborting quest.\n", outContent.toString());
        outContent.reset();

        //have one survivor
        outcome.add(true);
        continueQuest = game.findStageSurvivors(outcome);
        assertTrue(continueQuest);
        assertEquals("", outContent.toString());
    }

    @Test
    @DisplayName("Test discard attacks")
    void RESP_25_test_01() {
        Main game = new Main();
        //dummy attacks
        ArrayList<Main.Card> p1Attack = new ArrayList<>();
        Main.Card a = game.new Card("D5", "Weapon", 5);
        p1Attack.add(a);

        ArrayList<Main.Card> p2Attack = new ArrayList<>();
        Main.Card b = game.new Card("D5", "Weapon", 5);
        p2Attack.add(b);
        Main.Card c = game.new Card("S10", "Weapon", 10);
        p2Attack.add(c);

        ArrayList<ArrayList<Main.Card>> attackTeams = new ArrayList<>();
        attackTeams.add(p1Attack);
        attackTeams.add(p2Attack);

        //test to see if cards are discarded from the attack teams to the AdDiscard
        game.discardAttackTeams(attackTeams);
        assertTrue(game.AdDiscard.contains(a));
        assertTrue(game.AdDiscard.contains(b));
        assertTrue(game.AdDiscard.contains(c));
        assertEquals(3, game.AdDiscard.size());
    }

    @Test
    @DisplayName("Test distribute shields")
    void RESP_26_test_01() {
        Main game = new Main();
        //dummy attacks
        Main.Card curEvent = game.new Card("Q4", "Quest", 4);
        //dummy eligibility. If a player is still eligible after a quest, they must have survived.
        game.PlayerList.get(0).eligible = false;
        game.PlayerList.get(1).eligible = true;
        game.PlayerList.get(2).eligible = false;
        game.PlayerList.get(3).eligible = true;

        game.giveWinnersShields(curEvent.value);

        //test to see if cards are discarded from the attack teams to the AdDiscard
        assertEquals(0, game.PlayerList.get(0).shields);
        assertEquals(4, game.PlayerList.get(1).shields);
        assertEquals(0, game.PlayerList.get(2).shields);
        assertEquals(4, game.PlayerList.get(3).shields);
        assertEquals("The quest was completed by player(s) P2, P4. They each get 4 shields.\n", outContent.toString());
        outContent.reset();

        //again
        curEvent = game.new Card("Q2", "Quest", 2);
        //dummy eligibility. If a player is still eligible after a quest, they must have survived.
        game.PlayerList.get(0).eligible = true;
        game.PlayerList.get(1).eligible = false;
        game.PlayerList.get(2).eligible = false;
        game.PlayerList.get(3).eligible = true;

        game.giveWinnersShields(curEvent.value);

        //test to see if cards are discarded from the attack teams to the AdDiscard
        assertEquals(2, game.PlayerList.get(0).shields);
        assertEquals(4, game.PlayerList.get(1).shields);
        assertEquals(0, game.PlayerList.get(2).shields);
        assertEquals(6, game.PlayerList.get(3).shields);
        assertEquals("The quest was completed by player(s) P1, P4. They each get 2 shields.\n", outContent.toString());
        outContent.reset();

        //again with no winners
        curEvent = game.new Card("Q3", "Quest", 3);
        //dummy eligibility. If a player is still eligible after a quest, they must have survived.
        game.PlayerList.get(0).eligible = false;
        game.PlayerList.get(1).eligible = false;
        game.PlayerList.get(2).eligible = false;
        game.PlayerList.get(3).eligible = false;

        game.giveWinnersShields(curEvent.value);

        //test to see if cards are discarded from the attack teams to the AdDiscard
        assertEquals(2, game.PlayerList.get(0).shields);
        assertEquals(4, game.PlayerList.get(1).shields);
        assertEquals(0, game.PlayerList.get(2).shields);
        assertEquals(6, game.PlayerList.get(3).shields);
        assertEquals("The Quest was failed. No shields.\n", outContent.toString());
        outContent.reset();
    }

    @Test
    @DisplayName("Test discard quest")
    void RESP_27_test_01() {
        Main game = new Main();
        Main.Player sponsor = game.PlayerList.get(0);
        game.initializeAdventureDeck();
        //dummy quest stages
        ArrayList<ArrayList<Main.Card>> stages = new ArrayList<>();

        stages.add(new ArrayList<>());
        Main.Card a = game.new Card("F5", "Foe", 5);
        stages.get(0).add(a);
        Main.Card b = game.new Card("D5", "Weapon", 5);
        stages.get(0).add(b);

        stages.add(new ArrayList<>());
        Main.Card c = game.new Card("F15", "Foe", 15);
        stages.get(1).add(c);

        stages.add(new ArrayList<>());
        Main.Card d = game.new Card("F15", "Foe", 15);
        stages.get(2).add(d);
        Main.Card e = game.new Card("D5", "Weapon", 5);
        stages.get(2).add(e);
        stages.add(new ArrayList<>());
        Main.Card f = game.new Card("F70", "Foe", 70);
        stages.get(3).add(f);

        //since this can cause card draw, add in a scanner
        game.discardQuestStages(stages, sponsor, new Scanner(""));

        //check that the discard is correct
        assertTrue(game.AdDiscard.contains(a));
        assertTrue(game.AdDiscard.contains(b));
        assertTrue(game.AdDiscard.contains(c));
        assertTrue(game.AdDiscard.contains(d));
        assertTrue(game.AdDiscard.contains(e));
        assertTrue(game.AdDiscard.contains(f));
        //4 stages, 6 cards, sponsor draws 10
        assertEquals(10, sponsor.hand.size());
    }

    @Test
    @DisplayName("Test draw from empty adventure deck")
    void RESP_28_test_01() {
        Main game = new Main();
        //init the AdDeck and put them into the discard pile
        game.initializeAdventureDeck();
        game.AdDiscard.addAll(game.AdDeck);
        game.AdDeck.clear();

        game.curPlayer = game.PlayerList.get(0);

        //test drawing from empty ad deck
        game.drawAdCard(game.curPlayer, 1, new Scanner(""));

        //assert that hand size is correct, deck size is correct, discard is now empty
        assertEquals(1, game.curPlayer.hand.size());
        assertEquals(99, game.AdDeck.size());
        assertEquals(0, game.AdDiscard.size());
        assertEquals("Adventure Deck empty. Shuffling discard pile back in.\n", outContent.toString());
        //make sure that all cards were retained
        game.AdDeck.add(game.curPlayer.hand.removeFirst());
        assertTrue(game.AdventureDeckList.containsAll(game.AdDeck));
    }

    @Test
    @DisplayName("Test draw from empty event deck")
    void RESP_29_test_01() {
        Main game = new Main();
        //init the AdDeck and put them into the discard pile
        game.initializeEventDeck();
        game.EvDiscard.addAll(game.EvDeck);
        game.EvDeck.clear();

        //test drawing from empty ev deck
        game.drawEventCard();

        //assert that hand size is correct, deck size is correct, discard is now empty
        assertEquals(1, game.EvDiscard.size());
        assertEquals(16, game.EvDeck.size());
        assertTrue(outContent.toString().contains("Event Deck empty. Shuffling discard pile back in.\n"));
        //make sure that all cards were retained
        game.EvDeck.add(game.EvDiscard.removeFirst());
        assertTrue(game.EventDeckList.containsAll(game.EvDeck));
    }

    @Test
    @DisplayName("A-TEST-JP-Scenario")
    void A_TEST_JP_Scenario() {
        //1
        Main game = new Main();
        game.initializeEventDeck();
        game.initializeAdventureDeck();
        game.initializePlayerHands();
        game.curPlayer = game.PlayerList.get(0);

        //2
        String P1HandRig = "F5 F5 F15 F15 D5 S10 S10 H10 H10 B15 B15 L20";
        String P2HandRig = "F5 F5 F15 F15 F40 D5 S10 H10 H10 B15 B15 E30";
        String P3HandRig = "F5 F5 F5 F15 D5 S10 S10 S10 H10 H10 B15 L20";
        String P4HandRig = "F5 F15 F15 F40 D5 D5 S10 H10 H10 B15 L20 E30";
        //put back the drawn cards
        game.AdDeck.addAll(game.PlayerList.get(0).hand);
        game.PlayerList.get(0).hand.clear();
        game.AdDeck.addAll(game.PlayerList.get(1).hand);
        game.PlayerList.get(1).hand.clear();
        game.AdDeck.addAll(game.PlayerList.get(2).hand);
        game.PlayerList.get(2).hand.clear();
        game.AdDeck.addAll(game.PlayerList.get(3).hand);
        game.PlayerList.get(3).hand.clear();
        //rig the hands
        for (String r : P1HandRig.split(" ")) {
            for (Main.Card c : game.AdDeck) {
                if (c.toString().equals(r)) {
                    game.PlayerList.get(0).hand.add(c);
                    game.AdDeck.remove(c);
                    break;
                }
            }
        }
        for (String r : P2HandRig.split(" ")) {
            for (Main.Card c : game.AdDeck) {
                if (c.toString().equals(r)) {
                    game.PlayerList.get(1).hand.add(c);
                    game.AdDeck.remove(c);
                    break;
                }
            }
        }
        for (String r : P3HandRig.split(" ")) {
            for (Main.Card c : game.AdDeck) {
                if (c.toString().equals(r)) {
                    game.PlayerList.get(2).hand.add(c);
                    game.AdDeck.remove(c);
                    break;
                }
            }
        }
        for (String r : P4HandRig.split(" ")) {
            for (Main.Card c : game.AdDeck) {
                if (c.toString().equals(r)) {
                    game.PlayerList.get(3).hand.add(c);
                    game.AdDeck.remove(c);
                    break;
                }
            }
        }

        //3
        //lets rig the top card to be Q4 before drawing it. I think this is the easiest way to do it while maintaining the draw card func
        for (Main.Card c : game.EvDeck) {
            if (c.toString().equals("Q4")) {
                game.EvDeck.remove(c);
                game.EvDeck.addFirst(c);
                break;
            }
        }
        Main.Card curEventCard = game.drawEventCard();

        //4, 5
        Main.Player sponsor = game.seekSponsor(new Scanner("N\nY\n"));
        game.beginQuestBuilding(sponsor, new Scanner("\n"));
        ArrayList<ArrayList<Main.Card>> stages = game.beginStageBuilding(sponsor, curEventCard.value, new Scanner("1\n8\n\n2\n5\n\n2\n3\n4\n\n2\n3\n\n"));
        game.endStageBuilding(sponsor, stages, new Scanner("\n"));

        //6 - stage 1
        //Find participants for the current stage
        ArrayList<Main.Player> stageParticipants = game.seekParticipants(sponsor, true, new Scanner("Y\nY\nY\n"));
        //rig the top 3 cards to be F30, S10, B15
        for (Main.Card c : game.AdDeck) {
            if (c.toString().equals("B15")) {
                game.AdDeck.remove(c);
                game.AdDeck.addFirst(c);
                break;
            }
        }
        for (Main.Card c : game.AdDeck) {
            if (c.toString().equals("S10")) {
                game.AdDeck.remove(c);
                game.AdDeck.addFirst(c);
                break;
            }
        }
        for (Main.Card c : game.AdDeck) {
            if (c.toString().equals("F30")) {
                game.AdDeck.remove(c);
                game.AdDeck.addFirst(c);
                break;
            }
        }

        game.participantsDrawCard(stageParticipants, new Scanner("\n1\n\n1\n\n1\n"));
        ArrayList<ArrayList<Main.Card>> stageAttackTeams = game.createAttackTeams(stageParticipants, new Scanner("\n5\n5\n\n\n\n5\n4\n\n\n\n4\n6\n\n\n"));
        ArrayList<Boolean> stageOutcome = game.resolveAttacks(stages.get(0), stageAttackTeams, stageParticipants);
        game.discardAttackTeams(stageAttackTeams);

        //7 - stage 2
        //Find participants for the current stage
        stageParticipants = game.seekParticipants(sponsor, false, new Scanner("Y\nY\nY\n"));
        //rig the top 3 cards to be F10, L20, L20
        int lancesAdded = 0;
        for (Main.Card c : game.AdDeck) {
            if (c.toString().equals("L20")) {
                game.AdDeck.remove(c);
                game.AdDeck.addFirst(c);
                break;
            }
        }
        //move a different lance to the top
        for (Main.Card c : game.AdDeck) {
            if (c.toString().equals("L20") && game.AdDeck.getFirst() != c) {
                game.AdDeck.remove(c);
                game.AdDeck.addFirst(c);
                break;
            }
        }
        for (Main.Card c : game.AdDeck) {
            if (c.toString().equals("F10")) {
                game.AdDeck.remove(c);
                game.AdDeck.addFirst(c);
                break;
            }
        }

        game.participantsDrawCard(stageParticipants, new Scanner(""));
        stageAttackTeams = game.createAttackTeams(stageParticipants, new Scanner("\n7\n6\n\n\n\n9\n4\n\n\n\n6\n6\n\n\n"));
        stageOutcome = game.resolveAttacks(stages.get(1), stageAttackTeams, stageParticipants);
        assertEquals(0, game.PlayerList.get(0).shields);
        assertEquals("[F5, F10, F15, F15, F30, H10, B15, B15, L20]", game.PlayerList.get(0).hand.toString());
        game.discardAttackTeams(stageAttackTeams);

        //8 - stage 3
        //Find participants for the current stage
        stageParticipants = game.seekParticipants(sponsor, false, new Scanner("Y\nY\n"));
        //rig the top 2 cards to be B15, S10
        for (Main.Card c : game.AdDeck) {
            if (c.toString().equals("S10")) {
                game.AdDeck.remove(c);
                game.AdDeck.addFirst(c);
                break;
            }
        }
        for (Main.Card c : game.AdDeck) {
            if (c.toString().equals("B15")) {
                game.AdDeck.remove(c);
                game.AdDeck.addFirst(c);
                break;
            }
        }

        game.participantsDrawCard(stageParticipants, new Scanner(""));
        stageAttackTeams = game.createAttackTeams(stageParticipants, new Scanner("\n9\n7\n5\n\n\n\n7\n6\n7\n\n\n"));
        stageOutcome = game.resolveAttacks(stages.get(2), stageAttackTeams, stageParticipants);
        game.discardAttackTeams(stageAttackTeams);

        //9 - stage 4
        //Find participants for the current stage
        stageParticipants = game.seekParticipants(sponsor, false, new Scanner("Y\nY\n"));
        //rig the top 2 cards to be F30, L20
        for (Main.Card c : game.AdDeck) {
            if (c.toString().equals("L20")) {
                game.AdDeck.remove(c);
                game.AdDeck.addFirst(c);
                break;
            }
        }
        for (Main.Card c : game.AdDeck) {
            if (c.toString().equals("F30")) {
                game.AdDeck.remove(c);
                game.AdDeck.addFirst(c);
                break;
            }
        }

        game.participantsDrawCard(stageParticipants, new Scanner(""));
        stageAttackTeams = game.createAttackTeams(stageParticipants, new Scanner("\n7\n6\n6\n\n\n\n4\n4\n4\n5\n\n\n"));
        stageOutcome = game.resolveAttacks(stages.get(3), stageAttackTeams, stageParticipants);
        //After Quest Attack, distribute shields to the winners
        game.giveWinnersShields(curEventCard.value);
        game.discardAttackTeams(stageAttackTeams);
        //assert shield counts
        assertEquals(0, game.PlayerList.get(2).shields);
        assertEquals("[F5, F5, F15, F30, S10]", game.PlayerList.get(2).hand.toString());
        assertEquals(4, game.PlayerList.get(3).shields);
        assertEquals("[F15, F15, F40, L20]", game.PlayerList.get(3).hand.toString());

        game.discardQuestStages(stages, sponsor, new Scanner("\n1\n1\n1\n1\n"));
        assertEquals(12, game.PlayerList.get(1).hand.size());
    }
}