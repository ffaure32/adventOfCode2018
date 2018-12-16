import day16.OpCodeType;
import day16.Program;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class DaySixteen {
    public List<OpCodeSample> transformations = new ArrayList<>();
    Map<Integer, Set<OpCodeType>> possibleOpCodeValues = new HashMap<>();

    public void buildOpCodes(List<String> inputList) {
        for (int i = 0; i < inputList.size();) {
            OpCodeSample ds = new OpCodeSample();
            ds.setInput(inputList.get(i++));
            ds.setOpCode(inputList.get(i++));
            ds.setOutput(inputList.get(i++));
            collectCombinations(ds);
            transformations.add(ds);
        }
    }

    public long countCombinationsResolvedByAtLeast3OpCodes() {
        return transformations.stream().filter(op -> op.isResolvedByAtLeast3OpCodes()).count();
    }


    private void collectCombinations(OpCodeSample opCode) {
        Set<OpCodeType> opcodes = Arrays.stream(OpCodeType.values())
                .filter(op ->
                        Arrays.equals(op.apply(opCode.opCode, opCode.inputInt), opCode.outputInt))
                .collect(toSet());
        Set<OpCodeType> opCodeTypes = possibleOpCodeValues.get(opCode.opCode[0]);
        if (opCodeTypes == null) {
            possibleOpCodeValues.put(opCode.opCode[0], opcodes);
        } else {
            opCodeTypes.addAll(opcodes);
        }
    }


    public Map<Integer, OpCodeType> getTypesByCodes() {
        Map<Integer, OpCodeType> result = new HashMap<>();
        while(result.size()< OpCodeType.values().length) {
            possibleOpCodeValues.entrySet()
                    .stream().filter(es -> es.getValue().size() == 1)
                    .forEach(es -> result.put(es.getKey(), es.getValue().iterator().next()));
            possibleOpCodeValues.values()
                    .forEach(set -> set.removeAll(result.values()));
        }
        return result;
    }


    public int[] executeProgram(List<int[]> operations) {
        Program program = new Program(getTypesByCodes(), operations);
        program.execute();
        return program.value;
    }

}
