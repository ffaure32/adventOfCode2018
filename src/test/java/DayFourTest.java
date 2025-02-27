import com.google.common.collect.Lists;
import model.GuardAction;
import model.Pair;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DayFourTest {
    @Test
    public void testOrderByDate() {
        List<String> lines = InputLoader.loadInputList("inputDay4.txt");
        DayFour dayFour = new DayFour(lines);
        dayFour.getActions().stream().forEach(System.out::println);
        assertThat(dayFour.getActions().get(0).getAction()).startsWith("Guard #");

    }

    @Test
    public void testParseDay() {
        List<GuardAction> guardActions = Lists.newArrayList(
                GuardAction.toGuardAction("[1518-02-03 23:50] Guard #661 begins shift"),
                GuardAction.toGuardAction("[1518-02-04 00:01] falls asleep"),
                GuardAction.toGuardAction("[1518-02-04 00:57] wakes up")
        );
        Shift shift = Shift.fromActions(guardActions);

        assertThat(shift.getGuardId()).isEqualTo(661);
        assertThat(shift.getDate()).isEqualTo(LocalDate.parse("1518-02-04"));
        assertThat(shift.getActions()).hasSize(2);

    }


    @Test
    public void sampleAcceptanceTest() {
        List<String> guardActions = Lists.newArrayList(
            "[1518-11-01 00:00] Guard #10 begins shift",
            "[1518-11-01 00:05] falls asleep",
            "[1518-11-01 00:25] wakes up",
            "[1518-11-01 00:30] falls asleep",
            "[1518-11-01 00:55] wakes up",
            "[1518-11-01 23:58] Guard #99 begins shift",
            "[1518-11-02 00:40] falls asleep",
            "[1518-11-02 00:50] wakes up",
            "[1518-11-03 00:05] Guard #10 begins shift",
            "[1518-11-03 00:24] falls asleep",
            "[1518-11-03 00:29] wakes up",
            "[1518-11-04 00:02] Guard #99 begins shift",
            "[1518-11-04 00:36] falls asleep",
            "[1518-11-04 00:46] wakes up",
            "[1518-11-05 00:03] Guard #99 begins shift",
            "[1518-11-05 00:45] falls asleep",
            "[1518-11-05 00:55] wakes up"
        );
        DayFour df = new DayFour(guardActions);
        List<Shift> shifts = df.getShifts();
        assertThat(shifts).hasSize(5);
        int idGuard = df.findMostAsleepGuard();
        assertThat(idGuard).isEqualTo(10);
        int minute = df.findMostAsleepMinuteForGuard(idGuard).first;
        assertThat(minute).isEqualTo(24);
        Pair pair = df.findMostAsleepMinuteForAnyGuard();
        assertThat(pair.first).isEqualTo(99);
        assertThat(pair.second).isEqualTo(45);

    }

    @Test
    public void realAcceptanceTest() {
        List<String> guardActions = InputLoader.loadInputList("inputDay4.txt");
        DayFour df = new DayFour(guardActions);
        List<Shift> shifts = df.getShifts();
        assertThat(shifts).hasSize(286);
        int idGuard = df.findMostAsleepGuard();
        assertThat(idGuard).isEqualTo(2593);
        int minute = df.findMostAsleepMinuteForGuard(idGuard).first;
        assertThat(minute).isEqualTo(40);
        assertThat(idGuard * minute).isEqualTo(103720);
        Pair pair = df.findMostAsleepMinuteForAnyGuard();
        assertThat(pair.first).isEqualTo(3361);
        assertThat(pair.second).isEqualTo(33);
        assertThat(pair.first * pair.second).isEqualTo(110913);

    }

}
