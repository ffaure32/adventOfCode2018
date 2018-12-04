import model.GuardAction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        List<Action> actionList = new ArrayList<>();
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
}
