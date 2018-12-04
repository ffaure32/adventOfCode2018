import com.google.common.collect.Lists;
import model.GuardAction;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
/*
                GuardAction{dateTime=1518-02-11T00:00, action='Guard #1367 begins shift'}
        GuardAction{dateTime=1518-02-11T00:32, action='falls asleep'}
        GuardAction{dateTime=1518-02-11T00:38, action='wakes up'}
        GuardAction{dateTime=1518-02-11T00:41, action='falls asleep'}
        GuardAction{dateTime=1518-02-11T00:55, action='wakes up'}
        GuardAction{dateTime=1518-02-12T00:00, action='Guard #61 begins shift'}
*/

    }
}
