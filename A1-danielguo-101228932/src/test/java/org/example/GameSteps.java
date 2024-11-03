package org.example;

import io.cucumber.java.en.*;

import static org.junit.Assert.*;

import java.util.*;

public class GameSteps {

    public GameSteps() {
    }

    ;

    private Main game;
    Random generator = new Random();
    ArrayList<ArrayList<Main.Card>> curStages = new ArrayList<>();

    public Map<String, Main.Card> stringToAdCard = new HashMap<>();
    public Map<String, Main.Card> stringToEvCard = new HashMap<>();

    @Given("a new game starts")
    public void a_new_game_starts() {
        game = new Main();
        //define the maps
        stringToAdCard.put("F5", game.new Card("F5", "Foe", 5));
        stringToAdCard.put("F10", game.new Card("F10", "Foe", 10));
        stringToAdCard.put("F15", game.new Card("F15", "Foe", 15));
        stringToAdCard.put("F20", game.new Card("F10", "Foe", 20));
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
        stringToEvCard.put("Queen's Favor", game.new Card("Queen's Favor", "Event", 0));
        stringToEvCard.put("Prosperity", game.new Card("Prosperity", "Event", 0));

        game.curPlayer = game.PlayerList.get(0);
    }

    @And("{string} has a rigged hand of {string}")
    public void rigHand(String player, String hand) {
        //separate the given rigged hand into an array
        String[] handNameArray = hand.split(" ");

        //add the cards
        for (Main.Player p : game.PlayerList) {
            if (p.toString().equals(player)) {
                for (String cardName : handNameArray) {
                    p.addCard(stringToAdCard.get(cardName));
                }
                break;
            }
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
            game.EvDeck.add(stringToEvCard.get(cardName));
        }
    }

    @And("the adventure deck is rigged to have {string} on top")
    public void rigAdDeck(String cards) {
        //separate the given rigged cards into an array
        String[] cardNameArray = cards.split(" ");
        for (String cardName : cardNameArray) {
            game.AdDeck.add(stringToAdCard.get(cardName));
        }
    }

    @And("the event deck has {int} random cards at the bottom")
    public void randEvDeckBottom(int numCards) {
        Object[] possibleCards = stringToAdCard.values().toArray();
        for (int i = 0; i < numCards; i++) {
            game.EvDeck.add((Main.Card) possibleCards[generator.nextInt(possibleCards.length)]);
        }
    }

    @And("the adventure deck has {int} random cards at the bottom")
    public void randAdDeckBottom(int numCards) {
        Object[] possibleCards = stringToAdCard.values().toArray();
        for (int i = 0; i < numCards; i++) {
            game.AdDeck.add((Main.Card) possibleCards[generator.nextInt(possibleCards.length)]);
        }
    }

    @And("an event card is drawn")
    public void drawEvent() {
        game.drawEventCard();
    }

    @And("The {string} player asked accepts the sponsor")
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
        for (Main.Player p : game.PlayerList) {
            //find the sponsor from the given string
            if (p.toString().equals(sponsor)) {
                //convert cardsUsed into a series of inputs
                String[] stageCards = cardsUsed.split(" ");
                ArrayList<Main.Card> pHandCopy = (ArrayList<Main.Card>) p.hand.clone();
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
                game.beginStageBuilding(p, numStages, new Scanner(inputString.toString()));
                break;
            }
        }

    }

    @And("{string} are participants for stage {int} of the quest sponsored by {string}")
    public void getParticipants(String participants, int stageNum, String sponsor) {
        //format the participant string
        List<String> participantList = Arrays.asList(participants.split(","));
        for (int i = 0; i < participantList.size(); i++) {
            participantList.set(i, participantList.get(i).trim());
        }

        //get the actual sponsor player
        for (Main.Player p : game.PlayerList) {
            if (p.toString().equals(sponsor)) {
                StringBuilder inputString = new StringBuilder();
                //loop through the player list and create the input
                for (int i = 0; i < 4; i++) {
                    //if they're eligible an action is needed
                    if (stageNum == 1 || game.PlayerList.get(i).eligible) {
                        //yes if they're participating, otherwise no.
                        if (participantList.contains(game.PlayerList.get(i).toString())) {
                            inputString.append("Y\n");
                        } else if(!sponsor.equals(game.PlayerList.get(i).toString())){
                            inputString.append("N\n");
                        }
                    }
                }
                game.seekParticipants(p, stageNum == 1, new Scanner(inputString.toString()));
                break;
            }
        }
    }

    @And("{string} draws {int} card\\(s) and discards {string}")
    public void drawsCardAndDiscards(String player, int numCard, String discardedCards) {
        Main.Player curPlayer = game.PlayerList.get(Integer.parseInt(player.substring(player.length() - 1)));
    }
}
