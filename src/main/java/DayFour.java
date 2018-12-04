import model.GuardAction;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayFour {
    private final List<GuardAction> actions;

    public DayFour(List<String> lines) {
        this.actions = lines.stream().map(GuardAction::toGuardAction).sorted(Comparator.comparing(GuardAction::getDateTime)).collect(Collectors.toList());
    }

    public List<GuardAction> getActions() {
        return Collections.unmodifiableList(actions);
    }
}
