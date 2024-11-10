package org.example;

import io.cucumber.java.en.*;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

public class GameSteps {

    private Main game;
    Random generator = new Random();
    ArrayList<Main.Player> curParticipants = new ArrayList<>();
    ArrayList<ArrayList<Main.Card>> curStages = new ArrayList<>();
    ArrayList<ArrayList<Main.Card>> curAttacks = new ArrayList<>();

    public Map<String, Main.Card> stringToAdCard = new HashMap<>();
    public Map<String, Main.Card> stringToEvCard = new HashMap<>();

    @Given("a new game starts")
    public void a_new_game_starts() {
        game = new Main();
        //define the maps
        stringToAdCard.put("F5", game.new Card("F5", "Foe", 5));
        stringToAdCard.put("F10", game.new Card("F10", "Foe", 10));
        stringToAdCard.put("F15", game.new Card("F15", "Foe", 15));
        stringToAdCard.put("F20", game.new Card("F20", "Foe", 20));
        stringToAdCard.put("F25", game.new Card("F25", "Foe", 25));
        stringToAdCard.put("F30", game.new Card("F30", "Foe", 30));
        stringToAdCard.put("F35", game.new Card("F35", "Foe", 35));
        stringToAdCard.put("F40", game.new Card("F40", "Foe", 40));
        stringToAdCard.put("F50", game.new Card("F50", "Foe", 50));
        stringToAdCard.put("F70", game.new Card("F70", "Foe", 70));

        stringToAdCard.put("D5", game.new Card("D5", "Weapon", 5));
        stringToAdCard.put("H10", game.new Card("H10", "Weapon", 10));
        stringToAdCard.put("S10", game.new Card("S10", "Weapon", 10));
        stringToAdCard.put("B15", game.new Card("B15", "Weapon", 15));
        stringToAdCard.put("L20", game.new Card("L20", "Weapon", 20));
        stringToAdCard.put("E30", game.new Card("E30", "Weapon", 30));

        stringToEvCard.put("Q2", game.new Card("Q2", "Quest", 2));
        stringToEvCard.put("Q3", game.new Card("Q3", "Quest", 3));
        stringToEvCard.put("Q4", game.new Card("Q4", "Quest", 4));
        stringToEvCard.put("Q5", game.new Card("Q5", "Quest", 5));

        stringToEvCard.put("Plague", game.new Card("Plague", "Event", 0));
        stringToEvCard.put("Queen's_Favour", game.new Card("Queen's Favour", "Event", 0));
        stringToEvCard.put("Prosperity", game.new Card("Prosperity", "Event", 0));
    }

    //deprecated; deck is rigged instead
    @And("{string} has a rigged hand of {string}")
    public void rigHand(String player, String hand) {
        Main.Player curPlayer = game.PlayerList.get(Integer.parseInt(player.substring(player.length() - 1)) - 1);
        //separate the given rigged hand into an array
        String[] handNameArray = hand.split(" ");

        //clear the current hand
        curPlayer.hand = new ArrayList<>();
        //rig it
        for (String cardName : handNameArray) {
            curPlayer.addCard(game.new Card(stringToAdCard.get(cardName)));
        }
    }

    @And("the adventure deck is initialized")
    public void initAdDeck() {
        game.initializeAdventureDeck();
    }

    @And("the event deck is initialized")
    public void initEvDeck() {
        game.initializeEventDeck();
    }

    @And("the event deck is rigged to have {string} on top")
    public void rigEvDeck(String cards) {
        //separate the given rigged cards into an array
        String[] cardNameArray = cards.split(" ");
        for (String cardName : cardNameArray) {
            game.EvDeck.add(game.new Card(stringToEvCard.get(cardName)));
        }
    }

    @And("the adventure deck is rigged to have {string} on top")
    public void rigAdDeck(String cards) {
        //separate the given rigged cards into an array
        String[] cardNameArray = cards.split(" ");
        for (String cardName : cardNameArray) {
            game.AdDeck.add(game.new Card(stringToAdCard.get(cardName)));
        }
    }

    @And("the event deck has {int} random cards at the bottom")
    public void randEvDeckBottom(int numCards) {
        Object[] possibleCards = stringToEvCard.values().toArray();
        for (int i = 0; i < numCards; i++) {
            game.EvDeck.add(game.new Card((Main.Card) possibleCards[generator.nextInt(possibleCards.length)]));
        }
    }

    @And("the adventure deck has {int} random cards at the bottom")
    public void randAdDeckBottom(int numCards) {
        Object[] possibleCards = stringToAdCard.values().toArray();
        for (int i = 0; i < numCards; i++) {
            game.AdDeck.add(game.new Card((Main.Card) possibleCards[generator.nextInt(possibleCards.length)]));
        }
    }

    @And("a {string} event card is drawn")
    public void drawEvent(String eventName) {
        Main.Card curEvent = game.drawEventCard();
        assertEquals(eventName.replace("_", " "), curEvent.toString());
    }

    @And("the {string} player asked accepts the sponsor")
    public void askForSponsor(String pos) {
        switch (pos) {
            case "first":
                game.seekSponsor(new Scanner("Y\n"));
                break;
            case "second":
                game.seekSponsor(new Scanner("N\nY\n"));
                break;
            case "third":
                game.seekSponsor(new Scanner("N\nN\nY\n"));
                break;
            case "fourth":
                game.seekSponsor(new Scanner("N\nN\nN\nY\n"));
                break;
            default:
                game.seekSponsor(new Scanner("N\nN\nN\nN\n"));
                break;
        }
    }

    @And("the sponsor {string} composes {int} stages that consist of {string} in order")
    public void stageBuild(String sponsor, int numStages, String cardsUsed) {
        Main.Player sponsorPlayer = game.PlayerList.get(Integer.parseInt(sponsor.substring(sponsor.length() - 1)) - 1);
        //convert cardsUsed into a series of inputs
        String[] stageCards = cardsUsed.split(" ");
        ArrayList<Main.Card> pHandCopy = new ArrayList<>();
        for (Main.Card c : sponsorPlayer.hand) {
            pHandCopy.add(game.new Card(c));
        }
        Collections.sort(pHandCopy);
        //build an input string based on data given
        StringBuilder inputString = new StringBuilder();

        //build the input string by matching the given card with the index
        for (String s : stageCards) {
            if (s.endsWith(",")) {
                s = s.substring(0, s.length() - 1);
                int cardIndex = 0;

                //find the index of a matching card
                for (int i = 0; i < pHandCopy.size(); i++) {
                    if (pHandCopy.get(i).toString().equals(s)) {
                        cardIndex = i + 1;
                        break;
                    }
                }

                inputString.append(cardIndex).append("\n\n");
                pHandCopy.remove(cardIndex - 1);
            } else {
                int cardIndex = 0;

                //find the index of a matching card
                for (int i = 0; i < pHandCopy.size(); i++) {
                    if (pHandCopy.get(i).toString().equals(s)) {
                        cardIndex = i + 1;
                        break;
                    }
                }
                inputString.append(cardIndex).append("\n");
                pHandCopy.remove(cardIndex - 1);
            }
        }
        inputString.append("\n");
        //call the function
        curStages = game.beginStageBuilding(sponsorPlayer, numStages, new Scanner(inputString.toString()));


    }

    @And("{string} are participants for stage {int} of the quest sponsored by {string}")
    public void getParticipants(String participants, int stageNum, String sponsor) {
        //format the participant string
        List<String> participantList = Arrays.asList(participants.split(" "));
        for (int i = 0; i < participantList.size(); i++) {
            participantList.set(i, participantList.get(i));
        }

        //get the sponsor as the player object
        Main.Player playerSponsor = game.PlayerList.get(Integer.parseInt(sponsor.substring(sponsor.length() - 1)) - 1);

        StringBuilder inputString = new StringBuilder();
        //loop through the player list and create the input
        for (int i = 0; i < 4; i++) {
            //if they're eligible an action is needed
            if (stageNum == 1 || game.PlayerList.get(i).eligible) {
                //yes if they're participating, otherwise no.
                if (participantList.contains(game.PlayerList.get(i).toString())) {
                    inputString.append("Y\n");
                } else if (!sponsor.equals(game.PlayerList.get(i).toString())) {
                    //skips over the sponsor player as they are not asked
                    inputString.append("N\n");
                }
            }
        }
        curParticipants = game.seekParticipants(playerSponsor, stageNum == 1, new Scanner(inputString.toString()));
    }

    @And("{string} draws {int} card\\(s) and discards {string}")
    public void drawsCardsAndDiscard(String player, int numCard, String discardedCards) {
        Main.Player curPlayer = game.PlayerList.get(Integer.parseInt(player.substring(player.length() - 1)) - 1);
        String[] discardList = discardedCards.split(" ");

        //create a simulated copy of the hand after the cards are drawn
        ArrayList<Main.Card> pHandCopy = new ArrayList<>();
        for (Main.Card c : curPlayer.hand) {
            pHandCopy.add(game.new Card(c));
        }
        Collections.sort(pHandCopy);
        //draw the x cards into the simulated hand
        for (int i = 0; i < numCard; i++) {
            pHandCopy.add(game.new Card(game.AdDeck.get(i)));
        }
        //find the indices at which to discard the requested card
        //build an input string based on data given
        StringBuilder inputString = new StringBuilder("\n");

        //build the input string by matching the given card with the index
        for (String s : discardList) {
            int cardIndex = 0;

            //find the index of a matching card
            for (int i = 0; i < pHandCopy.size(); i++) {
                if (pHandCopy.get(i).toString().equals(s)) {
                    cardIndex = i + 1;
                    break;
                }
            }
            inputString.append(cardIndex).append("\n");
            pHandCopy.remove(cardIndex - 1);

        }
        inputString.append("\n");
        inputString.append("\n");

        game.drawAdCard(curPlayer, numCard, new Scanner(inputString.toString()));
    }

    @And("{string} draws {int} card\\(s)")
    public void drawsCards(String player, int numCard) {
        Main.Player curPlayer = game.PlayerList.get(Integer.parseInt(player.substring(player.length() - 1)) - 1);
        game.drawAdCard(curPlayer, numCard, new Scanner(""));
    }

    @And("{string} build attack teams of {string}")
    public void attackTeamBuilding(String players, String attackCards) {
        //get the list of participants
        ArrayList<Main.Player> participants = new ArrayList<>();
        for (String s : players.split(" ")) {
            for (Main.Player p : game.PlayerList) {
                if (s.equals(p.toString())) {
                    participants.add(p);
                }
            }
        }

        //make the input for the attacks
        String[] attackTeamStrings = attackCards.split(",");
        StringBuilder inputString = new StringBuilder();
        for (int i = 0; i < attackTeamStrings.length; i++) {
            //relate the given cards in the attack to the player
            Main.Player curPlayer = participants.get(i);
            //make a copy of the hand to derive inputs from
            ArrayList<Main.Card> curPlayerHandCopy = new ArrayList<>();
            for (Main.Card c : curPlayer.hand) {
                curPlayerHandCopy.add(game.new Card(c));
            }

            //confirmation line
            inputString.append("\n");
            String[] attackTeamCardString = attackTeamStrings[i].trim().split(" ");
            for (String c : attackTeamCardString) {
                int cardIndex = 0;

                //find the index of a matching card
                for (int j = 0; j < curPlayerHandCopy.size(); j++) {
                    if (curPlayerHandCopy.get(j).toString().equals(c)) {
                        cardIndex = j + 1;
                        break;
                    }
                }
                inputString.append(cardIndex).append("\n");
                curPlayerHandCopy.remove(cardIndex - 1);
            }
            inputString.append("\n\n");
        }
        curAttacks = game.createAttackTeams(participants, new Scanner(inputString.toString()));
    }

    @And("the attacks are resolved and discarded for stage {int}")
    public void resolveAttacks(int stageNum) {
        game.resolveAttacks(curStages.get(stageNum - 1), curAttacks, curParticipants);
        game.discardAttackTeams(curAttacks);
    }

    @And("{string} has a hand equal to {string}")
    public void hasAHandEqualTo(String player, String hand) {
        Main.Player curPlayer = game.PlayerList.get(Integer.parseInt(player.substring(player.length() - 1)) - 1);
        assertEquals(hand, curPlayer.hand.toString().substring(1, curPlayer.hand.toString().length() - 1).replace(",", ""));
    }

    @And("{string} are still eligible")
    public void areStillEligible(String players) {
        List<String> pList = Arrays.asList(players.split(" "));
        for (Main.Player p : game.PlayerList) {
            if (pList.contains(p.toString())) {
                assertTrue(p.eligible);
            }
        }
    }

    @And("{string} has {int} shields")
    public void hasShields(String player, int shieldCount) {
        Main.Player curPlayer = game.PlayerList.get(Integer.parseInt(player.substring(player.length() - 1)) - 1);
        assertEquals(shieldCount, curPlayer.shields);
    }

    @When("shields are given out")
    public void giveShields() {
        game.giveWinnersShields(curStages.size());
    }

    @And("{string} discards the quest stages")
    public void discardStages(String sponsor) {
        Main.Player sponsorPlayer = game.PlayerList.get(Integer.parseInt(sponsor.substring(sponsor.length() - 1)) - 1);

        //arbitrarily choose to discard the 1st card over and over
        game.discardQuestStages(curStages, sponsorPlayer, new Scanner("\n" + "1\n".repeat(100)));
    }

    @Then("{string} has a hand of {int} cards")
    public void handSizeCheck(String player, int numCards) {
        Main.Player curPlayer = game.PlayerList.get(Integer.parseInt(player.substring(player.length() - 1)) - 1);
        assertEquals(numCards, curPlayer.hand.size());
    }

    @And("{string} starts with {int} shields")
    public void startsWithShields(String player, int shieldCount) {
        Main.Player curPlayer = game.PlayerList.get(Integer.parseInt(player.substring(player.length() - 1)) - 1);
        curPlayer.shields = shieldCount;
    }

    @And("the current player is {string}")
    public void theCurrentPlayerIs(String player) {
        game.curPlayer = game.PlayerList.get(Integer.parseInt(player.substring(player.length() - 1)) - 1);
    }

    @And("{string} are ineligible")
    public void areIneligible(String players) {
        List<String> pList = Arrays.asList(players.split(" "));
        for (Main.Player p : game.PlayerList) {
            if (pList.contains(p.toString())) {
                assertFalse(p.eligible);
            }
        }
    }

    @And("the turn ends")
    public void theTurnEnds() {
        game.endTurn(new Scanner("\n"));
    }

    @And("{string} are winners")
    public void areWinners(String players) {
        //there are winners
        assertTrue(game.findWinners());

        //test the string output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream outContentOriginal = System.out;
        System.setOut(new PrintStream(outContent));

        game.printWinners();

        String expected = "Player(s) " + players.replace(" ", ", ") + " Won.";

        assertEquals(expected, outContent.toString());

        System.setOut(outContentOriginal);
    }

    @And("{string} takes effect")
    public void effects(String card) {
        switch (card) {
            case "Plague" -> game.plagueEffect();
            case "Queen's_Favour" -> game.queenEffect();
            case "Prosperity" -> game.prosperityEffect();
        }
    }

    @And("the players hands are initialized")
    public void initHands() {
        game.initializePlayerHands();
    }
}
