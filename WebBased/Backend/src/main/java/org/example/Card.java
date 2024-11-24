package org.example;

public class Card implements Comparable<Card> {
    //valid ID include but aren't limited to: F5, F10, D, H, S, Q2, Q3, Plague, Queen's favour
    private String id;
    //Types include Foe, Weapon, Quest, and Event
    private String type;
    //values are the number associated with the card; such as a 5 power foe, or a 30 power excalibur
    private int value;

    //constructor
    public Card(String id, String type, int value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    //copy constructor
    public Card(Card c) {
        this.id = c.id;
        this.type = c.type;
        this.value = c.value;
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

    public String getId(){
        return id;
    }

    public String getType(){
        return type;
    }

    public int getValue(){
        return value;
    }
}
