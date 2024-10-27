Feature: Adventure Game

  #given - when - then
  #if more steps are needed, use and

  Scenario: A1_scenario
    Given a new game starts
    And P1 has a rigged hand of "F5 F5 F15 F15 D5 S10 S10 H10 H10 B15 B15 L20"
    And P2 has a rigged hand of "F5 F5 F15 F15 F40 D5 S10 H10 H10 B15 B15 E30"
    And P3 has a rigged hand of "F5 F5 F5 F15 D5 S10 S10 S10 H10 H10 B15 L20"
    And P4 has a rigged hand of "F5 F15 F15 F40 D5 D5 S10 H10 H10 B15 L20 E30"
    And the adventure deck is rigged to have "F30 S10 B15 F10 L20 L20 B15 S10 F30 L20" at the top
    And the adventure deck has 58 random cards at the bottom

    And P1 draws a "Q4" event card
    And P2 is the sponsor

    And P2 composes stage 1 with "F5 H10"
    And P2 composes stage 2 with "F15 S10"
    And P2 composes stage 3 with "F15 D5 B15"
    And P2 composes stage 4 with "F40 B15"

    And P1 P3 P4 are participants
    And P1 draws 1 card and discards F5
    And P3 draws 1 card and discards F5
    And P4 draws 1 card and discards F5

    And P1 has an attack of D5 S10
    And P3 has an attack of S10 D5
    And P4 has an attack of D5 H10

    And P1 P3 P4 are participants
    And P1 draws 1 card
    And P3 draws 1 card
    And P4 draws 1 card

    And P1 has an attack of H10 S10
    And P3 has an attack of B15 S10
    And P4 has an attack of H10 B15

    And P3 P4 are participants
    And P3 draws 1 card
    And P4 draws 1 card

    And P3 has an attack of L20 H10 S10
    And P4 has an attack of B15 S10 L20

    And P3 P4 are participants
    And P3 draws 1 card
    And P4 draws 1 card

    And P3 has an attack of B15 H10 L20
    And P4 has an attack of D5 S10 L20 E30

    When the quest ends
    And P2 draws 9 cards

    Then the winners should be P4
    And P3 has 0 shields
    And P3 has a hand equal to "F5 F5 F15 F30 S10"
    And P4 has 4 shields
    And P4 has a hand equal to "F15 F15 F40 L20"
    And P1 has a hand equal to "F5 F10 F15 F15 F30"
    And P2 has a hand of 12 cards

  Scenario: 2winner_game_2winner_quest
  Scenario: 1winner_game_with_events
  Scenario: 0_winner_quest