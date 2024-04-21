import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    public void nullNameException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\n", "\t\t"})
    public void blankNameException(String name) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0, 1.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void negativeSpeedException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("horseName", -1.0, 1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void negativeDistanceException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("horseName", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    void getName() {
        Horse horse = new Horse("horseName", 1.0, 1.0);
        assertEquals("horseName", horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("horseName", 1.0, 1.0);
        assertEquals(1.0, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("horseName", 1.0, 1.0);
        assertEquals(1.0, horse.getDistance());
    }

    @Test
    void move() {
        try(MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            Horse horse = new Horse("Ghost", 1.0,1.0);
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.3);
            Double distance = horse.getDistance() + 0.3 * horse.getSpeed();
            horse.move();
            mocked.verify(() -> Horse.getRandomDouble(0.2,0.9));
            assertEquals(distance, horse.getDistance());
        }
    }
}