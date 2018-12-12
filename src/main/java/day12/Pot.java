package day12;

import java.util.Set;

public class Pot {
    public final int index;
    public char plant;
    public String state;

    public void fixState(String state) {
        this.state = state;
    }

    public char toChar() {
        return plant;
    }

    public Pot(int index, char initValue) {
        this.index = index;
        plant = initValue;

    }

    public void changeState(Set<String> notes) {
        boolean isPlant = notes.stream().filter(n -> n.equals(state)).findAny().isPresent();
        plant = isPlant ? '#' : '.';
    }

    public boolean isPlant() {
        return plant == '#';
    }

}
