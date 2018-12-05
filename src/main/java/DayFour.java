import com.google.common.collect.*;
import model.GuardAction;
import model.Pair;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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
            Shift shift = Shift.fromActions(guardActions.subList(guardIndexes.get(i), i == guardIndexes.size() - 1 ? guardActions.size() : guardIndexes.get(i + 1)));
            shifts.add(shift);
        }
        return shifts;
    }

    public int findMostAsleepGuard() {
        Map<Shift, Integer> sleepTimePerShift = shifts.stream().collect(Collectors.toMap(Function.identity(), Shift::asleepTime));
        Map<Integer, Integer> collect = sleepTimePerShift.entrySet().stream().collect(groupingBy(ks -> ks.getKey().getGuardId(), summingInt(Map.Entry::getValue)));
        return collect.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getKey).orElseThrow(IllegalStateException::new);
    }

    public Pair findMostAsleepMinuteForGuard(int idGuard) {
        List<Shift> shiftsForGuard = shifts.stream().filter(sh -> sh.getGuardId() == idGuard).collect(Collectors.toList());
        return findMostAsleepMinuteForShifts(shiftsForGuard);
    }

    public Pair findMostAsleepMinuteForAnyGuard() {
        ListMultimap<Integer, Shift> shiftsPerGuard = ArrayListMultimap.create();
        shifts.forEach(sh -> shiftsPerGuard.put(sh.getGuardId(), sh));
        Map<Integer, Pair> mostAsleepMinutePerGuard = new HashMap<>();
        for (Integer guardId : shiftsPerGuard.keySet()) {
            mostAsleepMinutePerGuard.put(guardId, findMostAsleepMinuteForGuard(guardId));
        }
        return mostAsleepMinutePerGuard.entrySet().stream()
                .max(Comparator.comparing(ks -> ks.getValue().second)).map(ks -> new Pair(ks.getKey(), ks.getValue().first)).orElseThrow(IllegalArgumentException::new);
    }

    private Pair findMostAsleepMinuteForShifts(List<Shift> shiftsForGuard) {
        List<Pair> allRanges = new ArrayList<>();
        shiftsForGuard.forEach(sh -> allRanges.addAll(sh.getSleepIntervals()));
        Map<Integer, AtomicInteger> collect = IntStream.range(0, 60).boxed().collect(Collectors.toMap(Function.identity(), in -> new AtomicInteger(0)));
        for(Pair range : allRanges) {
            for (int i = range.first; i <= range.second ; i++) {
                collect.get(i).addAndGet(1);
            }
        }
        return collect.entrySet().stream().max(Comparator.comparing(ks -> ks.getValue().get())).map(ks -> new Pair(ks.getKey(), ks.getValue().intValue())).orElseThrow(IllegalStateException::new);
    }
}
