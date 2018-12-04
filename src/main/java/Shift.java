import model.GuardAction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.util.stream.Collectors.*;

public class Shift {
    private final int guardId;
    private final LocalDate date;
    private final List<Action> actions;

    public Shift(int guardId, LocalDate date, List<Action> actions) {
        this.guardId = guardId;
        this.date = date;
        this.actions = actions;
    }

    public int getGuardId() {
        return guardId;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Action> getActions() {
        return actions;
    }

    static Pattern shiftPattern = Pattern.compile(".* #([0-9]*) .*");

    public static Shift fromActions(List<GuardAction> guardActions) {
        GuardAction guardShift = guardActions.get(0);
        LocalDateTime shiftDate = guardShift.getDateTime();
        LocalDate computedDate = getShiftDate(shiftDate);
        int guardId = getGuardId(guardShift);

        List<GuardAction> actions = guardActions.stream().filter(GuardAction::isAction).collect(toList());
        List<Action> actionList = actions.stream().map(act -> Action.toAction(act)).collect(toList());
        return new Shift(guardId, computedDate, actionList);
    }

    private static LocalDate getShiftDate(LocalDateTime shiftDate) {
        LocalDate computedDate = null;
        if(shiftDate.getHour()>0) {
            computedDate = shiftDate.toLocalDate().plusDays(1);
        } else {
            computedDate = shiftDate.toLocalDate();
        }
        return computedDate;
    }

    private static int getGuardId(GuardAction guardShift) {
        Matcher matcher = shiftPattern.matcher(guardShift.getAction());
        if(matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            throw new IllegalStateException("mauvais format");
        }
    }

    public Integer asleepTime() {
        List<Integer> sleepIndexes = IntStream
                .range(0, actions.size())
                .filter(i -> actions.get(i).fellAsleep())
                .boxed()
                .collect(Collectors.toList());
        int sleepTime = 0;
        for (int i = 0; i < sleepIndexes.size(); i++) {
            sleepTime += MINUTES.between(actions.get(sleepIndexes.get(i)).getTime(), actions.get(sleepIndexes.get(i)+1).getTime());
        }
        return Integer.valueOf(sleepTime);
    }
}
