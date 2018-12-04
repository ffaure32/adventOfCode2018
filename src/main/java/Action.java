import model.GuardAction;

import java.time.LocalTime;

public class Action {
    private final LocalTime time;
    private final ActionType type;

    public enum ActionType {
        WAKEUP,
        ASLEEP;
    }
    public Action(LocalTime time, ActionType type) {
        this.time = time;
        this.type = type;
    }

    public static Action toAction(GuardAction act) {
        LocalTime localTime = act.getDateTime().toLocalTime();
        ActionType type = act.getAction().contains("asleep") ? ActionType.ASLEEP : ActionType.WAKEUP;
        return new Action(localTime, type);
    }

    public boolean fellAsleep() {
        return type == ActionType.ASLEEP;
    }

    public LocalTime getTime() {
        return time;
    }
}
