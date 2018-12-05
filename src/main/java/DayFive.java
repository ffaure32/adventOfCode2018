import java.util.Comparator;
import java.util.Optional;
import java.util.stream.IntStream;

public class DayFive {
    public int findProblemUnit(String input) {

        Optional<Integer> shortest = IntStream.rangeClosed('a', 'z')
                .boxed()
                .map(in -> String.valueOf((char) in.intValue()))
                .map(in -> input.replace(in, "").replace(in.toUpperCase(), ""))
                .map(this::getLengthAfterReaction)
                .min(Comparator.naturalOrder());
        return shortest.orElseThrow(IllegalArgumentException::new);
    }

    private int getLengthAfterReaction(String in) {
        Polymer pol = new Polymer(in);
        pol.reaction();
        return pol.getUnits().length();
    }
}
