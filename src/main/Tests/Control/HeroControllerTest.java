package Control;

import Model.Characters.Hero;
import Model.Characters.Heroes.Warrior;
import Model.Room;
import Model.RoomType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.management.relation.Role;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class HeroControllerTest {

    @Test
    void moveCharacter() {
        Hero hero = new Warrior("test_hero");

        assertEquals(hero.getCharacterLocation(), new Point(0,0));
        HeroController.moveCharacter(hero, "s");
        assertEquals(hero.getCharacterLocation(), new Point(0,1));
        HeroController.moveCharacter(hero, "e");
        assertEquals(hero.getCharacterLocation(), new Point(1,1));
        HeroController.moveCharacter(hero, "w");
        assertEquals(hero.getCharacterLocation(), new Point(0,1));
        HeroController.moveCharacter(hero, "n");
        assertEquals(hero.getCharacterLocation(), new Point(0,0));
    }

    @ParameterizedTest
    @EnumSource(value = RoomType.class, names = {"INHERITANCE", "ABSTRACTION", "POLYMORPHISM", "ENCAPSULATION", "HEALING", "VISION"})
    void itemPickUp(RoomType roomType) {
        Hero hero = new Warrior("test_hero");

        Room room = new Room(roomType);

        assertTrue(room.hasRoomType(roomType));
        assertFalse(hero.satchelContains(roomType));

        HeroController.itemPickUp(room, hero);

        assertFalse(room.hasRoomType(roomType));
        assertTrue(hero.satchelContains(roomType));

    }
}