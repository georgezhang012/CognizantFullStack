import time
import random


def main():
    printStartMessage()
    begining_decision()


def sprint(string):
    print(string)
    time.sleep(1)


def printStartMessage():
    sprint("\nYou find yourself standing in an open field, \
        filled with grass and yellow wildflowers.")
    sprint("Rumor has it that a gorgon is somewhere around here, \
        and has been terrifying the nearby village.")
    sprint("In front of you is a house.")
    sprint("To your right is a dark cave.")
    sprint("In your hand you hold your trusty (but not \
        very effective) dagger.")


def begining_decision():
    sprint("\nEnter 1 to knock on the door of the house.")
    sprint("Enter 2 to peer into the cave.")
    sprint("What would you like to do?")
    sprint("(Please enter 1 or 2.)")

    decision = input()

    if decision == '1':
        knock_on_door()
        knock_on_door_decision()
    if decision == '2':
        peer_into_cave()
    else:
        begining_decision()


def knock_on_door():
    sprint("\nYou approach the door of the house.")
    sprint("You are about to knock when the door opens\
        and out steps a pirate.")
    sprint("Eep! This is the pirate's house!")
    sprint("The pirate attacks you!")
    sprint("You feel a bit under-prepared for this, \
        what with only having a tiny dagger.")


def knock_on_door_decision():
    decision = input("\nWould you like to (1) fight or (2) run away?")
    if decision == '1':
        fight()
    elif decision == '2':
        run_away()
    else:
        knock_on_door_decision()
    retry()


def fight():
    power = random.randint(0, 100)
    if (power < 50):
        sprint("\nYou do your best...")
        sprint("but your dagger is no match for the pirate.")
        sprint("You have been defeated!")
    else:
        sprint("\nAs the troll moves to attack, you unsheath your new sword")
        sprint(
            "The Sword of Ogoroth shines brightly in your hand \
                as you brace yourself for the attack.")
        sprint("But the troll takes one look at your \
            shiny new toy and runs away!")
        sprint("You have rid the town of the troll. \
            You are victorious!")


def run_away():
    sprint("You run back into the field. Luckily, \
        you don't seem to have been followed.")
    begining_decision()


def retry():
    decision = input("\nWould you like to play again? (y/n)")
    if decision == 'y':
        sprint("\nExcellent! Restarting the game ...")
        main()
    if decision == 'n':
        sprint("\nThanks for playing! See you next time.")
        exit()


def peer_into_cave():
    sprint("\nYou peer cautiously into the cave.")

    chance = random.randint(0, 100)

    if (chance > 50):
        sprint("It turns out to be only a very small cave.")
        sprint("Your eye catches a glint of metal behind a rock.")
        sprint("You have found the magical Sword of Ogoroth!")
        sprint("You discard your silly old dagger \
            and take the sword with you.")
        sprint("You walk back out to the field.")
    else:
        sprint(
            "You've been here before, and gotten all \
                the good stuff. It's just an empty cave now.")
        sprint("You walk back out to the field.")
    begining_decision()


main()
