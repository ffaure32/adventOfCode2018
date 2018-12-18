package day18;

import java.util.Arrays;
import java.util.List;

public enum AcreType {

    OPEN('.') {
        @Override
        public AcreType change(List<AcreType> adjacentTypes) {
            if(verifyThreshold(adjacentTypes, TREE, 3)) {
                return TREE;
            }
            return this;
        }
    },
    TREE('|') {
        @Override
        public AcreType change(List<AcreType> adjacentTypes) {
            if(verifyThreshold(adjacentTypes, LUMBERYARD, 3)) {
                return LUMBERYARD;
            }
            return this;
        }
    },
    LUMBERYARD('#') {
        @Override
        public AcreType change(List<AcreType> adjacentTypes) {
            if(verifyThreshold(adjacentTypes, LUMBERYARD, 1)
                && verifyThreshold(adjacentTypes, TREE, 1)) {
                return LUMBERYARD;
            }
            return OPEN;
        }
    };

    public final char typeChar;

    AcreType(char openChar) {
        this.typeChar = openChar;
    }

    public boolean verifyThreshold(List<AcreType> adjacents, AcreType typeToFind, int threshold) {
        return adjacents.stream().filter(t -> typeToFind == t).count() >= threshold;
    }

    public AcreType change(List<AcreType> adjacents) {
        return this;
    }

    public static AcreType from(char c) {
        return Arrays.stream(AcreType.values()).filter(a -> a.typeChar == c).findAny().orElseThrow(RuntimeException::new);
    }
}
