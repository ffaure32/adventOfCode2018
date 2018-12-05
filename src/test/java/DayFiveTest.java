import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DayFiveTest {
    @Test
    public void testSimpleString() {
        Polymer polymer = new Polymer("aA");

        polymer.reaction();

        assertThat(polymer.getUnits()).isEmpty();
    }

    @Test
    public void testDoubleReactString() {
        Polymer polymer = new Polymer("abBA");

        polymer.reaction();

        assertThat(polymer.getUnits()).isEmpty();
    }

    @Test
    public void testNoReactString() {
        Polymer polymer = new Polymer("aabAAB");

        polymer.reaction();

        assertThat(polymer.getUnits()).isEqualTo("aabAAB");
    }

    @Test
    public void testSampleString() {
        Polymer polymer = new Polymer("dabAcCaCBAcCcaDA");

        polymer.reaction();

        assertThat(polymer.getUnits()).isEqualTo("dabCBAcaDA");
    }

    @Test
    public void testRealString() {
        List<String> strings = InputLoader.loadInputList("inputDay5.txt");
        Polymer polymer = new Polymer(strings.get(0));

        polymer.reaction();

        assertThat(polymer.getUnits().length()).isEqualTo(11252);
    }

    @Test
    public void testFirstUnitProblemSample() {
        DayFive df = new DayFive();
        int result = df.findProblemUnit("dabAcCaCBAcCcaDA");
        assertEquals(4, result);
    }

    @Test
    public void testFirstUnitProblemRealString() {
        List<String> strings = InputLoader.loadInputList("inputDay5.txt");
        DayFive df = new DayFive();
        int result = df.findProblemUnit(strings.get(0));
        assertEquals(6118, result);
    }

}
