package dayseven;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Step {
    private final String stepLetter;
    private final Set<String> children;
    private boolean done = false;

    public Step(String stepLetter) {
        this.stepLetter = stepLetter;
        children = new TreeSet<>();
    }

    public void addChild(String child) {
        children.add(child);
    }

    public String getStepLetter() {
        return stepLetter;
    }

    public Set<String> getChildren() {
        return Collections.unmodifiableSet(children);
    }

    public void markAsDone() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return Objects.equals(stepLetter, step.stepLetter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepLetter);
    }

    @Override
    public String toString() {
        return "Step{" +
                "stepLetter='" + stepLetter + '\'' +
                ", children=" + children +
                ", done=" + done +
                '}';
    }

    boolean isValidStep() {
        return getStepLetter() != "";
    }

    public static Step emptyStep() {
        return new Step("");
    }
}
