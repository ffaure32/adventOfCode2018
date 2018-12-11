package day11;

public class FuelCell {
    public final int powerLevel;
    public final int x;
    public final int y;
    public static int gridSerialNumber = 1723;
    public FuelCell(int x, int y) {
        this.x = x;
        this.y = y;
        this.powerLevel = powerLevel();
    }

    public int powerLevel() {
        int rackId = x+10;
        long powerLevel = rackId * y;
        powerLevel += gridSerialNumber;
        powerLevel *= rackId;
        int hundreds = (int)(powerLevel/100)%10;
        return hundreds  - 5;

    }
}
