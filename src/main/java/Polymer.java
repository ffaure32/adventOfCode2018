import com.google.common.primitives.Chars;

import java.util.*;
import java.util.stream.Collectors;

public class Polymer {
    private String units;

    public Polymer(String units) {
        this.units = units;
    }

    public String getUnits() {
        return units;
    }

    public void reaction() {
        boolean reductionRealized;
        do {
            reductionRealized = reductionAttempt();
        } while(reductionRealized);
    }

    private boolean reductionAttempt() {
        int initSize = this.units.length();
        this.units = reduction();
        return initSize != units.length();
    }

    private String reduction() {
        StringBuilder polymerAfterReaction = new StringBuilder(units.length());
        char[] unitChar = units.toCharArray();
        int length = unitChar.length;
        for (int i = 0; i < length; i++) {
            if(i< length -1 && areReacting(unitChar[i], unitChar[i+1])) {
                i++;
            } else {
                polymerAfterReaction.append(unitChar[i]);
            }
        }
        return polymerAfterReaction.toString();
    }

    public static final int DIFF_MAJ_MIN = 'A' - 'a';
    public boolean areReacting(char firstUnit, char secondUnit) {
        int diffChars = firstUnit - secondUnit;
        if(diffChars == DIFF_MAJ_MIN || diffChars == -DIFF_MAJ_MIN) {
            return true;
        }
        return false;
    }

}
