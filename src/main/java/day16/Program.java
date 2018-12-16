package day16;

import java.util.List;
import java.util.Map;

public class Program {
    public int[] value;
    private List<int[]> operations;
    private Map<Integer, OpCodeType> combinations;

    public Program(Map<Integer, OpCodeType> combinations, List<int[]> operations) {
        this.value = new int[4];
        this.operations = operations;
        this.combinations = combinations;
    }

    public void execute() {
        for(int[] operation : operations) {
            OpCodeType opCodeType = combinations.get(operation[0]);
            value = opCodeType.apply(operation, value);
        }

    }
}
