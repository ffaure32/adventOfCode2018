package day11;

import org.junit.Test;

import static org.junit.Assert.*;

public class FuelCellTest {

    @Test
    public void testSample1() {
        FuelCell fc = new FuelCell(122, 79);
        fc.gridSerialNumber = 57;

        assertEquals(-5, fc.powerLevel());
    }

    @Test
    public void testSample2() {
        FuelCell fc = new FuelCell(217, 196);
        fc.gridSerialNumber = 39;

        assertEquals(0, fc.powerLevel());
    }

    @Test
    public void testSample3() {
        FuelCell fc = new FuelCell(101, 153);
        fc.gridSerialNumber = 71;

        assertEquals(4, fc.powerLevel());
    }

}