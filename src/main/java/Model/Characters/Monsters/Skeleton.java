package Model.Characters.Monsters;/*
 * Varun Parbhakar
 *
 * TCSS-143
 * Heroes VS Monster (Dungeon DLC)
 */

import Model.Characters.Monster;

/**
 * This is a subclass of Monster.
 *
 * @authors Varun Parbhakar, Austin Luu, Yacine Bennour
 * @version 06/07/2022
 * */

public class Skeleton extends Monster {

    /**
     * This constructor initializes all the values
     * for the Skeleton character
     * @param theC_Name (The character name)
     */

    public Skeleton(final String theC_Name) {
        super(theC_Name,
                100,
                3,
                30,
                50,
                .8,
                .3,
                30,
                50);
    }
}
