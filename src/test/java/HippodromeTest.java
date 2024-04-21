import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    public void nullHorsesException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void emptyHorsesException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }


    @Test
    void getHorses() {
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            horsesList.add(new Horse("Horse " + i, 1.0, 1.0));
        }
        Hippodrome hippodrome = new Hippodrome(horsesList);
        assertEquals(horsesList, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();
        for(Horse mockHorse : horses) {
            verify(mockHorse).move();
        }
    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("Horse 1", 1.0, 2.0);
        Horse horse2 = new Horse("Horse 2", 2.0, 4.0);
        Horse horse3 = new Horse("Horse 3", 3.0, 8.0);
        List<Horse> horses = List.of(horse1, horse2, horse3);
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();
        assertEquals(horse3, winner);
    }
}