import day16.OpCodeType;
import day16.Program;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DaySixteenTest {
    @Test
    public void testSample() {
        int[] register = {3, 2, 1, 1};

        int[] result = {3, 2, 2, 1};

        int[] opCodeData = {9, 2, 1, 2};

        assertArrayEquals(result,
        OpCodeType.ADDI.apply(opCodeData, register));
        assertArrayEquals(result,
                OpCodeType.MULR.apply(opCodeData, register));
        assertArrayEquals(result,
                OpCodeType.SETI.apply(opCodeData, register));
    }

    @Test
    public void testRealInput() {
        DaySixteen daySixteen = new DaySixteen();
        List<String> inputList = InputLoader.loadInputList("inputDay16part1.txt");
        daySixteen.buildOpCodes(inputList);
        assertEquals(560, daySixteen.countCombinationsResolvedByAtLeast3OpCodes());

        List<String> inputOperations = InputLoader.loadInputList("inputDay16part2.txt");
        List<int[]> operations = inputOperations.stream().map(s -> OpCodeSample.convert(s, " ")).collect(toList());
        int[] result = daySixteen.executeProgram(operations);
        assertEquals(622, result[0]);

    }


}
