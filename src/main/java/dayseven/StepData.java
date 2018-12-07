package dayseven;

import java.util.*;
import java.util.stream.Collectors;

public class StepData {
    private final Map<String, Step> stepsByLetter;
    private final List<Step> validatedSteps;
    private Set<String> nextAvailableSteps;

    public StepData(Map<String, Step> stepsByLetter) {
        this.stepsByLetter = stepsByLetter;
        validatedSteps = new ArrayList<>();
        nextAvailableSteps = new TreeSet<>();
        initAvailableSteps();
    }

    public Set<String> getNextAvailableSteps() {
        return nextAvailableSteps;
    }

    public Step nextStep() {
        Step step = nextChildStep();
        doStep(step);
        return step;
    }

    public void doStep(Step step) {
        validatedSteps.add(step);
        nextAvailableSteps.addAll(step.getChildren());
        step.markAsDone();
    }

    public Step nextChildStep() {
        String nextLetter = nextAvailableSteps.stream()
                .filter(this::allParentsDone).findFirst().orElse("");
        Step step = stepsByLetter.get(nextLetter);
        if(step != null) {
            nextAvailableSteps.remove(step.getStepLetter());
            return step;
        }
        return new Step(nextLetter);
    }

    private boolean allParentsDone(String letter) {
        return stepsByLetter.values().stream().filter(s -> s.getChildren().contains(letter)).allMatch(Step::isDone);
    }

    public void initAvailableSteps() {
        nextAvailableSteps.addAll(stepsByLetter.keySet().stream()
                .filter(this::isNotChildren).collect(Collectors.toSet()));
    }

    private boolean isNotChildren(String key) {
        return stepsByLetter.values().stream().noneMatch(s -> s.getChildren().contains(key));
    }


    public String sequence() {
        while(!isComplete()) {
            nextStep();
        }
        return validatedSteps.stream().map(Step::getStepLetter).reduce((a, b) -> a+b).orElseThrow(RuntimeException::new);
    }

    public boolean isComplete() {
        return validatedSteps.size()>stepsByLetter.size()+1;
    }
}
