import day16.OpCodeType;

import java.util.*;

import static java.util.stream.Collectors.toSet;

public class OpCodeSample {
    int[] inputInt;
    int[] opCode;
    int[] outputInt;

    public void setInput(String input) {
        inputInt = convert(input, ", ");
    }

    public void setOpCode(String op) {
        opCode = convert(op, " ");
    }

    public void setOutput(String output) {
        outputInt = convert(output, ", ");
    }

    public boolean isResolvedByAtLeast3OpCodes() {
        return Arrays.stream(OpCodeType.values())
                .filter(op ->
                        Arrays.equals(op.apply(opCode, inputInt), outputInt))
                .count() >= 3;

    }

    public static int[] convert(String input, String split) {
        Integer[] tempArray = Arrays.stream(input.split(split)).map(s -> Integer.valueOf(s)).toArray(Integer[]::new);
        return Arrays.stream(tempArray).mapToInt(Integer::intValue).toArray();
    }
}
