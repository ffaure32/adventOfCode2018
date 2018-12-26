import day19.Register;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import sun.java2d.pipe.RegionIterator;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DayNineteenTest {

    private List<String> sampleInstructions;

    @Before
    public void setUp() throws Exception {
        sampleInstructions = Lists.newArrayList(
                "seti 5 0 1",
                "seti 6 0 2",
                "addi 0 1 0",
                "addr 1 2 3",
                "setr 1 0 0",
                "seti 8 0 4",
                "seti 9 0 5"
        );
    }

    @Test
    public void initRegister() {
        String firsLine = "#ip 2";

        Register register = new Register(firsLine);

        assertEquals(2, register.initInstructionPointer);
    }

    @Test
    public void executeFirstInstruction() {
        Register register = new Register(0);
        register.initInstructions(sampleInstructions);

        register.executeInstruction();
        assertArrayEquals(new int[]{0, 5, 0, 0, 0, 0}, register.register);
    }

    @Test
    public void execute2Instructions() {
        Register register = new Register(0);
        register.initInstructions(sampleInstructions);

        register.executeInstruction();
        register.executeInstruction();

        assertArrayEquals(new int[]{1, 5, 6, 0, 0, 0}, register.register);
    }

    @Test
    public void execute3Instructions() {
        Register register = new Register(0);
        register.initInstructions(sampleInstructions);

        register.executeInstruction();
        register.executeInstruction();
        register.executeInstruction();

        assertArrayEquals(new int[]{3, 5, 6, 0, 0, 0}, register.register);
    }

    @Test
    public void execute4Instructions() {
        Register register = new Register(0);
        register.initInstructions(sampleInstructions);

        register.executeInstruction();
        register.executeInstruction();
        register.executeInstruction();
        register.executeInstruction();

        assertArrayEquals(new int[]{5, 5, 6, 0, 0, 0}, register.register);
    }

    @Test
    public void execute5Instructions() {
        Register register = new Register(0);
        register.initInstructions(sampleInstructions);

        register.executeInstruction();
        register.executeInstruction();
        register.executeInstruction();
        register.executeInstruction();
        register.executeInstruction();

        assertArrayEquals(new int[]{6, 5, 6, 0, 0, 9}, register.register);
    }

    @Test
    public void executeAllInstructions() {
        Register register = new Register(0);
        register.initInstructions(sampleInstructions);

        register.executeInstructions();

        assertArrayEquals(new int[]{6, 5, 6, 0, 0, 9}, register.register);
    }

    @Test
    public void executeAllInstructionsRealInput() {
        List<String> realInput = InputLoader.loadInputList("inputDay19.txt");
        Register register = new Register(realInput.get(0));
        register.initInstructions(realInput.subList(1, realInput.size()-1));

        register.executeInstructions();

        System.out.println(Arrays.toString(register.register));
        assertEquals(1464, register.register[0]);
    }

    @Test
    public void executeAllInstructionsRealInputPart2() {
        List<String> realInput = InputLoader.loadInputList("inputDay19.txt");
        Register register = new Register(realInput.get(0));
        register.initInstructions(realInput.subList(1, realInput.size()));
        register.register[1] = 1;
        register.executeInstructions();

        System.out.println(Arrays.toString(register.register));
        assertEquals(1464, register.register[1]);
    }

}
