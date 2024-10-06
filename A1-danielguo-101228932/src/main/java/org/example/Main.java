package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Main {
    public Player curPlayer;
    public ArrayList<Card> AdDeck;
    public ArrayList<Card> EvDeck;
    public ArrayList<Card> EvDiscard = new ArrayList<Card>();
    public ArrayList<Player> PlayerList = new ArrayList<Player>(
            Arrays.asList(
                    new Player(1),
                    new Player(2),
                    new Player(3),
                    new Player(4)
            )
    );
    //list of all 100 cards included in the deck
    public final ArrayList<Card> AdventureDeckList = new ArrayList<Card>(
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
    public final ArrayList<Card> EventDeckList = new ArrayList<Card>(
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
            //get the next player
            game.curPlayer = game.PlayerList.get((game.PlayerList.indexOf(game.curPlayer) + 1) % 4);
            System.out.print("Player " + game.curPlayer + ", this is your hand:\n");
            System.out.print(game.curPlayer.printHand() + "\n");
            System.out.print("The current event is: \n");
            Card curEventCard = game.drawEventCard();
            System.out.print(curEventCard + "\n");

            
            if(curEventCard.id.equals("Plague")){
                System.out.print("Plague Drawn. Current player's shields decreased from " + game.curPlayer.getShields());
                game.plagueEffect();
                System.out.print(" to " + game.curPlayer.getShields() + "\n");
            }else if(curEventCard.id.equals("Queen's Favour")){
                System.out.print("Queen's Favour Drawn. Current player's shields increased from " + game.curPlayer.getShields());
                game.queenEffect();
                System.out.print(" to " + game.curPlayer.getShields() + "\n");
            }
        }
        System.out.print(game.printWinners());

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

        public String getId() {
            return id;
        }

        public int getValue() {
            return value;
        }

        public String getType() {
            return type;
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
            hand = new ArrayList<Card>();
        }

        public ArrayList<Card> getHand() {
            return hand;
        }

        public int getShields() {
            return shields;
        }

        public void addShields(int i) {
            shields += i;
        }

        public void addCard(Card c) {
            hand.add(c);
        }

        public String printHand() {
            String outString = "P" + id + " Hand: ";
            Collections.sort(hand);
            for (Card c : hand) {
                outString += c + ", ";
            }
            return outString.substring(0, outString.length() - 2);
        }

        @Override
        public String toString() {
            return "P" + id;
        }
    }

    public void initializePlayerHands() {
        for (Player p : PlayerList) {
            for (int i = 0; i < 12; i++) {
                p.addCard(AdDeck.getFirst());
                AdDeck.removeFirst();
            }
        }
    }

    public void initializeAdventureDeck() {
        AdDeck = new ArrayList<>(AdventureDeckList);
        Collections.shuffle(AdDeck);
    }

    public int getAdventureDeckSize() {
        return AdDeck.size();
    }

    public ArrayList<Card> getAdventureDeckCards() {
        return AdDeck;
    }

    public void initializeEventDeck() {
        EvDeck = new ArrayList<>(EventDeckList);
        Collections.shuffle(EvDeck);
    }

    public ArrayList<Card> getEventDeckCards() {
        return EvDeck;
    }

    public int getEventDeckSize() {
        return EvDeck.size();
    }

    public boolean findWinners() {
        for (Player p : PlayerList) {
            if (p.getShields() >= 7) {
                return true;
            }
        }
        return false;
    }

    public String printWinners() {
        String outString = "Player(s) ";
        for (Player p : PlayerList) {
            if (p.getShields() >= 7) {
                outString += p.toString() + ", ";
            }
        }
        return outString.substring(0, outString.length() - 2) + " Won.";
    }

    public Card drawEventCard() {
        Card curEvent = EvDeck.removeFirst();
        EvDiscard.add(curEvent);
        return curEvent;
    }

    public void plagueEffect() {
        curPlayer.addShields(-2);
        //if it's less than 0, set it to 0
        if (curPlayer.getShields() < 0) {
            curPlayer.addShields(-curPlayer.getShields());
        }
    }

    public void queenEffect() {
        curPlayer.addShields(2);
    }
}