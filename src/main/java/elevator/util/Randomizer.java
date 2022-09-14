package elevator.util;

public class Randomizer {
    public static int getRandomQuantity(int minValue, int maxValue) {
        return (int) ((Math.random() * (maxValue - minValue)) + minValue);
    }
}
