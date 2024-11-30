package org.example;

import ch.qos.logback.core.joran.sanity.Pair;
import io.cucumber.java.bs.A;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class Main {
    public String gamePhase = "";
    public Player curPlayer;
    public Card curEvent;
    public Player sponsor = null;
    public ArrayList<Card> AdDeck = new ArrayList<>();
    public ArrayList<Card> EvDeck = new ArrayList<>();
    public ArrayList<Card> AdDiscard = new ArrayList<>();
    public ArrayList<Card> EvDiscard = new ArrayList<>();
    public ArrayList<ArrayList<Card>> stages;
    public ArrayList<Card> curStage;
    public int curStageNumber;
    public int curAttackStageNumber;
    public ArrayList<ArrayList<Card>> curStageAttackTeams;
    public ArrayList<Card> curAttackTeam;
    public final ArrayList<Player> PlayerList = new ArrayList<>(
            Arrays.asList(
                    new Player(1),
                    new Player(2),
                    new Player(3),
                    new Player(4)
            )
    );
    //list of all 100 cards included in the deck
    public final ArrayList<Card> AdventureDeckList = new ArrayList<>(
            Arrays.asList(
                    new Card("F5", "Foe", 5),
                    new Card("F5", "Foe", 5),
                    new Card("F5", "Foe", 5),
                    new Card("F5", "Foe", 5),
                    new Card("F5", "Foe", 5),
                    new Card("F5", "Foe", 5),
                    new Card("F5", "Foe", 5),
                    new Card("F5", "Foe", 5),
                    new Card("F10", "Foe", 10),
                    new Card("F10", "Foe", 10),
                    new Card("F10", "Foe", 10),
                    new Card("F10", "Foe", 10),
                    new Card("F10", "Foe", 10),
                    new Card("F10", "Foe", 10),
                    new Card("F10", "Foe", 10),
                    new Card("F15", "Foe", 15),
                    new Card("F15", "Foe", 15),
                    new Card("F15", "Foe", 15),
                    new Card("F15", "Foe", 15),
                    new Card("F15", "Foe", 15),
                    new Card("F15", "Foe", 15),
                    new Card("F15", "Foe", 15),
                    new Card("F15", "Foe", 15),
                    new Card("F20", "Foe", 20),
                    new Card("F20", "Foe", 20),
                    new Card("F20", "Foe", 20),
                    new Card("F20", "Foe", 20),
                    new Card("F20", "Foe", 20),
                    new Card("F20", "Foe", 20),
                    new Card("F20", "Foe", 20),
                    new Card("F25", "Foe", 25),
                    new Card("F25", "Foe", 25),
                    new Card("F25", "Foe", 25),
                    new Card("F25", "Foe", 25),
                    new Card("F25", "Foe", 25),
                    new Card("F25", "Foe", 25),
                    new Card("F25", "Foe", 25),
                    new Card("F30", "Foe", 30),
                    new Card("F30", "Foe", 30),
                    new Card("F30", "Foe", 30),
                    new Card("F30", "Foe", 30),
                    new Card("F35", "Foe", 35),
                    new Card("F35", "Foe", 35),
                    new Card("F35", "Foe", 35),
                    new Card("F35", "Foe", 35),
                    new Card("F40", "Foe", 40),
                    new Card("F40", "Foe", 40),
                    new Card("F50", "Foe", 50),
                    new Card("F50", "Foe", 50),
                    new Card("F70", "Foe", 70),
                    new Card("D5", "Weapon", 5),
                    new Card("D5", "Weapon", 5),
                    new Card("D5", "Weapon", 5),
                    new Card("D5", "Weapon", 5),
                    new Card("D5", "Weapon", 5),
                    new Card("D5", "Weapon", 5),
                    new Card("H10", "Weapon", 10),
                    new Card("H10", "Weapon", 10),
                    new Card("H10", "Weapon", 10),
                    new Card("H10", "Weapon", 10),
                    new Card("H10", "Weapon", 10),
                    new Card("H10", "Weapon", 10),
                    new Card("H10", "Weapon", 10),
                    new Card("H10", "Weapon", 10),
                    new Card("H10", "Weapon", 10),
                    new Card("H10", "Weapon", 10),
                    new Card("H10", "Weapon", 10),
                    new Card("H10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("S10", "Weapon", 10),
                    new Card("B15", "Weapon", 15),
                    new Card("B15", "Weapon", 15),
                    new Card("B15", "Weapon", 15),
                    new Card("B15", "Weapon", 15),
                    new Card("B15", "Weapon", 15),
                    new Card("B15", "Weapon", 15),
                    new Card("B15", "Weapon", 15),
                    new Card("B15", "Weapon", 15),
                    new Card("L20", "Weapon", 20),
                    new Card("L20", "Weapon", 20),
                    new Card("L20", "Weapon", 20),
                    new Card("L20", "Weapon", 20),
                    new Card("L20", "Weapon", 20),
                    new Card("L20", "Weapon", 20),
                    new Card("E30", "Weapon", 30),
                    new Card("E30", "Weapon", 30)
            )
    );
    //list of all 17 cards included in the deck
    public final ArrayList<Card> EventDeckList = new ArrayList<>(
            Arrays.asList(
                    new Card("Q2", "Quest", 2),
                    new Card("Q2", "Quest", 2),
                    new Card("Q2", "Quest", 2),
                    new Card("Q3", "Quest", 3),
                    new Card("Q3", "Quest", 3),
                    new Card("Q3", "Quest", 3),
                    new Card("Q3", "Quest", 3),
                    new Card("Q4", "Quest", 4),
                    new Card("Q4", "Quest", 4),
                    new Card("Q4", "Quest", 4),
                    new Card("Q5", "Quest", 5),
                    new Card("Q5", "Quest", 5),
                    new Card("Plague", "Event", 0),
                    new Card("Queen's Favour", "Event", 0),
                    new Card("Queen's Favour", "Event", 0),
                    new Card("Prosperity", "Event", 0),
                    new Card("Prosperity", "Event", 0)
            )
    );

    @PostMapping("/shieldDistribution")
    public String giveWinnersShields() {
        ArrayList<Player> winners = new ArrayList<>();
        for (Player p : PlayerList) {
            if (p.isEligible()) {
                winners.add(p);
                p.addShields(stages.size());
            }
        }
        if (winners.isEmpty()) {
            return "The quest was failed. No shields.\n";
        } else {
            return "The quest was completed by player(s) " + winners.toString().substring(1, winners.toString().length() - 1) + ". They each get " + stages.size() + " shields.\nCurrent shield counts: \nP1: " + PlayerList.get(0).getShields() + "\nP2: " + PlayerList.get(1).getShields() + "\nP3: " + PlayerList.get(2).getShields() + "\nP4: " + PlayerList.get(3).getShields() + "\n\nThe sponsor draws cards; press enter to continue.\n\n\n";
        }
    }

    @PostMapping("/discardStages")
    public void discardQuestStages() {
        int numCardsUsed = 0;
        for (ArrayList<Card> s : stages) {
            AdDiscard.addAll(s);
            numCardsUsed += s.size();
        }
        drawAdCard(sponsor, numCardsUsed + stages.size());
    }

    @GetMapping("/getGamePhase")
    public String getGamePhase() {
        return gamePhase;
    }

    @GetMapping("/getSponsor")
    public String getSponsor() {
        if (sponsor == null) {
            return "no sponsor";
        }
        return sponsor.toString();
    }

    @GetMapping("/printGameInfo")
    public String printGameInfo() {
        StringBuilder outString = new StringBuilder();
        outString.append("stats are updated every button click.\n\n");
        for (Player p : PlayerList) {
            outString.append(p.printHand()).append("\n");
        }
        for (Player p : PlayerList) {
            outString.append(p).append(" Shields: ").append(p.getShields()).append("\n");
        }
        return outString.toString();
    }

    public void initializePlayerHands() {
        for (Player p : PlayerList) {
            p.setHand(new ArrayList<>());
            drawAdCard(p, 12);
        }
    }

    public void initializeAdventureDeck() {
        AdDeck = new ArrayList<>(AdventureDeckList);
        Collections.shuffle(AdDeck);
        AdDiscard = new ArrayList<>();
    }

    public void initializeAdventureDeckRig(String cards) {
        ArrayList<Card> tempAdDeck = new ArrayList<>(AdventureDeckList);
        AdDeck = new ArrayList<>();
        AdDiscard = new ArrayList<>();
        for(String sc : cards.split(" ")){
            for(Card c : tempAdDeck){
                if(c.toString().equals(sc)){
                    AdDeck.add(c);
                    tempAdDeck.remove(c);
                    break;
                }
            }
        }
        Collections.shuffle(tempAdDeck);
        AdDeck.addAll(tempAdDeck);
    }

    public void initializeEventDeck() {
        EvDeck = new ArrayList<>(EventDeckList);
        Collections.shuffle(EvDeck);
        EvDiscard = new ArrayList<>();
    }

    public void initializeEventDeckRig(String cards) {
        ArrayList<Card> tempEvDeck = new ArrayList<>(EventDeckList);
        EvDeck = new ArrayList<>();
        EvDiscard = new ArrayList<>();
        for(String sc : cards.split(" ")){
            for(Card c : tempEvDeck){
                if(c.toString().equals(sc)){
                    EvDeck.add(c);
                    tempEvDeck.remove(c);
                    break;
                }
            }
        }
        Collections.shuffle(tempEvDeck);
        EvDeck.addAll(tempEvDeck);
    }

    public int getValueOfStage(ArrayList<Card> stage) {
        int output = 0;
        for (Card c : stage) {
            output += c.getValue();
        }
        return output;
    }

    public String printStage(ArrayList<Card> stage) {
        return stage.toString().substring(1, stage.toString().length() - 1);
    }

    public String printAllStages() {
        StringBuilder outString = new StringBuilder();
        for (int i = 0; i < stages.size(); i++) {
            outString.append("Stage ").append(i + 1).append(": ").append(printStage(stages.get(i))).append("\n");
        }
        return outString.toString();
    }

    public boolean hasFoe(ArrayList<Card> stage) {
        for (Card c : stage) {
            if (c.getType().equals("Foe")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEligible() {
        return PlayerList.get(0).isEligible() || PlayerList.get(1).isEligible() || PlayerList.get(2).isEligible() || PlayerList.get(3).isEligible();
    }

    public void discardAttackTeams(ArrayList<ArrayList<Card>> attackTeams) {
        for (ArrayList<Card> team : attackTeams) {
            AdDiscard.addAll(team);
        }
    }

    @GetMapping("/HasEligibleString")
    public String hasEligibleString() {
        if (hasEligible()) {
            return "true";
        }
        return "false";
    }

    public String drawAdCard(Player p, int num) {
        StringBuilder outString = new StringBuilder();
        for (int i = 0; i < num; i++) {
            if (AdDeck.isEmpty()) {
                outString.append("Adventure Deck empty. Shuffling discard pile back in.\n");
                AdDeck.addAll(AdDiscard);
                AdDiscard.clear();
                Collections.shuffle(AdDeck);
            }
            p.addCard(AdDeck.remove(0));
        }

        if (p.getHand().size() > 12) {
            outString.append("\n\n").append(p).append(" is over the max hand size by ").append(p.getHand().size() - 12).append(". Please give controls to ").append(p).append(", and press submit.\n");
            gamePhase = p + " Discard Start";
        }
        Collections.sort(p.getHand());
        return outString.toString();
    }

    //helper function for parseInt
    public boolean isValidInt(String s) {
        try {
            Integer.parseInt(s);
            return true; // Parsing succeeded
        } catch (NumberFormatException e) {
            return false; // Parsing failed
        }
    }

    @PostMapping("/inputProcessing")
    public String processInput(@RequestBody String input) {
        String outString = "";
        switch (gamePhase) {
            case "End Turn":
                gamePhase = "New Turn";
                return "";
            case "P1 Discard Start":
                gamePhase = "P1 Discard";
                return "\nAre you P1?\n";
            case "P2 Discard Start":
                gamePhase = "P2 Discard";
                return "\nAre you P2?\n";
            case "P3 Discard Start":
                gamePhase = "P3 Discard";
                return "\nAre you P3?\n";
            case "P4 Discard Start":
                gamePhase = "P4 Discard";
                return "\nAre you P4?\n";

            case "P1 Discard":
                gamePhase = "P1 Discarding";
                return "You have to discard to 12 cards.\nP1, This is your hand:\n" + PlayerList.get(0).printHand() + "\nPlease select a card to discard by index, starting at 1\n";
            case "P2 Discard":
                gamePhase = "P2 Discarding";
                return "You have to discard to 12 cards.\nP2, This is your hand:\n" + PlayerList.get(1).printHand() + "\nPlease select a card to discard by index, starting at 1\n";
            case "P3 Discard":
                gamePhase = "P3 Discarding";
                return "You have to discard to 12 cards.\nP3, This is your hand:\n" + PlayerList.get(2).printHand() + "\nPlease select a card to discard by index, starting at 1\n";
            case "P4 Discard":
                gamePhase = "P4 Discarding";
                return "You have to discard to 12 cards.\nP4, This is your hand:\n" + PlayerList.get(3).printHand() + "\nPlease select a card to discard by index, starting at 1\n";

            case "P1 Discarding":
                //valid index
                if (isValidInt(input) && 1 <= Integer.parseInt(input) && Integer.parseInt(input) <= PlayerList.get(0).getHand().size()) {
                    AdDiscard.add(PlayerList.get(0).getHand().remove((Integer.parseInt(input)) - 1));
                    //continue
                    if (PlayerList.get(0).getHand().size() > 12) {
                        return "P1, Continue discarding. This is your hand:\n" + PlayerList.get(0).printHand() + "\n";
                    } else {
                        outString += "Discarding Complete. This is your new hand:\n";
                        outString += PlayerList.get(0).printHand();
                        outString += "\n\n";
                        gamePhase = "";
                        return outString;
                    }
                } else {
                    return "Invalid index. Try again.\n";
                }
            case "P2 Discarding":
                //valid index
                if (isValidInt(input) && 1 <= Integer.parseInt(input) && Integer.parseInt(input) <= PlayerList.get(1).getHand().size()) {
                    AdDiscard.add(PlayerList.get(1).getHand().remove((Integer.parseInt(input)) - 1));
                    //continue
                    if (PlayerList.get(1).getHand().size() > 12) {
                        return "P2, Continue discarding. This is your hand:\n" + PlayerList.get(1).printHand() + "\n";
                    } else {
                        outString += "Discarding Complete. This is your new hand:\n";
                        outString += PlayerList.get(1).printHand();
                        outString += "\n\n";
                        gamePhase = "";
                        return outString;
                    }
                } else {
                    return "Invalid index. Try again.\n";
                }
            case "P3 Discarding":
                //valid index
                if (isValidInt(input) && 1 <= Integer.parseInt(input) && Integer.parseInt(input) <= PlayerList.get(2).getHand().size()) {
                    AdDiscard.add(PlayerList.get(2).getHand().remove((Integer.parseInt(input)) - 1));
                    //continue
                    if (PlayerList.get(2).getHand().size() > 12) {
                        return "P3, Continue discarding. This is your hand:\n" + PlayerList.get(2).printHand() + "\n";
                    } else {
                        outString += "Discarding Complete. This is your new hand:\n";
                        outString += PlayerList.get(2).printHand();
                        outString += "\n\n";
                        gamePhase = "";
                        return outString;
                    }
                } else {
                    return "Invalid index. Try again.\n";
                }
            case "P4 Discarding":
                //valid index
                if (isValidInt(input) && 1 <= Integer.parseInt(input) && Integer.parseInt(input) <= PlayerList.get(3).getHand().size()) {
                    AdDiscard.add(PlayerList.get(3).getHand().remove((Integer.parseInt(input)) - 1));
                    //continue
                    if (PlayerList.get(3).getHand().size() > 12) {
                        return "P4, Continue discarding. This is your hand:\n" + PlayerList.get(3).printHand() + "\n";
                    } else {
                        outString += "Discarding Complete. This is your new hand:\n";
                        outString += PlayerList.get(3).printHand();
                        outString += "\n\n";
                        gamePhase = "";
                        return outString;
                    }
                } else {
                    return "Invalid index. Try again.\n";
                }

            case "Seek Sponsor 1":
                if (input.equals("Y")) {
                    gamePhase = "Sponsor Search End";
                    sponsor = curPlayer;
                    return sponsor + " has accepted to sponsor this quest.\n";
                } else if (input.equals("N")) {
                    gamePhase = "Seek Sponsor 2";
                    return "";
                } else {
                    return "Invalid input. Must be 'Y' or 'N'.\n";
                }
            case "Seek Sponsor 2":
                if (input.equals("Y")) {
                    gamePhase = "Sponsor Search End";
                    sponsor = PlayerList.get((curPlayer.getId()) % 4);
                    return sponsor + " has accepted to sponsor this quest.\n";
                } else if (input.equals("N")) {
                    gamePhase = "Seek Sponsor 3";
                    return "";
                } else {
                    return "Invalid input. Must be 'Y' or 'N'.\n";
                }
            case "Seek Sponsor 3":
                if (input.equals("Y")) {
                    gamePhase = "Sponsor Search End";
                    sponsor = PlayerList.get((curPlayer.getId() + 1) % 4);
                    return sponsor + " has accepted to sponsor this quest.\n";
                } else if (input.equals("N")) {
                    gamePhase = "Seek Sponsor 4";
                    return "";
                } else {
                    return "Invalid input. Must be 'Y' or 'N'.\n";
                }
            case "Seek Sponsor 4":
                if (input.equals("Y")) {
                    gamePhase = "Sponsor Search End";
                    sponsor = PlayerList.get((curPlayer.getId() + 2) % 4);
                    return sponsor + " has accepted to sponsor this quest.\n";
                } else if (input.equals("N")) {
                    gamePhase = "Sponsor Search End";
                    return "Everybody turned down the sponsor opportunity.\n";
                } else {
                    return "Invalid input. Must be 'Y' or 'N'.\n";
                }
            case "Quest Build Begin":
                gamePhase = "Building Quest Stages";
                stages = new ArrayList<>();
                for (int i = 0; i < curEvent.getValue(); i++) {
                    stages.add(new ArrayList<>());
                }
                return "";
            case "Building Quest Stages":
                Collections.sort(stages.get(curStageNumber - 1));
                curStage = stages.get(curStageNumber - 1);
                if (isValidInt(input) && 1 <= Integer.parseInt(input) && Integer.parseInt(input) <= sponsor.getHand().size()) {//add a card to the stage
                    int handIndex = Integer.parseInt(input) - 1;
                    if (sponsor.getHand().get(handIndex).getType().equals("Foe")) {//selected card is a foe
                        //check if there's already a foe or not
                        for (Card card : curStage) {
                            if (card.getType().equals("Foe")) {
                                return "Invalid input. Cannot put two foes in one stage.\n";
                            }
                        }
                        curStage.add(sponsor.getHand().remove(handIndex));
                        return "Card Added.\n\nStage " + curStageNumber + ": " + printStage(stages.get(curStageNumber - 1)) + "\nPlease select an index of a card you wish to add to the stage, or press enter to finish.\n" + sponsor.printHand() + "\n";
                    } else if (sponsor.getHand().get(handIndex).getType().equals("Weapon")) {
                        //check for dupe weapon
                        for (Card card : curStage) {
                            if (card.toString().equals(sponsor.getHand().get(Integer.parseInt(input) - 1).toString())) {
                                return "Invalid input. Duplicate weapon.\n";
                            }
                        }
                        curStage.add(sponsor.getHand().remove(handIndex));
                        return "Card Added.\n\nStage " + curStageNumber + ": " + printStage(stages.get(curStageNumber - 1)) + "\nPlease select an index of a card you wish to add to the stage, or press enter to finish.\n" + sponsor.printHand() + "\n";
                    }
                } else if (input.equals("empty_string")) {//proceed to the next stage if possible
                    //invalids
                    if (stages.get(curStageNumber - 1).isEmpty()) {
                        return "Stage is empty. Please add Foe and Weapons.\n";
                    } else if (curStageNumber != 1 && getValueOfStage(stages.get(curStageNumber - 1)) <= getValueOfStage(stages.get(curStageNumber - 2))) {
                        return "Current stage must have value greater than the prior stage.\n";
                    } else if (!hasFoe(stages.get(curStageNumber - 1))) {
                        return "Current stage does not have a foe.\n";
                    } else {
                        //valid
                        if (curStageNumber < curEvent.getValue()) {
                            stages.set(curStageNumber - 1, curStage);
                            curStageNumber++;
                            return "Stage submitted. Moving to Stage " + curStageNumber + "\n\nStage " + curStageNumber + ": " + printStage(stages.get(curStageNumber - 1)) + "\nPlease select an index of a card you wish to add to the stage, or press enter to finish.\n" + sponsor.printHand() + "\n";
                        } else {
                            gamePhase = "Quest Build End";
                            //setup eligibility for the next phase
                            for (Player p : PlayerList) {
                                p.setEligible(true);
                            }
                            sponsor.setEligible(false);
                            return "Quest building finished. Here are your stages: \n" + printAllStages() + "\n\n";
                        }
                    }
                } else {//input is not an index nor empty; invalid
                    return "Input must be a valid index or empty.\n";
                }

            case "Seek Participant 1":
                if (input.equals("Y")) {
                    gamePhase = "Seek Participant 1 End";
                    return "P1 has accepted to participate this quest.\n";
                } else if (input.equals("N")) {
                    gamePhase = "Seek Participant 1 End";
                    PlayerList.get(0).setEligible(false);
                    return "P1 has declined to participate in this quest.\n";
                } else {
                    return "Invalid input. Must be 'Y' or 'N'.\n";
                }
            case "Seek Participant 2":
                if (input.equals("Y")) {
                    gamePhase = "Seek Participant 2 End";
                    return "P2 has accepted to participate this quest.\n";
                } else if (input.equals("N")) {
                    gamePhase = "Seek Participant 2 End";
                    PlayerList.get(1).setEligible(false);
                    return "P2 has declined to participate in this quest.\n";
                } else {
                    return "Invalid input. Must be 'Y' or 'N'.\n";
                }
            case "Seek Participant 3":
                if (input.equals("Y")) {
                    gamePhase = "Seek Participant 3 End";
                    return "P3 has accepted to participate this quest.\n";
                } else if (input.equals("N")) {
                    gamePhase = "Seek Participant 3 End";
                    PlayerList.get(2).setEligible(false);
                    return "P3 has declined to participate in this quest.\n";
                } else {
                    return "Invalid input. Must be 'Y' or 'N'.\n";
                }
            case "Seek Participant 4":
                if (input.equals("Y")) {
                    gamePhase = "Seek Participant 4 End";
                    return "P4 has accepted to participate this quest.\n";
                } else if (input.equals("N")) {
                    gamePhase = "Seek Participant 4 End";
                    PlayerList.get(3).setEligible(false);
                    return "P4 has declined to participate in this quest.\n";
                } else {
                    return "Invalid input. Must be 'Y' or 'N'.\n";
                }
            case "Building P1 Attacks Begin":
                gamePhase = "Building P1 Attacks";
                return "P1 you must build an attack team.\nPlease select an index of a card you wish to add to your attack, or press enter to finish.\n" + PlayerList.get(0).printHand() + "\n";
            case "Building P1 Attacks":
                Collections.sort(curStageAttackTeams.get(0));
                curAttackTeam = curStageAttackTeams.get(0);
                Player P1 = PlayerList.get(0);
                if (isValidInt(input) && 1 <= Integer.parseInt(input) && Integer.parseInt(input) <= P1.getHand().size()) {//add a card to the stage
                    int handIndex = Integer.parseInt(input) - 1;
                    if (P1.getHand().get(handIndex).getType().equals("Foe")) {//selected card is a foe
                        return "Invalid input. Cannot put foes in an attack.\n";
                    } else if (P1.getHand().get(handIndex).getType().equals("Weapon")) {
                        //check for dupe weapon
                        for (Card card : curAttackTeam) {
                            if (card.toString().equals(P1.getHand().get(Integer.parseInt(input) - 1).toString())) {
                                return "Invalid input. Duplicate weapon.\n";
                            }
                        }
                        curAttackTeam.add(P1.getHand().remove(handIndex));
                        return "Card Added.\n\nAttack: " + printStage(curAttackTeam) + "\nPlease select an index of a card you wish to add to your attack, or press enter to finish.\n" + P1.printHand() + "\n";
                    }
                } else if (input.equals("empty_string")) {
                    //invalids
                    if (curAttackTeam.isEmpty()) {
                        return "Attack is empty. Please add Weapons.\n";
                    } else {
                        //valid
                        gamePhase = "Building P1 Attacks End";
                        return "Attack building finished. Here is your attack: \n" + printStage(curAttackTeam) + "\n\n";
                    }
                } else {//input is not an index nor empty; invalid
                    return "Input must be a valid index or empty.\n";
                }
            case "Building P2 Attacks Begin":
                gamePhase = "Building P2 Attacks";
                return "P2 you must build an attack team.\nPlease select an index of a card you wish to add to your attack, or press enter to finish.\n" + PlayerList.get(1).printHand() + "\n";
            case "Building P2 Attacks":
                Collections.sort(curStageAttackTeams.get(1));
                curAttackTeam = curStageAttackTeams.get(1);
                Player P2 = PlayerList.get(1);
                if (isValidInt(input) && 1 <= Integer.parseInt(input) && Integer.parseInt(input) <= P2.getHand().size()) {//add a card to the stage
                    int handIndex = Integer.parseInt(input) - 1;
                    if (P2.getHand().get(handIndex).getType().equals("Foe")) {//selected card is a foe
                        return "Invalid input. Cannot put foes in an attack.\n";
                    } else if (P2.getHand().get(handIndex).getType().equals("Weapon")) {
                        //check for dupe weapon
                        for (Card card : curAttackTeam) {
                            if (card.toString().equals(P2.getHand().get(Integer.parseInt(input) - 1).toString())) {
                                return "Invalid input. Duplicate weapon.\n";
                            }
                        }
                        curAttackTeam.add(P2.getHand().remove(handIndex));
                        return "Card Added.\n\nAttack: " + printStage(curAttackTeam) + "\nPlease select an index of a card you wish to add to your attack, or press enter to finish.\n" + P2.printHand() + "\n";
                    }
                } else if (input.equals("empty_string")) {
                    //invalids
                    if (curAttackTeam.isEmpty()) {
                        return "Attack is empty. Please add Weapons.\n";
                    } else {
                        //valid
                        gamePhase = "Building P2 Attacks End";
                        return "Attack building finished. Here is your attack: \n" + printStage(curAttackTeam) + "\n\n";
                    }
                } else {//input is not an index nor empty; invalid
                    return "Input must be a valid index or empty.\n";
                }
            case "Building P3 Attacks Begin":
                gamePhase = "Building P3 Attacks";
                return "P3 you must build an attack team.\nPlease select an index of a card you wish to add to your attack, or press enter to finish.\n" + PlayerList.get(2).printHand() + "\n";
            case "Building P3 Attacks":
                Collections.sort(curStageAttackTeams.get(2));
                curAttackTeam = curStageAttackTeams.get(2);
                Player P3 = PlayerList.get(2);
                if (isValidInt(input) && 1 <= Integer.parseInt(input) && Integer.parseInt(input) <= P3.getHand().size()) {//add a card to the stage
                    int handIndex = Integer.parseInt(input) - 1;
                    if (P3.getHand().get(handIndex).getType().equals("Foe")) {//selected card is a foe
                        return "Invalid input. Cannot put foes in an attack.\n";
                    } else if (P3.getHand().get(handIndex).getType().equals("Weapon")) {
                        //check for dupe weapon
                        for (Card card : curAttackTeam) {
                            if (card.toString().equals(P3.getHand().get(Integer.parseInt(input) - 1).toString())) {
                                return "Invalid input. Duplicate weapon.\n";
                            }
                        }
                        curAttackTeam.add(P3.getHand().remove(handIndex));
                        return "Card Added.\n\nAttack: " + printStage(curAttackTeam) + "\nPlease select an index of a card you wish to add to your attack, or press enter to finish.\n" + P3.printHand() + "\n";
                    }
                } else if (input.equals("empty_string")) {
                    //invalids
                    if (curAttackTeam.isEmpty()) {
                        return "Attack is empty. Please add Weapons.\n";
                    } else {
                        //valid
                        gamePhase = "Building P3 Attacks End";
                        return "Attack building finished. Here is your attack: \n" + printStage(curAttackTeam) + "\n\n";
                    }
                } else {//input is not an index nor empty; invalid
                    return "Input must be a valid index or empty.\n";
                }
            case "Building P4 Attacks Begin":
                gamePhase = "Building P4 Attacks";
                return "P4 you must build an attack team.\nPlease select an index of a card you wish to add to your attack, or press enter to finish.\n" + PlayerList.get(3).printHand() + "\n";
            case "Building P4 Attacks":
                Collections.sort(curStageAttackTeams.get(3));
                curAttackTeam = curStageAttackTeams.get(3);
                Player P4 = PlayerList.get(3);
                if (isValidInt(input) && 1 <= Integer.parseInt(input) && Integer.parseInt(input) <= P4.getHand().size()) {//add a card to the stage
                    int handIndex = Integer.parseInt(input) - 1;
                    if (P4.getHand().get(handIndex).getType().equals("Foe")) {//selected card is a foe
                        return "Invalid input. Cannot put foes in an attack.\n";
                    } else if (P4.getHand().get(handIndex).getType().equals("Weapon")) {
                        //check for dupe weapon
                        for (Card card : curAttackTeam) {
                            if (card.toString().equals(P4.getHand().get(Integer.parseInt(input) - 1).toString())) {
                                return "Invalid input. Duplicate weapon.\n";
                            }
                        }
                        curAttackTeam.add(P4.getHand().remove(handIndex));
                        return "Card Added.\n\nAttack: " + printStage(curAttackTeam) + "\nPlease select an index of a card you wish to add to your attack, or press enter to finish.\n" + P4.printHand() + "\n";
                    }
                } else if (input.equals("empty_string")) {
                    //invalids
                    if (curAttackTeam.isEmpty()) {
                        return "Attack is empty. Please add Weapons.\n";
                    } else {
                        //valid
                        gamePhase = "Building P4 Attacks End";
                        return "Attack building finished. Here is your attack: \n" + printStage(curAttackTeam) + "\n\n";
                    }
                } else {//input is not an index nor empty; invalid
                    return "Input must be a valid index or empty.\n";
                }

            default:
                return "";
        }
    }

    @GetMapping("/startGame")
    public void startGame() {
        initializeAdventureDeck();
        initializeEventDeck();
        initializePlayerHands();
        sponsor = null;
        curPlayer = PlayerList.get(3);
        gamePhase = "New Game";
    }

    @GetMapping("/startGameT1")
    public void startGameT1() {
        initializeAdventureDeckRig("F5 F5 F15 F15 D5 S10 S10 H10 H10 B15 B15 L20 F5 F5 F15 F15 F40 D5 S10 H10 H10 B15 B15 E30 F5 F5 F5 F15 D5 S10 S10 S10 H10 H10 B15 L20 F5 F15 F15 F40 D5 D5 S10 H10 H10 B15 L20 E30 F30 S10 B15 F10 L20 L20 B15 S10 F30 L20");
        initializeEventDeckRig("Q4");
        initializePlayerHands();
        sponsor = null;
        curPlayer = PlayerList.get(3);
        gamePhase = "New Game";
    }

    @GetMapping("/startGameT2")
    public void startGameT2() {
        initializeAdventureDeck();
        initializeEventDeck();
        initializePlayerHands();
        sponsor = null;
        curPlayer = PlayerList.get(3);
        gamePhase = "New Game";
    }

    @GetMapping("/startGameT3")
    public void startGameT3() {
        initializeAdventureDeck();
        initializeEventDeck();
        initializePlayerHands();
        sponsor = null;
        curPlayer = PlayerList.get(3);
        gamePhase = "New Game";
    }

    @GetMapping("/startGameT4")
    public void startGameT4() {
        initializeAdventureDeck();
        initializeEventDeck();
        initializePlayerHands();
        sponsor = null;
        curPlayer = PlayerList.get(3);
        gamePhase = "New Game";
    }

    @GetMapping("/findWinners")
    public boolean findWinners() {
        for (Player p : PlayerList) {
            if (p.getShields() >= 7) {
                return true;
            }
        }
        return false;
    }

    @PostMapping("/nextPlayer")
    public String nextPlayer() {
        sponsor = null;
        curPlayer = PlayerList.get((PlayerList.indexOf(curPlayer) + 1) % 4);
        return "Player " + curPlayer + ", this is your hand:\n" + curPlayer.printHand();
    }

    @PostMapping("/endTurn")
    public String endTurn() {
        gamePhase = "End Turn";
        return "\n\n" + curPlayer + "'s turn has ended. Please give controls to " + (PlayerList.get((PlayerList.indexOf(curPlayer) + 1) % 4)) + ", and press enter.\n";
    }

    @GetMapping("/printWinners")
    public String printWinners() {
        StringBuilder outString = new StringBuilder("Players ");
        for (Player p : PlayerList) {
            if (p.getShields() >= 7) {
                outString.append(p).append(", ");
            }
        }
        return outString.substring(0, outString.length() - 2) + " Won.";
    }

    @PostMapping("/drawEvent")
    public Map<String, Card> drawEventCard() {
        String outString = "";
        if (EvDeck.isEmpty()) {
            outString += "Event Deck empty. Shuffling discard pile back in.\n";
            EvDeck.addAll(EvDiscard);
            EvDiscard.clear();
            Collections.shuffle(EvDeck);
        }
        curEvent = EvDeck.remove(0);
        //immediately discard it; no reason to have it in the deck anymore, and no other zone it can be in
        EvDiscard.add(curEvent);
        outString += "\nThe current event is: " + curEvent + "\n";
        Map<String, Card> outMap = new HashMap<>();
        outMap.put(outString, curEvent);
        return outMap;
    }

    @PostMapping("/plagueEffect")
    public String plagueEffect() {
        String outString = "Plague Drawn. Current player's shields decreased from " + curPlayer.getShields();
        curPlayer.addShields(-2);
        //if it's less than 0, set it to 0
        if (curPlayer.getShields() < 0) {
            curPlayer.addShields(-curPlayer.getShields());
        }
        outString += " to " + curPlayer.getShields() + "\n";
        return outString;
    }

    @PostMapping("/queenEffect")
    public String queenEffect() {
        return drawAdCard(curPlayer, 2);
    }

    @PostMapping("/prosperityEffect1")
    public String prosperityEffectP1() {
        return drawAdCard(PlayerList.get(0), 2);
    }

    @PostMapping("/prosperityEffect2")
    public String prosperityEffectP2() {
        return drawAdCard(PlayerList.get(1), 2);
    }

    @PostMapping("/prosperityEffect3")
    public String prosperityEffectP3() {
        return drawAdCard(PlayerList.get(2), 2);
    }

    @PostMapping("/prosperityEffect4")
    public String prosperityEffectP4() {
        return drawAdCard(PlayerList.get(3), 2);
    }

    @PostMapping("/seekSponsor1")
    public String seekSponsor1() {
        sponsor = null;
        gamePhase = "Seek Sponsor 1";
        return (PlayerList.get((curPlayer.getId() - 1) % 4) + ", would you like to sponsor this quest? (Y/N)\n");
    }

    @PostMapping("/seekSponsor2")
    public String seekSponsor2() {
        gamePhase = "Seek Sponsor 2";
        return (PlayerList.get(curPlayer.getId() % 4) + ", would you like to sponsor this quest? (Y/N)\n");
    }

    @PostMapping("/seekSponsor3")
    public String seekSponsor3() {
        gamePhase = "Seek Sponsor 3";
        return (PlayerList.get((curPlayer.getId() + 1) % 4) + ", would you like to sponsor this quest? (Y/N)\n");
    }

    @PostMapping("/seekSponsor4")
    public String seekSponsor4() {
        gamePhase = "Seek Sponsor 4";
        return (PlayerList.get((curPlayer.getId() + 2) % 4) + ", would you like to sponsor this quest? (Y/N)\n");
    }

    @PostMapping("/startQuestBuild")
    public String beginQuestBuilding() {
        gamePhase = "Quest Build Begin";
        return sponsor + ", you are the sponsor. Please confirm you are in control.\n";
    }

    @PostMapping("/QuestBuild")
    public String beginStageBuilding() {
        gamePhase = "Building Quest Stages";
        curAttackStageNumber = 1;
        curStageNumber = 1;
        return "You must build " + curEvent.getValue() + " stages.\nStage 1: \nPlease select an index of a card you wish to add to the stage, or press enter to finish.\n" + sponsor.printHand() + "\n";
    }

    @PostMapping("/SeekParticipant1")
    public String seekParticipant1() {
        gamePhase = "Seek Participant 1";
        if (PlayerList.get(0).isEligible()) {
            return "P1, would you like to participate in this stage? (Y/N)\n";
        }
        gamePhase = "Seek Participant 1 End";
        return "";
    }

    @PostMapping("/SeekParticipant2")
    public String seekParticipant2() {
        gamePhase = "Seek Participant 2";
        if (PlayerList.get(1).isEligible()) {
            return "P2, would you like to participate in this stage? (Y/N)\n";
        }
        gamePhase = "Seek Participant 2 End";
        return "";
    }

    @PostMapping("/SeekParticipant3")
    public String seekParticipant3() {
        gamePhase = "Seek Participant 3";
        if (PlayerList.get(2).isEligible()) {
            return "P3, would you like to participate in this stage? (Y/N)\n";
        }
        gamePhase = "Seek Participant 3 End";
        return "";
    }

    @PostMapping("/SeekParticipant4")
    public String seekParticipant4() {
        gamePhase = "Seek Participant 4";
        if (PlayerList.get(3).isEligible()) {
            return "P4, would you like to participate in this stage? (Y/N)\n";
        }
        gamePhase = "Seek Participant 4 End";
        return "";
    }

    @PostMapping("/ParticipantDrawCard1")
    public String participantsDrawCard1() {
        gamePhase = "";
        if (PlayerList.get(0).isEligible()) {
            return drawAdCard(PlayerList.get(0), 1);
        }
        return "";
    }

    @PostMapping("/ParticipantDrawCard2")
    public String participantsDrawCard2() {
        gamePhase = "";
        if (PlayerList.get(1).isEligible()) {
            return drawAdCard(PlayerList.get(1), 1);
        }
        return "";
    }

    @PostMapping("/ParticipantDrawCard3")
    public String participantsDrawCard3() {
        gamePhase = "";
        if (PlayerList.get(2).isEligible()) {
            return drawAdCard(PlayerList.get(2), 1);
        }
        return "";
    }

    @PostMapping("/ParticipantDrawCard4")
    public String participantsDrawCard4() {
        gamePhase = "";
        if (PlayerList.get(3).isEligible()) {
            return drawAdCard(PlayerList.get(3), 1);
        }
        return "";
    }

    @PostMapping("/startQuestAttack")
    public String questAttackBegin() {
        curStageAttackTeams = new ArrayList<>();
        curStageAttackTeams.add(new ArrayList<>()); //P1
        curStageAttackTeams.add(new ArrayList<>()); //P2
        curStageAttackTeams.add(new ArrayList<>()); //P3
        curStageAttackTeams.add(new ArrayList<>()); //P4
        gamePhase = "Quest Attack Begin";
        return "Participants will now create attacks.\n";
    }

    @PostMapping("/QuestAttack1")
    public String beginAttackBuilding1() {
        gamePhase = "Building P1 Attacks Begin";
        if (PlayerList.get(0).isEligible()) {
            curAttackTeam = new ArrayList<>();
            return "P1, Please confirm you are in control.\n";
        }
        gamePhase = "Building P1 Attacks End";
        return "";
    }

    @PostMapping("/QuestAttack2")
    public String beginAttackBuilding2() {
        gamePhase = "Building P2 Attacks Begin";
        if (PlayerList.get(1).isEligible()) {
            curAttackTeam = new ArrayList<>();
            return "P2, Please confirm you are in control.\n";
        }
        gamePhase = "Building P2 Attacks End";
        return "";
    }

    @PostMapping("/QuestAttack3")
    public String beginAttackBuilding3() {
        gamePhase = "Building P3 Attacks Begin";
        if (PlayerList.get(2).isEligible()) {
            curAttackTeam = new ArrayList<>();
            return "P3, Please confirm you are in control.\n";
        }
        gamePhase = "Building P3 Attacks End";
        return "";
    }

    @PostMapping("/QuestAttack4")
    public String beginAttackBuilding4() {
        gamePhase = "Building P4 Attacks Begin";
        if (PlayerList.get(3).isEligible()) {
            curAttackTeam = new ArrayList<>();
            return "P4, Please confirm you are in control.\n";
        }
        gamePhase = "Building P4 Attacks End";
        return "";
    }

    @PostMapping("/ResolveAttacks")
    public String resolveAttacks() {
        String outString = "Stage " + curAttackStageNumber + " was: " + stages.get(curAttackStageNumber - 1).toString().substring(1, stages.get(curAttackStageNumber - 1).toString().length() - 1) + ".\n";

        //value of stage
        int stageValue = getValueOfStage(stages.get(curAttackStageNumber - 1));
        //find and check hands against stage
        for (int i = 0; i < 4; i++) {
            int attackValue = getValueOfStage(curStageAttackTeams.get(i));
            if (attackValue < stageValue) {
                PlayerList.get(i).setEligible(false);
            }
        }
        //discard the attack teams after
        discardAttackTeams(curStageAttackTeams);
        //find who won
        StringBuilder winners = new StringBuilder();
        for (Player p : PlayerList) {
            if (p.isEligible()) {
                winners.append(p).append(", ");
            }
        }
        curAttackStageNumber += 1;
        if (!winners.isEmpty()) {
            winners = new StringBuilder(winners.substring(0, winners.length() - 2));
            winners.append(" are the winner(s) and are eligible to continue.\n\n");
            if (curAttackStageNumber > stages.size()) {
                gamePhase = "Quest Attack End";
                return outString + winners + "The quest is over. Cleanup phase.\n\n";
            }
            return outString + winners + "Stage " + curAttackStageNumber + " Begins.\n\n";
        }
        gamePhase = "Quest Attack End";
        return "No one beat the stage. The quest is over.\n\n";
    }
}