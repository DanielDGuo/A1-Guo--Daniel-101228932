Feature: Adventure Game

  Scenario: A1_scenario
    #setup the game state. Rig if necessary, otherwise use random values.
    Given a new game starts
    And the current player is "P1"
    And "P1" has a rigged hand of "F5 F5 F15 F15 D5 S10 S10 H10 H10 B15 B15 L20"
    And "P2" has a rigged hand of "F5 F5 F15 F15 F40 D5 S10 H10 H10 B15 B15 E30"
    And "P3" has a rigged hand of "F5 F5 F5 F15 D5 S10 S10 S10 H10 H10 B15 L20"
    And "P4" has a rigged hand of "F5 F15 F15 F40 D5 D5 S10 H10 H10 B15 L20 E30"
    And the event deck is rigged to have "Q4" on top
    And the event deck has 16 random cards at the bottom
    And the adventure deck is rigged to have "F30 S10 B15 F10 L20 L20 B15 S10 F30 L20" on top
    And the adventure deck has 58 random cards at the bottom

    #begin the event
    When a "Q4" event card is drawn
    #seek a sponsor for the quest. first/second/third/fourth is based on order ASKED, not player order
    And the "second" player asked accepts the sponsor

    #quest composition
    And the sponsor "P2" composes 4 stages that consist of "F5 H10, F15 S10, F15 D5 B15, F40 B15" in order

    #stage 1 participation. Each draw and discard simulates game.participantsDrawCard,
      #however splits it up and uses the more atomic drawCard function.
    And "P1 P3 P4" are participants for stage 1 of the quest sponsored by "P2"
    And "P1" draws 1 card(s) and discards "F5"
    And "P3" draws 1 card(s) and discards "F5"
    And "P4" draws 1 card(s) and discards "F5"

    #stage 1 attack building
    And "P1 P3 P4" build attack teams of "D5 S10, S10 D5, D5 H10"
    And the attacks are resolved and discarded for stage 1

    #stage 1 asserts
    Then "P1 P3 P4" are still eligible

    #stage 2 participation
    And "P1 P3 P4" are participants for stage 2 of the quest sponsored by "P2"
    And "P1" draws 1 card(s)
    And "P3" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 2 attack building
    And "P1 P3 P4" build attack teams of "H10 S10, B15 S10, H10 B15"
    And the attacks are resolved and discarded for stage 2

    #stage 2 asserts
    Then "P1" has a hand equal to "F5 F10 F15 F15 F30 H10 B15 B15 L20"
    And "P1" has 0 shields
    And "P3 P4" are still eligible

    #stage 3 participation
    And "P3 P4" are participants for stage 3 of the quest sponsored by "P2"
    And "P3" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 3 attack building
    And "P3 P4" build attack teams of "L20 H10 S10, B15 S10 L20"
    And the attacks are resolved and discarded for stage 3

    #stage 3 asserts
    Then "P3 P4" are still eligible

    #stage 4 participation
    And "P3 P4" are participants for stage 4 of the quest sponsored by "P2"
    And "P3" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 4 quest building
    And "P3 P4" build attack teams of "B15 H10 L20, D5 S10 L20 E30"
    And the attacks are resolved and discarded for stage 4

    #stage 4 asserts
    Then "P3" has a hand equal to "F5 F5 F15 F30 S10"
    And "P4" has a hand equal to "F15 F15 F40 L20"
    And "P4" are still eligible

    #quest conclusion
    And shields are given out
    And "P2" discards the quest stages

    Then "P2" has a hand of 12 cards
    And "P3" has 0 shields
    And "P4" has 4 shields

  Scenario: 2winner_game_2winner_quest
    #setup the game state
    Given a new game starts
    And the current player is "P1"
    And "P1" has a rigged hand of "F10 F15 F20 F25 F5 F5 F5 F5 F5 F5 F5 F5"
    And "P2" has a rigged hand of "S10 B15 L20 L20 D5 H10 H10 S10 D5 F5 F5 F5"
    And "P3" has a rigged hand of "F5 F10 F15 D5 F5 F5 F5 F5 F5 F5 F5 F5"
    And "P4" has a rigged hand of "H10 B15 L20 S10 H10 D5 S10 H10 S10 D5 F5 F5"
    And the event deck is rigged to have "Q4 Q3" on top
    And the event deck has 15 random cards at the bottom
    And the adventure deck has 50 random cards at the bottom

    #begin the event
    When a "Q4" event card is drawn
    #seek a sponsor for the quest
    And the "first" player asked accepts the sponsor

    #quest composition
    And the sponsor "P1" composes 4 stages that consist of "F10, F15, F20, F25" in order

    #stage 1 participation.
    And "P2 P3 P4" are participants for stage 1 of the quest sponsored by "P1"
    And "P2" draws 1 card(s) and discards "F5"
    And "P3" draws 1 card(s) and discards "F5"
    And "P4" draws 1 card(s) and discards "F5"

    #stage 1 attack building
    And "P2 P3 P4" build attack teams of "S10, D5, H10"
    And the attacks are resolved and discarded for stage 1

    #stage 1 asserts
    Then "P3" has 0 shields
    And "P3" are ineligible
    And "P2 P4" are still eligible

    #stage 2 participation
    And "P2 P4" are participants for stage 2 of the quest sponsored by "P1"
    And "P2" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 2 attack building
    And "P2 P4" build attack teams of "B15, B15"
    And the attacks are resolved and discarded for stage 2

    #stage 2 asserts
    Then "P2 P4" are still eligible

    #stage 3 participation
    And "P2 P4" are participants for stage 3 of the quest sponsored by "P1"
    And "P2" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 3 attack building
    And "P2 P4" build attack teams of "L20, L20"
    And the attacks are resolved and discarded for stage 3

    #stage 3 asserts
    Then "P2 P4" are still eligible

    #stage 4 participation
    And "P2 P4" are participants for stage 4 of the quest sponsored by "P1"
    And "P2" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 4 quest building
    And "P2 P4" build attack teams of "L20 D5, S10 H10 D5"
    And the attacks are resolved and discarded for stage 4

    #stage 4 asserts
    Then "P2 P4" are still eligible

    #first quest completion
    And shields are given out
    And "P1" discards the quest stages
    And the turn ends

    #quest 1 asserts
    Then "P2" has 4 shields
    And "P4" has 4 shields

    #Quest 1 complete, begin Quest 2
    And the current player is "P2"
    And a "Q3" event card is drawn
    And the "second" player asked accepts the sponsor

    #quest composition
    And the sponsor "P3" composes 3 stages that consist of "F5, F10, F15" in order

    #stage 1 participation.
    And "P2 P4" are participants for stage 1 of the quest sponsored by "P3"
    And "P2" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 1 attack building
    And "P2 P4" build attack teams of "H10, S10"
    And the attacks are resolved and discarded for stage 1

    #stage 1 asserts
    Then "P1" are ineligible
    And "P2 P4" are still eligible

    #stage 2 participation
    And "P2 P4" are participants for stage 2 of the quest sponsored by "P3"
    And "P2" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 2 attack building
    And "P2 P4" build attack teams of "H10, H10"
    And the attacks are resolved and discarded for stage 2

    #stage 2 asserts
    Then "P2 P4" are still eligible

    #stage 3 participation
    And "P2 P4" are participants for stage 3 of the quest sponsored by "P3"
    And "P2" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 3 attack building
    And "P2 P4" build attack teams of "S10 D5, S10 D5"
    And the attacks are resolved and discarded for stage 3

    #stage 3 asserts
    Then "P2 P4" are still eligible

    #quest 2 conclusion
    And shields are given out
    And "P3" discards the quest stages

    Then "P2" has 7 shields
    And "P4" has 7 shields
    And "P2 P4" are winners

  Scenario: 1winner_game_with_events
    Given a new game starts
    And the current player is "P1"
    And "P1" has a rigged hand of "F5 F10 F15 F20 F5 F5 F5 F5 F5 F5 F5 F5"
    And "P2" has a rigged hand of "D5 S10 H10 D5 L20 F5 F5 F5 F5 F5 F5 F5"
    And "P3" has a rigged hand of "D5 H10 S10 D5 L20 F5 F5 F5 F5 F5 F5 F5"
    And "P4" has a rigged hand of "D5 S10 H10 D5 L20 F20 F20 F5 F5 F5 F5 F5"
    And the event deck is rigged to have "Q4 Plague Prosperity Queen's_Favour Q3" on top
    And the event deck has 12 random cards at the bottom
    And the adventure deck is rigged to have "S10 S10 D5 B15 B15 F5 H10 L20 F5 S10 F5 F10 F10 F15 F20 F10 F15 F20 F10 F15 F15 F40" on top
    And the adventure deck has 50 random cards at the bottom

    #begin the event
    When a "Q4" event card is drawn
    #seek a sponsor for the quest
    And the "first" player asked accepts the sponsor

    #quest composition
    And the sponsor "P1" composes 4 stages that consist of "F5, F10, F15, F20" in order

    #stage 1 participation.
    And "P2 P3 P4" are participants for stage 1 of the quest sponsored by "P1"
    And "P2" draws 1 card(s) and discards "F5"
    And "P3" draws 1 card(s) and discards "F5"
    And "P4" draws 1 card(s) and discards "F5"

    #stage 1 attack building
    And "P2 P3 P4" build attack teams of "D5, D5, D5"
    And the attacks are resolved and discarded for stage 1

    #stage 1 asserts
    Then "P2 P3 P4" are still eligible

    #stage 2 participation
    And "P2 P3 P4" are participants for stage 2 of the quest sponsored by "P1"
    And "P2" draws 1 card(s)
    And "P3" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 2 attack building
    And "P2 P3 P4" build attack teams of "S10, H10, S10"
    And the attacks are resolved and discarded for stage 2

    #stage 2 asserts
    Then "P2 P3 P4" are still eligible

    #stage 3 participation
    And "P2 P3 P4" are participants for stage 3 of the quest sponsored by "P1"
    And "P2" draws 1 card(s)
    And "P3" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 3 attack building
    And "P2 P3 P4" build attack teams of "H10 D5, S10 D5, H10 D5"
    And the attacks are resolved and discarded for stage 3

    #stage 3 asserts
    Then "P2 P3 P4" are still eligible

    #stage 4 participation
    And "P2 P3 P4" are participants for stage 4 of the quest sponsored by "P1"
    And "P2" draws 1 card(s)
    And "P3" draws 1 card(s)
    And "P4" draws 1 card(s)

    #stage 4 quest building
    And "P2 P3 P4" build attack teams of "L20, L20, L20"
    And the attacks are resolved and discarded for stage 4

    #stage 4 asserts
    Then "P2 P3 P4" are still eligible

    #first quest completion
    And shields are given out
    #draws 8 cards, discards the first card until hand size is hit. Since F5 is the lowest value card, it is prioritized
    And "P1" discards the quest stages

    Then "P1" has a hand of 12 cards
    And "P2" has a hand of 10 cards
    And "P3" has a hand of 10 cards
    And "P4" has a hand of 10 cards

    And "P1" has 0 shields
    And "P2" has 4 shields
    And "P3" has 4 shields
    And "P4" has 4 shields
    And the turn ends

    #first event
    And the current player is "P2"
    And a "Plague" event card is drawn
    And "Plague" takes effect
    Then "P1" has 0 shields
    And "P2" has 2 shields
    And "P3" has 4 shields
    And "P4" has 4 shields
    And the turn ends

    #second event; could use "a "Prosperity" event card is drawn" but discards are required
    And the current player is "P3"
    And a "Prosperity" event card is drawn
    And "P1" draws 2 card(s) and discards "F5 F5"
    And "P2" draws 2 card(s)
    And "P3" draws 2 card(s)
    And "P4" draws 2 card(s)

    Then "P1" has a hand of 12 cards
    And "P2" has a hand of 12 cards
    And "P3" has a hand of 12 cards
    And "P4" has a hand of 12 cards
    And the turn ends

    #third event; could use "a "Queen's_Favour" event card is drawn" but discards are required
    And the current player is "P4"
    And a "Queen's_Favour" event card is drawn
    And "P4" draws 2 card(s) and discards "F5 F5"
    Then "P1" has a hand of 12 cards
    And "P2" has a hand of 12 cards
    And "P3" has a hand of 12 cards
    And "P4" has a hand of 12 cards
    And the turn ends

    #second quest
    And the current player is "P1"

    #begin the event
    And a "Q3" event card is drawn
    #seek a sponsor for the quest
    And the "first" player asked accepts the sponsor

    #quest composition
    And the sponsor "P1" composes 3 stages that consist of "F10, F15, F20" in order

    #stage 1 participation.
    And "P2 P3 P4" are participants for stage 1 of the quest sponsored by "P1"
    And "P2" draws 1 card(s) and discards "F5"
    And "P3" draws 1 card(s) and discards "F5"
    And "P4" draws 1 card(s) and discards "F5"

    #stage 1 attack building
    And "P2 P3 P4" build attack teams of "S10, S10, D5"
    And the attacks are resolved and discarded for stage 1

    #stage 1 asserts
    Then "P2 P3" are still eligible

    #stage 2 participation
    And "P2 P3" are participants for stage 2 of the quest sponsored by "P1"
    And "P2" draws 1 card(s)
    And "P3" draws 1 card(s)

    #stage 2 attack building
    And "P2 P3" build attack teams of "B15, B15"
    And the attacks are resolved and discarded for stage 2

    #stage 2 asserts
    Then "P2 P3" are still eligible

    #stage 3 participation
    And "P2 P3" are participants for stage 3 of the quest sponsored by "P1"
    And "P2" draws 1 card(s)
    And "P3" draws 1 card(s)

    #stage 3 attack building
    And "P2 P3" build attack teams of "H10 S10, L20"
    And the attacks are resolved and discarded for stage 3

    #stage 3 asserts
    Then "P2 P3" are still eligible

    And shields are given out
    And "P1" discards the quest stages

    Then "P1" has 0 shields
    And "P2" has 5 shields
    And "P3" has 7 shields
    And "P4" has 4 shields
    And "P3" are winners

  Scenario: 0_winner_quest
    Given a new game starts
    And the current player is "P1"
    And "P1" has a rigged hand of "F5 F5 F15 F15 D5 S10 S10 H10 H10 B15 F25 F30"
    And "P2" has a rigged hand of "F5 F5 F15 F15 F40 D5 S10 H10 H10 B15 B15 E30"
    And "P3" has a rigged hand of "F5 F5 F5 F15 D5 S10 S10 S10 H10 H10 B15 L20"
    And "P4" has a rigged hand of "F5 F15 F15 F40 D5 D5 S10 H10 H10 B15 L20 E30"
    And the event deck is rigged to have "Q2" on top
    And the event deck has 16 random cards at the bottom
    And the adventure deck has 58 random cards at the bottom

    #begin the event
    When a "Q2" event card is drawn
    #seek a sponsor for the quest. first/second/third/fourth is based on order ASKED, not player order
    And the "first" player asked accepts the sponsor

    #quest composition
    And the sponsor "P1" composes 2 stages that consist of "F25, F30" in order

    #stage 1 participation. Each draw and discard simulates game.participantsDrawCard,
      #however splits it up and uses the more atomic drawCard function.
    And "P2 P3 P4" are participants for stage 1 of the quest sponsored by "P1"
    And "P2" draws 1 card(s) and discards "F5"
    And "P3" draws 1 card(s) and discards "F5"
    And "P4" draws 1 card(s) and discards "F5"

    #stage 1 attack building
    And "P2 P3 P4" build attack teams of "D5, D5, D5"
    And the attacks are resolved and discarded for stage 1

    #stage 1 asserts
    Then "P2 P3 P4" are ineligible

    #quest conclusion
    And shields are given out
    And "P1" discards the quest stages

    Then "P1" has a hand of 12 cards
    And "P2" has a hand of 11 cards
    And "P3" has a hand of 11 cards
    And "P4" has a hand of 11 cards

    And "P1" has 0 shields
    And "P2" has 0 shields
    And "P3" has 0 shields
    And "P4" has 0 shields