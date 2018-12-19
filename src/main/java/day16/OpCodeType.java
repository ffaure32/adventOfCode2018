package day16;

import java.util.Arrays;

public enum OpCodeType {
    ADDR {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int registerA = opCodeInfo[1];
            int registerB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = output[registerA]+output[registerB];
            return output;
        }
    },
    ADDI {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int registerA = opCodeInfo[1];
            int valueB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = output[registerA]+valueB;
            return output;
        }
    },
    MULR {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int registerA = opCodeInfo[1];
            int registerB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = output[registerA]*output[registerB];
            return output;
        }
    },
    MULI {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int registerA = opCodeInfo[1];
            int valueB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = output[registerA]*valueB;
            return output;
        }
    },
    BANR {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int registerA = opCodeInfo[1];
            int registerB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = output[registerA]&output[registerB];
            return output;
        }
    },
    BANI {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int registerA = opCodeInfo[1];
            int valueB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = output[registerA] & valueB;
            return output;
        }
    },
    BORR {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int registerA = opCodeInfo[1];
            int registerB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = output[registerA]|output[registerB];
            return output;
        }
    },
    BORRI {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int registerA = opCodeInfo[1];
            int valueB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = output[registerA]|valueB;
            return output;
        }
    },
    SETR {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int registerA = opCodeInfo[1];
            int registerC = opCodeInfo[3];
            output[registerC] = output[registerA];
            return output;
        }
    },
    SETI {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int valueA = opCodeInfo[1];
            int registerC = opCodeInfo[3];
            output[registerC] = valueA;
            return output;
        }
    },
    GTIR {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int valueA = opCodeInfo[1];
            int registerB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = valueA>output[registerB] ? 1 : 0;
            return output;
        }
    },
    GTRI {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int registerA = opCodeInfo[1];
            int valueB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = output[registerA]>valueB ? 1 : 0;
            return output;
        }
    },
    GTRR {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int registerA = opCodeInfo[1];
            int registerB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = output[registerA]>output[registerB] ? 1 : 0;
            return output;
        }
    },
    EQIR {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int valueA = opCodeInfo[1];
            int registerB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = valueA==output[registerB] ? 1 : 0;
            return output;
        }
    },
    EQRI { // 11
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int registerA = opCodeInfo[1];
            int valueB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = output[registerA]==valueB ? 1 : 0;
            return output;
        }
    },
    EQRR {
        @Override
        public int[] apply(int[] opCodeInfo, int[] input) {
            int[] output = Arrays.copyOf(input, input.length);
            int registerA = opCodeInfo[1];
            int registerB = opCodeInfo[2];
            int registerC = opCodeInfo[3];
            output[registerC] = output[registerA]==output[registerB] ? 1 : 0;
            return output;
        }
    };

    public abstract int[] apply(int[] opCodeInfo, int[]input);
}
