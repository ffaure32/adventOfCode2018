import com.google.common.base.CharMatcher;
import day12.Pot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DayTwelve {
    Pattern pattern = Pattern.compile("([#|/.]*) => ([#|/.])");

    Set<String> outputPatterns;
    private long rounds;
    private String initialInput;
    private Set<String> notes;
    public DayTwelve(long rounds, String initialInput, Set<String> initNotes) {
        this.outputPatterns = new HashSet<>();
        this.rounds = rounds;
        this.initialInput = initialInput;
        initNotes(initNotes);
    }

    public long computeResult() {
        List<Pot> pots = initializePots();
        long indexPattern = 0;
        for (long i = 0; i < rounds; i++) {
            for (Pot pot: pots) {
                pot.changeState(notes);
            }
            completeList(pots);
            computeState(pots);
            String output = pots.stream().map(p -> String.valueOf(p.plant)).collect(Collectors.joining());
            String outputPattern = CharMatcher.is('.').trimFrom(output);
            if(outputPatterns.contains(outputPattern)) {
                indexPattern = rounds - i -1;
                break;
            } else {
                outputPatterns.add(outputPattern);
            }
        }
        long finalIndexPattern = indexPattern;
        return pots.stream().filter(p -> p.isPlant()).collect(Collectors.summingLong(p -> finalIndexPattern +p.index));

    }

    private List<Pot> initializePots() {
        List<Pot> pots = new ArrayList<>();
        char[] inputChar = initialInput.toCharArray();
        for (int i = 0; i < inputChar.length; i++) {
            Pot pot = new Pot(i, inputChar[i]);
            pots.add(pot);
        }
        completeList(pots);
        computeState(pots);
        return pots;
    }

    private void initNotes(Set<String> validInput) {
        notes = new HashSet<>();
        for(String input : validInput) {
            Matcher matcher = pattern.matcher(input);
            if(matcher.find()) {
                if("#".equals(matcher.group(2))) {
                    notes.add(matcher.group(1));
                }
            }
        }
    }

    private void computeState(List<Pot> pots) {

        int size = pots.size();
        for (int i = 0; i < size; i++) {
            char[] chars = new char[5];
            chars[0] = i>=2 ? pots.get(i-2).plant : '.';
            chars[1] = i>=1 ? pots.get(i-1).plant : '.';
            chars[2] = pots.get(i).plant;
            chars[3] = i< size-2 ? pots.get(i+1).plant : '.';
            chars[4] = i< size-3 ? pots.get(i+2).plant : '.';
            pots.get(i).fixState(String.valueOf(chars));
        }
    }

    private void completeList(List<Pot> pots) {
        if(pots.get(1).isPlant()) {
            pots.add(0, new Pot(pots.get(1).index-2, '.'));
        }
        if(pots.get(0).isPlant()) {
            pots.add(0, new Pot(pots.get(0).index-2, '.'));
        }
        if(pots.get(pots.size()-2).isPlant()) {
            pots.add(new Pot(pots.get(pots.size()-2).index+2, '.'));
        }
        if(pots.get(pots.size()-1).isPlant()) {
            pots.add(new Pot(pots.get(pots.size()-1).index+2, '.'));
        }

    }

}
