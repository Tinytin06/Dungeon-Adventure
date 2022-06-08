package Model.Characters.Monsters;

import Model.Characters.Monster;

/**
 *
 * DungeonMonster is a template that the monster factory can use
 * to create monsters from the sqlite database
 *
 * @authors Varun Parbhakar, Austin Luu, Yacine Bennour
 * @version 06/07/2022
 */
public class DungeonMonster extends Monster {
    public DungeonMonster(String theC_Name,
                   int theC_Health,
                   int theC_AttackSpeed,
                   int theC_MinDamage,
                   int theC_MaxDamage,
                   double theC_Chance2Attack,
                   double theC_Chance2Heal,
                   int theC_Heal_MinPoints,
                   int theC_Heal_MaxPoints) {

        super(theC_Name,
         theC_Health,
         theC_AttackSpeed,
         theC_MinDamage,
         theC_MaxDamage,
         theC_Chance2Attack,
         theC_Chance2Heal,
         theC_Heal_MinPoints,
         theC_Heal_MaxPoints);
    }
}
