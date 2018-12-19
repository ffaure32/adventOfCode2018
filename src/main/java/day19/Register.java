package day19;

import day16.OpCodeType;

import java.awt.font.OpenType;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static day16.OpCodeType.*;

public class Register {
    private Pattern initPattern = Pattern.compile("#ip ([0-9])");
    private Pattern instructionPattern = Pattern.compile("(.*) ([0-9]+) ([0-9]+) ([0-9]+)");
    public int[] register = new int[6];
    public int initInstructionPointer;
    public int instructionPointer;
    public List<Instruction> instructions;

    public Register(String pointerLine) {
        Matcher matcher = initPattern.matcher(pointerLine);
        if(matcher.find()) {
            this.initInstructionPointer = Integer.parseInt(matcher.group(1));
        } else {
            throw new RuntimeException();
        }
    }

    public Register(int pointer) {
        this.initInstructionPointer = pointer;
    }

    public void initInstructions(List<String> instructionsList) {
        instructions = instructionsList.stream().map(i -> buildInstruction(i)).collect(Collectors.toList());
    }

    private Instruction buildInstruction(String instructionLine) {
        Matcher matcher = instructionPattern.matcher(instructionLine);
        if(matcher.find()) {
            String operation = matcher.group(1);
            int[] values = new int[4];
            values[0] = Integer.parseInt(matcher.group(2));
            values[1] = Integer.parseInt(matcher.group(2));
            values[2] = Integer.parseInt(matcher.group(3));
            values[3] = Integer.parseInt(matcher.group(4));
            return new Instruction(OpCodeType.valueOf(operation.toUpperCase()), values);
        } else {
            throw new RuntimeException();
        }
    }

    public void executeInstructions() {
        this.instructionPointer = 974;
        while(instructionPointer>=0 && instructionPointer<instructions.size()) {
            executeInstruction();
        }
    }

    public void executeInstruction() {
        register[initInstructionPointer] = instructionPointer;
        Instruction instruction = instructions.get(instructionPointer);

        int[] newRegister = instruction.operation.apply(instruction.values, register);

        instructionPointer = newRegister[initInstructionPointer];
        instructionPointer++;
        this.register = newRegister;
    }
}
