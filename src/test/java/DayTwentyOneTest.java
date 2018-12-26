import day19.Register;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DayTwentyOneTest {

    @Test
    public void executeAllInstructionsRealInput() {
        List<String> realInput = InputLoader.loadInputList("inputDay21.txt");
        Register register = new Register(realInput.get(0));
        register.initInstructions(realInput.subList(1, realInput.size()-1));
        register.register[1] = 1;
        register.executeInstructions();

        //System.out.println(Arrays.toString(register.countExecutions));
        assertEquals(1464, register.countExecutions);
    }

}
