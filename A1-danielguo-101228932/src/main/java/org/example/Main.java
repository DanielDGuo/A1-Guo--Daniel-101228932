package org.example;

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
                //Enter Quest Gather
                //Enter Quest Attack, if Quest Gather was accepted by other players
                //After Quest Attack, distribute shields to the winners
                //After Quest attack, Quest enemy cards are discarded. Sponsor draws that many cards + Quest value
            }
            game.endTurn(game.inContent);
        }
        game.printWinners();
    }

    class Card implements Comparable<Card> {
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

    class Player {
        int id;
        int shields;
        ArrayList<Card> hand;

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

    public void drawAdCard(Player p, int num) {
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
        drawAdCard(curPlayer, 2);
    }

    //for testing tests prior to R-CODE-13
    public void queenEffectNoDiscard() {
        System.out.print("Queen's Favour Drawn. Current player draws 2 cards.\n");
        drawAdCardNoDiscard(curPlayer, 2);
    }

    public void prosperityEffect() {
        System.out.print("Prosperity Drawn. Each player draws 2 cards.\n");
        for (Player p : PlayerList) {
            drawAdCard(p, 2);
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

    public void beginQuestBuilding(Player sponsor, Scanner inContent){
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

    public ArrayList<ArrayList<Card>> begineStageBuilding(Player sponsor, int numStages, Scanner inContent){
        return null;
    }
}