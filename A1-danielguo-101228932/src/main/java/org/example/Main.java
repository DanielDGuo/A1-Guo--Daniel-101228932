package org.example;

import javax.swing.*;
import java.util.*;

public class Main {
    public Player curPlayer;
    public Scanner inContent = new Scanner(System.in);
    public ArrayList<Card> AdDeck;
    public ArrayList<Card> EvDeck;
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

    public static void main(String[] args) {
        Main game = new Main();
        game.initializeAdventureDeck();
        game.initializeEventDeck();
        game.initializePlayerHands();

        game.curPlayer = game.PlayerList.get(3);
        while (!game.findWinners()) {
            //Start of Beginning Phase
            //get the next player
            game.curPlayer = game.PlayerList.get((game.PlayerList.indexOf(game.curPlayer) + 1) % 4);

            System.out.print("Player " + game.curPlayer + ", this is your hand:\n");
            game.curPlayer.printHand();

            Card curEventCard = game.drawEventCard();

            switch (curEventCard.id) {
                case "Plague" -> game.plagueEffect();
                case "Queen's Favour" -> game.queenEffect();
                case "Prosperity" -> game.prosperityEffect();
                default -> game.questEffect(curEventCard);
            }
            //end of Beginning Phase. Move on to Questing if needed
            if (curEventCard.type.equals("Quest")) {
                Player sponsor = game.seekSponsor(game.inContent);
                //Enter Quest Build
                game.beginQuestBuilding(sponsor, game.inContent);
                ArrayList<ArrayList<Card>> stages = game.beginStageBuilding(sponsor, curEventCard.value, game.inContent);
                game.endStageBuilding(sponsor, stages, game.inContent);
                //Enter Quest Attack. Loop through stages and attacks N times, where N is the quest value
                ArrayList<Player> stageParticipants = new ArrayList<>();
                for (int i = 0; i < curEventCard.value; i++) {
                    //Find participants for the current stage
                    stageParticipants = game.seekParticipants(sponsor, i == 0, game.inContent);
                    game.participantsDrawCard(stageParticipants, game.inContent);
                    ArrayList<ArrayList<Card>> stageAttackTeams = game.createAttackTeams(stageParticipants, game.inContent);
                    ArrayList<Boolean> stageOutcome = game.resolveAttacks(stages.get(i), stageAttackTeams, stageParticipants);
                }
                //After Quest Attack, distribute shields to the winners
                //After Quest attack, Quest enemy cards are discarded. Sponsor draws that many cards + Quest value
            }
            game.endTurn(game.inContent);
        }
        game.printWinners();
    }

    public class Card implements Comparable<Card> {
        //valid ID include but aren't limited to: F5, F10, D, H, S, Q2, Q3, Plague, Queen's favor
        String id;
        //Types include Foe, Weapon, Quest, and Event
        String type;
        //values are the number associated with the card; such as a 5 power foe, or a 30 power excalibur
        int value;

        //constructor
        public Card(String id, String type, int value) {
            this.id = id;
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString() {
            return id;
        }

        @Override
        public int compareTo(Card c) {
            //check if this is a foe and if that is a weapon; foes are displayed first
            if (this.type.equals("Foe") && c.type.equals("Weapon")) {
                return -1;
            } else if ((this.type.equals("Foe") && c.type.equals("Foe")) || (this.type.equals("Weapon") && c.type.equals("Weapon"))) {
                //special case for horses and swords
                if (this.id.equals("H10") && c.id.equals("S10")) {
                    return 1;
                } else if (this.id.equals("S10") && c.id.equals("H10")) {
                    return -1;
                }
                //compare values if they're both foe or weapons
                return this.value - c.value;
            } else if (this.type.equals("Quest") && c.type.equals("Quest")) {
                return this.value - c.value;
            } else if (this.type.equals("Event") && c.type.equals("Event")) {
                return this.id.compareTo(c.id);
            } else if (((this.type.equals("Event") && c.type.equals("Quest"))) || (this.type.equals("Quest") && c.type.equals("Event"))) {
                return this.type.compareTo(c.type);
            }
            //otherwise return a 1
            return 1;
        }
    }

    public class Player {
        int id;
        int shields;
        ArrayList<Card> hand;
        boolean eligible;

        public Player(int id) {
            this.id = id;
            hand = new ArrayList<>();
        }

        public void addShields(int i) {
            shields += i;
        }

        public void addCard(Card c) {
            hand.add(c);
        }

        public void printHand() {
            String outString = "P" + id + " Hand: ";
            Collections.sort(hand);
            for (Card c : hand) {
                outString += c + ", ";
            }
            System.out.print(outString.substring(0, outString.length() - 2));
        }

        @Override
        public String toString() {
            return "P" + id;
        }
    }

    public void initializePlayerHands() {
        for (Player p : PlayerList) {
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
            if (p.shields >= 7) {
                return true;
            }
        }
        return false;
    }

    public void printWinners() {
        String outString = "Player(s) ";
        for (Player p : PlayerList) {
            if (p.shields >= 7) {
                outString += p + ", ";
            }
        }
        System.out.print(outString.substring(0, outString.length() - 2) + " Won.");
    }

    public void drawAdCard(Player p, int num, Scanner inContent) {
        for (int i = 0; i < num; i++) {
            p.addCard(AdDeck.getFirst());
            AdDeck.removeFirst();
        }
        if (p.hand.size() > 12) {
            System.out.print("\n\n\n\n\n\n\n\n\n\n" + p + " is over the max hand size by " + (p.hand.size() - 12) + ". Please give controls to " + p + ", and press enter.\n");
            this.discardAdCard(p, (p.hand.size() - 12), inContent);
        }
    }

    //for testing tests prior to R-CODE-13
    public void drawAdCardNoDiscard(Player p, int num) {
        for (int i = 0; i < num; i++) {
            p.addCard(AdDeck.getFirst());
            AdDeck.removeFirst();
        }
        if (p.hand.size() > 12) {
            System.out.print("\n\n\n\n\n\n\n\n\n\n" + p + " is over the max hand size by " + (p.hand.size() - 12) + ". Please give controls to " + p + ", and press enter.\n");
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
            p.hand.remove((Integer.parseInt(input)) - 1);
            num--;
        }
        System.out.print("Discarding Complete. This is your new hand:\n");
        p.printHand();
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public Card drawEventCard() {
        Card curEvent = EvDeck.removeFirst();
        EvDiscard.add(curEvent);
        System.out.print("\nThe current event is: " + curEvent + "\n");
        return curEvent;
    }

    public void plagueEffect() {
        System.out.print("Plague Drawn. Current player's shields decreased from " + curPlayer.shields);
        curPlayer.addShields(-2);
        //if it's less than 0, set it to 0
        if (curPlayer.shields < 0) {
            curPlayer.addShields(-curPlayer.shields);
        }
        System.out.print(" to " + curPlayer.shields + "\n");
    }

    public void queenEffect() {
        System.out.print("Queen's Favour Drawn. Current player draws 2 cards.\n");
        drawAdCard(curPlayer, 2, inContent);
    }

    //for testing tests prior to R-CODE-13
    public void queenEffectNoDiscard() {
        System.out.print("Queen's Favour Drawn. Current player draws 2 cards.\n");
        drawAdCardNoDiscard(curPlayer, 2);
    }

    public void prosperityEffect() {
        System.out.print("Prosperity Drawn. Each player draws 2 cards.\n");
        for (Player p : PlayerList) {
            drawAdCard(p, 2, inContent);
        }
    }

    //for testing tests prior to R-CODE-13
    public void prosperityEffectNoDiscard() {
        System.out.print("Prosperity Drawn. Each player draws 2 cards.\n");
        for (Player p : PlayerList) {
            drawAdCardNoDiscard(p, 2);
        }
    }

    public void questEffect(Card c) {
        System.out.print("Beginning the effects of a Quest card with " + c.value + " stages.\n");
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
        int curPlayerIndex = curPlayer.id - 1;
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
                System.out.print(PlayerList.get(curPlayerIndex + i) + " has accepted to sponsor this quest.\n");
                return PlayerList.get(curPlayerIndex + i);
            }
        }
        System.out.print("Everybody turned down the sponsor.\n");
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
                //print the relevant information
                String curStageToString = curStage.toString().substring(1, curStage.toString().length() - 1);
                System.out.print("Stage " + (i + 1) + ": " + curStageToString + "\n");
                System.out.print("Please select an index of a card you wish to add to the stage, or press enter to finish.\n");
                sponsor.printHand();
                System.out.print("\n");
                input = inContent.nextLine();
                //valid input is an empty line to go to next stage, or a valid index
                if (!(input.isEmpty() || (1 <= Integer.parseInt(input) && Integer.parseInt(input) <= sponsor.hand.size()))) {
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
                        curStageValue += c.value;
                    }
                    int priorStageValue = 0;
                    if (!stages.isEmpty()) {
                        for (Card c : stages.getLast()) {
                            priorStageValue += c.value;
                        }
                    }
                    if (priorStageValue >= curStageValue) {
                        System.out.print("Current stage must have value greater than the prior stage.\n");
                        input = "temp";
                    }
                } else if (sponsor.hand.get(Integer.parseInt(input) - 1).type.equals("Foe")) {
                    //check if there's already a foe or not
                    boolean hadFoe = false;
                    for (Card card : curStage) {
                        if (card.type.equals("Foe")) {
                            System.out.print("Invalid input. Cannot put two foes in one stage.\n");
                            hadFoe = true;
                            break;
                        }
                    }
                    if (!hadFoe) {
                        //add the card at index and continue. Remove from sponsor hand.
                        curStage.add(sponsor.hand.remove(Integer.parseInt(input) - 1));
                    }
                } else if (sponsor.hand.get(Integer.parseInt(input) - 1).type.equals("Weapon")) {
                    //check if there's already a foe or not
                    boolean hadDupe = false;
                    for (Card card : curStage) {
                        if (card.toString().equals(sponsor.hand.get(Integer.parseInt(input) - 1).toString())) {
                            System.out.print("Invalid input. Duplicate weapon.\n");
                            hadDupe = true;
                            break;
                        }
                    }
                    if (!hadDupe) {
                        //add the card at index and continue. Remove from sponsor hand.
                        curStage.add(sponsor.hand.remove(Integer.parseInt(input) - 1));
                    }
                }
            }
            Collections.sort(curStage);
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
                p.eligible = true;
            }
            sponsor.eligible = false;
        }
        ArrayList<Player> stageParticipants = new ArrayList<>();
        //loop and ask each player for participation
        for (Player p : PlayerList) {
            if (p.eligible) {
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
                    p.eligible = false;
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
                //print the relevant information
                curAttackToString = curAttack.toString().substring(1, curAttack.toString().length() - 1);
                System.out.print("Current Attack Team: " + curAttackToString + "\n");
                System.out.print("Please select an index of a card you wish to add to the attack, or press enter to finish.\n");
                p.printHand();
                System.out.print("\n");
                input = inContent.nextLine();
                //valid input is an empty line to go to end the attack creation, or a valid index
                if (!(input.isEmpty() || (1 <= Integer.parseInt(input) && Integer.parseInt(input) <= p.hand.size()))) {
                    System.out.print("Invalid input. Please provide a valid index or press enter.\n");
                    continue;
                }
                if (!input.isEmpty()) {
                    //non-empty input; get the card and check if its valid
                    //check if it's a weapon
                    if (!p.hand.get(Integer.parseInt(input) - 1).type.equals("Weapon")) {
                        //must be a foe.
                        System.out.print("Invalid input. Cannot add foes to attack team.\n");
                        continue;
                    }
                    //check if it's a dupe
                    boolean hasDupe = false;
                    for (Card c : curAttack) {
                        if (c.toString().equals(p.hand.get(Integer.parseInt(input) - 1).toString())) {
                            hasDupe = true;
                        }
                    }
                    if (hasDupe) {
                        System.out.print("Invalid input. Duplicate weapon.\n");
                        continue;
                    }
                    curAttack.add(p.hand.remove(Integer.parseInt(input) - 1));
                }
            }
            Collections.sort(curAttack);
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
            stageValue += c.value;
        }
        //find and check hands against stage
        for (int i = 0; i < attackTeams.size(); i++) {
            int handValue = 0;
            for (Card c : attackTeams.get(i)) {
                handValue += c.value;
            }
            if (handValue >= stageValue) {
                outcome.add(true);
            } else {
                outcome.add(false);
                participants.get(i).eligible = false;
            }
        }
        //find who won
        String winners = "";
        for(Player p : participants){
            if(p.eligible){
                winners += p + ", ";
            }
        }
        winners = winners.substring(0, winners.length()-2);
        winners += " are the winner(s) and are eligible to continue.\n";
        System.out.print(winners);
        return outcome;
    }

    public boolean findStageSurvivors(ArrayList<Boolean> outcome){
        return false;
    }
}