package org.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class Main {
    public Player curPlayer;
    public Scanner inContent = new Scanner(System.in);
    public ArrayList<Card> AdDeck = new ArrayList<>();
    public ArrayList<Card> EvDeck = new ArrayList<>();
    public ArrayList<Card> AdDiscard = new ArrayList<>();
    public ArrayList<Card> EvDiscard = new ArrayList<>();
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

//    public static void main(String[] args) {
//        Main game = new Main();
//        game.initializeAdventureDeck();
//        game.initializeEventDeck();
//        game.initializePlayerHands();
//
//        game.curPlayer = game.PlayerList.get(3);
//        while (!game.findWinners()) {
//            //Start of Beginning Phase
//            //get the next player
//            game.curPlayer = game.PlayerList.get((game.PlayerList.indexOf(game.curPlayer) + 1) % 4);
//
//            System.out.print("Player " + game.curPlayer + ", this is your hand:\n");
//            game.curPlayer.printHand();
//
//            Card curEventCard = game.drawEventCard();
//
//            switch (curEventCard.getId()) {
//                case "Plague" -> game.plagueEffect();
//                case "Queen's Favour" -> game.queenEffect();
//                case "Prosperity" -> game.prosperityEffect();
//                default -> game.questEffect(curEventCard);
//            }
//            //end of Beginning Phase. Move on to Questing if needed
//            if (curEventCard.getType().equals("Quest")) {
//                Player sponsor = game.seekSponsor(game.inContent);
//                if (sponsor == null) {
//                    continue;
//                }
//                //Enter Quest Build
//                game.beginQuestBuilding(sponsor, game.inContent);
//                ArrayList<ArrayList<Card>> stages = game.beginStageBuilding(sponsor, curEventCard.getValue(), game.inContent);
//                game.endStageBuilding(sponsor, stages, game.inContent);
//                //Enter Quest Attack. Loop through stages and attacks N times, where N is the quest value
//                for (int i = 0; i < curEventCard.getValue(); i++) {
//                    //Find participants for the current stage
//                    ArrayList<Player> stageParticipants = game.seekParticipants(sponsor, i == 0, game.inContent);
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
//            }
//            game.endTurn(game.inContent);
//        }
//        game.printWinners();
//    }

    public void initializePlayerHands() {
        for (Player p : PlayerList) {
            p.setHand(new ArrayList<>());
            drawAdCard(p, 12, inContent);
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

    public boolean findWinners() {
        for (Player p : PlayerList) {
            if (p.getShields() >= 7) {
                return true;
            }
        }
        return false;
    }

    public void printWinners() {
        StringBuilder outString = new StringBuilder("Player(s) ");
        for (Player p : PlayerList) {
            if (p.getShields() >= 7) {
                outString.append(p).append(", ");
            }
        }
        System.out.print(outString.substring(0, outString.length() - 2) + " Won.");
    }

    public void drawAdCard(Player p, int num, Scanner inContent) {
        for (int i = 0; i < num; i++) {
            if (AdDeck.isEmpty()) {
                System.out.print("Adventure Deck empty. Shuffling discard pile back in.\n");
                AdDeck.addAll(AdDiscard);
                AdDiscard.clear();
                Collections.shuffle(AdDeck);
            }
            //p.addCard(AdDeck.removeFirst());
            p.addCard(AdDeck.remove(0));
        }
        Collections.sort(p.getHand());
        if (p.getHand().size() > 12) {
            System.out.print("\n\n\n\n\n\n\n\n\n\n" + p + " is over the max hand size by " + (p.getHand().size() - 12) + ". Please give controls to " + p + ", and press enter.\n");
            this.discardAdCard(p, (p.getHand().size() - 12), inContent);
        }
    }

    public void discardAdCard(Player p, int num, Scanner inContent) {
        System.out.print("\nAre you " + p + "?\n");
        String input = inContent.nextLine();
        while (!input.isEmpty()) {
            System.out.print("Invalid input. Please press Enter.\n");
            try {
                input = inContent.nextLine();
            } catch (java.util.NoSuchElementException e) {
                input = "";
            }
        }
        while (num > 0) {
            System.out.print("You have to discard " + num + " more card(s)\n");
            System.out.print(p + ", This is your hand:\n");
            p.printHand();
            System.out.print("\nPlease select a card to discard by index(1 - " + (num + 12) + ")\n");
            input = inContent.nextLine();
            if (!input.isEmpty() && !(1 <= Integer.parseInt(input) && Integer.parseInt(input) <= num + 12)) {
                System.out.print("Invalid index. Try again.\n");
                continue;
            } else if (input.isEmpty()) {
                System.out.print("Please specify an index.\n");
                continue;
            }
            AdDiscard.add(p.getHand().remove((Integer.parseInt(input)) - 1));
            num--;
        }
        System.out.print("Discarding Complete. This is your new hand:\n");
        p.printHand();
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void plagueEffect() {
        System.out.print("Plague Drawn. Current player's shields decreased from " + curPlayer.getShields());
        curPlayer.addShields(-2);
        //if it's less than 0, set it to 0
        if (curPlayer.getShields() < 0) {
            curPlayer.addShields(-curPlayer.getShields());
        }
        System.out.print(" to " + curPlayer.getShields() + "\n");
    }

    public void queenEffect() {
        System.out.print("Queen's Favour Drawn. Current player draws 2 cards.\n");
        drawAdCard(curPlayer, 2, inContent);
    }

    public void prosperityEffect() {
        System.out.print("Prosperity Drawn. Each player draws 2 cards.\n");
        for (Player p : PlayerList) {
            drawAdCard(p, 2, inContent);
        }
    }

    public void questEffect(Card c) {
        System.out.print("Beginning the effects of a Quest card with " + c.getValue() + " stages.\n");
    }

    public void endTurn(Scanner inContent) {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + curPlayer + "'s turn has ended. Please give controls to " + (PlayerList.get((PlayerList.indexOf(curPlayer) + 1) % 4)) + ", and press enter.\n");

        String input = inContent.nextLine();
        while (!input.isEmpty()) {
            System.out.print("Invalid input.\n");
            try {
                input = inContent.nextLine();
            } catch (java.util.NoSuchElementException e) {
                input = "";
            }
        }
    }

    public Player seekSponsor(Scanner inContent) {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        int curPlayerIndex = curPlayer.getId() - 1;
        for (int i = 0; i < 4; i++) {
            System.out.print(PlayerList.get((curPlayerIndex + i) % 4) + ", would you like to sponsor this quest? (Y/N)\n");
            String input = inContent.nextLine();
            while (!(input.equals("Y") || input.equals("N"))) {
                System.out.print("Invalid input.\n");
                try {
                    input = inContent.nextLine();
                } catch (java.util.NoSuchElementException e) {
                    input = "";
                }
            }
            if (input.equals("Y")) {
                System.out.print(PlayerList.get((curPlayerIndex + i) % 4) + " has accepted to sponsor this quest.\n");
                return PlayerList.get((curPlayerIndex + i) % 4);
            }
        }
        System.out.print("Everybody turned down the sponsor opportunity.\n");
        return null;
    }

    public void beginQuestBuilding(Player sponsor, Scanner inContent) {
        System.out.print(sponsor + ", you are the sponsor. Please confirm you are in control.\n");
        String input = inContent.nextLine();
        while (!input.isEmpty()) {
            System.out.print("Invalid input.\n");
            try {
                input = inContent.nextLine();
            } catch (java.util.NoSuchElementException e) {
                input = "";
            }
        }
        System.out.print(sponsor + ", this is your hand:\n");
        sponsor.printHand();
        System.out.print("\n");
    }

    public ArrayList<ArrayList<Card>> beginStageBuilding(Player sponsor, int numStages, Scanner inContent) {
        ArrayList<ArrayList<Card>> stages = new ArrayList<>();
        System.out.print("You must build " + numStages + " stages.\n");
        //start building each stage
        for (int i = 0; i < numStages; i++) {
            ArrayList<Card> curStage = new ArrayList<>();
            String input = "temp";
            while (!input.isEmpty()) {
                Collections.sort(curStage);
                //print the relevant information
                String curStageToString = curStage.toString().substring(1, curStage.toString().length() - 1);
                System.out.print("Stage " + (i + 1) + ": " + curStageToString + "\n");
                System.out.print("Please select an index of a card you wish to add to the stage, or press enter to finish.\n");
                sponsor.printHand();
                System.out.print("\n");
                input = inContent.nextLine();
                //valid input is an empty line to go to next stage, or a valid index
                if (!(input.isEmpty() || (1 <= Integer.parseInt(input) && Integer.parseInt(input) <= sponsor.getHand().size()))) {
                    System.out.print("Invalid input. Please provide a valid index or press enter.\n");
                    continue;
                }
                if (input.isEmpty()) {
                    //stages cannot be empty.
                    if (curStage.isEmpty()) {
                        System.out.print("Stage cannot be empty. Please insert at least one foe.\n");
                        input = "temp";
                        continue;
                    }
                    //stages must be in ascending order
                    int curStageValue = 0;
                    for (Card c : curStage) {
                        curStageValue += c.getValue();
                    }
                    int priorStageValue = 0;
                    if (!stages.isEmpty()) {
                        for (Card c : stages.get(stages.size() - 1)) {
                            priorStageValue += c.getValue();
                        }
                    }
                    if (priorStageValue >= curStageValue) {
                        System.out.print("Current stage must have value greater than the prior stage.\n");
                        input = "temp";
                    }
                } else if (sponsor.getHand().get(Integer.parseInt(input) - 1).getType().equals("Foe")) {
                    //check if there's already a foe or not
                    boolean hadFoe = false;
                    for (Card card : curStage) {
                        if (card.getType().equals("Foe")) {
                            System.out.print("Invalid input. Cannot put two foes in one stage.\n");
                            hadFoe = true;
                            break;
                        }
                    }
                    if (!hadFoe) {
                        //add the card at index and continue. Remove from sponsor hand.
                        curStage.add(sponsor.getHand().remove(Integer.parseInt(input) - 1));
                    }
                } else if (sponsor.getHand().get(Integer.parseInt(input) - 1).getType().equals("Weapon")) {
                    //check if there's already a foe or not
                    boolean hadDupe = false;
                    for (Card card : curStage) {
                        if (card.toString().equals(sponsor.getHand().get(Integer.parseInt(input) - 1).toString())) {
                            System.out.print("Invalid input. Duplicate weapon.\n");
                            hadDupe = true;
                            break;
                        }
                    }
                    if (!hadDupe) {
                        //add the card at index and continue. Remove from sponsor hand.
                        curStage.add(sponsor.getHand().remove(Integer.parseInt(input) - 1));
                    }
                }
            }
            stages.add(curStage);
        }
        return stages;
    }

    public void endStageBuilding(Player sponsor, ArrayList<ArrayList<Card>> stages, Scanner inContent) {
        System.out.print(sponsor + ", here are your stages:\n");
        for (int i = 0; i < stages.size(); i++) {
            System.out.print("Stage " + (i + 1) + ": ");
            System.out.print(stages.get(i).toString().substring(1, stages.get(i).toString().length() - 1) + "\n");
        }
        System.out.print("Press enter to confirm.\n");
        String input = inContent.nextLine();
        while (!input.isEmpty()) {
            System.out.print("Invalid input.\n");
            try {
                input = inContent.nextLine();
            } catch (java.util.NoSuchElementException e) {
                input = "";
            }
        }
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public ArrayList<Player> seekParticipants(Player sponsor, boolean firstStage, Scanner inContent) {
        //set up the base eligibility of the quest if it's the first stage
        //this is to overwrite any prior changes to the eligibility
        if (firstStage) {
            for (Player p : PlayerList) {
                p.setEligible(true);
            }
            sponsor.setEligible(false);
        }
        ArrayList<Player> stageParticipants = new ArrayList<>();
        //loop and ask each player for participation
        for (Player p : PlayerList) {
            if (p.isEligible()) {
                System.out.print(p + ", would you like to participate in this stage? (Y/N)\n");
                String input = inContent.nextLine();
                while (!(input.equals("Y") || input.equals("N"))) {
                    System.out.print("Invalid input.\n");
                    try {
                        input = inContent.nextLine();
                    } catch (java.util.NoSuchElementException e) {
                        input = "";
                    }
                }
                if (input.equals("Y")) {
                    stageParticipants.add(p);
                } else {
                    p.setEligible(false);
                }
            }
        }
        System.out.print(stageParticipants.toString().substring(1, stageParticipants.toString().length() - 1) + " will participate in this stage.\n");
        return stageParticipants;
    }

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
            drawAdCard(p, 1, inContent);
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
        drawAdCard(sponsor, numCardsUsed + stages.size(), inContent);
    }

    @GetMapping("/startGame")
    public String startGame() {
        initializeAdventureDeck();
        initializeEventDeck();
        initializePlayerHands();
        curPlayer = PlayerList.get(3);
        return "game started";
    }

    @PostMapping("/drawEvent")
    public Card drawEventCard() {
        if (EvDeck.isEmpty()) {
            System.out.print("Event Deck empty. Shuffling discard pile back in.\n");
            EvDeck.addAll(EvDiscard);
            EvDiscard.clear();
            Collections.shuffle(EvDeck);
        }
        Card curEvent = EvDeck.remove(0);
        //immediately discard it; no reason to have it in the deck anymore, and no other zone it can be in
        EvDiscard.add(curEvent);
        System.out.print("\nThe current event is: " + curEvent + "\n");
        return curEvent;
    }
}