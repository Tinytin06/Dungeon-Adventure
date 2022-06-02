package Control;

import Model.Characters.Hero;
import Model.Characters.Heroes.Warrior;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UserInputValidateTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

//    @Test
    @ParameterizedTest
    @ValueSource(strings = {""})
    void heroName() {
        String input = "1.0 1 2 1.0 -2010 -109 Test";
        Scanner scanner = new Scanner(input);

        assertEquals(UserInputValidate.heroName(scanner), "Test");

    }

    @ParameterizedTest
    @ValueSource(strings = {"Test", "tEst", "t!est", "afs9", "joe01!"})
    void heroName1(String input) {
        Scanner scanner = new Scanner(input);

        assertEquals(UserInputValidate.heroName(scanner), input);
    }

    @Test
    void getYN() {
        String input = "y";
        Scanner scanner = new Scanner(input);

        assertTrue(UserInputValidate.getYN(scanner));
    }

    @Test
    void getYN1() {
        String input = "n";
        Scanner scanner = new Scanner(input);

        assertFalse(UserInputValidate.getYN(scanner));
    }

    @ParameterizedTest
    @ValueSource(strings = {"n", "s", "e", "w", "k", "save"})
    void heroDirectionHeading(String direction) {
        Scanner scanner = new Scanner(direction);
        ArrayList<String> availableDirections = new ArrayList(Arrays.asList("n", "e", "s", "w"));
        assertEquals(UserInputValidate.heroDirectionHeading(scanner, availableDirections), direction);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    void attackChoice(String input) {
        Scanner scanner = new Scanner(input);
        Hero hero = new Warrior("test_hero");

        assertEquals(UserInputValidate.attackChoice(hero, scanner), input);
    }

    @Test
    void attackChoice1() {
        String input = "0 4 5 6 7 1";
        Scanner scanner = new Scanner(input);
        Hero hero = new Warrior("test_hero");

        assertEquals(UserInputValidate.attackChoice(hero, scanner), 1);
    }

//    @Test
//    void saveGame() {
//
//    }

    @Test
    void getFileName() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    void getLoadNumber(String input) {
        Scanner scanner = new Scanner(input);

        assertEquals(UserInputValidate.getLoadNumber(scanner, 4), Integer.parseInt(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    void heroSelector(String input) {
        Scanner scanner = new Scanner(input);

        assertEquals(UserInputValidate.heroSelector("test_name", scanner), Integer.parseInt(input));
    }

    @After
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}