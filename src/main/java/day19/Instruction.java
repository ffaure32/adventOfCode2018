package day19;

import day16.OpCodeType;

public class Instruction {
    public final OpCodeType operation;
    public final int[] values;

    public Instruction(OpCodeType valueOf, int[] values) {
        this.operation = valueOf;
        this.values = values;
    }
}
