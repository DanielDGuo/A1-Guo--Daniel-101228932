package org.example;

import ch.qos.logback.core.joran.sanity.Pair;
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
//                //Enter Quest Attack. Loop through stages and attacks N times, where N is the quest value
//                for (int i = 0; i < curEventCard.getValue(); i++) {
//                    game.participantsDrawCard(stageParticipants, game.inContent);
//                    ArrayList<ArrayList<Card>> stageAttackTeams = game.createAttackTeams(stageParticipants, game.inContent);
//                    ArrayList<Boolean> stageOutcome = game.resolveAttacks(stages.get(i), stageAttackTeams, stageParticipants);
//                    game.discardAttackTeams(stageAttackTeams);
//                    if (!game.findStageSurvivors(stageOutcome)) {
//                        break;
//                    }
//                }
//                //After Quest Attack, distribute shields to the winners
//                game.giveWinnersShields(curEventCard.getValue());
//                //After Quest attack, Quest enemy cards are discarded. Sponsor draws that many cards + Quest value
//                game.discardQuestStages(stages, sponsor, game.inContent);


    public ArrayList<ArrayList<Card>> createAttackTeams(ArrayList<Player> stageParticipants, Scanner inContent) {
        ArrayList<ArrayList<Card>> attackTeams = new ArrayList<>();
        for (Player p : stageParticipants) {
            System.out.print("Please confirm you are " + p + "\n");
            String input = inContent.nextLine();
            while (!input.isEmpty()) {
                System.out.print("Invalid input.\n");
                try {
                    input = inContent.nextLine();
                } catch (java.util.NoSuchElementException e) {
                    input = "";
                }
            }

            ArrayList<Card> curAttack = new ArrayList<>();
            String curAttackToString = "";
            input = "temp";
            while (!input.isEmpty()) {
                Collections.sort(curAttack);
                //print the relevant information
                curAttackToString = curAttack.toString().substring(1, curAttack.toString().length() - 1);
                System.out.print("Current Attack Team: " + curAttackToString + "\n");
                System.out.print("Please select an index of a card you wish to add to the attack, or press enter to finish.\n");
                p.printHand();
                System.out.print("\n");
                input = inContent.nextLine();
                //valid input is an empty line to go to end the attack creation, or a valid index
                if (!(input.isEmpty() || (1 <= Integer.parseInt(input) && Integer.parseInt(input) <= p.getHand().size()))) {
                    System.out.print("Invalid input. Please provide a valid index or press enter.\n");
                    continue;
                }
                if (!input.isEmpty()) {
                    //non-empty input; get the card and check if its valid
                    //check if it's a weapon
                    if (!p.getHand().get(Integer.parseInt(input) - 1).getType().equals("Weapon")) {
                        //must be a foe.
                        System.out.print("Invalid input. Cannot add foes to attack team.\n");
                        continue;
                    }
                    //check if it's a dupe
                    boolean hasDupe = false;
                    for (Card c : curAttack) {
                        if (c.toString().equals(p.getHand().get(Integer.parseInt(input) - 1).toString())) {
                            hasDupe = true;
                        }
                    }
                    if (hasDupe) {
                        System.out.print("Invalid input. Duplicate weapon.\n");
                        continue;
                    }
                    curAttack.add(p.getHand().remove(Integer.parseInt(input) - 1));
                }
            }
            attackTeams.add(curAttack);
            System.out.print(p + ", Here is your attack team:\n");
            System.out.print("Current Attack Team: " + curAttackToString + "\n");
            System.out.print("Press enter to confirm.\n");
            input = inContent.nextLine();
            while (!input.isEmpty()) {
                System.out.print("Invalid input.\n");
                try {
                    input = inContent.nextLine();
                } catch (java.util.NoSuchElementException e) {
                    input = "";
                }
            }
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        return attackTeams;
    }

    public void participantsDrawCard(ArrayList<Player> participants, Scanner inContent) {
        System.out.print("Player(s) " + participants.toString().substring(1, participants.toString().length() - 1) + " will draw a card.\n");
        for (Player p : participants) {
            drawAdCard(p, 1);
        }
    }

    public ArrayList<Boolean> resolveAttacks(ArrayList<Card> curStage, ArrayList<ArrayList<Card>> attackTeams, ArrayList<Player> participants) {
        System.out.print("The current stage was: " + curStage.toString().substring(1, curStage.toString().length() - 1) + ".\n");

        ArrayList<Boolean> outcome = new ArrayList<>();
        //value of stage
        int stageValue = 0;
        for (Card c : curStage) {
            stageValue += c.getValue();
        }
        //find and check hands against stage
        for (int i = 0; i < attackTeams.size(); i++) {
            int handValue = 0;
            for (Card c : attackTeams.get(i)) {
                handValue += c.getValue();
            }
            if (handValue >= stageValue) {
                outcome.add(true);
            } else {
                outcome.add(false);
                participants.get(i).setEligible(false);
            }
        }
        //find who won
        StringBuilder winners = new StringBuilder();
        for (Player p : participants) {
            if (p.isEligible()) {
                winners.append(p).append(", ");
            }
        }
        if (!winners.isEmpty()) {
            winners = new StringBuilder(winners.substring(0, winners.length() - 2));
            winners.append(" are the winner(s) and are eligible to continue.\n");
            System.out.print(winners);
        }
        return outcome;
    }

    public boolean findStageSurvivors(ArrayList<Boolean> outcome) {
        if (!outcome.contains(true)) {
            System.out.print("No one passed the stage. Aborting quest.\n");
            return false;
        }
        return true;
    }

    public void discardAttackTeams(ArrayList<ArrayList<Card>> attackTeams) {
        for (ArrayList<Card> team : attackTeams) {
            AdDiscard.addAll(team);
        }
    }

    public void giveWinnersShields(int numStages) {
        ArrayList<Player> winners = new ArrayList<>();
        for (Player p : PlayerList) {
            if (p.isEligible()) {
                winners.add(p);
                p.addShields(numStages);
            }
        }
        if (winners.isEmpty()) {
            System.out.print("The Quest was failed. No shields.\n");
        } else {
            System.out.print("The quest was completed by player(s) " + winners.toString().substring(1, winners.toString().length() - 1) + ". They each get " + numStages + " shields.\n");
        }
    }

    public void discardQuestStages(ArrayList<ArrayList<Card>> stages, Player sponsor, Scanner inContent) {
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

    public void initializePlayerHands() {
        for (Player p : PlayerList) {
            p.setHand(new ArrayList<>());
            drawAdCard(p, 12);
        }
    }

    public void initializeAdventureDeck() {
        AdDeck = new ArrayList<>(AdventureDeckList);
        Collections.shuffle(AdDeck);
    }

    public void initializeEventDeck() {
        EvDeck = new ArrayList<>(EventDeckList);
        Collections.shuffle(EvDeck);
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
                        return "Card Added.\nStage " + curStageNumber + ": " + printStage(stages.get(curStageNumber - 1)) + "\nPlease select an index of a card you wish to add to the stage, or press enter to finish.\n" + sponsor.printHand() + "\n";
                    } else if (sponsor.getHand().get(handIndex).getType().equals("Weapon")) {
                        //check for dupe weapon
                        for (Card card : curStage) {
                            if (card.toString().equals(sponsor.getHand().get(Integer.parseInt(input) - 1).toString())) {
                                return "Invalid input. Duplicate weapon.\n";
                            }
                        }
                        curStage.add(sponsor.getHand().remove(handIndex));
                        return "Card Added.\nStage " + curStageNumber + ": " + printStage(stages.get(curStageNumber - 1)) + "\nPlease select an index of a card you wish to add to the stage, or press enter to finish.\n" + sponsor.printHand() + "\n";
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
        curStageNumber = 1;
        curAttackStageNumber = 1;
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
        return "P4 will participate in this stage.\n";
    }
}