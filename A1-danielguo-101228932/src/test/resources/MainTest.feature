Feature: Adventure Game

  Scenario: A1_scenario
    #setup the game state. Rig if necessary, otherwise use random values.
    Given a new game starts
    And "P1" has a rigged hand of "F5 F5 F15 F15 D5 S10 S10 H10 H10 B15 B15 L20"
    And "P2" has a rigged hand of "F5 F5 F15 F15 F40 D5 S10 H10 H10 B15 B15 E30"
    And "P3" has a rigged hand of "F5 F5 F5 F15 D5 S10 S10 S10 H10 H10 B15 L20"
    And "P4" has a rigged hand of "F5 F15 F15 F40 D5 D5 S10 H10 H10 B15 L20 E30"
    And the event deck is rigged to have "Q4" on top
    And the event deck has 16 random cards at the bottom
    And the adventure deck is rigged to have "F30 S10 B15 F10 L20 L20 B15 S10 F30 L20" on top
    And the adventure deck has 58 random cards at the bottom

    #begin the event
    And an event card is drawn
    #seek a sponsor for the quest. first/second/third/fourth is based on order ASKED, not player order
    And The "second" player asked accepts the sponsor

    #quest composition
    And the sponsor "P2" composes 4 stages that consist of "F5 H10, F15 S10, F15 D5 B15, F40 B15" in order

    #stage 1 participation. Each draw and discard simulates game.participantsDrawCard,
      #however splits it up and uses the more atomic drawCard function.
    And "P1, P3, P4" are participants for stage 1 of the quest sponsored by "P2"
    And "P1" draws 1 card(s) and discards "F5"
    And "P3" draws 1 card(s) and discards "F5"
    And "P4" draws 1 card(s) and discards "F5"

    #stage 1 attack building
    And "P1" builds an attack of D5 S10
    And "P3" builds an attack of S10 D5
    And "P4" builds an attack of D5 H10

    #stage 1 asserts
    And "P1" has a hand equal to "F5 F10 F15 F15 F30 H10 B15 B15 L20"
    And "P1" has 0 shields

    #stage 2 participation
    And "P1, P3, P4" are participants for stage 2 of the quest sponsored by "P2"
    And "P1" draws 1 card(s)
    And "P3" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 2 attack building
    And "P1" builds an attack of H10 S10
    And "P3" builds an attack of B15 S10
    And "P4" builds an attack of H10 B15

    #stage 3 participation
    And "P3, P4" are participants for stage 3 of the quest sponsored by "P2"
    And "P3" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 3 attack building
    And "P3" builds an attack of L20 H10 S10
    And "P4" builds an attack of B15 S10 L20

    #stage 4 participation
    And "P3, P4" are participants for stage 4 of the quest sponsored by "P2"
    And "P3" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 4 quest building
    And "P3" builds an attack of B15 H10 L20
    And "P4" builds an attack of D5 S10 L20 E30

    #stage 4 asserts
    And "P3" has 0 shields
    And "P3" has a hand equal to "F5 F5 F15 F30 S10"
    And "P4" has 4 shields
    And "P4" has a hand equal to "F15 F15 F40 L20"

    #quest conclusion
    And "P2" draws 9 card(s)

    #quest conclusion
    When the quest fully concludes
    Then the winner(s) should be "P4"
    And "P2" has a hand of 12 cards

  Scenario: 2winner_game_2winner_quest
  Scenario: 1winner_game_with_events
  Scenario: 0_winner_quest