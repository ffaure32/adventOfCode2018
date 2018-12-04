import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import model.GuardAction;

import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class DayFour {
    private final List<GuardAction> actions;
    private final List<Shift> shifts;

    public DayFour(List<String> lines) {
        this.actions = lines.stream().map(GuardAction::toGuardAction).sorted(Comparator.comparing(GuardAction::getDateTime)).collect(Collectors.toList());
        this.shifts = buildShifts(actions);
    }
    public List<Shift> getShifts() {
        return shifts;
    }

    public List<GuardAction> getActions() {
        return Collections.unmodifiableList(actions);
    }

    public List<Shift> buildShifts(List<GuardAction> guardActions) {
        List<Integer> guardIndexes = IntStream
                .range(0, guardActions.size())
                .filter(i -> guardActions.get(i).isGuard())
                .boxed()
                .collect(Collectors.toList());

        List<Shift> shifts = new ArrayList<>();
        for (int i = 0; i < guardIndexes.size(); i++) {
            if(i == guardIndexes.size() - 1) {
                Shift shift = Shift.fromActions(guardActions.subList(guardIndexes.get(i), guardActions.size()));
                shifts.add(shift);
            } else {
                Shift shift = Shift.fromActions(guardActions.subList(guardIndexes.get(i), guardIndexes.get(i + 1)));
                shifts.add(shift);
            }
        }
        return shifts;
    }

    public int findMostAsleepGuard() {
        Map<Shift, Integer> sleemTimePerShift = shifts.stream().collect(Collectors.toMap(Function.identity(), sh -> sh.asleepTime()));
        Map<Integer, Integer> collect = sleemTimePerShift.entrySet().stream().collect(groupingBy(ks -> ks.getKey().getGuardId(), summingInt(ks -> ks.getValue())));
        return collect.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).map(ks -> ks.getKey()).orElseThrow(IllegalStateException::new);
    }

    public void findMostAsleepMinuteForGuard(int idGuard) {
        List<Shift> shiftsForGuard = shifts.stream().filter(sh -> sh.getGuardId() == idGuard).collect(Collectors.toList());
        Range r = null;
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        //rangeSet.

        Map<Shift, Integer> sleemTimePerShift = shiftsForGuard.stream().collect(Collectors.toMap(Function.identity(), sh -> sh.asleepTime()));

    }
}
